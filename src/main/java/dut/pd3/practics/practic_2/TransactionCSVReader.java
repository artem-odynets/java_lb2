package dut.pd3.practics.practic_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    private TransactionCSVReader() {
        // Екземпляри цього класу не створюються.
    }

    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader reader;

            if (filePath.startsWith("http://") || filePath.startsWith("https://")) {
                reader = new BufferedReader(
                        new InputStreamReader(
                                URI.create(filePath).toURL().openStream(),
                                StandardCharsets.UTF_8
                        )
                );
            } else {
                reader = Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8);
            }

            try (reader) {
                String line;
                boolean firstLine = true;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (line.isEmpty()) {
                        continue;
                    }

                    // Якщо CSV має заголовок — пропускаємо його.
                    if (firstLine && line.toLowerCase().contains("date")) {
                        firstLine = false;
                        continue;
                    }
                    firstLine = false;

                    String[] values = line.split(",", 3);

                    if (values.length < 3) {
                        continue;
                    }

                    String date = values[0].trim();
                    double amount = Double.parseDouble(values[1].trim());
                    String description = values[2].trim();

                    transactions.add(new Transaction(date, amount, description));
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка читання CSV: " + e.getMessage());
        }

        return transactions;
    }
}
