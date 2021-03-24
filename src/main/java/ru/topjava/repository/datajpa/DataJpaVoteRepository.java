package ru.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.topjava.model.Vote;
import ru.topjava.repository.VoteRepository;
import ru.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {


    private  CrudVoteRepository voteRepository;


    private  CrudRestaurantRepository restaurantRepository;


    private  CrudDishRepository dishRepository;

    public DataJpaVoteRepository() {
    }

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudRestaurantRepository restaurantRepository, CrudDishRepository dishRepository) {
        this.voteRepository = crudVoteRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Vote save(Vote vote) {
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

    @Override
    public List<Vote> getVoteBetween(LocalDate startDate, LocalDate endDate, int userid) {
        return voteRepository.getVoteBetween(startDate, endDate, userid);
    }

    @Override
    public List<Vote> getAllVoteByUser(int userId) {
        return voteRepository.getAllVoteByUser(userId);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
       return voteRepository.delete(id, userId) != 0;
    }
}
