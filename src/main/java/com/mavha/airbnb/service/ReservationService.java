package com.mavha.airbnb.service;

import com.mavha.airbnb.model.ReservationRequest;
import com.mavha.airbnb.model.ReservationResponse;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    ResponseEntity<Object> createReservation(ReservationRequest reservationRequest, String listing_id);
}
