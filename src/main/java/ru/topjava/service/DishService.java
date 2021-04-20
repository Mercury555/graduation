package ru.topjava.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.model.Dish;
import ru.topjava.repository.RestaurantRepository;
import ru.topjava.repository.datajpa.DishRepository;

import java.util.List;

import static ru.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish create(Dish dish, int rest_id) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurantRepository.get(rest_id));
        return dishRepository.save(dish);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void update(Dish dish, int rest_id) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurantRepository.get(rest_id));
        checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    public Dish get(int id, int rest_id) {
        return checkNotFoundWithId(dishRepository.get(id, rest_id), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(int id, int rest_id) {
        checkNotFoundWithId(dishRepository.delete(id, rest_id) != 0, id);
    }

    @Cacheable("dishes")
    public List<Dish> getAllByRestoran(int rest_id) {
        return dishRepository.getAllByRestaurant(rest_id);
    }

    @Cacheable("dishes")
    public List<Dish> getByName(String name) {
        return dishRepository.getByName(name);
    }

}
