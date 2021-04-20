package ru.topjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.topjava.model.Dish;
import ru.topjava.service.DishService;

import static ru.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private DishService service;

    public AbstractDishController(DishService service) {
        this.service = service;
    }

    public Dish get(int id, int restaurant_id) {
        log.info("get {}", id);
        return service.get(id, restaurant_id);
    }

    public Dish create(Dish dish, int restaurant_id) {
        log.info("create {}", dish);
        checkNew(dish);
        return service.create(dish, restaurant_id);
    }

    public void delete(int id, int restaurant_id) {
        log.info("delete {}", id);
        service.delete(id,restaurant_id);
    }

    public void update(Dish dish, int restaurant_id) {
        log.info("update {} with id={}", dish, restaurant_id);
        assureIdConsistent(dish, restaurant_id);
        service.update(dish, restaurant_id);
    }

}
