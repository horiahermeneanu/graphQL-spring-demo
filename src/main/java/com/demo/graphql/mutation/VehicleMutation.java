package com.demo.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.graphql.dao.entity.Vehicle;
import com.demo.graphql.exception.VehicleNotFoundException;
import com.demo.graphql.service.VehicleService;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

	@Autowired
	private VehicleService vehicleService;

	public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {

		return vehicleService.createVehicle(type, modelCode, brandName, launchDate);
	}

	public boolean deleteVehicle(final int id) {

		boolean isDeleted = vehicleService.deleteVehicle(id);
		if(!isDeleted) {
			throw new VehicleNotFoundException("Vehicle not found!", "id");
		}
		return isDeleted;
	}

	public Vehicle updateVehicle(final int id, final String brandName, final String launchDate) {

		Vehicle vehicle = vehicleService.updateVehicle(id, brandName, launchDate);
		if(vehicle == null) {
			throw new VehicleNotFoundException("Vehicle not found!", "id");
		}
		return vehicle;
	}
}
