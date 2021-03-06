package ru.topjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.VoteTestData;
import ru.topjava.model.Role;
import ru.topjava.model.User;
import ru.topjava.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.topjava.UserTestData.*;
import static ru.topjava.VoteTestData.VOTES_BETWEEN;
import static ru.topjava.VoteTestData.VOTES_BETWEEN_ASC;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void testSave() {
        User created = service.create(getNew());
        User newUser = getNew();
        int newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void testDelete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    public void testNotFoundDelete() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void testGet() {
        User user = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(ADMIN, user);
    }

    @Test
    public void testGetNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void testGetByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(ADMIN, user);
    }

    @Test
    public void testGetAll() {
        Collection<User> all = service.getAll();
        USER_MATCHER.assertMatch(Arrays.asList(ADMIN, USER, USER2, USER3), all);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void createWithException() {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "  ", "user1@mail.ru", "password1", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User1", "  ", "password1", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User1", "user1@mail.ru", "  ", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User1", "user1 mail.ru", "password1", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User1", "user1@mail.ru", "password1", true, null, Set.of())));
    }

    @Test
    public void getWithVotes() {
        User user = service.getWithVotes(USER_ID+3);
        USER_MATCHER.assertMatch(user, USER3);
        VoteTestData.VOTE_MATCHER.assertMatch(user.getVotes(), VOTES_BETWEEN_ASC);
    }

    @Test
    void getWithVotesNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithVotes(1));
    }
}