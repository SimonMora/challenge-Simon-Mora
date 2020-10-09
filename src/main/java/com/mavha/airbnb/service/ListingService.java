package com.mavha.airbnb.service;

import com.mavha.airbnb.model.Listing;
import com.mavha.airbnb.model.SpecialPrice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListingService {

    ResponseEntity<Object> createNewListing(Listing newListing);

    ResponseEntity<Object> retrieveAllListings();

    ResponseEntity<Object> retrieveOneListing(String listingId);

    ResponseEntity<String> deleteOneListing(String listingId);

    ResponseEntity<Object> modifyOneListing(String listingId, Listing listing);

    ResponseEntity<Object> addSpecialPriceToListing(String listingId, SpecialPrice specialPrice);

    ResponseEntity<String> deleteSpecialPrice(String specialPriceId);
}
