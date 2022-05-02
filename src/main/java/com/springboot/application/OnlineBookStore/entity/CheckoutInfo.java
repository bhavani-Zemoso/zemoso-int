package com.springboot.application.OnlineBookStore.entity;

import java.util.Calendar;
import java.util.Date;

public class CheckoutInfo {

    private double bookCost;

    private double bookTotal;

    private int deliveryDays;

    private Date deliveryDate;

    public CheckoutInfo() {}

    public double getBookCost() {
        return bookCost;
    }

    public void setBookCost(double bookCost) {
        this.bookCost = bookCost;
    }

    public double getBookTotal() {
        return bookTotal;
    }

    public void setBookTotal(double bookTotal) {
        this.bookTotal = bookTotal;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public Date getDeliveryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, deliveryDays);

        return calendar.getTime();
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
