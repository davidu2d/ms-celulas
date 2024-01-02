package com.igrejasobrenatural.repositories;

import com.igrejasobrenatural.domain.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface VehicleCustomRepository {
    //List<Vehicle> findByNameAndBrandAndYearAndPrice(String name, String brand, int yearInitial, int yearFinal, BigDecimal priceInitial, BigDecimal priceFinal);
    Page<Vehicle> findByNameAndBrandAndYearAndPrice(String name, String brand, Integer yearInitial, Integer yearFinal, BigDecimal priceInitial, BigDecimal priceFinal, Pageable pageable);
}
