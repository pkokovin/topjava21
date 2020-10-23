package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_01_ID = START_SEQ + 2;
    public static final int MEAL_02_ID = START_SEQ + 3;
    public static final int MEAL_03_ID = START_SEQ + 4;
    public static final int MEAL_04_ID = START_SEQ + 5;
    public static final int MEAL_05_ID = START_SEQ + 6;
    public static final int MEAL_06_ID = START_SEQ + 7;
    public static final int MEAL_07_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL_01_ID = START_SEQ + 9;
    public static final int ADMIN_MEAL_02_ID = START_SEQ + 10;
    public static final int NOT_FOUND = 10;

    public static final Meal meal01 = new Meal(
            MEAL_01_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0, 0),
            "Завтрак",
            500
    );
    public static final Meal meal02 = new Meal(
            MEAL_02_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 13, 0, 0),
            "Обед",
            1000
    );
    public static final Meal meal03 = new Meal(
            MEAL_03_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 20, 0, 0),
            "Ужин",
            500
    );
    public static final Meal meal04 = new Meal(
            MEAL_04_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 0, 0, 0),
            "Еда на граничное значение",
            100
    );
    public static final Meal meal05 = new Meal(
            MEAL_05_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 10, 0, 0),
            "Завтрак",
            500
    );
    public static final Meal meal06 = new Meal(
            MEAL_06_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 13, 0, 0),
            "Обед",
            1000
    );
    public static final Meal meal07 = new Meal(
            MEAL_07_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 20, 0, 0),
            "Ужин",
            510
    );
    public static final Meal adminMeal01 = new Meal(
            ADMIN_MEAL_01_ID,
            LocalDateTime.of(2015, Month.JUNE, 1, 14, 0, 0),
            "Админ ланч",
            510
    );
    public static final Meal adminMeal02 = new Meal(
            ADMIN_MEAL_02_ID,
            LocalDateTime.of(2015, Month.JUNE, 1, 21, 0, 0),
            "Админ ужин",
            1500
    );

    public static final List<Meal> userMeals = Arrays.asList(meal07, meal06, meal05, meal04, meal03, meal02, meal01);
    public static final List<Meal> adminMeals = Arrays.asList(adminMeal02, adminMeal01);

    public static Meal getNew() {
        Meal newMeal = new Meal();
        newMeal.setDateTime(LocalDateTime.of(2020, Month.OCTOBER, 23, 12, 0, 0));
        newMeal.setDescription("New Meal");
        newMeal.setCalories(1000);
        return newMeal;
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal01);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("user").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }
}
