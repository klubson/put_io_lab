package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class ExpenseManagerTest {
    private static ArrayList<Expense> expenses;
    @BeforeAll
    static void setUP() {
        expenses = new ArrayList<>();
        Expense expense = new Expense();
        expense.setAmount(3);
        IntStream.range(0, 3).forEach(i -> expenses.add(expense));
    }
    @Test
    void calculateTotalTest() {
        IExpenseRepository expenseRepositoryMocked = mock(IExpenseRepository.class);
        when(expenseRepositoryMocked.getExpenses()).thenReturn(expenses);
        FancyService fancyServiceMoced = mock(FancyService.class);
        ExpenseManager expenseManager = new ExpenseManager(expenseRepositoryMocked, fancyServiceMoced);
        assertEquals(9, expenseManager.calculateTotal());
    }
    @Test
    void calculateTotalForCategoryTest() {
        IExpenseRepository expenseRepositoryMocked = mock(IExpenseRepository.class);
        when(expenseRepositoryMocked.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(expenseRepositoryMocked.getExpensesByCategory("Home")).thenReturn(expenses);
        when(expenseRepositoryMocked.getExpensesByCategory("Car")).thenReturn(expenses);
        FancyService fancyServiceMocked = mock(FancyService.class);
        ExpenseManager expenseManager = new ExpenseManager(expenseRepositoryMocked, fancyServiceMocked);
        assertEquals(0, expenseManager.calculateTotalForCategory("Sport"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Food"));
        assertEquals(9, expenseManager.calculateTotalForCategory("Car"));
        assertEquals(9, expenseManager.calculateTotalForCategory("Home"));
        //Pytanie 5.1: Kolejność oczekiwań ma znaczenie, ponieważ ze względu na to, iż są one sprawdzane po kolei, na początku trzeba
        // spełnić oczekiwania bardziej ogólne, a dopiero potem te bardziej szczegółowe
    }
    @Test
    void calculateTotalInDollarsTest() throws ConnectException {
        IExpenseRepository expenseRepositoryMocked = mock(IExpenseRepository.class);
        when(expenseRepositoryMocked.getExpenses()).thenReturn(expenses);
        FancyService fancyServiceMocked = mock(FancyService.class);
        ExpenseManager expenseManager = new ExpenseManager(expenseRepositoryMocked, fancyServiceMocked);
        //Exception throw checking
        //        when(fancyServiceMocked.convert(anyDouble(),eq("PLN"),eq("USD"))).thenThrow(new ConnectException());
        when(fancyServiceMocked.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                return (Double) args[0] / 4;
            }
        });
        expenseManager.calculateTotalInDollars();
        assertEquals(2, expenseManager.calculateTotalInDollars());
    }
}
