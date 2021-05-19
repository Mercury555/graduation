package ru.topjava.web.dish;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.model.Dish;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController extends AbstractDishController {
    public static final String REST_URL = "/rest/admin/restaurants";


    @Override
    @GetMapping("/{restaurantId}/dishes/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{restaurantId}/dishes/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        super.delete(id, restaurantId);
    }

//    @Override
//    @GetMapping
//    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
//        return super.getAll(restaurantId);
//    }

    @Override
    @GetMapping("/{restaurantId}/dishes")
    public List<Dish> findByRestaurant(@PathVariable ("restaurantId") int restaurantId) {
        return super.findByRestaurant(restaurantId);
    }

    @Override
    @GetMapping("/dishes")
    public List<Dish> findByName(@RequestParam String name) {
        return super.findByName(name);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) {
        super.update(dish, id, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        Dish created = super.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
