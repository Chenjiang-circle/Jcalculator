package github.chenjiangchaoAndYujunpo.Service;

import github.chenjiangchaoAndYujunpo.Core.Calculate;
import github.chenjiangchaoAndYujunpo.Core.Transform;

public class Service {
    /**
     * 计算表达式
     * @param operator 运算符，如tan、cos、幂运算，如果operator为空字符串，表示一般的运算
     * @param expre 运算符运算的表达式
     * @return 返回结果
     */
    public double calculate(String operator, String expre) {
        // 首先先将表达式运算出来

        String[] strings = Transform.ToPostfixNotation(expre);
       // System.out.println("-------------------------");
        //System.out.println(Arrays.toString(strings));
       // System.out.println(operator.isEmpty());
        double result = Calculate.calculatePostfixNotation(strings);
        // 判断是否为高级运算，如果不是高级运算直接输出结果，否则进行高级运算
        if (operator.isEmpty()) {
            return result;
        } else {
            double v = Calculate.advancedCalculate(operator, result);
            return v;
        }
    }

    /**
     * 计算两位数字的高级表达式
     * @param operator 高级操作符
     * @param expre1 数值表达式1
     * @param expre2 数值表达式2
     * @return 返回高级表达式的结果
     */
    public double calculate(String operator, String expre1, String expre2) {
        // 首先先将表达式运算出来
        String[] strings1 = Transform.ToPostfixNotation(expre1);
        double result1 = Calculate.calculatePostfixNotation(strings1);
        String[] strings2 = Transform.ToPostfixNotation(expre2);
        double result2 = Calculate.calculatePostfixNotation(strings2);

        double v = Calculate.advancedCalculate(operator, result1, result2);
        return v;
    }
}
