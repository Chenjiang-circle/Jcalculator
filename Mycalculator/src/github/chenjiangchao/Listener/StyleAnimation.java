package github.chenjiangchao.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
    StyleAnimation
    实现界面动画效果
    date    : 2019/11/15
    version : 1.0
 */

public class StyleAnimation extends MouseAndKeyboardListener
{
    private Color c;
    private JComponent com;
    private JButton button;
    private ArrayList<String> btnList;

    public StyleAnimation() {
        btnList = new ArrayList<String>();
        String number[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for (int i = 0; i < number.length; i++) {
            btnList.add(number[i]);
        }
    }
    public StyleAnimation(JButton btn)
    {
        this();
        button = btn;
    }


    //鼠标移入动画
    public void mouseEntered(MouseEvent e)
    {
        c = new Color(200,200,200);
        button.setBackground(c);
    }

    //鼠标移出动画
    public void mouseExited(MouseEvent e)
    {
        if(btnList.contains(button.getText()))
        {
            c = new Color(255,255,255);
        }
        else
        {
            c = new Color(230,230,230);
        }
        button.setBackground(c);

    }

    //鼠标按下动画
    public void mousePressed(MouseEvent e)
    {
        //无法实现
        c = new Color(255,255,255);
        button.setBackground(c);
    }

    //鼠标松开动画
    public void mouseReleased(MouseEvent e)
    {
        //存在问题
        mouseExited(e);
    }

    //键盘动画
    public void keyTyped(KeyEvent e)
    {
        //响应


        //动画
    }
}
