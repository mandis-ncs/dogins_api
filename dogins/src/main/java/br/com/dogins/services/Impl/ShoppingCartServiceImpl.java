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

import java.util.ArrayList;
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

        // Tenta encontrar um carrinho de compras existente
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        ShoppingCart shoppingCart;
        if (!shoppingCarts.isEmpty()) {
            // Se existir, pega o primeiro carrinho de compras
            shoppingCart = shoppingCarts.get(0);
        } else {
            // Se não existir, cria um novo carrinho de compras
            shoppingCart = new ShoppingCart();
        }

        // Adiciona os novos itens à lista de itens do carrinho de compras
        List<Item> itemList = shoppingCart.getItemList();
        if (itemList == null) {
            itemList = new ArrayList<>();
        }

        for (Item newItem : shoppingCartItemsList) {
            boolean itemExists = false;
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getId().equals(newItem.getId())) {
                    itemList.set(i, newItem); // Substitui o item existente pelo novo item
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) {
                itemList.add(newItem); // Adiciona o novo item se ele não existir na lista
            }
        }

        shoppingCart.setItemList(itemList);

        // Salva o carrinho de compras atualizado
        shoppingCartRepository.save(shoppingCart);

        return itemList;
    }



}
