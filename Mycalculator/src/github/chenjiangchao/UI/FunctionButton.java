package github.chenjiangchao.UI;

import github.chenjiangchao.Listener.ButtonResponse;
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
        String name[] = { "√x","x^y", "x^2","Mod", "<-", "sin", "cos", "tan", "π", "1/x", "10^x", "log", "Exp", "n!", "C"};
        for(int i = 0; i < name.length; i++)
        {
            btns.add(new JButton(name[i]));
        }

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
            btn.addMouseListener(new ButtonResponse(btn,this));
            this.add(btn);//添加
        }

    }

    public ArrayList getButtons()
    {
        return btns;
    }
}
