package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.impl.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.helpers.RedirectHelper.localRedirect;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealService service = new MealServiceImpl(new InMemoryMealRepository());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");
        String act = request.getParameter("action");
        if (act != null) {
            if (act.equals("delete")) {
                service.deleteById(getId(request));
            }
            if (act.equals("update")) {
                Meal meal = service.getById(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/updateMeal.jsp").forward(request, response);
            }
        }
        List<MealTo> mealTos = filteredByStreams(service.list(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("meals", mealTos);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("save");
        request.setCharacterEncoding("UTF-8");
        String datetime = request.getParameter("dateTime");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME);
        int cal = 0;
        try {
            cal = Integer.parseInt(calories);
        } catch (Exception ignore) {}
        Meal meal = new Meal(localDateTime, description, cal);
        try {
            meal.setId(getId(request));
        } catch (Exception ignore) {}
        service.add(meal);
        localRedirect(request, response, "/meals");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
