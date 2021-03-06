package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Override
    public Cart add(Cart cart, String productId, int amount) {
        ResponseEntity<Product> productResponseEntity = restTemplate.getForEntity("http://POS-PRODUCTS/api/products/"  + productId, Product.class);
        Product product = productResponseEntity.getBody();
        if (product == null) {
            return cart;
        }

        cart.addItem(new Item(product, amount));
        return cart;
    }
}