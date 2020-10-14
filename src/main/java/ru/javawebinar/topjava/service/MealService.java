package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        Meal result = repository.save(meal, userId);
        if (result != null) return result;
        else throw new NotFoundException("Not such user!");
    }

    public void delete(int mealId, int userId) {
        if (!repository.delete(mealId, userId))
            throw new NotFoundException("Not such user or meal!");
    }

    public Meal get(int mealId, int userId) {
        Meal result = repository.get(mealId, userId);
        if (result != null) return result;
        else throw new NotFoundException("Not such user or meal!");
    }

    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Meal meal, int userId) {
        Meal rezult = checkNotFoundWithId(repository.save(meal, userId), meal.getId());
        if (rezult == null) throw new NotFoundException("Not such user or meal!");
    }
}