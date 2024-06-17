package box.Controller;

import box.Model.Model;
import box.View.View;

public class Controller
{
    public static void main(String[] args)
    {
        Model pn = new Model();
        String text = "5e+(4^2)!*(2l+2^2)";
        System.out.println(Model.infixToPostfix(text));
        String x = Model.infixToPostfix(text);
        View.print(text, pn.Math(x));
    }
}
