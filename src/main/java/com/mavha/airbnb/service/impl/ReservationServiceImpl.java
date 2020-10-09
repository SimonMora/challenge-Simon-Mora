package com.mavha.airbnb.service.impl;

import com.mavha.airbnb.model.Listing;
import com.mavha.airbnb.model.ReservationRequest;
import com.mavha.airbnb.model.ReservationResponse;
import com.mavha.airbnb.repository.ListingRepository;
import com.mavha.airbnb.service.ReservationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service("reservationServiceImpl")
public class ReservationServiceImpl implements ReservationService {

    private ListingRepository listingRepository;

    @Override
    public ResponseEntity<Object> createAReservation(ReservationRequest reservationRequest, String listing_id) {
        try {
            Listing listing = listingRepository.findById(UUID.fromString(listing_id)).get();

            long diffInMillies = Math.abs(reservationRequest.getCheckout().getTime() - reservationRequest.getCheckin().getTime());
            long count = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            double nightCost = listing.getBase_price();
            double discount = 0;
            if (count >= 7) {
                discount = listing.getWeekly_discount();
            } else if (count >= 30) {
                discount = listing.getMonthly_discount();
            } else {
                discount = 0;
            }
            double cleaningFee = listing.getCleaning_fee();
            double total = (count * nightCost) - discount + cleaningFee;

            return new ResponseEntity<>(new ReservationResponse(count, nightCost, discount, cleaningFee, total), HttpStatus.OK);

        } catch(Exception e){
            JSONObject error = new JSONObject();
            error.put("code",1);
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    protected double calculateDiscount(Listing listing){
        return 0;
    }

    protected double calculateNightCost(Listing listing) {
        return 0;
    }

    @Autowired
    public void setListingRepository(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }
}
