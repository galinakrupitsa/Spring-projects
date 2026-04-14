package org.example.bakery;

import org.example.bakery.DTO.StoreAddDTO;
import org.example.bakery.Model.Store;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.StoreRepository;
import org.example.bakery.Service.StoreProducer;
import org.example.bakery.Service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private BakeryRepository bakeryRepository;
    @Mock
    private StoreProducer storeProducer;
    @InjectMocks
    private StoreService storeService;
    @Mock
    private Menu itemMenu;
    @Mock
    private StoreAddDTO storeAddDTO;


    @Test
    void shouldAddQuantityAndSendKafkaEvent() {

//        // ===== GIVEN =====
//        StoreAddDTO dto = new StoreAddDTO();
//        dto.setId(1L);
//        dto.setQuantity(5);
//
//        Menu item = new Menu();
//        item.setName("Bread");
//
//        Store store = new Store();
//        store.setAvailableQuantity(10);
//        store.setItem(item);
//
//        when(storeRepository.findByItemId(1L)).thenReturn(store);


    }
}
