package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of
                        (2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of
                        (2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of
                        (2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of
                        (2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of
                        (2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of
                        (2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of
                        (2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals,
                LocalTime.of(7, 0),
                LocalTime.of(12, 0),
                2000);

        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of
                (7, 0), LocalTime.of(12, 0), 2000));
        filteredByStreams(meals, LocalTime.of
                (7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);
        System.out.println(filteredByCycles(meals, LocalTime.of
                (7, 0), LocalTime.of(12, 0), 2000));
        filteredByCycles(meals, LocalTime.of
                (7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals,
                                                            LocalTime startTime,
                                                            LocalTime endTime,
                                                            int caloriesPerDay) {
        Map<LocalDate, Integer> userPerDayCaloriesMap = new HashMap<>();
//        for (UserMeal meal : meals) {
//            userPerDayCaloriesMap.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
//        }

        meals.forEach(meal -> userPerDayCaloriesMap.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum));

        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();

//        for (UserMeal meal : meals) {
//            if (isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
//                userMealWithExcesses.add(UserMealWithExcess.fromUserMeal(meal,
//                        userPerDayCaloriesMap.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
//            }
//        }

        meals.forEach(meal -> {
            if (isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExcesses.add(UserMealWithExcess.fromUserMeal(meal,
                        userPerDayCaloriesMap.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        });

        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals,
                                                             LocalTime startTime,
                                                             LocalTime endTime,
                                                             int caloriesPerDay) {
        Map<LocalDate, Integer> userPerDayCaloriesMap = meals.stream()
                .collect(Collectors.groupingBy(u -> u.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));
        return meals.stream()
                .filter(userMeal -> isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> UserMealWithExcess.fromUserMeal(userMeal,
                        userPerDayCaloriesMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
