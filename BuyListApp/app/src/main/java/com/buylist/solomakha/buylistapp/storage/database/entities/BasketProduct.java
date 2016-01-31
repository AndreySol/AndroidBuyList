package com.buylist.solomakha.buylistapp.storage.database.entities;

/**
 * Created by asolomakha on 1/27/2016.
 */
public class BasketProduct extends BaseEntity {
    long basketId;
    long proructId;
    boolean bought;

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public long getProructId() {
        return proructId;
    }

    public void setProructId(long proructId) {
        this.proructId = proructId;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
