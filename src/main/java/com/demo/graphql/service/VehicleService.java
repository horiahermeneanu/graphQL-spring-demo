package com.demo.graphql.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.graphql.dao.entity.Vehicle;
import com.demo.graphql.dao.entity.VehicleBuilder;
import com.demo.graphql.dao.repository.VehicleRepository;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;

	@Autowired
	private VehicleBuilder vehicleBuilder;

	public VehicleService(final VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Transactional
	public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
		final Vehicle vehicle = vehicleBuilder.buildVehicle(type, modelCode, brandName, launchDate);
		return vehicleRepository.save(vehicle);
	}

	@Transactional
	public boolean deleteVehicle(final int id) {
		final Optional<Vehicle> vehicle = getVehicle(id);
		if(!vehicle.isPresent()) {
			return false;
		}

		vehicleRepository.deleteById(id);
		return true;
	}

	@Transactional
	public Vehicle updateVehicle(final int id, final String brandName, final String launchDate) {
		final Optional<Vehicle> vehicleOptional = getVehicle(id);
		if(!vehicleOptional.isPresent()) {
			return null;
		}

		Vehicle vehicle = vehicleOptional.get();
		vehicle.setBrandName(brandName);
		vehicle.setLaunchDate(LocalDate.parse(launchDate));
		return vehicleRepository.save(vehicle);
	}


	@Transactional(readOnly = true)
	public List<Vehicle> getAllVehicles(final int count) {
		return vehicleRepository.findAll().stream().limit(count).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Optional<Vehicle> getVehicle(final int id) {
		return vehicleRepository.findById(id);
	}
}
