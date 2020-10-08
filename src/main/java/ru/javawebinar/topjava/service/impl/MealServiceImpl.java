package ru.javawebinar.topjava.service.impl;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

public class MealServiceImpl implements MealService {
    private final InMemoryMealRepository repository;

    public MealServiceImpl(InMemoryMealRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Meal> list() {
        return repository.getAll();
    }

    @Override
    public Meal getById(int id) {
        return repository.byId(id);
    }

    @Override
    public Meal add(Meal meal) {
        return repository.add(meal);
    }

    @Override
    public void deleteById(int id) {
        repository.delete(id);
    }
}
