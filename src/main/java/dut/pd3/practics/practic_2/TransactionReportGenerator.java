package dut.pd3.practics.practic_2;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    private TransactionReportGenerator() {
        // Екземпляри цього класу не створюються.
    }

    public static void printBalanceReport(double totalBalance) {
        System.out.printf("Загальний баланс: %.2f%n", totalBalance);
    }

    public static void printTransactionsCountByMonth(
            String monthYear,
            int count
    ) {
        System.out.println(
                "Кількість транзакцій за " + monthYear + ": " + count
        );
    }

    public static void printTopExpensesReport(
            List<Transaction> topExpenses
    ) {
        System.out.println("\n10 найбільших витрат:");

        if (topExpenses.isEmpty()) {
            System.out.println("Витрати не знайдено.");
            return;
        }

        int number = 1;

        for (Transaction expense : topExpenses) {
            System.out.printf(
                    "%d. %s: %.2f%n",
                    number++,
                    expense.getDescription(),
                    expense.getAmount()
            );
        }
    }

    public static void printPeriodExpensesReport(
            String monthYear,
            Transaction largest,
            Transaction smallest
    ) {
        System.out.println("\nВитрати за період " + monthYear + ":");

        if (largest == null || smallest == null) {
            System.out.println("Витрати за цей період не знайдено.");
            return;
        }

        System.out.printf(
                "Найбільша витрата: %s — %.2f%n",
                largest.getDescription(),
                largest.getAmount()
        );

        System.out.printf(
                "Найменша витрата: %s — %.2f%n",
                smallest.getDescription(),
                smallest.getAmount()
        );
    }

    public static void printCategoryReport(
            Map<String, Double> expensesByCategory
    ) {
        System.out.println("\nСумарні витрати за категоріями:");

        if (expensesByCategory.isEmpty()) {
            System.out.println("Дані відсутні.");
            return;
        }

        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            System.out.printf(
                    "%s: %.2f грн%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }

    public static void printMonthReport(
            Map<String, Double> expensesByMonth
    ) {
        System.out.println("\nСумарні витрати за місяцями:");

        if (expensesByMonth.isEmpty()) {
            System.out.println("Дані відсутні.");
            return;
        }

        for (Map.Entry<String, Double> entry : expensesByMonth.entrySet()) {
            int stars = (int) (entry.getValue() / 1000);

            if (stars == 0) {
                stars = 1;
            }

            System.out.printf(
                    "%s: %.2f грн %s%n",
                    entry.getKey(),
                    entry.getValue(),
                    "*".repeat(stars)
            );
        }
    }
}
