package ru.topjava.web.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.model.Vote;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VoteProfileRestController.REST_URL)
public class VoteProfileRestController extends AbstractVoteController {
    public static final String REST_URL = "/rest/votes/restaurants";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {
        Vote created = super.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Vote vote, @PathVariable int id) {
       super.update(vote, id);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id){
       return super.get(id);
    }

    @GetMapping
    public List<Vote> getAllByUser(){
        return super.getAllByUser();
    }

}
