package org.example.bakery.Controller;

import org.example.bakery.DTO.ItemsDTO;
import org.example.bakery.DTO.StoreItemDTO;
import org.example.bakery.Model.Store;
import org.example.bakery.Service.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StoreController {
    private final StoreService storeService;
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }
    @GetMapping("/store")
    public List<Store> showAllItemsWithQuantity() {
        return storeService.showAll();
    }
    @GetMapping("/store/id/{id}")
    public StoreItemDTO findByStoreId(@PathVariable Long id){
        return storeService.findById(id);
    }
    @GetMapping("/store/lowstock")
    public List<ItemsDTO> findByLowStock(){
        return storeService.getLowStock();
    }
}
