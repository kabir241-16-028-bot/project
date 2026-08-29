package com.coffeeshop.management.controller;

import com.coffeeshop.management.model.Discount;
import com.coffeeshop.management.repository.DiscountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "*")
public class DiscountController {

    @Autowired
    private DiscountRepository discountRepository;

    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable Long id) {
        return discountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Discount createDiscount(@Valid @RequestBody Discount discount) {
        return discountRepository.save(discount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Long id, @Valid @RequestBody Discount updated) {
        return discountRepository.findById(id)
                .map(discount -> {
                    discount.setCode(updated.getCode());
                    discount.setPercentage(updated.getPercentage());
                    discount.setValidUntil(updated.getValidUntil());
                    discount.setProduct(updated.getProduct());
                    return ResponseEntity.ok(discountRepository.save(discount));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        if (!discountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        discountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
