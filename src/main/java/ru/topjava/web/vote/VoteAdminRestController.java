package ru.topjava.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.model.Vote;
import ru.topjava.model.to.VoteTo;
import ru.topjava.util.VoteUtil;

import java.util.List;


@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAdminRestController extends AbstractVoteController {
    public static final String REST_URL = "/rest/admin/votes";

    @Override
    @DeleteMapping(value = "/{id}/users/{userId}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        super.delete(id, userId);
    }

    @Override
    @GetMapping("/users/{userId}")
    public List<VoteTo> getAllByUser(@PathVariable int userId) {
        return super.getAllByUser(userId);
    }

    @Override
    @GetMapping
    public List<Vote> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}/withUser/{userId}")
    public Vote getWithUser(@PathVariable int id, @PathVariable int userId) {
        return super.getWithUser(id, userId);
    }


    @GetMapping(value = "/{id}/users/{userId}")
    public VoteTo get(@PathVariable("id") int id, @PathVariable("userId") int userId) {
       return super.get(id, userId);

    }

}
