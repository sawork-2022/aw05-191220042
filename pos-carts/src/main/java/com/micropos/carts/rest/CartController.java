package com.micropos.carts.rest;

import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.service.CartService;
import com.micropos.carts.api.CartApi;
import com.micropos.carts.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CartController implements CartApi {

    private final CartMapper cartmapper;

    private final CartService cartService;

    @Autowired
    Cart cart;

    public CartController(CartMapper cartmapper, CartService cartService) {
        this.cartmapper = cartmapper;
        this.cartService = cartService;
    }

    @Override
    public ResponseEntity<CartDto> addToCart(String productId) {
        if (cart == null) cart = new Cart();
        cartService.add(cart, productId, 1);
        return  new ResponseEntity<>(cartmapper.toCartDTO(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> listCart() {
        if (cart == null) cart = new Cart();
        return new ResponseEntity<>(cartmapper.toCartDTO(cart), HttpStatus.OK);
    }
}
