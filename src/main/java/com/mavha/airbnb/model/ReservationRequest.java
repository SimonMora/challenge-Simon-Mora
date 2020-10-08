package com.mavha.airbnb.model;

import java.util.Date;

public class ReservationRequest {
    private Date checkin;
    private Date checkout;

    protected ReservationRequest() {
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }
}
