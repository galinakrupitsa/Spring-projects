package org.example.bakery.Service;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.example.bakery.DTO.ItemsDTO;
import org.example.bakery.DTO.StoreAddDTO;
import org.example.bakery.DTO.StoreItemDTO;
import org.example.bakery.Exception.ItemNotFoundException;
import org.example.bakery.Exception.NotEnoughStockException;
import org.example.bakery.Model.MenuItem;
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
    private BakeryRepository bakeryRepository;

    public StoreService(StoreRepository storeRepository, OrderRepository orderRepository, BakeryRepository bakeryRepository) {
        this.storeRepository = storeRepository;
        this.bakeryRepository = bakeryRepository;
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
    public String addStore(StoreAddDTO dto) {
        Store store = storeRepository.findByItemId(dto.getId());
        if(store == null){
            throw new ItemNotFoundException("MenuItem" + dto.getId() + " not found");
        }
        store.setAvailableQuantity(store.getAvailableQuantity() + dto.getQuantity());
        storeRepository.save(store);
        String itemName = store.getItem().getName();
        return "Успешно добавлено " + itemName;
    }
    @Transactional
    public String addBatch(List<StoreAddDTO> list) {
        for (StoreAddDTO dto : list) {
            MenuItem item = bakeryRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Товар не найден с id: " + dto.getId()
                    ));
            Store store = storeRepository.findByItem(item)
                    .orElse(new Store());
            if (store == null) {
                store.setItem(item);
                store.setAvailableQuantity(dto.getQuantity());
            }else{
                store.setAvailableQuantity(store.getAvailableQuantity() + dto.getQuantity());
            }
            storeRepository.save(store);
        }
        return "Товары успешно добавлены: " + list.size();
    }
}
