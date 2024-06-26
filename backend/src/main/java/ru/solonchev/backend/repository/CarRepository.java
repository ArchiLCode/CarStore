package ru.solonchev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solonchev.backend.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByName(String name);
}
