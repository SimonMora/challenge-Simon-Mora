package com.mavha.airbnb.service.impl;

import com.mavha.airbnb.model.Listing;
import com.mavha.airbnb.model.SpecialPrice;
import com.mavha.airbnb.model.User;
import com.mavha.airbnb.repository.ListingRepository;
import com.mavha.airbnb.repository.SpecialPriceRepository;
import com.mavha.airbnb.repository.UserRepository;
import com.mavha.airbnb.service.ListingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service("listingServiceImpl")
public class ListingServiceImpl implements ListingService {


    private ListingRepository listingRepository;

    private SpecialPriceRepository specialPriceRepository;

    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> createNewListing(Listing newListing) {
        Listing listing = new Listing(newListing);
        try {
            Stream<User> userStream = userRepository.findAll().stream();
            listing.setOwner(userStream.findAny().get());
            listing.setSlug(this.calculateSlug(newListing.getName()));

            listing = listingRepository.save(listing);
            return new ResponseEntity<>(listing, HttpStatus.CREATED);
        } catch (Exception e){
            JSONObject error = new JSONObject();
            error.put("code",1);
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> retrieveAllListings() {
        try{
            return new ResponseEntity<>(listingRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            JSONObject error = new JSONObject();
            error.put("code",1);
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> retrieveOneListing(String listingId) {
        try{
            return new ResponseEntity<>(listingRepository.findById(UUID.fromString(listingId)).get(),HttpStatus.OK);
        } catch (Exception e){
            JSONObject error = new JSONObject();
            error.put("code",1);
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteOneListing(String listingId) {
        try{
            listingRepository.deleteById(UUID.fromString(listingId));
            JSONObject response = new JSONObject();
            response.put("id",listingId);
            return new ResponseEntity<>(response.toString(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put("error",e.getMessage()).toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> modifyOneListing(String listingId, Listing listing) {
       try {
           Listing listingToUpdate = listingRepository.findById(UUID.fromString(listingId)).get();
           listingToUpdate.setAdults(listing.getAdults());
           listingToUpdate.setChildren(listing.getChildren());
           listingToUpdate.setBase_price(listing.getBase_price());
           listingToUpdate.setName(listing.getName());
           listingToUpdate.setDescription(listing.getDescription());
           listingToUpdate.setCleaning_fee(listing.getCleaning_fee());
           listingToUpdate.setIs_pets_allowed(listing.isIs_pets_allowed());
           listingToUpdate.setImage_url(listing.getImage_url());
           listingToUpdate.setWeekly_discount(listing.getWeekly_discount());
           listingToUpdate.setMonthly_discount(listing.getMonthly_discount());
           listingToUpdate.setSlug(listing.getName().toLowerCase());


           listingRepository.deleteById(listingToUpdate.getId());
           Listing updatedListing = listingRepository.saveAndFlush(listingToUpdate);
           return new ResponseEntity<>(updatedListing, HttpStatus.OK);
       } catch (Exception e){
           JSONObject error = new JSONObject();
           error.put("code",1);
           error.put("message",e.getMessage());
           return new ResponseEntity<>(error.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public ResponseEntity<Object> addSpecialPriceToListing(String listingId, SpecialPrice specialPrice) {
        try {
            Listing listing = listingRepository.findById(UUID.fromString(listingId)).get();
            specialPrice.setListing(listing);
            SpecialPrice specialPriceSaved = specialPriceRepository.save(specialPrice);

            return new ResponseEntity<>(specialPriceSaved, HttpStatus.OK);
        }catch (Exception e){
            JSONObject error = new JSONObject();
            error.put("code",1);
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteSpecialPrice(String specialPriceId) {
        try{
            specialPriceRepository.deleteById(UUID.fromString(specialPriceId));
            JSONObject response = new JSONObject();
            response.put("id",specialPriceId);
            return new ResponseEntity<>(response.toString(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put("error",e.getMessage()).toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String calculateSlug(String name){
        return name.toLowerCase().replaceAll(" ","-");
    }

    @Autowired
    public void setListingRepository(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Autowired
    public void setSpecialPriceRepository(SpecialPriceRepository specialPriceRepository) {
        this.specialPriceRepository = specialPriceRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
