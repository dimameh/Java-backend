package ru.cft.starterkit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.starterkit.entity.Budget;
import ru.cft.starterkit.entity.Category;
import ru.cft.starterkit.entity.Purchase;
import ru.cft.starterkit.repository.BudgetPlanerRepository;
import ru.cft.starterkit.service.BudgetPlanerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BudgetPlanerServiceImpl implements BudgetPlanerService {

    private final BudgetPlanerRepository budgetPlanerRepository;

    @Autowired
    public BudgetPlanerServiceImpl(BudgetPlanerRepository budgetPlanerRepository) {
        this.budgetPlanerRepository = budgetPlanerRepository;
    }


    @Override
    public Budget getBudget() {
        return budgetPlanerRepository.getBudget();
    }

    @Override
    public void setBudget(long budget) {
        budgetPlanerRepository.setBudget(budget);
    }

    @Override
    public long getConsumption() {
        return budgetPlanerRepository.getConsumption();
    }

    @Override
    public void setConsumption(long consumption) {
        budgetPlanerRepository.setConsumption(consumption);
    }

    @Override
    public Category getCategory(long id) {
        return budgetPlanerRepository.getCategory(id);
    }

    @Override
    public void AddCategory(Category category) {
        budgetPlanerRepository.AddCategory(category);
    }

    @Override
    public void AddCategory(String name, int budget) {
        budgetPlanerRepository.AddCategory(name,budget);
    }

    @Override
    public void AddPurchase(long categoryId, Purchase purchase) {
        budgetPlanerRepository.AddPurchase(categoryId,purchase);
    }

    @Override
    public void AddPurchase(long categoryId, String name, String date, int cost) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date docDate = null;
        try {
            docDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        budgetPlanerRepository.AddPurchase(categoryId, name, docDate, cost);
    }
}
