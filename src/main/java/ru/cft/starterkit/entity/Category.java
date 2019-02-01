package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Category {

    private long id;

    private String name;

    private int budget;

    private int consumption = 0;

    private List<Purchase> purchaseList = new ArrayList();

    private final AtomicLong idCounter = new AtomicLong();

    @JsonCreator
    public Category(@JsonProperty("name") String name, @JsonProperty("budget") int budget, @JsonProperty("id") long id) {
        setName(name);
        setBudget(budget);
        setID(id);
    }

    private long setID(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public void AddPurchase(Purchase purchase){
        purchaseList.add(purchase);
        consumption += purchase.getCost();
    }

    public void AddPurchase(String name, Date date, int cost){
        purchaseList.add(new Purchase(name, date, cost, idCounter.incrementAndGet()));
        consumption += cost;
    }

    public void RemovePurchase(long id)
    {
        purchaseList.remove(id);
    }
}
