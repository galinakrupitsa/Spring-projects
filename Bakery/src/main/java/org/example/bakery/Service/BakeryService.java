package org.example.bakery.Service;

import org.example.bakery.Repository.BakeryRepository;
import org.springframework.stereotype.Service;

@Service
public class BakeryService {
    private BakeryRepository bakeryRepository;
    public BakeryService(BakeryRepository bakeryRepository) {
        this.bakeryRepository = bakeryRepository;
    }
}
