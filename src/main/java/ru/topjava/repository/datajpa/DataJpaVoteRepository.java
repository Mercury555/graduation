package ru.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.topjava.model.Vote;
import ru.topjava.repository.VoteRepository;
import ru.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private CrudVoteRepository voteRepository;
    private CrudRestaurantRepository restaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudRestaurantRepository restaurantRepository) {
        this.voteRepository = crudVoteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        return voteRepository.save(vote);
    }

    @Override
    public Vote findOne(Integer id) {
        return voteRepository.getOne(id);
    }

    @Override
    public Vote getVoteToday(LocalDate date, int userId) {
        return voteRepository.getVoteByDate(date, userId);
    }

    //
    @Override
    public List<Vote> getVoteBetween(LocalDate startDate, LocalDate endDate, int userid) {
        return voteRepository.getVoteBetween(startDate, endDate, userid);
//        return null;
    }

    @Override
    public List<Vote> getAllVoteByUser(int userId) {
        return voteRepository.getAllVoteByUser(userId);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        return voteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return voteRepository.get(id, userId);
    }

    public Vote getWithUser(int id, int userId){
        return voteRepository.getWithUser(id, userId);
    }
}
