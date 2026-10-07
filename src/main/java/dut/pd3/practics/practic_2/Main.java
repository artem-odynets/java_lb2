package dut.pd3.practics.practic_2;

import java.util.List;
import java.util.Map;

public class Main {

    private static final String FILE_PATH = "pr2.csv";
    public static void main(String[] args) {

        System.out.println("===== ФІНАНСОВИЙ АНАЛІЗАТОР =====\n");

        List<Transaction> transactions =
                TransactionCSVReader.readTransactions(FILE_PATH);

        if (transactions.isEmpty()) {
            System.out.println("Транзакції не завантажено.");
            System.out.println(
                    "Перевірте підключення до Інтернету або адресу CSV-файлу."
            );
            return;
        }

        System.out.println("Кількість завантажених транзакцій: "
                + transactions.size());

        double totalBalance =
                TransactionAnalyzer.calculateTotalBalance(transactions);

        TransactionReportGenerator.printBalanceReport(totalBalance);

        // Місяць можна змінити відповідно до даних CSV.
        String monthYear = "01-2024";

        int transactionsCount =
                TransactionAnalyzer.countTransactionsByMonth(
                        transactions,
                        monthYear
                );

        TransactionReportGenerator.printTransactionsCountByMonth(
                monthYear,
                transactionsCount
        );

        List<Transaction> topExpenses =
                TransactionAnalyzer.findTopExpenses(transactions);

        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // Самостійна робота.
        List<Transaction> periodExpenses =
                TransactionAnalyzer.findExpensesByPeriod(
                        transactions,
                        monthYear
                );

        Transaction largest =
                TransactionAnalyzer.findLargestExpense(periodExpenses);

        Transaction smallest =
                TransactionAnalyzer.findSmallestExpense(periodExpenses);

        TransactionReportGenerator.printPeriodExpensesReport(
                monthYear,
                largest,
                smallest
        );

        Map<String, Double> expensesByCategory =
                TransactionAnalyzer.calculateExpensesByCategory(transactions);

        TransactionReportGenerator.printCategoryReport(
                expensesByCategory
        );

        Map<String, Double> expensesByMonth =
                TransactionAnalyzer.calculateExpensesByMonth(transactions);

        TransactionReportGenerator.printMonthReport(expensesByMonth);
    }
}
