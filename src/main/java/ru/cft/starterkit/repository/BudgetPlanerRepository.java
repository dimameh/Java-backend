package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Budget;
import ru.cft.starterkit.entity.Category;
import ru.cft.starterkit.entity.Purchase;

import java.util.Date;

public interface BudgetPlanerRepository {

    Budget getBudget();
    void setBudget(long budget);
    long getConsumption();
    void setConsumption(long consumption);
    Category getCategory(long id);
    void AddCategory(Category category);
    void AddCategory(String name, int budget);
    void AddPurchase(long categoryId, Purchase purchase);
    void AddPurchase(long categoryId, String name, Date date, int cost);
}
