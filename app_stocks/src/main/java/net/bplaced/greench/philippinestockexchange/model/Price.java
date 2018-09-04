package net.bplaced.greench.philippinestockexchange.model;

import lombok.Data;

public @Data
class Price {
    private String currency;
    private double amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
