package com.ims.CartManager;

import com.ims.CartManager.model.Cart;
import com.ims.CartManager.model.ProdOfCart;
import com.ims.CartManager.model.Sweets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final SweetsRepository sweetsRepository;
    private final CartRepository cartRepository;

    public List<Sweets> getCart(Long id) {
        List<Long> sweetsIds = this.cartRepository.getByUserId(id).getProducts();
        return this.sweetsRepository.findAllById(sweetsIds);
    }

    public ResponseEntity<String> addProdToCart(ProdOfCart prodOfCart) {
        if (this.cartRepository.existsByUserId(prodOfCart.getUserId())) {
            Cart cart = this.cartRepository.getByUserId(prodOfCart.getUserId());
            if (!cart.addProductId(prodOfCart.getProduct()))
                return ResponseEntity.ok("Already exists");
            else {
                this.cartRepository.save(cart);
                return ResponseEntity.ok("Success");
            }
        } else {
            List<Long> products = List.of(prodOfCart.getProduct());
            this.cartRepository.save(new Cart(prodOfCart.getUserId(), products));
            return ResponseEntity.ok("Cart created and product added");
        }
    }

    public ResponseEntity<String> deleteProdFromCart(ProdOfCart prodOfCart) {
        if (cartRepository.existsByUserId(prodOfCart.getUserId())) {
            Cart cart = cartRepository.getByUserId(prodOfCart.getUserId());
            System.out.println(cart);
            cart.getProducts().remove(prodOfCart.getProduct());
            System.out.println(cart);
            cartRepository.save(cart);
            return ResponseEntity.ok("Product deleted");
        } else {
            return ResponseEntity.ok("Cart not found");
        }
    }
}
