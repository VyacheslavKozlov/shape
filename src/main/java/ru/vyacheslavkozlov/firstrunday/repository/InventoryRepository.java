package ru.vyacheslavkozlov.firstrunday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vyacheslavkozlov.firstrunday.entity.Inventory;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "select * from inventory i where i.account_id = (select id from accounts where accounts.email = ?)", nativeQuery = true)
    public List<Inventory> findAllInventoryByAccountEmail(String email);
}
