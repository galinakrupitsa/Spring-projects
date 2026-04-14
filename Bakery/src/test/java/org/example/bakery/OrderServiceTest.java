package org.example.bakery;

import org.example.bakery.DTO.BestDayDTO;
import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.DTO.OrderResponseTodayDTO;
import org.example.bakery.Model.Orders;
import org.example.bakery.Repository.BakeryRepository;
import org.example.bakery.Repository.CustomerRepository;
import org.example.bakery.Repository.OrderRepository;
import org.example.bakery.Repository.StoreRepository;
import org.example.bakery.Service.OrderService;
import org.example.bakery.Service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Test
    public void shouldReturnMappedOrdersForGivenDate() {
        // 🔹 mock repository
        OrderRepository orderRepository = mock(OrderRepository.class);
        BakeryRepository bakeryRepository = mock(BakeryRepository.class);
        StoreRepository storeRepository = mock(StoreRepository.class);
        StoreService storeService = mock(StoreService.class);
        CustomerRepository customerRepository = mock(CustomerRepository.class);


        // 🔹 сервис
        OrderService service = new OrderService(
                orderRepository,
                bakeryRepository,
                storeRepository,
                storeService,
                customerRepository);
        // 🔹 дата
        LocalDate date = LocalDate.now();
        // 🔹 тестовые данные (entity)
        Orders order1 = new Orders();
        order1.setId(1L);
        order1.setTotal(100.0);
        order1.setCreatedAt(date);

        Orders order2 = new Orders();
        order2.setId(2L);
        order2.setTotal(200.0);
        order2.setCreatedAt(date);

        when(orderRepository.findByCreatedAt(date))
                .thenReturn(List.of(order1, order2));
        // 🔹 вызов метода
        List<OrderResponseTodayDTO> result = service.getDayOrders(date);

// 🔹 проверки
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(100.0, result.get(0).getTotal());
        assertEquals(date, result.get(0).getDate());

        assertEquals(2L, result.get(1).getId());
        assertEquals(200.0, result.get(1).getTotal());
        assertEquals(date, result.get(1).getDate());

        // 🔹 проверка вызова репозитория
        verify(orderRepository, times(1)).findByCreatedAt(date);
    }

    @Test
    void shouldReturnOrdersForGivenCustomerId(){
        OrderRepository orderRepository = mock(OrderRepository.class);
        BakeryRepository bakeryRepository = mock(BakeryRepository.class);
        StoreRepository storeRepository = mock(StoreRepository.class);
        StoreService storeService = mock(StoreService.class);
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        OrderService service = new OrderService(orderRepository,
                bakeryRepository,
                storeRepository,
                storeService,
                customerRepository);


        // ===== GIVEN =====
        Long customerId = 1L;
        Orders order1 = new Orders();
        order1.setId(10L);
        order1.setTotal(150.0);
        order1.setCreatedAt(LocalDate.now());

        Orders order2 = new Orders();
        order2.setId(20L);
        order2.setTotal(300.0);
        order2.setCreatedAt(LocalDate.now());
        when(orderRepository.findByCustomerId(anyLong()))
                .thenReturn(List.of(order1, order2));
        // ===== WHEN =====
        List<OrderResponseDTO> result =
                service.getOrdersByCustomerId(customerId);
        //THEN
        assertEquals(2, result.size());
        OrderResponseDTO dto1 = result.get(0);
        assertEquals(10L, dto1.getOrderId());
        assertEquals(150.0, dto1.getTotal());
        assertEquals(order1.getCreatedAt(), dto1.getOrderDate());

        OrderResponseDTO dto2 = result.get(1);
        assertEquals(20L, dto2.getOrderId());
        assertEquals(300.0, dto2.getTotal());
        assertEquals(order2.getCreatedAt(), dto2.getOrderDate());

        verify(orderRepository).findByCustomerId(customerId);
    }

    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderService service;
    @Test
    public void getBestDayTest(){
        // 🔹 тестовые данные
        LocalDate day1 = LocalDate.of(2024, 1, 1);
        LocalDate day2 = LocalDate.of(2024, 1, 2);

        Orders o1 = new Orders();
        o1.setCreatedAt(day1);
        o1.setTotal(100.0);
        Orders o2 = new Orders();
        o2.setCreatedAt(day2);
        o2.setTotal(200.0);
        Orders o3 = new Orders();
        o3.setCreatedAt(day2);
        o3.setTotal(300.0);
    when(orderRepository.findAll()).thenReturn(List.of(o1, o2, o3));
    BestDayDTO result = service.getBestDay();

    assertEquals(day2, result.getDate());
    assertEquals(500, result.getRevenue());
    verify(orderRepository, times(1)).findAll();

    }

}
