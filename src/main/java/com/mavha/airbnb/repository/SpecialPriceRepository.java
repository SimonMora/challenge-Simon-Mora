package com.mavha.airbnb.repository;

import com.mavha.airbnb.model.SpecialPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecialPriceRepository extends JpaRepository<SpecialPrice, UUID> {
}
