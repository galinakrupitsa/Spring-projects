package org.example.bakery.Controller;

import org.example.bakery.DTO.MenuResponseDTO;
import org.example.bakery.DTO.MenuUpdateDTO;
import org.example.bakery.Model.MenuItem;
import org.example.bakery.Service.BakeryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BakeryController {
    private final BakeryService bakeryService;
    public BakeryController(BakeryService bakeryService) {
        this.bakeryService = bakeryService;
    }
    @GetMapping("/menu")
    public List<MenuResponseDTO> getMenu() {
        return bakeryService.showAll();
    }
    @PostMapping("/bakery/add-menu")
    public MenuItem postMenuItem(@RequestBody MenuResponseDTO menu){
        return bakeryService.postMenu(menu);
    }
    @PutMapping("/bakery/update/{id}/price")
    public MenuUpdateDTO updateMenuItem(@PathVariable Long id, @RequestParam double price){
        return bakeryService.updateMenuPrice(id,price);
    }
}
