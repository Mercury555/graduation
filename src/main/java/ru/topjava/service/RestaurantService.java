package ru.topjava.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.model.Restaurant;
import ru.topjava.repository.RestaurantRepository;

import java.util.List;

import static ru.topjava.util.ValidationUtil.*;

@Service
public class RestaurantService {


    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public List<Restaurant> getAll() {
            return repository.getAll();
    }

    public List<Restaurant> getByName(String  name){
        return repository.getByName(name);
    }
}
