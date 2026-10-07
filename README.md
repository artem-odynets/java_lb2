# Практична робота №2 — Фінансовий аналізатор

Студент: Одинець Артем Віталійович  
Група: ПД-33

## Що використано

- Java 17
- Maven
- Lombok
- JUnit 5
- Java Streams
- CSV
- ООП

## Запуск у VS Code

1. Відкрити папку `practical-2-finance-analyzer`.
2. Відкрити термінал у корені проєкту.
3. Перевірити Java:

```bash
java -version
```

4. Перевірити Maven:

```bash
mvn -version
```

5. Запустити тести:

```bash
mvn test
```

6. Зібрати проєкт:

```bash
mvn clean package
```

## Запуск Main

У VS Code відкрий:

`src/main/java/dut/pd3/practics/practic_2/Main.java`

та натисни Run.

Або після встановлення Maven можна використовувати:

```bash
mvn compile
```

## Важливо

У презентації початкові класи створюються через `new TransactionCSVReader()`,
`new TransactionAnalyzer(transactions)` та `new TransactionReportGenerator()`.

За умовою практичної роботи ці три класи переписані як `abstract`,
а їхні робочі методи зроблені `static`, тому додаткові екземпляри цих класів
не створюються.

URL CSV-файлу взято з презентації:

https://informer.com.ua/dut/java/pr2.csv
