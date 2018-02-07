package com.kroger.priceisright;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    @JsonCreator
    public Item(
            @JsonProperty("upc") String upc,
            @JsonProperty("description") String description,
            @JsonProperty("finalPrice") Float price) {
        this.description = description;
        this.price = price;
        this.upc = upc;
    }

    public Float getPrice() {
        return price;
    }

    public String getSrc() {
        return String.format(
                "https://www.kroger.com/product/images/large/front/%s", getUpc());
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    private Float price;

    private String description;

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    private String upc;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
