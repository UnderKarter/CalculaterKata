import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScannerException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String text = scanner.nextLine();
        calc(text);
    }

    public static String calc(String input) throws ScannerException {

        Converter converter = new Converter();
        String textResult = "Результат";
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
        int a = 0;
        int b = 0;
        boolean isRoman = false;
        try {
            //Значение арабских конвертируем в int
            a = Integer.parseInt(data[0]);
            b = Integer.parseInt(data[1]);
            isRoman = false;
        } catch (NumberFormatException e) {
            //если римские, то конвертируем их в арабские
            try {
                a = converter.romanToInt(data[0]);
            } catch (NullPointerException exception) {
                System.err.println("Первое значение не целочисленное");
            }
            try {
                b = converter.romanToInt(data[1]);
                isRoman = true;
            } catch (NullPointerException exception) {
                System.err.println("Второе значение не целочисленное");
            }
        } catch (ArrayIndexOutOfBoundsException e){}

        //Проыерка диапазона цифр
        if ((a > 10 || b > 10) || (a < 1 || b < 1)) {
            if (isRoman) {
                throw new ScannerException("Цифры должны быть в диапазоне от 1 до 10 включительно");
            } else {
                throw new ScannerException("Цифры должны быть в диапазоне от I до X включительно");
            }
        }

        //Выполняем арифметическое действие
        int result = 0;
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

        if (isRoman) {
            // Проверка формата римских цифр
            try {
                System.out.println(converter.intToRoman(result));
                textResult = converter.intToRoman(result);
            } catch (NullPointerException e) {
                System.err.println("В римском формате нет таких цифр");
            }
        } else {
            System.out.print(result);
            textResult = String.valueOf(result);
        }
        return textResult;
    }
}