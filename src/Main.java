import java.util.Scanner;

public class Main {
    public static int calcType (String typeOp, int op1, int op2) { //метод производит вычисление по между 2 операндами
        int ans;
        switch (typeOp) {
            case "+" :
                ans = op1 + op2;
                break;
            case "-" :
                ans = op1 - op2;
                break;
            case "/" :
                ans = op1 / op2;
                break;
            case "*" :
                ans = op1 * op2;
                break;
            default:
                ans = 10000;
        }
        return ans;
    }
    public static String calc(String input) {
        String [] task = input.split(" "); // разбиваем input на массив строк task, разделитель - пробел

        // Проверяем формат вводимого примера на количество составляющих
        if (task.length > 3) { // если больше 3х составляющих, то не соответствие заданию
            try {
                task[10] = "";
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } finally {
                System.exit(1);
            }
        }
        if (task.length < 3) { // если меньше 3х составляющих, то не может быть математической операцией
            try {
                task[10] = "";
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
            } finally {
                System.exit(1);
            }
        }

        // Проверяем математическую операцию + - * /
        String[] operations = {"+","-","/","*"}; //объявляем пул операций
        int flagOp = 10; //флаг операции

        for (int i = 0; i<4; i++) {
            if (task[1].equals(operations[i])) {
                flagOp = i; //запоминаем операцию 0 +, 1 -, 2 /, 3 *
            }
        }
        if (flagOp == 10) { //проверяем входит ли введенная строка в пул допустимых операций, если нет выбрасываем исключение
            try {
                task[flagOp] = " ";
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } finally {
                System.exit(1);
            }
        }

        // Выявляем системы счисления операндов и допустимые значения от 1 до 10
        // нужно добавить исключение на превышение допустимых значений, изначально пошел не тем путем
        String fstOpType = NumberR.contains(task[0]); // вычисление типа первого операнда, rim, arb, null
        String scndOpType = NumberR.contains(task[2]); // вычисление типа второго операнда
        if ((fstOpType == null) || (scndOpType == null)) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(1);
        }
        if (!(fstOpType.equals(scndOpType))) {
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            System.exit(1);
        }

        //Вычисление
        int solution;      //переменная ответа
        if (fstOpType.equals("rim")) {
            solution = calcType(task[1],NumberR.valueOf(task[0]).getNum(),NumberR.valueOf(task[2]).getNum()); //получение решения (римские)
        } else {
            solution = calcType(task[1],Integer.parseInt(task[0]),Integer.parseInt(task[2])); // получение решения (арабские), ошибка преобразования должна быть исключена всеми предыдущими условиями
        }
        if (fstOpType.equals("rim")) { // преобразование решения в римские
            if (solution < 1) {
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                System.exit(1);
            } else {
                String solutionRim;
                switch (solution / 10) {
                    case 10:
                        solutionRim = "C";
                        break;
                    case 9:
                        solutionRim = "XC";
                        break;
                    case 8:
                        solutionRim = "LXXX";
                        break;
                    case 7:
                        solutionRim = "LXX";
                        break;
                    case 6:
                        solutionRim = "LX";
                        break;
                    case 5:
                        solutionRim = "L";
                        break;
                    case 4:
                        solutionRim = "XL";
                        break;
                    case 3:
                        solutionRim = "XXX";
                        break;
                    case 2:
                        solutionRim = "XX";
                        break;
                    case 1:
                        solutionRim = "X";
                        break;
                    case 0:
                        solutionRim = String.valueOf(NumberR.values()[solution - 1]);
                        break;
                    default:
                        solutionRim = "";
                }
                if (((solution % 10) != 0) && (solution / 10) != 0) {
                    solutionRim = solutionRim + NumberR.values()[(solution % 10) - 1];
                }
                return solutionRim;
            }
        } else {
            return String.valueOf(solution); // преобразование решения (арабские) строку и возвращение
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner inputString = new Scanner(System.in); //создаем объект класса сканнер
        System.out.print("Input a task: ");
        String s = inputString.nextLine(); //считываем строку
        inputString.close();

        //Вызов решения
        System.out.println(calc(s));
    }

}