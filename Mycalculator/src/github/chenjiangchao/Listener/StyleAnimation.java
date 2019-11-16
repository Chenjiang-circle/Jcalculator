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

    public StyleAnimation()
    {
        btnList = new ArrayList<String>();
        btnList.add("1");
        btnList.add("2");
        btnList.add("3");
        btnList.add("4");
        btnList.add("5");
        btnList.add("6");
        btnList.add("7");
        btnList.add("8");
        btnList.add("9");
        btnList.add("0");
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
/*
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
*/
    //键盘动画及响应
    public void keyTyped(KeyEvent e)
    {

    }
}
