package org.example.bakery.Service;

import org.example.bakery.DTO.ItemsDTO;
import org.example.bakery.DTO.StoreItemDTO;
import org.example.bakery.Exception.ItemNotFoundException;
import org.example.bakery.Exception.NotEnoughStockException;
import org.example.bakery.Model.Store;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            throw new ItemNotFoundException("MenuItem" + itemId + " not found");
        }
        if(store.getAvailableQuantity() < amount){
            throw new NotEnoughStockException("Sorry, Not enough stock for item id " + itemId);
        }
        store.setAvailableQuantity(store.getAvailableQuantity() - amount);
        storeRepository.save(store);
   }
    public StoreItemDTO findById(Long id){
        Store store = storeRepository.findById(id).orElse(null);
        return new StoreItemDTO(store.getItem().getName(), store.getAvailableQuantity());
    }
    public List<ItemsDTO> getLowStock(){
        List<Store> stores = storeRepository.findAll();
        List<ItemsDTO> result = new ArrayList<>();
        for(Store store : stores){
            if(store.getAvailableQuantity()<=5){
                ItemsDTO dto = new ItemsDTO();
                dto.setName(store.getItem().getName());
                dto.setQuantity(store.getAvailableQuantity());
                result.add(dto);
            }
        }
        return result;
    }
}
