package com.mavha.airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "LISTINGS")
@JsonIgnoreProperties
public class Listing implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "listing_id")
    private UUID id;
    @ManyToOne(optional = false,cascade = CascadeType.ALL,
    fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private String owner_id;
    private String slug;
    private String description;
    @NotNull
    private int adults;
    @NotNull
    private int children;
    private boolean is_pets_allowed;
    @NotNull
    private double base_price;
    @NotEmpty
    private double cleaning_fee;
    private String image_url;
    private double weekly_discount;
    private double monthly_discount;

    protected Listing() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
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
