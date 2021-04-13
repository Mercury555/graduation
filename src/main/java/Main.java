import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.topjava.model.Restaurant;
import ru.topjava.model.Vote;
import ru.topjava.repository.RestaurantRepository;
import ru.topjava.repository.VoteRepository;
import ru.topjava.repository.datajpa.DishRepository;
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

            VoteService service = appCtx.getBean(VoteService.class);

            System.out.println(service.getBetween(LocalDate.of(2021, Month.MAY, 28), LocalDate.of(2021, Month.MAY, 29), 100003));
            Restaurant RESTAURANT2 = new Restaurant(100005, "Restaurant 2", "sea_food");
            Vote VOTE5 = new Vote(100017, of(2021, Month.MAY, 28), RESTAURANT2);
            Vote VOTE6 = new Vote(100018, of(2021, Month.MAY, 29), RESTAURANT2);

            List<Vote> VOTES_BETWEEN = Arrays.asList(VOTE6, VOTE5);
            System.out.println(VOTES_BETWEEN);

        }
    }
}