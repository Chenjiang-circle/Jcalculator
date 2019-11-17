package github.chenjiangchao.Core;

import org.junit.Test;


public class CalculateTest {

    @Test
    public void calculatePostfixNotation() {
//        String[] strList = {"1.2", "1.9", "6.1", "3.1", "/", "+", "1.5", "-", "*", "1.4", "+"};
//        String[] strList = {"1.2"};
//        double v = Calculate.calculatePostfixNotation(strList);
//        System.out.println(v);
        System.out.println(1/0);
    }

    @Test
    public void advancedCalculate() {
        double tan = Calculate.advancedCalculate("Mod", 10,3);
        System.out.println(tan);
    }
}