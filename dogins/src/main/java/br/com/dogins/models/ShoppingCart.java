package br.com.dogins.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("shopping-cart")
public class ShoppingCart {
    private List<Item> productList;
}
