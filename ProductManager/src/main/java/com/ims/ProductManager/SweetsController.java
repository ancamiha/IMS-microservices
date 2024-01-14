package com.ims.ProductManager;

import com.ims.ProductManager.model.Sweets;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SweetShop")
@RequiredArgsConstructor
public class SweetsController {

    private final SweetsService sweetsService;

    @GetMapping(value = { "/sweets" })
    public List<Sweets> getSweets() {
        return sweetsService.getSweets();
    }

    @PostMapping(value = { "/addSweet" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSweet(@RequestBody Sweets sweet) {
        return sweetsService.addSweet(sweet);
    }

    @DeleteMapping(value = { "/deleteSweet" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSweet(@RequestBody Sweets sweet) {
        return sweetsService.deleteSweet(sweet);
    }
}
