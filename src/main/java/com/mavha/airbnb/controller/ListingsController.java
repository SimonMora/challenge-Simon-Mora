package com.mavha.airbnb.controller;

import com.mavha.airbnb.model.Listing;
import com.mavha.airbnb.model.ReservationRequest;
import com.mavha.airbnb.model.SpecialPrice;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ListingsController {

    @PostMapping(path="/listings",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> createNewListing(@RequestHeader Map<String,Object> headers,
                                                       @RequestBody Listing body){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

    @GetMapping(path="/listings",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> showAllListing(@RequestHeader Map<String,Object> headers){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

    @GetMapping(path="/listings/:{listing_id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> showListing(@RequestHeader Map<String,Object> headers,
                                                  @PathVariable("listing") String listing_id){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

    @PutMapping(path="/listings/:{listing_id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> updateAListing(@RequestHeader Map<String,Object> headers,
                                                     @PathVariable("listing_id") String listing_id){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

    @DeleteMapping(path="/listings/:{listing_id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> deleteAListing(@RequestHeader Map<String,Object> headers,
                                                     @PathVariable("listing_id") String listing_id){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

    @PostMapping(path="/listings/:{listing_id}/special-prices",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> addSpecialPriceToListing(@RequestHeader Map<String,Object> headers,
                                                               @PathVariable("listing_id") String listing_id,
                                                               @RequestBody SpecialPrice body){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

    @DeleteMapping(path="/listings/:{listing_id}/special-prices/:uuid",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> deleteSpecialPrice(@RequestHeader Map<String,Object> headers,
                                                         @PathVariable("listing_id") String listing_id){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

    @GetMapping(path="/listings/:uuid/checkout",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> calculateReservationCost(@RequestHeader Map<String,Object> headers,
                                                               @RequestBody ReservationRequest body){
        return new ResponseEntity<>(new JSONObject(), HttpStatus.CREATED);
    }

}
