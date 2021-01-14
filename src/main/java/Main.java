import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.topjava.repository.DishRepository;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
//        System.out.format("Hello Topjava Enterprise!");
//        System.out.println();

        try (ConfigurableApplicationContext appCtx =
                     new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {

//            Arrays.stream(appCtx.getBeanDefinitionNames()).forEach(s -> System.out.println(s));

            DishRepository dishRepository = appCtx.getBean(DishRepository.class);
//            RestaurantRepository restaurantRepository = appCtx.getBean(RestaurantRepositoryImpl.class);
//            Restaurant restaurant = restaurantRepository.get(100006);
//            System.out.println("Main restaurant.get       " +restaurantRepository.get(100006).getId());
//            Dish test = new Dish(null, "newDish", LocalDate.now(), 2000, restaurant);
//            test.setRestaurant(restaurantRepository.get(100005));
//            System.out.println(test);
//            dishRepository.save(test, 100006);

//            List<Dish> dish = dishRepository.getByName("Dish 1");
//            dish.stream().forEach(System.out::println);
            System.out.println(dishRepository.get(100012, 100006));
//            System.out.println(service.get(100012, 100006 ));
//

        }
    }
}