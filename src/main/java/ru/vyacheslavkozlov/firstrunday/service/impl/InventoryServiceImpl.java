package ru.vyacheslavkozlov.firstrunday.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vyacheslavkozlov.firstrunday.entity.Inventory;
import ru.vyacheslavkozlov.firstrunday.repository.InventoryRepository;
import ru.vyacheslavkozlov.firstrunday.service.InventoryService;

import java.util.List;

@AllArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventoryByAccountEmail(String email) {
        return inventoryRepository.findAllInventoryByAccountEmail(email);
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
