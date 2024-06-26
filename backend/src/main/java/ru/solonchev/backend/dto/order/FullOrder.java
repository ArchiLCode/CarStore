package ru.solonchev.backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.solonchev.backend.model.Car;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullOrder {
    private List<OrderItem> cars;
    private double resultPrice;

    public static FullOrder createFullOrder(List<Car> cars) {
        double resultPrice = 0;
        for (Car car : cars)
            resultPrice += car.getPrice();
        return FullOrder.builder()
                .cars(OrderItem.createListOfCars(cars))
                .resultPrice(resultPrice)
                .build();
    }
}