package github.chenjiangchao.Core;

import java.util.Stack;

public class Calculate {
    /**
     * 利用栈，计算后缀表达式
     * @param strList 后缀表达式的String数组形式，每一个元素对应后缀表达式中的一项
     * @return 返回后缀表达式的值
     */
    public static double calculatePostfixNotation(String[] strList) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strList.length; i++) {
            // 判断元素是否为数字，是数字就压如栈中。否则就将栈中的元素取出来，进行相应的计算，将结果重新压入栈中
            if (strList[i].charAt(0) >= '0' && strList[i].charAt(0) <= '9') {
                // 元素第一个字符是数字，说明就是数字喽
                stack.push(strList[i]);
            } else {
                // 如果是操作符，就将压在栈顶上的两个数字弹出来进行相应的计算，并把结果重新压入栈中
                String num2 = stack.pop();
                String num1 = stack.pop();
                double result = basicCalculate(num1, num2, strList[i]);
                stack.push(String.valueOf(result));
            }
        }

        // 运算结束，将栈中的最后结果弹出来
        String pop = stack.pop();
        return Double.parseDouble(pop);
    }

    private static double basicCalculate(String num1, String num2, String operator) {
        double tmp1 = Double.parseDouble(num1);
        double tmp2 = Double.parseDouble(num2);
        double result = 0;
        switch (operator){
            case "+":
                result = tmp1 + tmp2;
                break;
            case "-":
                result = tmp1 - tmp2;
                break;
            case "*":
                result = tmp1 * tmp2;
                break;
            case "/":
                result = tmp1 / tmp2;
                break;
        }
        return result;
    }

    /**
     * 一个数值的高级运算
     * @param opreator 运算符，如tan、cos、sin、1/x、10^x、log、Exp、n！、x^2、√x、
     * @param num 数值
     * @return 结果
     */
    public static double advancedCalculate(String opreator, double num) {
        switch (opreator) {
            case "tan":
                return Math.tan(num);
            case "sin":
                return Math.sin(num);
            case "cos":
                return Math.cos(num);
            case "1/x":
                return Math.pow(num, -1);
            case "10^x":
                return Math.pow(10, num);
            case "log":
                return Math.log10(num);
            case "Exp":
                return Math.exp(num);
            case "n!":
                return factorial((int)(num));
            case "x^2":
                return Math.pow(num, 2.0);
            case "√x":
                return Math.sqrt(num);
        }
        return 0;
    }

    /**
     * 两个数值的高级运算
     * @param opreator 运算符，比如x^y、Mod
     * @param num1 第一个数值
     * @param num2 第二个数值
     * @return 返回结果
     */
    public static double advancedCalculate(String opreator, double num1, double num2) {
        switch (opreator) {
            case "x^y":
                return Math.pow(num1, num2);
            case "Mod":
                return num1 % num2;
        }
        return 0;
    }

    /**
     * n!的计算
     * @param num n
     * @return 计算结果值
     */
    private static int factorial(int num) {
        int a = 1;
        for(int i = 1; i <= num; i++){
            a *= i;
        }
        return a;
    }
}
