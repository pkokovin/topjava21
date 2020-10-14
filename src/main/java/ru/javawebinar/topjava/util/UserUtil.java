package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<User> users = Arrays.asList(
            new User(1, "User", "user@mail.ru", "password", Role.USER),
            new User(2, "Admin", "admin@gmail.com", "password", Role.ADMIN)
    );

}
