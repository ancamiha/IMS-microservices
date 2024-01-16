package com.ims.OrderManager;

import com.ims.OrderManager.model.Address;
import com.ims.OrderManager.model.Cart;
import com.ims.OrderManager.model.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Transactional
    public ResponseEntity<String> order(Long userId, Address address) {
        Cart cart = cartRepository.getByUserId(userId);
        orderRepository.save(Order.builder()
                .userId(userId)
                .address(address.getAddress())
                .cartId(cart.getId())
                .products(new ArrayList<>(cart.getProducts()))
                .build());
        cart.getProducts().clear();
        cartRepository.save(cart);
        cartRepository.deleteById(cart.getId());
        return ResponseEntity.ok("Order placed");
    }
}
