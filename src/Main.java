import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScannerException {

        Converter converter = new Converter();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();

        String[] actions = {"+", "-", "*", "/"};
        String[] gotActions = {"\\+", "-", "\\*", "/"};

        //Определяем действие
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        //Если не нашли знак выходим из программы:
        if (actionIndex == -1) {
            throw new ScannerException("Отсутствуют арифметические знаки");
        }
        //Разделяем выражение
        String[] data = input.split(gotActions[actionIndex]);

        //Проверяем на кол-во арифметических знаков
        if (data.length > 2) {
            throw new ScannerException("Слишком много арифметических операций");
        }
        //Сравниваем формат выражений
        try {
            if (converter.isRoman(data[0]) != converter.isRoman(data[1])) {
                throw new ScannerException("Формат значений не должен различаться");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Отсутствует значение");
        }

        //Инициализация
        int a, b;
        try {
            //Значение арабских конвертируем в int
            a = Integer.parseInt(data[0]);
            b = Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            //если римские, то конвертируем их в арабские
            a = converter.romanToInt(data[0]);
            b = converter.romanToInt(data[1]);
        }

        //Выполняем арифметическое действие
        int result;
        switch (actions[actionIndex]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            default:
                result = a / b;
                break;
        }

        //Определяем формат
        boolean isRoman = converter.isRoman(data[0]);
        if (isRoman) {
            // Проверка формата римских цифр
            try {
                System.out.println(converter.intToRoman(result));
            } catch (NullPointerException e) {
                System.err.println("В римском формате нет таких цифр");
            }
        } else {
            //Проыерка диапазона арабских цифр
            if ((a > 10 || b > 10) || (a < 1 || b < 1)) {
                    throw new ScannerException("Арабские цифры используются в диапазоне от 1 до 10");
            }
            System.out.print(result);
        }
    }
}