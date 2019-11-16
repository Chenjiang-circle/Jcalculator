package github.chenjiangchao.Service;

import github.chenjiangchao.Core.Calculate;
import github.chenjiangchao.Core.Transform;

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
        double result = Calculate.calculatePostfixNotation(strings);
        // 判断是否为高级运算，如果不是高级运算直接输出结果，否则进行高级运算
        if (operator.isEmpty()) {
            return result;
        } else {
            double v = Calculate.advancedCalculate(operator, result);
            return v;
        }
    }
}
