package com.demo.graphql.dao.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class VehicleBuilder {

	public Vehicle buildVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {

		final Vehicle vehicle = new Vehicle();
		vehicle.setType(type);
		vehicle.setModelCode(modelCode);
		vehicle.setBrandName(brandName);
		vehicle.setLaunchDate(LocalDate.parse(launchDate));
		return vehicle;
	}
}
