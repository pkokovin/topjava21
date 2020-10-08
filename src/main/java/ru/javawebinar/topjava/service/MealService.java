package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    List<Meal> list();
    Meal getById(int id);
    Meal add(Meal meal);
    void deleteById(int id);
}
