package com.example.RouteDistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.RouteDistance.entity.Routeinfo;
import com.example.RouteDistance.service.Routeservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RouteDistanceApplicationTests {

	 @InjectMocks
	    private Routeservice routeService;

	    @Mock
	    private  RestTemplate restTemplate;

	    @Test
	    public void testGetRouteInfo_Success() {
	        // Mock API response
	        Map<String, Object> responseBody = new HashMap<>();
	        responseBody.put("rows", List.of(Map.of("elements", List.of(Map.of("distance", Map.of("text", "10 km"))))));
	        when(restTemplate.getForObject(anyString(), Map.class)).thenReturn(responseBody);

	        Routeinfo routeInfo = routeService.getRouteInfo("110001", "400001");

	        assertNotNull(routeInfo);
	        assertEquals("10 km", routeInfo.getDistance());
	    }

	    @Test
	    public void testGetRouteInfo_NullParameters() {
	        assertThrows(IllegalArgumentException.class, () -> routeService.getRouteInfo(null, "400001"));
	        assertThrows(IllegalArgumentException.class, () -> routeService.getRouteInfo("110001", null));
	    }

	    @Test
	    public void testGetRouteInfo_InvalidResponse() {
	        when(restTemplate.getForObject(anyString(), Map.class)).thenReturn(null);

	        assertThrows(RuntimeException.class, () -> routeService.getRouteInfo("110001", "400001"));
	    }

}
