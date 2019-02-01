package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Budget {

    @JsonProperty
    private List<String> categoryListString = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private long budget;
    private long consumption;
    private final AtomicLong idCounter;


    public int getCategorySize(){
        return categoryList.size();
    }

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
        if(categoryList.size() < 1) {
            return null;
        }
        return categoryList.get((int)id-1);
    }

    //Добавить категорию
    public void addCategory(Category category) {

        this.consumption += category.getBudget();
        if(categoryList==null)
        {
            categoryList = Arrays.asList(category);
            return;
        }
        category.setId((int)idCounter.incrementAndGet());

        categoryListString.add(category.getName());
        categoryList.add(category);
    }

    //Возвращает id созданной категории
    public long addCategory(String name, int budget)
    {
        this.consumption += budget;
        if(categoryList==null)
        {
            Category category = new Category(name, budget);
            category.setId((int)idCounter.incrementAndGet());
            categoryList = Arrays.asList(category);
            return idCounter.get();
        }
        Category category = new Category(name, budget);
        category.setId((int)idCounter.incrementAndGet());

        categoryListString.add(category.getName());
        categoryList.add(category);
        return idCounter.get();
    }

    public void removeCategory(long id){
        if(categoryList.size()<=0)
        {
            return;
        }
        categoryList.remove((int)id);
        categoryListString.remove((int)id);
    }
}
