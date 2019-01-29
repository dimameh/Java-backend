package ru.cft.starterkit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.Budget;
import ru.cft.starterkit.entity.Category;
import ru.cft.starterkit.entity.Purchase;
import ru.cft.starterkit.service.BudgetPlanerService;

@RestController
public class BudgetPlanerController {

    private final BudgetPlanerService budgetPlanerService;

    @Autowired
    public BudgetPlanerController(BudgetPlanerService BudgetPlanerService) {
        this.budgetPlanerService = BudgetPlanerService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/categories",
            consumes = "application/json"
    )
    public void addCategory(@RequestBody Category category)
    {
        budgetPlanerService.AddCategory(category);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/budgets",
            consumes = "application/json"
    )
    public void setBudget(@RequestBody long budget)
    {
        budgetPlanerService.setBudget(budget);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/categories/{id}/purchases",
            consumes = "application/json"
    )
    public void addPurchase(@RequestBody Purchase purchase, @RequestBody long categoryId)
    {
        budgetPlanerService.AddPurchase(categoryId, purchase);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/budgets",
            produces = "application/json"
    )
    public Budget getBudget() {
        return budgetPlanerService.getBudget();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/categories/{id}",
            produces = "application/json"
    )
    public Category getCategory(@PathVariable(name = "id") long id) {
        return budgetPlanerService.getCategory(id);
    }
}
