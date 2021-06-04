package ru.topjava.util;

import ru.topjava.model.Vote;
import ru.topjava.model.to.VoteTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VoteUtil {

    public static List<VoteTo> get(Collection<Vote> votes) {

        return votes.stream().map(vote -> createTo(vote)).collect(Collectors.toList());
    }


    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getLocal_date(), vote.getUser().getEmail(), vote.getUser().getId(), vote.getRestaurant().getName(), vote.getRestaurant().getId());
    }

}
