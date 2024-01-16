package com.ims.OrderManager;

import com.ims.OrderManager.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getByUserId(Long userId);

    void deleteById(Long cartId);
}
