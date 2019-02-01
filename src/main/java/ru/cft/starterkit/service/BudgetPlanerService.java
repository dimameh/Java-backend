package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.Budget;
import ru.cft.starterkit.entity.Category;
import ru.cft.starterkit.entity.Purchase;
import ru.cft.starterkit.exception.ObjectNotFoundException;

import java.util.Date;

public interface BudgetPlanerService {

    Budget getBudget();
    void setBudget(long budget);
    long getConsumption();
    void setConsumption(long consumption);
    Category getCategory(long id);
    void AddCategory(Category category);
    void AddCategory(String name, int budget);
    void AddPurchase(long categoryId, Purchase purchase);
    void AddPurchase(long categoryId, String name, String date, int cost);
    void RemovePurchase(long categoryId, long id);
    void RemoveCategory(long id);


}
