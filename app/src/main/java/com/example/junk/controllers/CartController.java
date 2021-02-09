package com.example.junk.controllers;

import android.content.Context;

import com.example.junk.datamodel.Cart;
import com.example.junk.datamodel.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class CartController {

    private Cart cart;
    private static CartController instance;
    private Context context;

    private CartController (Context context) {
        super();
        this.context = context;
        buildCart();
    }


    public static CartController newInstance(Context context) {
        if (instance == null) {
            instance = new CartController(context);
        }
        return instance;
    }

    public static CartController getInstance() {
        assert instance != null;

        return instance;
    }

    private void buildCart() {
        Gson gson = new Gson();

        String json = "";
        try {
            InputStream inputStream = context.getAssets().open("cart.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            json = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.cart = gson.fromJson(json, Cart.class);
    }

    public float getSubTotal() {
        float subTotal = 0.0f;
        for (Product product : cart.getProducts() ) {
            subTotal += (product.getUnitprice() * product.getQuantity());
        }
        return subTotal;
    }

    public float getShipping() {
        return 0.0f;
    }

    public float getTotal() {
        return getSubTotal() + getShipping();
    }

    public Cart getCart() {
        return cart;
    }
}
