package github.chenjiangchao.Listener;

import github.chenjiangchao.UI.BasicButton;
import github.chenjiangchao.UI.FunctionButton;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ButtonResponse extends MouseAndKeyboardListener {
    private static JTextField resultText;
    private static StringBuilder inputString = new StringBuilder("0");
    private static int bracketCount = 0;
    private static boolean isNewNumber = true;

    private JPanel actionObject;
    private JButton button;

    public ButtonResponse(JButton btn,JPanel obj)//设置
    {
        button = btn;
        actionObject = obj;
;
    }

    //鼠标点击
    public void mouseClicked(MouseEvent e)
    {
        ArrayList<String> operator = new ArrayList<String >()
        {
            {
                add("+");
                add("-");
                add("×");
                add("÷");
            }
        };
        //如果是基本输入区
        if (actionObject instanceof BasicButton)
        {
            //如果此时处于初始状态，输入时先清除头部的零,但是若第一个输入的为点，则
            if(inputString.length() == 1 && inputString.toString().equals("0"))
            {
                if(!button.getText().equals("."))
                {
                    inputString.delete(0, 1);
                    //System.out.println("清楚");
                }

            }

            //如果是等号，进行计算
            if (button.getText().equals("="))
            {
                //TODO:检查括号，配对补齐

                //TODO:状态初始化
            }

            //如果是左括号，记录其个数
            if(button.getText().equals("("))
            {
                //TODO：前一个输入不是运算符

                bracketCount++;
            }

            //如果是右括号，抵消左括号计数
            if(button.getText().equals(")"))
            {
                //TODO:前一个输入是运算符

                if(bracketCount > 0)
                {
                    bracketCount--;
                }
                else//若右括号多余左括号,退出不输入
                {
                    return;
                }

            }
            //若输入为点
            if (button.getText().equals("."))
            {
                String preInput = String.valueOf(inputString.charAt(inputString.length() - 1));
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
                //TODO:一个数字里多个点

                //TODO：一个数字中的点后面没有合法输入（数字）

            }

            //如果输入为加减乘除运算符
            if(operator.contains(button.getText()))
            {


                //检查前一个输入是否为同类型运算符
                String preInput = String.valueOf(inputString.charAt(inputString.length()-1));
                if(operator.contains(preInput))//若是则替换
                {
                    inputString.deleteCharAt(inputString.length()-1);
                }
                //TODO：前一个输入为左括号或一个点
            }

            //TODO：如果输入的数字，而前一个为右括号

            inputString.append(button.getText());

        }

        //如果是功能按键，分类调用
        else if(actionObject instanceof FunctionButton)
        {
            //如果是初始状态，即此时没有任何输入
            if(inputString.length() == 1 && inputString.toString().equals("0"))
            {

            }
            else//对应按键调用对应计算方法
            {
                switch (button.getText())
                {
                    case "<-":
                        inputString.deleteCharAt(inputString.length()-1);
                        break;
                    case "C":
                        inputString = new StringBuilder("0");
                        //TODO：状态初始化
                        break;

                    default:

                }
            }

        }
        else
        {

        }
        //如果使用退格符清空了所有内容
        if(inputString.length() == 0)
        {
            inputString = new StringBuilder("0");
        }
        resultText.setText(inputString.toString());//重设文本框内容
    }

    public static void setResultText(JTextField result)
    {
        resultText = result;
    }
}
