package ru.topjava.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.model.Restaurant;

import java.util.List;
@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {

    public static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/find")
    public List<Restaurant> findByName(@RequestParam String name) {
        return super.findByName(name);
    }


    @Override
    @GetMapping("/{id}/withDishes")
    public Restaurant getWithDishes(@PathVariable int id) {
        return super.getWithDishes(id);
    }

}
