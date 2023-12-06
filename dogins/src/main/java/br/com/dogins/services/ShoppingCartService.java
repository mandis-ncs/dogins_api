package br.com.dogins.services;

import br.com.dogins.models.Item;

import java.util.List;

public interface ShoppingCartService {

    List<Item> postInShoppingCart(List<Item> shoppingCartItensList);

    Item findItemByIdInShoppingCart(String ItemId);

    List<Item> findAllItemsInShoppingCart();

    void deleteItemInShoppingCart(String itemId);

}
