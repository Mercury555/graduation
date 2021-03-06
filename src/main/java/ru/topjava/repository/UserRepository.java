package ru.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.topjava.model.User;

import java.util.List;

@Repository
public interface UserRepository {

    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default User getWithVotes(int id) {
        throw new UnsupportedOperationException();
    }
}
