package box.Model;
import java.util.Objects;
import java.util.Stack;
/*put the logic here
    * math methods*/
public class Model
{
    public double Math(String equation)
    {
        String[] str = equation.split(" ");
        Stack<Double> box = new Stack<Double>();

        for(int i = 0; i < str.length; i++) {
            if(isNum(str[i])) {
                box.push(Double.parseDouble(str[i]));
            } else {
                switch (str[i]) {
                    case "+":
                        box.push(box.pop()+box.pop());
                        break;
                    case "-":
                        box.push(-box.pop()+box.pop());
                        break;
                    case "*":
                        box.push(box.pop()*box.pop());
                        break;
                    case "/":
                        double temp = box.pop();
                        //box.push(box.pop()/(1/box.pop()));
                        box.push(box.pop()/temp);
                        break;
                    case "!": //Факториал
                        double fact = 1;
                        for(int j = 2; j <= box.peek(); j++) fact*=j;
                        box.pop();
                        box.push(fact);
                        break;
                    case "l": //Логарифм по основанию 2
                        box.push(Math.log(box.pop())/Math.log(2));
                        break;
                    case "e": //Экспонента
                        box.push(Math.exp(box.pop()));
                        break;
                    case "^" : //Возведение в степень
                        double temp1 = box.pop();
                        box.push(Math.pow(box.pop(), temp1));
                        break;
                }
            }
        }
        return box.pop();
    }
    private boolean isNum(String str)
    {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {

            if (Character.isDigit(ch)) {
                result.append(ch).append(" ");
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop(); // Удаляем открывающую скобку из стека
            } else if (ch == 'e') {

            }

            else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }


        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        return result.toString().trim();
    }

    private static int precedence(char op) {
        switch (op){
            case '+': case '-':
                return 1;
            case '*': case '/':  //Умножение и деление
                return 2;
            case '^':
                return 3;
            default: return 0;
        }
    }
}
