import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Converter converter = new Converter();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();

        String[] actions = {"+","-","*","/"};
        String[] gotActions = {"\\+","-","\\*","/"};

        //Определяем действие
        int actionIndex = -1;
        for (int i = 0 ; i < actions.length ; i++) {

            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }

        }

        //Если не нашли знак выходим из программы:
        if (actionIndex==-1 ){
            System.out.println("Некорректное выражение");
            return;
        }
        //Разделяем выражение
        String[] data = input.split(gotActions[actionIndex]);

        //Проверяем на кол-во арифметических знаков
        if (data.length > 2){
            System.out.println("Больше одного арифметическго действия");
            return;
        }

        //Сравниваем формат выражений
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

                if (a == 0 || b == 0){
                    System.out.println("В римском формате нет таких ");
                    return;
                }
            }else{
                //Значение арабских конвертируем в int
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);

            }

            //Выполняем арифметическое действие
            int result;
            switch(actions[actionIndex]){
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }

            //Конвертируем арабские в римские
            if(isRoman) {
                //Результат от расчета римских цифр возвращаем в римском формате
                if (result <= 0){
                    System.out.println("В римском формате нет таких цифр");
                } else {
                    System.out.println(converter.intToRoman(result));
                }

            } else {
                //Проверям диапазан чисел
                if ((a > 10 || b > 10) || (a < 1 || b < 1)) {
                    System.out.println("Арабские числа должны быть в диапазоне от 1 до 10 включительно");
                } else {
                    System.out.print(result);
                }

            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }
    }
}