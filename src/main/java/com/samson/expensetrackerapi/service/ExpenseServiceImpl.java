package com.samson.expensetrackerapi.service;

import com.samson.expensetrackerapi.entity.Expense;
import com.samson.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.samson.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable pageable){
        return expenseRepository.findAll(pageable);
    }

    @Override
    public Expense getExpenseById(Long id) {
       Optional<Expense> expense = expenseRepository.findById(id);
       if (expense.isPresent()){
           return expense.get();
       }else {
           throw new ResourceNotFoundException("Expense is not found for the ID " + id);
       }
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense saveExpenseDetail(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetail(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable pageable) {
        return expenseRepository.findByCategory(category, pageable).toList();
    }

    @Override
    public List<Expense> readByName(String name, Pageable pageable) {
        return expenseRepository.findByNameContaining(name, pageable).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable pageable) {
        if (startDate == null){
            startDate = new Date(0);
        }
        if (endDate == null){
            endDate = new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByDateBetween(startDate, endDate, pageable).toList();
    }
}
