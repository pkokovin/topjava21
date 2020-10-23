package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;
    public static final int MEAL_02_ID = MEAL_ID + 1;
    public static final int MEAL_03_ID = MEAL_ID + 2;
    public static final int MEAL_04_ID = MEAL_ID + 3;
    public static final int MEAL_05_ID = MEAL_ID + 4;
    public static final int MEAL_06_ID = MEAL_ID + 5;
    public static final int MEAL_07_ID = MEAL_ID + 6;
    public static final int ADMIN_MEAL_ID = START_SEQ + 9;
    public static final int ADMIN_MEAL_02_ID = ADMIN_MEAL_ID + 1;
    public static final int NOT_FOUND = 10;

    public static final Meal MEAL01 = new Meal(
            MEAL_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0, 0),
            "Завтрак",
            500
    );
    public static final Meal MEAL02 = new Meal(
            MEAL_02_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 13, 0, 0),
            "Обед",
            1000
    );
    public static final Meal MEAL03 = new Meal(
            MEAL_03_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 20, 0, 0),
            "Ужин",
            500
    );
    public static final Meal MEAL04 = new Meal(
            MEAL_04_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 0, 0, 0),
            "Еда на граничное значение",
            100
    );
    public static final Meal MEAL05 = new Meal(
            MEAL_05_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 10, 0, 0),
            "Завтрак",
            500
    );
    public static final Meal MEAL06 = new Meal(
            MEAL_06_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 13, 0, 0),
            "Обед",
            1000
    );
    public static final Meal MEAL07 = new Meal(
            MEAL_07_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 20, 0, 0),
            "Ужин",
            510
    );
    public static final Meal ADMIN_MEAL_01 = new Meal(
            ADMIN_MEAL_ID,
            LocalDateTime.of(2015, Month.JUNE, 1, 14, 0, 0),
            "Админ ланч",
            510
    );
    public static final Meal ADMIN_MEAL_02 = new Meal(
            ADMIN_MEAL_02_ID,
            LocalDateTime.of(2015, Month.JUNE, 1, 21, 0, 0),
            "Админ ужин",
            1500
    );

    public static final List<Meal> USER_MEALS = Arrays.asList(MEAL07, MEAL06, MEAL05, MEAL04, MEAL03, MEAL02, MEAL01);
    public static final List<Meal> ADMIN_MEALS = Arrays.asList(ADMIN_MEAL_02, ADMIN_MEAL_01);

    public static Meal getNew() {
        Meal newMeal = new Meal();
        newMeal.setDateTime(LocalDateTime.of(2020, Month.OCTOBER, 23, 12, 0, 0));
        newMeal.setDescription("New Meal");
        newMeal.setCalories(1000);
        return newMeal;
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL01);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
