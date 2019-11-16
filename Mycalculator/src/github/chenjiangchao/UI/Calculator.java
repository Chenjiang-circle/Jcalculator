package github.chenjiangchao.UI;

import javax.swing.*;
import java.awt.*;

/**
    计算器主窗口，组织所有功能区
    date        : 2019/11/14
    version     : 1.0
 */

public class Calculator extends JFrame {

    public Calculator()
    {
        super("计算器");//窗口标题
        this.setBounds(100,100,400,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //网格布局，三行一列
        //自上而下：（菜单，文本框），（功能组件），（数字键盘与基本运算）
        this.getContentPane().setLayout(new GridLayout(3,1));

        ShowArea show = new ShowArea();//最上方（菜单与文本框）
        this.add(show);

        FunctionButton funcBtnArea = new FunctionButton();//高级功能按键区
        this.add(funcBtnArea);

        BasicButton basicBtnArea = new BasicButton();//基本按键区
        this.add(basicBtnArea);

        this.setVisible(true);
    }
    public static void main(String args[])
    {
        new Calculator();
    }
}
