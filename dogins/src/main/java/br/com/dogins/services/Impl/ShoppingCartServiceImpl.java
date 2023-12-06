package br.com.dogins.services.Impl;

import br.com.dogins.exceptions.ListIsEmptyException;
import br.com.dogins.exceptions.ResourceNotFoundException;
import br.com.dogins.models.Item;
import br.com.dogins.models.ShoppingCart;
import br.com.dogins.repositories.ProductRepository;
import br.com.dogins.repositories.ShoppingCartRepository;
import br.com.dogins.services.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository repository;

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ProductRepository repository, ShoppingCartRepository shoppingCartRepository) {
        this.repository = repository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<Item> postInShoppingCart(List<Item> shoppingCartItemsList) {
        log.info("postInShoppingCart called");

        ShoppingCart shoppingCart = getExistingOrNewShoppingCart();
        List<Item> itemList = getUpdatedItemList(shoppingCart, shoppingCartItemsList);

        shoppingCart.setItemList(itemList);
        shoppingCartRepository.save(shoppingCart);

        return itemList;
    }

    @Override
    public Item findItemByIdInShoppingCart(String itemId) {
        log.info("findItemByIdInShoppingCart called");
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        if (shoppingCarts.isEmpty()) {
            throw new ListIsEmptyException("Seu carrinho está vazio.");
        }
        for (ShoppingCart shoppingCart : shoppingCarts) {
            for (Item item : shoppingCart.getItemList()) {
                if (item.getId().equals(itemId)) {
                    return item;
                }
            }
        }
        throw new ResourceNotFoundException("Item não encontrado no seu Carrinho com id: " + itemId);
    }

    @Override
    public List<Item> findAllItemsInShoppingCart() {
        log.info("findAllItemsInShoppingCart called");
        List<ShoppingCart> carts = shoppingCartRepository.findAll();

        if (carts.isEmpty()) {
            throw new ListIsEmptyException("O carrinho de compras está vazio!");
        }

        List<Item> items = new ArrayList<>();
        for (ShoppingCart cart : carts) {
            items.addAll(cart.getItemList());
        }
        return items;
    }


    private ShoppingCart getExistingOrNewShoppingCart() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        if (!shoppingCarts.isEmpty()) {
            return shoppingCarts.get(0);
        } else {
            return new ShoppingCart();
        }
    }

    private List<Item> getUpdatedItemList(ShoppingCart shoppingCart, List<Item> newItems) {
        List<Item> itemList = shoppingCart.getItemList();
        if (itemList == null) {
            itemList = new ArrayList<>();
        }

        for (Item newItem : newItems) {
            updateOrAddItem(itemList, newItem);
        }

        return itemList;
    }

    private void updateOrAddItem(List<Item> itemList, Item newItem) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(newItem.getId())) {
                itemList.set(i, newItem);
                return;
            }
        }
        itemList.add(newItem);
    }



}
