package github.chenjiangchaoAndYujunpo.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
    显示文本框，实时显示交互结果
    date        : 2019/11/14
    version     : 1.0
 */
public class ShowArea extends JPanel {

    private JTextField result;
    public ShowArea()
    {
        this.setLayout(new GridLayout(2,1));
        this.add(new JPanel());//占据最上方，留备菜单


        result = new JTextField("0",20);
        result.setEditable(false);//设置文本框为不可编辑

        Font showFont = new Font("微软雅黑",Font.PLAIN,20);
        result.setFont(showFont);//设置字体

        Border border = BorderFactory.createEmptyBorder();
        result.setBorder(border);//去除边框

        result.setHorizontalAlignment(JTextField.RIGHT);//右对齐
        this.add(result);

    }

    public JTextField getTextField()
    {
        return result;
    }
}
