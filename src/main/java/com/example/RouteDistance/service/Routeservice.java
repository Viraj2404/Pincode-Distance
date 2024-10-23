package com.example.RouteDistance.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.RouteDistance.entity.Routeinfo;
import com.example.RouteDistance.repository.Routerepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Routeservice {

   

	@Autowired
    private Routerepository routerepository;

	  
	    private RestTemplate restTemplate = new RestTemplate();  // Assuming RestTemplate is configured with appropriate connection settings

	    private final String googleMapsApiUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s";

		private final String YOUR_API_KEY = "AIzaSyCkGXcFsEEps2tNM97gJQYqGzeBVu-Az4";

	    public Routeinfo getRouteInfo(String fromPincode, String toPincode) {
	    	
	    	
	        // Validate required parameters
	        if (fromPincode == null || toPincode == null) {
	            throw new IllegalArgumentException("fromPincode and toPincode are required");
	        }
	        

	        // Build the URL with your API key
	        String url = String.format(googleMapsApiUrl, fromPincode, toPincode, YOUR_API_KEY );
	        
	        
	        
	        try {
	            // Fetch response from Google Maps API
	            String response = restTemplate.getForObject(url, String.class);

	            // Parse JSON response using a library like Jackson
	            ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode root = objectMapper.readTree(response);

	            
	            
	            
	            // Validate response structure (optional)
	            if (!root.path("rows").isArray() || root.path("rows").size() == 0) {
	                throw new RuntimeException("Invalid API response: 'rows' missing or empty.");
	            }

	            
	            // Extract information from the first element (assuming single origin and destination)
	            JsonNode rows = root.path("rows").get(0).path("elements").get(0);

	            String distance = rows.path("distance").path("text").asText(null);
	            String duration = rows.path("duration").path("text").asText(null);

	            
	            
	            // Validate extracted data
	            if (distance == null || duration == null) {
	                throw new RuntimeException("Missing distance or duration information in response.");
	            }

	            
	            
	            // Construct Routeinfo object
	            Routeinfo routeInfo = new Routeinfo();
	            routeInfo.setFromPincode(fromPincode);
	            routeInfo.setToPincode(toPincode);
	            routeInfo.setDistance(distance);
	            routeInfo.setDuration(duration);
	            routeInfo.setRoute(fromPincode + " to " + toPincode);  // Optional route description
	            routeInfo.setTimestamp(LocalDateTime.now());
	            
	            // Save route information to the database
	            routerepository.save(routeInfo);

	            return routeInfo;

	        } catch (Exception e) {
	            throw new RuntimeException("Error fetching route information: " + e.getMessage(), e);
	            
	        }
	    }
}
