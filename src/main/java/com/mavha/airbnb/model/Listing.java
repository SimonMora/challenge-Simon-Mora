package com.mavha.airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "LISTINGS")
@JsonIgnoreProperties
public class Listing implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;
    @NotNull
    private String name;
    private String slug;
    private String description;
    @NotNull
    private int adults;
    @NotNull
    private int children;
    private boolean is_pets_allowed;
    @NotNull
    private double base_price;
    @NotNull
    private double cleaning_fee;
    private String image_url;
    private double weekly_discount;
    private double monthly_discount;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="listing_id")
    private List<SpecialPrice> special_prices;

    protected Listing() {
    }

    public Listing(Listing listing){
        this.name = listing.getName();
        this.description = listing.getDescription();
        this.adults = listing.getAdults();
        this.children = listing.getChildren();
        this.is_pets_allowed = listing.isIs_pets_allowed();
        this.base_price = listing.getBase_price();
        this.cleaning_fee = listing.getCleaning_fee();
        this.image_url = listing.getImage_url();
        this.weekly_discount = listing.getWeekly_discount();
        this.monthly_discount = listing.getMonthly_discount();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<SpecialPrice> getSpecial_prices() {
        return special_prices;
    }

    public void setSpecial_prices(List<SpecialPrice> special_prices) {
        this.special_prices = special_prices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public boolean isIs_pets_allowed() {
        return is_pets_allowed;
    }

    public void setIs_pets_allowed(boolean is_pets_allowed) {
        this.is_pets_allowed = is_pets_allowed;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public double getCleaning_fee() {
        return cleaning_fee;
    }

    public void setCleaning_fee(double cleaning_fee) {
        this.cleaning_fee = cleaning_fee;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getWeekly_discount() {
        return weekly_discount;
    }

    public void setWeekly_discount(double weekly_discount) {
        this.weekly_discount = weekly_discount;
    }

    public double getMonthly_discount() {
        return monthly_discount;
    }

    public void setMonthly_discount(double monthly_discount) {
        this.monthly_discount = monthly_discount;
    }
}
