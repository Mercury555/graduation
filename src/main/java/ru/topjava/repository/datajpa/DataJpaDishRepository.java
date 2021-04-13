//package ru.topjava.repository.datajpa;
//
//import org.springframework.stereotype.Repository;
//import ru.topjava.model.Dish;
//
//import java.util.List;
//
//@Repository
//public class DataJpaDishRepository implements DishRepository {
//
//
//    private DishRepository.java crudDishRepository;
//
//    private CrudRestaurantRepository crudRestaurantRepository;
//
//    public DataJpaDishRepository() {
//    }
//
//    public DataJpaDishRepository(DishRepository.java crudDishRepository, CrudRestaurantRepository crudRestaurantRepository) {
//        this.crudDishRepository = crudDishRepository;
//        this.crudRestaurantRepository = crudRestaurantRepository;
//    }
//
//    @Override
//    public Dish save(Dish dish, int rest_id) {
//        if (!dish.isNew() && get(dish.getId(), rest_id) == null) {
//            return null;
//        }
//        dish.setRestaurant(crudRestaurantRepository.getOne(rest_id));
//        return crudDishRepository.save(dish);
//    }
//
//    @Override
//    public boolean delete(int id, int rest_id) {
//        return crudDishRepository.delete(id, rest_id) !=0;
//    }
//
//    @Override
//    public Dish get(int id, int rest_id) {
//        Dish dish = crudDishRepository.getById(id);
//        return  dish != null && dish.getRestaurant().getId() == rest_id ? dish : null;
//
//    }
//
//    @Override
//    public List<Dish> getAllByRestaurant(int rest_id) {
//        return crudDishRepository.getAllByRestaurant(rest_id);
////        return crudDishRepository.findAll().stream().filter(dish -> dish.getRestaurant().getId() == rest_id).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Dish> getByName(String name) {
//        return crudDishRepository.getByName(name);
////        return crudDishRepository.findAll().stream().filter(dish -> dish.getName().equals(name)).collect(Collectors.toList());
//    }
//}
