package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Purchase {

    private long id;

    private String name;

    private Date date;

    private int cost;

    public Purchase(String name, Date date, int cost) {
        setName(name);
        setDate(date);
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase entity = (Purchase) o;
        return Objects.equals(id, entity.id) &&
               Objects.equals(name, entity.name) &&
               Objects.equals(date, entity.date) &&
               Objects.equals(cost, entity.cost);
    }
}
