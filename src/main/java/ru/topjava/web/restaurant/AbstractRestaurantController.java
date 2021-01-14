package ru.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.topjava.model.Restaurant;
import ru.topjava.service.RestaurantService;

import java.util.List;

import static ru.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.util.ValidationUtil.checkNew;

public class AbstractRestaurantController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantService service;

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

}
