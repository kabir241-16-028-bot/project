package com.coffeeshop.management.service;

import com.coffeeshop.management.model.Order;
import com.coffeeshop.management.model.OrderItem;
import com.coffeeshop.management.model.Product;
import com.coffeeshop.management.repository.OrderRepository;
import com.coffeeshop.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Marks an existing order as paid and moves it to COMPLETED.
     * Throws RuntimeException if the order doesn't exist or is already paid.
     */
    public Order payOrder(Long orderId, Order.PaymentMethod paymentMethod) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        if (order.getPaymentStatus() == Order.PaymentStatus.PAID) {
            throw new RuntimeException("Order " + orderId + " is already paid");
        }

        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(Order.PaymentStatus.PAID);
        order.setPaidAt(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.COMPLETED);

        return orderRepository.save(order);
    }

    public Order createOrder(Order order) {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProduct().getId()));

            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setSubtotal(subtotal);
            item.setProduct(product);
            item.setOrder(order);

            // reduce stock
            int newStock = product.getStockQuantity() - item.getQuantity();
            product.setStockQuantity(Math.max(newStock, 0));
            productRepository.save(product);

            total = total.add(subtotal);
        }

        order.setTotalAmount(total);
        return orderRepository.save(order);
    }
}
