package ru.vyacheslavkozlov.firstrunday.service;

import ru.vyacheslavkozlov.firstrunday.entity.Inventory;

import java.util.List;

public interface InventoryService {
    public List<Inventory> getAllInventoryByAccountEmail(String email);

    public Inventory createInventory(Inventory inventory);

    public void deleteInventory(long id);

    public Inventory updateInventory(Inventory inventory);
}
