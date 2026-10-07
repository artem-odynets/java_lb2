package dut.pd3.practics.practic_2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionAnalyzerTest {

    @Test
    void testCalculateTotalBalance() {
        Transaction transaction1 =
                new Transaction("01-01-2023", 100.0, "Дохід");

        Transaction transaction2 =
                new Transaction("02-01-2023", -50.0, "Витрата");

        Transaction transaction3 =
                new Transaction("03-01-2023", 150.0, "Дохід");

        List<Transaction> transactions =
                Arrays.asList(transaction1, transaction2, transaction3);

        double result =
                TransactionAnalyzer.calculateTotalBalance(transactions);

        assertEquals(
                200.0,
                result,
                0.001,
                "Розрахунок загального балансу неправильний"
        );
    }

    @Test
    void testCountTransactionsByMonth() {
        Transaction transaction1 =
                new Transaction("01-02-2023", 50.0, "Дохід");

        Transaction transaction2 =
                new Transaction("15-02-2023", -20.0, "Витрата");

        Transaction transaction3 =
                new Transaction("05-03-2023", 100.0, "Дохід");

        List<Transaction> transactions =
                Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb =
                TransactionAnalyzer.countTransactionsByMonth(
                        transactions,
                        "02-2023"
                );

        int countMar =
                TransactionAnalyzer.countTransactionsByMonth(
                        transactions,
                        "03-2023"
                );

        assertEquals(2, countFeb);
        assertEquals(1, countMar);
    }

    @Test
    void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-01-2023", -500.0, "Їжа"),
                new Transaction("02-01-2023", -1500.0, "Оренда"),
                new Transaction("03-01-2023", 2000.0, "Зарплата"),
                new Transaction("04-01-2023", -300.0, "Транспорт")
        );

        List<Transaction> result =
                TransactionAnalyzer.findTopExpenses(transactions);

        assertEquals(3, result.size());
        assertEquals(-1500.0, result.get(0).getAmount());
        assertEquals(-500.0, result.get(1).getAmount());
        assertEquals(-300.0, result.get(2).getAmount());
    }
}
