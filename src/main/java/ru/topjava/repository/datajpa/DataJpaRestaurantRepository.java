package ru.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.topjava.model.Restaurant;
import ru.topjava.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {


    private CrudRestaurantRepository repository;

    public DataJpaRestaurantRepository() {
    }

    public DataJpaRestaurantRepository(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        return repository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return repository.getOne(id);
    }

    @Override
    public List<Restaurant> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
