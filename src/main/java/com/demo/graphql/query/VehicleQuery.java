package com.demo.graphql.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.graphql.dao.entity.Vehicle;
import com.demo.graphql.service.VehicleService;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

	@Autowired
	private VehicleService vehicleService;

	public List<Vehicle> getVehicles(final int count) {
		return vehicleService.getAllVehicles(count);
	}

	public Optional<Vehicle> getVehicle(final int id) {
		return vehicleService.getVehicle(id);
	}
}
