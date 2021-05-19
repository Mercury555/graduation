import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import ru.topjava.model.Restaurant;
import ru.topjava.model.Role;
import ru.topjava.model.User;
import ru.topjava.model.Vote;
import ru.topjava.repository.RestaurantRepository;
import ru.topjava.repository.VoteRepository;
import ru.topjava.repository.datajpa.DishRepository;
import ru.topjava.service.UserService;
import ru.topjava.service.VoteService;
import ru.topjava.web.json.JsonUtil;

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

//            Arrays.stream(appCtx.getBeanDefinitionNames()).forEach(System.out::println);

//            final UserService userService = appCtx.getBean(UserService.class);
//            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//            userService.getAll().forEach(System.out::println);
//            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//            userService.delete(100002);
//            System.out.println("----------------------------------------------------------------");
//            userService.getAll().forEach(System.out::println);
//            System.out.println("----------------------------------------------------------------");

            Restaurant RESTAURANT2 = new Restaurant(100005, "Restaurant 2", "sea_food");
            Vote vote = new Vote(of(2021, Month.MAY, 4), RESTAURANT2);
            User USER3 = new User(100003, "User3", "user3@yandex.ru", "password", Role.USER);
            vote.setUser(USER3);
//        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "/" + RESTAURANT1_ID)
            String jsonString = JsonUtil.writeValue(vote);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(jsonString);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("____________________________________________");
            System.out.println(JsonUtil.readValues(jsonString, Vote.class));
            System.out.println("____________________________________________");
//            System.out.println(action.toString());
//            System.out.println("____________________________________________");
//            Vote created = readFromJson(action, Vote.class);

        }
    }
}