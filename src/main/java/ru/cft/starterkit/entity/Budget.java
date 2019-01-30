package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Budget {

    @JsonProperty
    private List<Category> categoryList;
    private long budget;
    private long consumption;
    private final AtomicLong idCounter;


    public Budget(long budget)
    {
        this.budget = budget;
        consumption = 0L;
        idCounter = new AtomicLong();
    }
    public long getBudget()
    {
        return budget;
    }

    public void setBudget(long budget)
    {
        if(budget <=0)
        {
            throw new IllegalArgumentException("Бюджет меньше или равен 0");
        }
        this.budget = budget;
    }

    public long getConsumption()
    {
        return consumption;
    }

    public void setConsumption(long consumption)
    {
        if(consumption > budget)
        {
            throw new IllegalArgumentException("Расход больше бюджета");
        }
        this.consumption = consumption;
    }

    //Возвращает категорию по id
    public Category getCategory(long id) {
        return categoryList.get((int)id - 1);
    }

    //Добавить категорию
    public void addCategory(Category category) {

        this.consumption += category.getBudget();
        if(categoryList==null)
        {
            categoryList = Arrays.asList(category);
            return;
        }
        categoryList.add(category);
    }

    //Возвращает id созданной категории
    public long addCategory(String name, int budget)
    {
        this.consumption += budget;
        if(categoryList==null)
        {
            categoryList = Arrays.asList(new Category(name, budget, idCounter.incrementAndGet()));
            return idCounter.get();
        }
        categoryList.add(new Category(name, budget, idCounter.incrementAndGet()));
        return idCounter.get();
    }
}
