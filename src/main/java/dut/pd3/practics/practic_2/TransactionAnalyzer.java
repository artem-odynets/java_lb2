package dut.pd3.practics.practic_2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TransactionAnalyzer {

    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    );

    private TransactionAnalyzer() {
        // Екземпляри цього класу не створюються.
    }

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }

    public static int countTransactionsByMonth(
            List<Transaction> transactions,
            String monthYear
    ) {
        int count = 0;

        for (Transaction transaction : transactions) {
            LocalDate date = parseDate(transaction.getDate());

            if (date != null) {
                String transactionMonthYear =
                        date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

                if (transactionMonthYear.equals(monthYear)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(10)
                .toList();
    }

    public static List<Transaction> findExpensesByPeriod(
            List<Transaction> transactions,
            String monthYear
    ) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {
            LocalDate date = parseDate(transaction.getDate());

            if (date != null) {
                String currentMonthYear =
                        date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

                if (currentMonthYear.equals(monthYear)
                        && transaction.getAmount() < 0) {
                    result.add(transaction);
                }
            }
        }

        return result;
    }

    public static Transaction findLargestExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .min(Comparator.comparingDouble(Transaction::getAmount))
                .orElse(null);
    }

    public static Transaction findSmallestExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .max(Comparator.comparingDouble(Transaction::getAmount))
                .orElse(null);
    }

    public static Map<String, Double> calculateExpensesByCategory(
            List<Transaction> transactions
    ) {
        Map<String, Double> result = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() >= 0) {
                continue;
            }

            String category = transaction.getDescription();
            double expense = Math.abs(transaction.getAmount());

            result.put(category, result.getOrDefault(category, 0.0) + expense);
        }

        return result;
    }

    public static Map<String, Double> calculateExpensesByMonth(
            List<Transaction> transactions
    ) {
        Map<String, Double> result = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() >= 0) {
                continue;
            }

            LocalDate date = parseDate(transaction.getDate());

            if (date != null) {
                String monthYear =
                        date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

                double expense = Math.abs(transaction.getAmount());

                result.put(
                        monthYear,
                        result.getOrDefault(monthYear, 0.0) + expense
                );
            }
        }

        return result;
    }

    private static LocalDate parseDate(String value) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(value, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }

        return null;
    }
}
