package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepository implements MealRepository {
    //    <userId, Map<mealId, meal>
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.userMeals.forEach(meal -> save(meal, 1));
        MealsUtil.adminMeals.forEach(meal -> save(meal, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.putIfAbsent(userId, new HashMap<>());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        Meal old = repository.get(userId) != null
                ? repository.get(userId).get(meal.getId())
                : null;

        return old != null
                ? repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal) : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId) != null && repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId) != null ? repository.get(userId).get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> rezult = new ArrayList<>(repository.get(userId).values());
        rezult.sort(new Comparator<Meal>() {
            @Override
            public int compare(Meal o1, Meal o2) {
                return o2.getDate().compareTo(o2.getDate());
            }
        });
        return repository.get(userId) != null ?  rezult : Collections.EMPTY_LIST;
    }
}

