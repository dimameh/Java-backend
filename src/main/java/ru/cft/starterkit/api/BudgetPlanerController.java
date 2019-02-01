package ru.cft.starterkit.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.Budget;
import ru.cft.starterkit.entity.Category;
import ru.cft.starterkit.entity.Purchase;
import ru.cft.starterkit.service.BudgetPlanerService;

import java.util.Date;

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
            consumes = "application/x-www-form-urlencoded"
    )
    public void addCategory(@RequestParam(name = "name") String name, @RequestParam(name = "budget") int budget)
    {
        budgetPlanerService.AddCategory(name, budget);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/budgets",
            consumes = "application/x-www-form-urlencoded"
    )
    public void setBudget(@RequestParam(name = "budget") long budget)
    {
        budgetPlanerService.setBudget(budget);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/categories/{categoryId}/purchases",
            consumes = "application/json"
    )
    public void addPurchase(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "cost") int cost,
            @PathVariable(name = "categoryId") long categoryId
    )
    {
        budgetPlanerService.AddPurchase(categoryId, name, date, cost);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/budgets",
            produces = "application/json"
    )
    public Budget getBudget() {
        try {
            return budgetPlanerService.getBudget();
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/categories/{id}",
            produces = "application/json"
    )
    public Category getCategory(@PathVariable(name = "id") long id) {
        return budgetPlanerService.getCategory(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/categories/{id}",
            produces = "application/json"
    )
    public void removeCategory(@PathVariable(name = "id") long id) {
       budgetPlanerService.RemoveCategory(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/categories/{categoryId}/purchases",
            consumes = "application/json"
    )
    public void removePurchase(
    ){
    }

}
