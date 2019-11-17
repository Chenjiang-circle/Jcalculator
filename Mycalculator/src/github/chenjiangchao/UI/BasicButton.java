package github.chenjiangchao.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

import github.chenjiangchao.Listener.ButtonResponse;
import github.chenjiangchao.Listener.StyleAnimation;

/**
    Basic Button
    计算器中的基本按键，提供最基本的加减乘除运算功能
    date        : 2019/11/14
    version     : 1.0
 */

public class BasicButton extends JPanel {
    private ArrayList<JButton> btns = new ArrayList<JButton>();

    public BasicButton()
    {
        /*
                . | 3 | 6 | 9 | ( | )
                0 | 2 | 5 | 8 | + | -
                = | 1 | 4 | 7 | * | /
         */
        this.setLayout(new GridLayout(3,6));//三行六列
        String name[] = {".","3","6","9","(",")","0","2","5","8","+","-","=","1","4","7","×","÷"};
        for(int i = 0; i < name.length; i++)
        {
            btns.add(new JButton(name[i]));
        }

        //按钮边框
        Border border = BorderFactory.createLineBorder(new Color(220,220,220));
        //数字键字体
        Font NumberFont = new Font("微软雅黑",Font.BOLD,18);
        //基本运算符键字体
        Font OtherBtnFont = new Font("微软雅黑",Font.PLAIN,15);

        for(int i = 0; i < btns.size(); i++)
        {
            JButton btn = btns.get(i);

            if( ( i > 0 && i < 4) || ( i > 5 && i < 10) || (i > 12 && i < 16 ))
            {
                btn.setFont(NumberFont);//设置字体
                btn.setBackground(new Color(255, 255, 255));//设置按钮背景色
                btn.setBorder(border);//设置边框
                this.add(btn);//添加
            }
            else
            {
                btn.setFont(OtherBtnFont);
                btn.setBackground(new Color(230, 230, 230));
                btn.setBorder(border);
                this.add(btn);
            }
            btn.addMouseListener(new StyleAnimation(btn));
            btn.addMouseListener(new ButtonResponse(btn,this));
        }
    }

    public ArrayList getButtons()
    {
        return btns;
    }
}
