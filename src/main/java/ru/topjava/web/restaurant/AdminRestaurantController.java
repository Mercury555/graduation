package ru.topjava.web.restaurant;

import ru.topjava.model.Restaurant;

import java.util.List;

public class AdminRestaurantController extends AbstractRestaurantController {
    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return super.create(restaurant);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Restaurant restaurant, int id) {
        super.update(restaurant, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return super.getAll();
    }
}
