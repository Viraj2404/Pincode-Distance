package com.example.RouteDistance.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Routeinfo {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String fromPincode;
	    private String toPincode;
	    private String route;
	    private String distance;
	    private String duration;
	    
	    @Column(name = "timestamp", columnDefinition = "DATETIME")
	    private LocalDateTime timestamp;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFromPincode() {
			return fromPincode;
		}

		public void setFromPincode(String fromPincode) {
			this.fromPincode = fromPincode;
		}

		public String getToPincode() {
			return toPincode;
		}

		public void setToPincode(String toPincode) {
			this.toPincode = toPincode;
		}

		public String getRoute() {
			return route;
		}

		public void setRoute(String route) {
			this.route = route;
		}

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

}
