package com.example.RouteDistance.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.RouteDistance.entity.Routeinfo;
import com.example.RouteDistance.service.Routeservice;


@RestController
@RequestMapping("/api/routes")
public class RouteControllerr {

	  private static final Logger logger = LoggerFactory.getLogger(RouteControllerr.class);
	
	@Autowired
	private Routeservice routeService;
	
//	@GetMapping
//	public String greet() {
//		return "welcome";
//	}
	
	@GetMapping("/info")
	public Routeinfo getRouteInfo(
	    @RequestParam(value = "fromPincode", required = false) String fromPincode, 
	    @RequestParam(value = "toPincode", required = false) String toPincode) {

	    if (fromPincode == null || toPincode == null) {
	        throw new IllegalArgumentException("fromPincode and toPincode are required");
	    }

	    // Log input parameters
        logger.info("Received fromPincode: {}, toPincode: {}", fromPincode, toPincode);
	    
	    return routeService.getRouteInfo(fromPincode, toPincode);
	}

}
