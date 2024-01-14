package com.ims.ProductManager;

import com.ims.ProductManager.model.Sweets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetsService {

    private final SweetsRepository sweetsRepository;


    public List<Sweets> getSweets() {
        return this.sweetsRepository.findAll();
    }

    public ResponseEntity<String> addSweet(Sweets sweet) {
        sweetsRepository.save(sweet);
        return ResponseEntity.ok("Sweet added");
    }

    public ResponseEntity<String> deleteSweet(Sweets sweet) {
        if (sweetsRepository.existsByName(sweet.getName())) {
            Sweets foundSweet = sweetsRepository.getByName(sweet.getName());
            sweetsRepository.delete(foundSweet);
            return ResponseEntity.ok("Sweet deleted");
        } else {
            return ResponseEntity.ok("Sweet not found");
        }
    }
}
