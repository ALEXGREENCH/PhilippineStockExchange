package net.bplaced.greench.philippinestockexchange.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

public @Data
class Stocks {
    private ArrayList<Stock> stock;
    private Date as_of;

    public ArrayList<Stock> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Stock> stock) {
        this.stock = stock;
    }

    public Date getAs_of() {
        return as_of;
    }

    public void setAs_of(Date as_of) {
        this.as_of = as_of;
    }
}
