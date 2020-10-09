package com.mavha.airbnb.model;

public class ReservationResponse {

    private long night_count;
    private double night_price;
    private double discount;
    private double cleaning_fee;
    private double total;

    public ReservationResponse() {
    }

    public ReservationResponse(long night_count, double night_price, double discount, double cleaning_fee, double total) {
        this.night_count = night_count;
        this.night_price = night_price;
        this.discount = discount;
        this.cleaning_fee = cleaning_fee;
        this.total = total;
    }

    public long getNight_count() {
        return night_count;
    }

    public void setNight_count(long night_count) {
        this.night_count = night_count;
    }

    public double getNight_price() {
        return night_price;
    }

    public void setNight_price(double night_price) {
        this.night_price = night_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCleaning_fee() {
        return cleaning_fee;
    }

    public void setCleaning_fee(double cleaning_fee) {
        this.cleaning_fee = cleaning_fee;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReservationResponse{" +
                "night_count=" + night_count +
                ", night_price=" + night_price +
                ", discount=" + discount +
                ", cleaning_fee=" + cleaning_fee +
                ", total=" + total +
                '}';
    }
}
