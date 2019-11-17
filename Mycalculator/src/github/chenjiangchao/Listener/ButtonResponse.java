package github.chenjiangchao.Listener;

import github.chenjiangchao.Core.Calculate;
import github.chenjiangchao.UI.BasicButton;
import github.chenjiangchao.UI.FunctionButton;
import github.chenjiangchao.Service.Service;
import github.chenjiangchao.Checker.Checker;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ButtonResponse extends MouseAndKeyboardListener {
    private static JTextField resultText;
    private static StringBuilder inputString = new StringBuilder("0");
    private static int bracketCount = 0;
    private static boolean isNewNumber = true; //该属性为true时，表示一个新数字即将输入

    private JPanel actionObject;
    private JButton button;
    private static Boolean twoParam = false;
    private static String fontParam,realParam;
    private static String twoParamOperator;

    private ArrayList<String> operator = new ArrayList<String >()
    {
        {
            add("+");
            add("-");
            add("*");
            add("/");
        }
    };

    public ButtonResponse(JButton btn,JPanel obj)//设置
    {
        button = btn;
        actionObject = obj;
;
    }

    //鼠标点击
    public void mouseReleased(MouseEvent e)
    {
        System.out.println(twoParam);
        //如果是基本输入区
        if (actionObject instanceof BasicButton)
        {
            String preInput = String.valueOf(inputString.charAt(inputString.length()-1));

            //如果此时处于初始状态，输入时先清除头部的零
            if(inputString.length() == 1 && inputString.toString().equals("0"))
            {
                //若第一个输入的是点,直接追加
                if(!button.getText().equals("."))
                {
                    //若点击了运算符,则只有减号生效,且此时不删除起始的零
                    if(operator.contains(button.getText()))
                    {
                        if (!button.getText().equals("-"))
                        {
                            return;
                        }
                        else
                        {
                            inputString.append("0");
                        }
                    }
                    inputString.delete(0, 1);

                }
            }

            //如果是等号，进行计算
            if (button.getText().equals("="))
            {

                if (twoParam)
                {
                    System.out.println("两值运算");
                    realParam = getAdvancedNotation();
                    Service service3 = new Service();
                    if(Checker.checker(realParam) && Checker.checker(fontParam))
                    {
                        inputString = new StringBuilder(String.valueOf(service3.calculate(twoParamOperator,fontParam,realParam)));
                        resultText.setText(inputString.toString());
                        //inputString = new StringBuilder("0");
                        //状态初始化
                        bracketCount = 0;
                        isNewNumber = true;
                        twoParam = false;
                        return;
                    }
                    else {
                        inputString = new StringBuilder("格式错误！");
                        resultText.setText(inputString.toString());
                        return;
                    }
                }
                //检查括号，配对补齐
                if(bracketCount != 0)
                {
                    while(bracketCount > 0)
                    {
                        bracketCount--;
                        inputString.append(")");
                    }
                }
                isNewNumber = true;
                //检验计算
                if(Checker.checker(inputString.toString()))
                {
                    Service service1 = new Service();//注册服务
                    inputString = new StringBuilder(String.valueOf(service1.calculate("",inputString.toString())));
                }
                else
                {
                    inputString = new StringBuilder("格式错误!");
                }
                resultText.setText(inputString.toString());
                return;
                //System.out.println("设置为真");
            }

            //如果是左括号，记录其个数
            if(button.getText().equals("("))
            {
                //如果前一个输入不是运算符,不能输入左括号
                if(!isNewNumber)
                {
                    return;
                }

                bracketCount++;
                isNewNumber = true;
                //System.out.println("设置为真");
            }

            //如果是右括号，抵消左括号计数
            if(button.getText().equals(")"))
            {
                //T前一个输入是运算符,不能输入右括号
                if(isNewNumber)
                {
                   return;
                }

                if(bracketCount > 0)
                {
                    bracketCount--;
                }
                else//若右括号多余左括号,退出不输入
                {
                    return;
                }
                isNewNumber = false;
               // System.out.println("设置为假");

            }
            //若输入为点
            if (button.getText().equals("."))
            {

                //不能连续输入点
                if(preInput.equals("."))
                {
                    return;
                }
                //如果前一个输入为右括号，则不可输入
                else if (preInput.equals(")"))
                {
                    return;
                }
                //若前一个输入为加减乘除运算符，或者为左括号，自动补零
                else if (operator.contains(preInput) || preInput.equals("("))
                {
                    inputString.append("0");
                }

                isNewNumber = false;
                //System.out.println("设置为假");


            }

            //如果输入为加减乘除运算符
            if(operator.contains(button.getText()))
            {
                //检查前一个输入是否为同类型运算符
                if(operator.contains(preInput))//若是则替换
                {
                    inputString.deleteCharAt(inputString.length()-1);
                }
                //前一个输入为左括号,禁止输入
                if(preInput.equals("("))
                {
                    return;
                }

                isNewNumber = true;
               // System.out.println("设置为真");
            }
            //判断前一个输入是否为数字或者点，或者右括号
            if( (button.getText().charAt(0) >='0' && button.getText().charAt(0) <= '9') || button.getText().equals(".") || button.getText().equals(")"))
            {
                //如果输入的是数字
                if(!button.getText().equals(")"))
                {
                    //前一个输入是右括号,禁止
                    if(preInput.equals(")"))
                    {
                        return ;
                    }
                }
                isNewNumber = false;
               // System.out.println("数字或者点或者右括号被输入了");
            }


            inputString.append(button.getText());

        }

        //如果是功能按键，分类调用
        else if(actionObject instanceof FunctionButton)
        {
            System.out.println("按下了功能按键");
            //如果是初始状态，即此时没有任何输入
            if(inputString.length() == 1 && inputString.toString().equals("0"))
            {
                System.out.println("初始状态");
                //输入PI
                if(button.getText().equals("π"))
                {
                    inputString.delete(0,1);
                    inputString.append(Math.PI);
                }
                else//其他按键不响应
                {
                    return;
                }

            }
            else//对应按键调用对应计算方法
            {
                System.out.println("非初始状态");
                switch (button.getText())
                {
                    case "<-":
                        //删除左括号时修改计数
                        if (inputString.charAt(inputString.length() -1 ) == '(')
                        {
                            bracketCount--;
                        }
                        inputString.deleteCharAt(inputString.length()-1);
                        break;
                    case "C":
                        //状态初始化'
                        System.out.println("Clear");
                        inputString = new StringBuilder("0");
                        bracketCount = 0;
                        isNewNumber = true;
                        twoParam = false;
                        break;
                    case "π":
                        if (isNewNumber)
                        {
                            inputString.append(Math.PI);
                            isNewNumber = false;
                        }
                        else
                        {
                            return;
                        }
                        break;
                    case "Mod":
                        System.out.println("取模");
                        twoParam = true;
                        fontParam = getAdvancedNotation();
                        inputString.append(fontParam);
                        inputString.append("%");
                        twoParamOperator = "Mod";
                        break;
                    case "x^y":
                        System.out.println("幂");
                        twoParam = true;
                        fontParam = getAdvancedNotation();
                        inputString.append(fontParam);
                        inputString.append("^");
                        twoParamOperator = "x^y";
                        break;
                    //对于其他运算符,传标识符以及需要的数值
                    default:

                        //获取需要计算的表达式
                        String advancedNotation = getAdvancedNotation();
                        Service service2 = new Service();//注册服务
                        System.out.println("执行高级运算");
                        System.out.println(advancedNotation);
                        //检验表达式正确性并计算
                        if (Checker.checker(advancedNotation) && Checker.legalAdvancedCalculate(button.getText(),advancedNotation))
                        {
                            System.out.println("检验正确");
                            System.out.println(advancedNotation);
                            inputString = new StringBuilder(String.valueOf(service2.calculate(button.getText(),advancedNotation)));
                        }
                        else
                        {
                            inputString = new StringBuilder("格式错误!");
                        }
                }
            }

        }

        //如果使用退格符清空了所有内容
        if(inputString.length() == 0)
        {
            inputString = new StringBuilder("0");
            //状态初始化
            bracketCount = 0;
            isNewNumber = true;
            twoParam = false;
        }
        resultText.setText(inputString.toString());//重设文本框内容
    }

    //设置文本框内容
    public static void setResultText(JTextField result)
    {
        resultText = result;
    }

    //获取高级按键下的表达式
    public String getAdvancedNotation()
    {
        //由后向前遍历表达式
        int start = 0;//记录被截取的字符串起始位置
        for (int i = inputString.length() - 1 ;i >= 0;i--)
        {
            //若以右括号结束
            if(inputString.charAt(inputString.length()-1) == ')')
            {
                if (inputString.charAt(i) == '(')
                {
                    start = i;
                    break;
                }
            }
            else
            {
                //遍历到运算符
                if (operator.contains(String.valueOf(inputString.charAt(i))))
                {
                    start = i + 1;
                    break;
                }
                else if (String.valueOf(inputString.charAt(i)).equals("%") || String.valueOf(inputString.charAt(i)).equals("^"))
                {
                    start = i + 1;
                    break;
                }
                //如果一直没有遍历到运算符，说明是一个数字，全部返回
                else
                {
                    start = 0;
                }
            }
        }
        String advancedNotation = inputString.substring(start,inputString.length());
        inputString.delete(start,inputString.length());
        return advancedNotation;
    }
}
