import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.topjava.repository.DishRepository;
import ru.topjava.repository.RestaurantRepository;
import ru.topjava.service.DishService;

import java.util.Arrays;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCtx =
                     new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {

            Arrays.stream(appCtx.getBeanDefinitionNames()).forEach(s -> System.out.println(s));

            DishRepository dishRepository = appCtx.getBean(DishRepository.class);
//            RestaurantRepository restaurantRepository = appCtx.getBean(RestaurantRepository.class);

//            System.out.println(restaurantRepository.get(100004));
            System.out.println(dishRepository.get(100012, 100006));
        }
    }
}