package com.samson.expensetrackerapi.controller;


import com.samson.expensetrackerapi.entity.Expense;
import com.samson.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable pageable){
        return expenseService.getAllExpenses(pageable).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable(name = "id") Long id){
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void getExpenseByParamId(@RequestParam(name = "id") Long id){
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetail(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetail(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetail(@PathVariable(name = "id") Long id, @RequestBody Expense expense){
        return expenseService.updateExpenseDetail(id, expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense>getExpenseByCategory(@RequestParam String category, Pageable pageable){
        return expenseService.readByCategory(category, pageable);
    }


    @GetMapping("/expenses/name")
    public List<Expense>getAllExpensesByName(@RequestParam String keyword, Pageable pageable){
        return expenseService.readByName(keyword, pageable);
    }

    @GetMapping("expenses/date")
    public List<Expense>getAllExpensesByDate(@RequestParam(required = false) Date startDate,
                                             @RequestParam(required = false) Date endDate,
                                             Pageable pageable){
        return expenseService.readByDate(startDate, endDate, pageable);
    }
}
