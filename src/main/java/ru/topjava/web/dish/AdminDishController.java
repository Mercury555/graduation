package ru.topjava.web.dish;

import org.springframework.stereotype.Controller;
import ru.topjava.model.Dish;
import ru.topjava.service.DishService;

@Controller
public class AdminDishController extends AbstractDishController {

    public AdminDishController(DishService service) {
        super(service);
    }

    @Override
    public Dish get(int id, int restaurant_id) {
        return super.get(id, restaurant_id);
    }

    @Override
    public Dish create(Dish dish,int restaurant_id) {
        return super.create(dish, restaurant_id);
    }

    @Override
    public void delete(int id, int restaurant_id) {
        super.delete(id, restaurant_id);
    }

    @Override
    public void update(Dish dish, int restaurant_id) {
        super.update(dish, restaurant_id);
    }


}
