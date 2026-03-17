package org.example.bakery.Service;

import jakarta.persistence.criteria.Order;
import org.example.bakery.DTO.MenuResponseDTO;
import org.example.bakery.Repository.BakeryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.example.bakery.Model.MenuItem;

@Service
public class BakeryService {
    private BakeryRepository bakeryRepository;
    public BakeryService(BakeryRepository bakeryRepository) {
        this.bakeryRepository = bakeryRepository;
    }

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
    public MenuItem postMenu(MenuResponseDTO menu){
        MenuItem dtomenu = new MenuItem();
        dtomenu.setName(menu.getName());
        dtomenu.setPrice(menu.getPrice());
        return bakeryRepository.save(dtomenu);
    }


}
