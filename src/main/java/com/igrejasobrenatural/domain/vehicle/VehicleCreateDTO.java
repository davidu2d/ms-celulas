package com.igrejasobrenatural.domain.vehicle;

import com.igrejasobrenatural.annotations.xss.NotXss;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record VehicleCreateDTO(
        @NotXss @NotBlank String name,
        @NotXss @NotBlank String brand,
        Integer year,
        @NotXss String description,
        boolean isSold,
        @NotXss @NotBlank String chassis,
        BigDecimal price
) {
    public Vehicle toVehicle(){
        return new Vehicle(name, brand, year, description, isSold, LocalDateTime.now(), chassis, price);
    }
}