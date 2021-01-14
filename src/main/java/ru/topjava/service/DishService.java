package ru.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.model.Dish;
import ru.topjava.repository.DishRepository;

@Service
public class DishService {

    @Autowired
    private DishRepository repository;

    public Dish update(Dish dish, int rest_id) {
        return repository.save(dish, rest_id);
    }

    public Dish get(int id, int rest_id) {
        return repository.get(id, rest_id);
    }

    public Dish create(Dish dish, int rest_id) {
        return repository.save(dish, rest_id);
    }

    public void delete(int id, int rest_id) {
        repository.delete(id, rest_id);
    }


}
