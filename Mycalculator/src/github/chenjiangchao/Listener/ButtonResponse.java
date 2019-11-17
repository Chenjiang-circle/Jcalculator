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
    private static int bracketCount = 0;//记录左括号个数
    private static boolean isNewNumber = true; //该属性为true时，表示一个新数字即将输入

    private JPanel actionObject;
    private JButton button;
    private static Boolean twoParam = false;//设置标志位，表示当前是否存在双值高级运算操作
    private static String fontParam,realParam;//双值高级运算中的两个取值表达式
    private static String twoParamOperator;//双值高级运算中的运算符
    //private static boolean giveFontParamStartIndex = true;
    private static int fontParamStartIndex = 0;

    private ArrayList<String> operator = new ArrayList<String >()//基本运算符序列
    {
        {
            add("+");
            add("-");
            add("*");
            add("/");
        }
    };

    public ButtonResponse(JButton btn,JPanel obj)//构造函数初始化
    {
        button = btn;
        actionObject = obj;
;
    }

    //鼠标点击
    public void mouseReleased(MouseEvent e)//设置为Released是为了避免Clicked下产生的鼠标点击时发生位移导致点击失效的问题
    {
        System.out.println(twoParam);
        //如果是基本输入区
        if (actionObject instanceof BasicButton)
        {
            //保存前一个字符输入，用于后续判断
            String preInput = String.valueOf(inputString.charAt(inputString.length()-1));

            //如果此时处于初始状态，输入时先清除头部的零
            if(inputString.length() == 1 && inputString.toString().equals("0"))
            {
                //若第一个输入的是点,直接追加不清除零
                if(!button.getText().equals("."))
                {
                    //若点击了运算符,则只有减号生效,且此时不删除起始的零
                    if(operator.contains(button.getText()))
                    {
                        if (!button.getText().equals("-"))//若点击的不是减号，不生效
                        {
                            return;
                        }
                        else
                        {
                            //以追加的方式来抵消所清除的零
                            inputString.append("0");
                        }
                    }
                    //清除起始的零
                    inputString.delete(0, 1);
                }
            }

            //如果是等号，进行计算
            if (button.getText().equals("="))
            {
                //若存在两参数高级运算操作
                if (twoParam)
                {
                    System.out.println("两值运算");
                    //获取后值表达式
                    realParam = getAdvancedNotation(false);
                    Service service3 = new Service();//注册服务
                    //判断前后两值的格式是否正确
                    if(Checker.checker(realParam) && Checker.checker(fontParam))
                    {
                        //传入服务层运算
                        String answer = String.valueOf(service3.calculate(twoParamOperator,fontParam,realParam));
                        //inputString = new StringBuilder(answer);
                        //inputString.delete()
                        //清除残存的前值及运算符 /
                        inputString.delete(fontParamStartIndex,inputString.length());
                        inputString.append(answer);//结果追加到文本框


                        resultText.setText(inputString.toString());

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
                //初始化 //TODO：似乎应该是false
                isNewNumber = true;
                //检验计算
                if(Checker.checker(inputString.toString()))
                {
                    Service service1 = new Service();//注册服务
                    //传入服务层计算并将结果赋给最终字符串
                    inputString = new StringBuilder(String.valueOf(service1.calculate("",inputString.toString())));
                }
                else
                {
                    inputString = new StringBuilder("格式错误!");
                }
                //显示
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

                if(bracketCount > 0)//左括号净个数最小为零
                {
                    bracketCount--;
                }

                else//若右括号多于左括号,操作失效
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
                //若存在两参数高级运算
                if (twoParam)
                {
                    System.out.println("两值运算");
                    //获取后值表达式
                    realParam = getAdvancedNotation(false);
                    Service service3 = new Service();//注册服务
                    //检查两值
                    if(Checker.checker(realParam) && Checker.checker(fontParam))
                    {
                        //计算结果
                        String answer = String.valueOf(service3.calculate(twoParamOperator,fontParam,realParam));
                        //inputString = new StringBuilder(answer);
                        //inputString.delete()
                        //显示，应删除前值字符串
                        inputString.delete(fontParamStartIndex,inputString.length());
                        //追加至文本框字符串
                        inputString.append(answer);

                        resultText.setText(inputString.toString());

                        //状态初始化
                        bracketCount = 0;
                        isNewNumber = true;
                        twoParam = false;

                    }
                    else {
                        inputString = new StringBuilder("格式错误！");
                        resultText.setText(inputString.toString());

                    }
                }
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
                        if (isNewNumber)//数字后面不能直接追加PI
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
                        //设置高级运算标识位
                        twoParam = true;
                        //获取前值表达式
                        fontParam = getAdvancedNotation(true);
                        //通过再次追加的方式暂时性保留前值
                        inputString.append(fontParam);
                        //追加运算符
                        inputString.append("%");
                        //设置运算符标识
                        twoParamOperator = "Mod";
                        break;
                    case "x^y":
                        //效果同上
                        System.out.println("幂");
                        twoParam = true;
                        fontParam = getAdvancedNotation(true);
                        inputString.append(fontParam);
                        inputString.append("^");
                        twoParamOperator = "x^y";
                        break;
                    //对于其他运算符,传标识符以及需要的数值
                    default:

                        //获取需要计算的表达式
                        String advancedNotation = getAdvancedNotation(false);

                        Service service2 = new Service();//注册服务
                        System.out.println("执行高级运算");
                        System.out.println(advancedNotation);
                        //检验表达式正确性并计算
                        if (Checker.checker(advancedNotation) && Checker.legalAdvancedCalculate(button.getText(),advancedNotation))
                        {
                            System.out.println("检验正确");
                            System.out.println(advancedNotation);
                            //获取结果
                            String answer = String.valueOf(service2.calculate(button.getText(),advancedNotation));
                            //检验，若结果为负数，前方补零，避免出现运算符连续出现
                            if (Double.parseDouble(answer) < 0)
                            {
                                inputString.append("0");
                            }
                            //保留表达式追加
                            inputString.append(answer);
                            //inputString = new StringBuilder(String.valueOf(service2.calculate(button.getText(),advancedNotation)));
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

    //设置文本框内容（似乎没用，我也忘了为啥写这个了）
    public static void setResultText(JTextField result)
    {
        resultText = result;
    }

    //获取高级按键下的表达式
    public String getAdvancedNotation(boolean giveFontParamStartIndex)
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
        //取出表达式
        String advancedNotation = inputString.substring(start,inputString.length());
        //从文本框中移除被取出的表达式
        inputString.delete(start,inputString.length());
        if(giveFontParamStartIndex )
        {
            fontParamStartIndex = start;
        }
        return advancedNotation;
    }
}
