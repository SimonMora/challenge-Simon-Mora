package com.mavha.airbnb.service.impl;

import com.mavha.airbnb.model.Listing;
import com.mavha.airbnb.model.ReservationRequest;
import com.mavha.airbnb.model.ReservationResponse;
import com.mavha.airbnb.model.SpecialPrice;
import com.mavha.airbnb.repository.ListingRepository;
import com.mavha.airbnb.service.ReservationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service("reservationServiceImpl")
public class ReservationServiceImpl implements ReservationService {

    private ListingRepository listingRepository;

    @Override
    public ResponseEntity<Object> createAReservation(ReservationRequest reservationRequest, String listing_id) {
        try {
            Listing listing = listingRepository.findById(UUID.fromString(listing_id)).get();

            long diffInMillies = Math.abs(reservationRequest.getCheckout().getTime() - reservationRequest.getCheckin().getTime());
            long count = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            double nightCost = this.calculateNightCost(listing,reservationRequest);
            double discount = this.calculateDiscount(listing,count);

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

    protected double calculateDiscount(Listing listing, long count){
        if (count >= 7) {
            return listing.getWeekly_discount();
        } else if (count >= 30) {
            return listing.getMonthly_discount();
        } else {
            return 0;
        }
    }

    protected double calculateNightCost(Listing listing, ReservationRequest request) {
        if (listing.getSpecial_prices() != null && !listing.getSpecial_prices().isEmpty()){

            Stream<SpecialPrice> specialPrices = listing.getSpecial_prices()
                                                .stream()
                                                .filter(sp ->
                                                                sp.getDate().compareTo(request.getCheckin()) <= 0 && sp.getDate().compareTo(request.getCheckout()) >= 0
                                                );
            return specialPrices.count() == 0 ? listing.getBase_price() : specialPrices.findFirst().get().getPrice();

        }
        return listing.getBase_price();
    }

    @Autowired
    public void setListingRepository(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }
}
