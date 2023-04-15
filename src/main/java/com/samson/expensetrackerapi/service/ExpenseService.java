package com.samson.expensetrackerapi.service;

import com.samson.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable pageable);
    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpenseDetail(Expense expense);

    Expense updateExpenseDetail(Long id, Expense expense);

    List<Expense> readByCategory(String category, Pageable pageable);
    List<Expense> readByName(String keyword, Pageable pageable);
    List<Expense> readByDate(Date startDate, Date endDate, Pageable pageable);
}
