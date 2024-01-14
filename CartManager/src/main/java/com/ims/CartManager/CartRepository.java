package com.ims.CartManager;

import com.ims.CartManager.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByUserId(Long userId);

    Cart getByUserId(Long userId);

    @Override
    void deleteById(Long cartId);
}
