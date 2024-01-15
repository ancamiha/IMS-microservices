package com.ims.CartManager;

import com.ims.CartManager.model.ProdOfCart;
import com.ims.CartManager.model.Sweets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SweetShop")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping(value = { "/cart/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sweets> getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PostMapping(value = { "/addProdToCart" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProdToCart(@RequestBody ProdOfCart prodOfCart) {
        return cartService.addProdToCart(prodOfCart);
    }

    @DeleteMapping(value = { "/deleteProdFromCart" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProdFromCart(@RequestBody ProdOfCart prodOfCart) {
        System.out.println(prodOfCart);
        return cartService.deleteProdFromCart(prodOfCart);
    }
}
