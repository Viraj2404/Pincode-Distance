package com.example.RouteDistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RouteDistance.entity.Routeinfo;

@Repository
public interface Routerepository extends JpaRepository<Routeinfo, Long>{
	Routeinfo findByFromPincodeAndToPincode(String fromPincode, String toPincode);
}
