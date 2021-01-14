package ru.topjava.repository;

import ru.topjava.model.Dish;

import java.util.List;


public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish, int rest_id);

    // false if not found
    boolean delete(int id, int rest_id);

    // null if not found
    Dish get(int id, int rest_id);

    List<Dish> getAllByRestaurant(int rest_id);

    List<Dish> getByName(String name);
}
