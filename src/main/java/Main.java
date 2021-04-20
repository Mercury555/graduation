import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.topjava.model.Restaurant;
import ru.topjava.model.Vote;
import ru.topjava.repository.RestaurantRepository;
import ru.topjava.repository.VoteRepository;
import ru.topjava.repository.datajpa.DishRepository;
import ru.topjava.service.UserService;
import ru.topjava.service.VoteService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCtx =
                     new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {

            Arrays.stream(appCtx.getBeanDefinitionNames()).forEach(System.out::println);

            final UserService userService = appCtx.getBean(UserService.class);
            userService.getAll().forEach(System.out::println);
            userService.get(100002);
            userService.delete(100002);
            System.out.println("----------------------------------------------------------------");
            userService.getAll().forEach(System.out::println);


        }
    }
}