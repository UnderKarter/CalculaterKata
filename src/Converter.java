import java.util.TreeMap;

public class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabicKeyMap = new TreeMap<>();

    public Converter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);
        romanKeyMap.put('D', 500);
        romanKeyMap.put('M', 1000);

        arabicKeyMap.put(1000, "M");
        arabicKeyMap.put(900, "CM");
        arabicKeyMap.put(500, "D");
        arabicKeyMap.put(400, "CD");
        arabicKeyMap.put(100, "C");
        arabicKeyMap.put(90, "XC");
        arabicKeyMap.put(50, "L");
        arabicKeyMap.put(40, "XL");
        arabicKeyMap.put(10, "X");
        arabicKeyMap.put(9, "IX");
        arabicKeyMap.put(8, "VIII");
        arabicKeyMap.put(7, "VII");
        arabicKeyMap.put(6, "VI");
        arabicKeyMap.put(5, "V");
        arabicKeyMap.put(4, "IV");
        arabicKeyMap.put(3, "III");
        arabicKeyMap.put(2, "II");
        arabicKeyMap.put(1, "I");
    }

    public boolean isRoman(String number){
        return romanKeyMap.containsKey(number.charAt(0));
    }

    public String intToRoman(int number){
        String roman = "";
        int arabic;
        do{
            arabic = arabicKeyMap.floorKey(number);
            roman += arabicKeyMap.get(arabic);
            number -= arabic;
        } while (number != 0);
        return roman;

    }

    public int romanToInt(String s){

        int end = s.length() - 1;
        char[] arr = s.toCharArray();

        int arabic;
        int count = 0;
        int result = romanKeyMap.get(arr[end]);

        //Проверяем, есть ли больше трех одинаковых знаков подряд
        for(int i = 1; i < arr.length; i++){
            if(arr[i - 1] == arr[i]){
                count++;
            } else {
                count = 0;
            }
            if (count >=3){
                return result = 0;
            }
        }

        //Проверяем есть ли больше двух одинаковых знаков перед большим знаком
        if (arr.length >= 3){
            int c = arr[end];
            int b = arr[end - 1];
            int a = arr[end - 2];

            if (a == b && a < c){
                return result = 0;
            }
        }

        for (int i = end - 1; i >= 0; i--) {
            arabic = romanKeyMap.get(arr[i]);

            if (arabic < romanKeyMap.get(arr[i + 1])) {
                result -= arabic;

            } else {
                result += arabic;
            }
        }
        return result;
    }
}
