package br.com.dogins.services.Impl;

import br.com.dogins.exceptions.ListIsEmptyException;
import br.com.dogins.models.Item;
import br.com.dogins.models.ShoppingCart;
import br.com.dogins.repositories.ProductRepository;
import br.com.dogins.repositories.ShoppingCartRepository;
import br.com.dogins.services.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setItemList(shoppingCartItemsList);
        shoppingCartRepository.save(shoppingCart);

        ShoppingCart savedShoppingCart = shoppingCartRepository.findById(shoppingCart.getId())
                .orElseThrow(() -> new ListIsEmptyException("ShoppingCart was empty or did not exist"));

        return savedShoppingCart.getItemList();
    }

}
