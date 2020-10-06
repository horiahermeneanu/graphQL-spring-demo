package com.demo.graphql.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.graphql.dao.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
