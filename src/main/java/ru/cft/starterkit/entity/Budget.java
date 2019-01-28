package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.UUID;

public class Budget {

    private List<Category> categoryList;

    private Long budget;
    private Long consumption;


    public Budget(Long budget)
    {
        setBudget(budget);
        setConsumption(0L);
    }

    public Long getBudget()
    {
        return budget;
    }

    public Long getConsumption() {
        return consumption;
    }

    public Long setConsumption(Long consumption)
    {
        if(consumption > budget)
        {throw new IllegalArgumentException("Расход больше бюджета");}
        this.consumption = consumption;
    }

    public Long setBudget(Long budget)
    {
        if(budget < 0)
        {throw new IllegalArgumentException("Отрицательное значение бюджета");}
        this.budget = budget;

    }
}
