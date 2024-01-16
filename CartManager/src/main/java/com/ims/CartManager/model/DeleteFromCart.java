package com.ims.CartManager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteFromCart {
    private Long userId;
    private Long product;
    private String price;

    public DeleteFromCart(Long userId, Long product, String price) {
        this.userId = userId;
        this.product = product;
        this.price = price;
    }
}
