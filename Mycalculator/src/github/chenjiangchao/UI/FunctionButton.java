package github.chenjiangchao.UI;

import github.chenjiangchao.Listener.StyleAnimation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
    Function Button
    计算器中高级功能键，提供函数计算功能
    date        : 2019/11/14
    version     : 1.0
 */
public class FunctionButton extends JPanel {
    private ArrayList<JButton> btns = new ArrayList<JButton>();

    public FunctionButton()
    {
        /*
               x^2 | x^y | sqrt| mod | <-
               sin | cos | tan | PI  | 1/x
               10^x| log | exp | n!  | c

         */
        this.setLayout(new GridLayout(3,5));//三行五列

        btns.add(new JButton("√x"));
        btns.add(new JButton("x^y"));
        btns.add(new JButton("x²"));
        btns.add(new JButton("Mod"));
        btns.add(new JButton("<-"));
        btns.add(new JButton("sin"));
        btns.add(new JButton("cos"));
        btns.add(new JButton("tan"));
        btns.add(new JButton("π"));
        btns.add(new JButton("1/x"));
        btns.add(new JButton("10^x"));
        btns.add(new JButton("log"));
        btns.add(new JButton("Exp"));
        btns.add(new JButton("n!"));
        btns.add(new JButton("C"));

        //按钮边框样式
        Border border = BorderFactory.createLineBorder(new Color(220,220,220));
        //按钮字体
        Font FuncBtnFont = new Font("微软雅黑",Font.PLAIN,15);

        for(JButton btn : btns)
        {
            btn.setFont(FuncBtnFont);//设置字体
            btn.setBackground(new Color(230,230,230));//设置背景色
            btn.setBorder(border);//设置边框
            btn.addMouseListener(new StyleAnimation(btn));
            this.add(btn);//添加
        }

    }

    public ArrayList getButtons()
    {
        return btns;
    }
}
