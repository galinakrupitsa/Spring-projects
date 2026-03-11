package org.example.bakery.Service;

import org.example.bakery.DTO.StoreItemDTO;
import org.example.bakery.Model.Store;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository, OrderRepository orderRepository, BakeryRepository bakeryRepository) {
        this.storeRepository = storeRepository;
    }
   public List<Store> showAll(){
        return storeRepository.findAllWithItems();
   }
   public void decreaseQuantity(Long itemId, int amount){
        Store store = storeRepository.findByItemId(itemId);
        if(store == null){
            throw new RuntimeException("MenuItem not found");
        }
        if(store.getAvailableQuantity() < amount){
            throw new RuntimeException("Sorry, Not enough stock");
        }
        store.setAvailableQuantity(store.getAvailableQuantity() - amount);
        storeRepository.save(store);
   }
    public StoreItemDTO findById(Long id){
        Store store = storeRepository.findById(id).orElse(null);
        return new StoreItemDTO(store.getItem().getName(), store.getAvailableQuantity());

    }
}
