package org.example.bakery.Service;

import jakarta.transaction.Transactional;
import org.example.bakery.DTO.MenuResponseDTO;
import org.example.bakery.DTO.MenuUpdateDTO;
import org.example.bakery.Model.Store;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.StoreRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.example.bakery.Model.MenuItem;

@Service
public class BakeryService {
    private BakeryRepository bakeryRepository;
    private StoreRepository storeRepository;
    public BakeryService(BakeryRepository bakeryRepository, StoreRepository storeRepository) {
        this.bakeryRepository = bakeryRepository;
        this.storeRepository = storeRepository;
    }
@Cacheable("menu")
    public List<MenuResponseDTO> showAll() {
        List<MenuResponseDTO> menu = new ArrayList<>();
        for (MenuItem item : bakeryRepository.findAll()) {
            MenuResponseDTO dtomenu = new MenuResponseDTO();
            dtomenu.setName(item.getName());
            dtomenu.setPrice(item.getPrice());
            menu.add(dtomenu);
        }
        return menu;
    }
    @Transactional
    public MenuItem postMenu(MenuResponseDTO menu){
        MenuItem item = new MenuItem();
        item.setName(menu.getName());
        item.setPrice(menu.getPrice());
        MenuItem savedItem = bakeryRepository.save(item);
        Store store = new Store();
        store.setItem(savedItem);
        store.setAvailableQuantity(menu.getQuantity());

        storeRepository.save(store);
        return savedItem;
    }
    public MenuUpdateDTO updateMenuPrice(Long id, double price){
        MenuItem item = bakeryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));

        item.setPrice(price);
        bakeryRepository.save(item);

        MenuUpdateDTO menuUpdateDTO = new MenuUpdateDTO();
        menuUpdateDTO.setName(item.getName());
        menuUpdateDTO.setPrice(item.getPrice());

        return menuUpdateDTO;
    }

}
