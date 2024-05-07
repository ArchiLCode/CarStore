package ru.solonchev.backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.solonchev.backend.model.Car;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    private String name;
    private double price;

    public static List<OrderItem> createListOfCars(List<Car> cars) {
        List<OrderItem> result = new ArrayList<>();
        cars.forEach(car -> result.add(OrderItem.builder()
                .name(car.getName())
                .price(car.getPrice())
                .build()));
        return result;
    }
}