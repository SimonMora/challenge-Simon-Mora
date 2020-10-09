package com.mavha.airbnb.service;

import com.mavha.airbnb.model.Listing;
import com.mavha.airbnb.model.SpecialPrice;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListingService {

    ResponseEntity<Listing> createNewListing(Listing newListing);

    ResponseEntity<List<Listing>> retrieveAllListings();

    ResponseEntity<Listing> retrieveOneListing(String listingId);

    ResponseEntity<String> deleteOneListing(String listingId);

    ResponseEntity<Listing> modifyOneListing(String listingId, Listing listing);

    ResponseEntity<SpecialPrice> addSpecialPriceToListing(String listingId, SpecialPrice specialPrice);

    ResponseEntity<String> deleteSpecialPrice(String specialPriceId);
}
