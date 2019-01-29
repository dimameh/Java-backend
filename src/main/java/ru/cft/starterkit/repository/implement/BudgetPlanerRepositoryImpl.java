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
        return budget;
    }

    @Override
    public void setBudget(long budget)
    {
        if(budget <=0)
        {
            throw new IllegalArgumentException("Бюджет меньше или равен 0");
        }
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
    }

    @Override
    //Возвращает категорию по id
    public Category getCategory(long id) {
        log.info("Return of category with id: {}", id);
        return budget.getCategory(id);
    }

    @Override
    //Добавить категорию
    public void AddCategory(Category category) {
        log.info("Added new category: {}", category.getName());
        budget.addCategory(category);
        budget.setConsumption(budget.getConsumption() + category.getBudget());
    }

    @Override
    public void AddCategory(String name, int budget)
    {
        log.info("Added new category: {}", name);
        this.budget.addCategory(name, budget);
        this.budget.setConsumption( this.budget.getConsumption() + budget);
}

    @Override
    public void AddPurchase(long categoryId, Purchase purchase) {
        log.info("Added new purchase: {1} at the category №: {2}", purchase, categoryId);
        budget.getCategory(categoryId).AddPurchase(purchase);
    }

    @Override
    public void AddPurchase(long categoryId, String name, Date date, int cost) {
        log.info("Added new purchase: {1} at the category №: {2}", name, categoryId);
        budget.getCategory(categoryId).AddPurchase(name, date, cost);
    }
}
