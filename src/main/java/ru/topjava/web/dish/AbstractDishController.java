package ru.topjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.model.Dish;
import ru.topjava.service.DishService;

import java.util.List;

import static ru.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.util.ValidationUtil.checkNew;


public abstract class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish create(Dish dish, int restaurantId) {
        checkNew(dish);
        log.info("create {} for Restaurant {}", dish, restaurantId);
        return service.create(dish, restaurantId);
    }

    public void update(Dish dish, int id, int restaurantId) {
        assureIdConsistent(dish, id);
        log.info("update {} for Restaurant {}", dish, restaurantId);
        service.update(dish, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete menu {} for Restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public Dish get(int id, int restaurantId) {
        log.info("get menu {} for Restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

//    public List<Dish> getAll(int restaurantId) {
//        log.info("getAll for Restaurant {}", restaurantId);
//        return service.getAllByRestoran(restaurantId);
//    }

    public List<Dish> findByRestaurant(int restaurantId) {
        log.info("find By Restaurant {}", restaurantId);
        return service.getAllByRestoran(restaurantId);
    }

    public List<Dish> findByName(String name) {
        log.info("find By Name {}", name);
        return service.getByName(name);
    }


}
