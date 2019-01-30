package ru.cft.starterkit.repository.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.Budget;
import ru.cft.starterkit.entity.Category;
import ru.cft.starterkit.entity.Purchase;
import ru.cft.starterkit.repository.BudgetPlanerRepository;

import java.util.Date;

@Repository
public class BudgetPlanerRepositoryImpl implements BudgetPlanerRepository {

    private static final Logger log = LoggerFactory.getLogger(BudgetPlanerRepositoryImpl.class);

    private Budget budget;

    @Override
    public Budget getBudget()
    {
        if(budget == null)
        {
            throw new NullPointerException("Бюджет не инициализирован");
        }
        log.info("Budget info has been send");
        return budget;
    }

    @Override
    public void setBudget(long budget)
    {
        if(budget <=0)
        {
            throw new IllegalArgumentException("Бюджет меньше или равен 0");
        }
        log.info("Budget was set on: {}", budget);
        this.budget = new Budget(budget);
    }

    @Override
    public long getConsumption()
    {

        return budget.getConsumption();
    }

    @Override
    public void setConsumption(long consumption)
    {
        if(consumption > budget.getConsumption())
        {
            throw new IllegalArgumentException("Расход больше бюджета");
        }
        budget.setConsumption(consumption);
        log.info("Consumption was set on: {}", consumption);
    }

    @Override
    //Возвращает категорию по id
    public Category getCategory(long id) {
        log.info("Return of category with id: {}", id);
        return budget.getCategory(id);
    }

//    @Override
    //Добавить категорию
//    public void AddCategory(Category category) {
//        log.info("Added new category: {}", category.getName());
//        budget.addCategory(category);
//        budget.setConsumption(budget.getConsumption() + category.getBudget());
//    }

    @Override
    public void AddCategory(String name, int budget)
    {
        this.budget.setConsumption( this.budget.getConsumption() + budget);
        log.info("Added new category: {}, id: {}", name , this.budget.addCategory(name, budget));
}

    @Override
    public void AddPurchase(long categoryId, Purchase purchase) {
        budget.getCategory(categoryId).AddPurchase(purchase);
        log.info("Added new purchase: {} at the category №: {}", purchase.getName(), categoryId);
    }

    @Override
    public void AddPurchase(long categoryId, String name, Date date, int cost) {
        budget.getCategory(categoryId).AddPurchase(name, date, cost);
        log.info("Added new purchase: {} at the category №: {}", name, categoryId);
    }
}
