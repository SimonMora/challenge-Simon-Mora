package com.mavha.airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "SPECIAL_PRICE")
@JsonIgnoreProperties
public class SpecialPrice implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @ManyToOne(optional = false,cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, targetEntity = Listing.class)
    @JoinColumn(name = "listing_id")
    private String listing_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotNull
    private double price;

    protected SpecialPrice(){
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
