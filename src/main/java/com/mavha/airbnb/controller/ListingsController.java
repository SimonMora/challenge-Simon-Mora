package com.mavha.airbnb.controller;

import com.mavha.airbnb.model.Listing;
import com.mavha.airbnb.model.ReservationRequest;
import com.mavha.airbnb.model.ReservationResponse;
import com.mavha.airbnb.model.SpecialPrice;
import com.mavha.airbnb.service.ListingService;
import com.mavha.airbnb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ListingsController {

    private ListingService listingService;

    private ReservationService reservationService;

    @PostMapping(path="/listings",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewListing(@RequestHeader Map<String,Object> headers,
                                                       @RequestBody Listing body){
        return listingService.createNewListing(body);
    }

    @GetMapping(path="/listings")
    public ResponseEntity<Object> showAllListing(@RequestHeader Map<String,Object> headers){
        return listingService.retrieveAllListings();
    }

    @GetMapping(path="/listings/{listing_id}")
    public ResponseEntity<Object> showListing(@RequestHeader Map<String,Object> headers,
                                                  @PathVariable("listing_id") String listing_id){
        return listingService.retrieveOneListing(listing_id);
    }

    @PutMapping(path="/listings/{listing_id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAListing(@RequestHeader Map<String,Object> headers,
                                                     @PathVariable("listing_id") String listing_id,
                                                     @RequestBody Listing listing){
        return listingService.modifyOneListing(listing_id,listing);
    }

    @DeleteMapping(path="/listings/{listing_id}")
    public ResponseEntity<String> deleteAListing(@RequestHeader Map<String,Object> headers,
                                                     @PathVariable("listing_id") String listing_id){
        return listingService.deleteOneListing(listing_id);
    }

    @PostMapping(path="/listings/{listing_id}/special-prices",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addSpecialPriceToListing(@RequestHeader Map<String,Object> headers,
                                                               @PathVariable("listing_id") String listing_id,
                                                               @RequestBody SpecialPrice body){
        return listingService.addSpecialPriceToListing(listing_id,body);
    }

    @DeleteMapping(path="/listings/{listing_id}/special-prices/{specialPrice_id}")
    public ResponseEntity<String> deleteSpecialPrice(@RequestHeader Map<String,Object> headers,
                                                         @PathVariable("listing_id") String listing_id,
                                                         @PathVariable("specialPrice_id") String specialPrice_id){
        return listingService.deleteSpecialPrice(specialPrice_id);
    }

    @GetMapping(path="/listings/{listing_id}/checkout",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> calculateReservationCost(@RequestHeader Map<String,Object> headers,
                                                                        @PathVariable("listing_id") String listing_id,
                                                                        @RequestBody ReservationRequest body){
        return reservationService.createAReservation(body,listing_id);
    }


    @Autowired
    @Qualifier("listingServiceImpl")
    public void setListingService(ListingService listingService) {
        this.listingService = listingService;
    }


    @Autowired
    @Qualifier("reservationServiceImpl")
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
