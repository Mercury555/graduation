package ru.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.model.Restaurant;
import ru.topjava.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public Restaurant get(int id) {
        return repository.get(id);
    }

    public Restaurant create(Restaurant restaurant) {
            return repository.save(restaurant);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Restaurant update(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public List<Restaurant> getAll() {
            return repository.getAll();
    }
}
