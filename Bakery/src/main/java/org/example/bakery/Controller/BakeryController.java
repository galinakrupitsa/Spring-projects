package org.example.bakery.Controller;

import org.example.bakery.Service.BakeryService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BakeryController {
    private BakeryService bakeryService;
    public BakeryController(BakeryService bakeryService) {
        this.bakeryService = bakeryService;
    }
}
