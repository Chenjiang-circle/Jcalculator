package github.chenjiangchao.Core;

import java.util.Stack;

public class Transform {

    /**
     * 将输入的字符串表达式(合法的)转化为后缀表达式
     * @param expr 用户输入的字符串表达式
     * @return 返回新生成的后缀表达式
     */
    public static String[] ToPostfixNotation(String expr) {
        Stack<String> operator = new Stack<>();
        String[] postixNotation = new String[expr.length()];
        // 遍历表达式
        int j = 0;
        for (int i = 0; i < expr.length(); i++) {
            // 如果发现是数组直接输出到新字符数组中
            if (expr.charAt(i) >= '0' && expr.charAt(i) <= '9') {
                String tmp = "";
                while (expr.charAt(i) >= '0' && expr.charAt(i) <= '9') {
                    tmp += expr.charAt(i);
                    if (i+1 < expr.length() && expr.charAt(i+1) >= '0' && expr.charAt(i+1) <= '9') {
                        i++;
                    } else {
                        break;
                    }
                }
                postixNotation[j++] = tmp;
                //System.out.println("发现是数字" + expr.charAt(i) + " 放入字符组中");
            }else {
                // 如果是(就压如栈中
                if (expr.charAt(i) == '(') {
                    operator.push(String.valueOf(expr.charAt(i)));
                    System.out.println("发现是（，将其压入栈中");
                } else if (expr.charAt(i) == '+' || expr.charAt(i) == '-') {
                    // 当发现是+-号的时候，需要将压在栈中的符号都弹出来（直到栈顶为“（”或者为空时停止，再将+-号压入栈中）
                    System.out.println("发现" + expr.charAt(i) + ",判断栈是否为空");
                    if (operator.isEmpty()) {
                        System.out.println("发现栈为空，直接将" + expr.charAt(i) + "压入栈中");
                        operator.push(String.valueOf(expr.charAt(i)));
                    } else {
                        System.out.println("发现栈不为空，现在进行将栈中的运算符弹出：");
                        try {
                            while (operator.isEmpty() || !operator.peek().equals("(")) {
                                String pop = operator.pop();
                                System.out.print("弹出" + pop + " ");
                                postixNotation[j++] = pop;
                            }
                        }catch (Exception e) {

                        }finally {
                            System.out.println();
                            System.out.println("将" + expr.charAt(i) + "压入栈中");
                            operator.push(String.valueOf(expr.charAt(i)));
                        }

                    }

                } else if (expr.charAt(i) == '*' || expr.charAt(i) == '/') {
                    // 如果发现是*/号，运算级较高，直接压入栈中
                    System.out.println("发现" + expr.charAt(i) + "，将其压入栈中");
                    operator.push(String.valueOf(expr.charAt(i)));
                } else if (expr.charAt(i) == ')') {
                    // 如果返现是“）”，需要将前面的操作符都弹出来，直到栈顶为“（”，之后，将“（”也弹出
                    System.out.println("发现），将栈中的运算符依次弹出：");
                    while (!operator.peek().equals("(")) {
                        String pop = operator.pop();
                        System.out.print("弹出" + expr.charAt(i));
                        postixNotation[j++] = pop;
                    }
                    System.out.println();
                    System.out.println("将（弹出");
                    operator.pop();
                }
            }
        }
        // 遍历完运算表达式，将栈中的运算符依次弹出
        System.out.println("遍历完运算表达式，将栈中的运算符依次弹出：");
        while (!operator.isEmpty()) {
            String pop = operator.pop();
            System.out.print("弹出" + pop);
            postixNotation[j++] = pop;
        }
        System.out.println();
        return postixNotation;
    }
}
