package ru.vyacheslavkozlov.firstrunday.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.vyacheslavkozlov.firstrunday.entity.Account;
import ru.vyacheslavkozlov.firstrunday.entity.Inventory;
import ru.vyacheslavkozlov.firstrunday.service.InventoryService;
import ru.vyacheslavkozlov.firstrunday.service.impl.AccountDetailsServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/runday")
public class InventoryController {

    private final InventoryService inventoryService;
    private final AccountDetailsServiceImpl accountDetailsService;

    @GetMapping("/inventory")
    @PreAuthorize("hasAnyAuthority('account:read', 'account:write')")
    public List<Inventory> getAllInventoryAccount(Authentication authentication){
        List<Inventory> inventories = inventoryService.getAllInventoryByAccountEmail(authentication.getName());
        return inventories;
    }

    @PostMapping(path = "/inventory")
    public Inventory createInventory(@RequestBody Inventory inventory, Authentication authentication){
        Account accountFromDB = accountDetailsService.findByEmail(authentication.getName());
        inventory.setAccount(accountFromDB);
        Inventory inventoryFromDB = inventoryService.createInventory(inventory);

        return inventoryFromDB;
    }

    @DeleteMapping("/inventory/{id}")
    public String deleteInventoryById(@PathVariable("id") long id){
        inventoryService.deleteInventory(id);
        return "delete inventory";
    }

    @PatchMapping("/inventory")
    public Inventory updateInventoryById(@RequestBody Inventory inventory, Authentication authentication){
        Account accountFromDB = accountDetailsService.findByEmail(authentication.getName());
        inventory.setAccount(accountFromDB);
        var inventoryFromDB = inventoryService.updateInventory(inventory);
        return inventoryFromDB;
    }


}
