package ru.vyacheslavkozlov.firstrunday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vyacheslavkozlov.firstrunday.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
