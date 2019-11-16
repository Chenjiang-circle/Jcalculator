package github.chenjiangchao.UI;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JCalculaterUI extends JFrame {
	String[] buttonNames = {"%", "√", "x^2", "1/x", "CE", "C", "del", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "±", "0", ".", "="};
	public JCalculaterUI() {
		super("JCalculator");
		this.setBounds(100, 100, 412, 732);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(2, 1));
		// 添加显示器
		Font fs = new Font("宋体", Font.BOLD, 50);
		JTextArea screen = new JTextArea("0");
		// 设置自动换行
		screen.setLineWrap(true);
		// 设置显示器的字体大小
		screen.setFont(fs);
		container.add(screen);
		// 添加按钮
		JPanel buttonsContainer = new JPanel(new GridLayout(6, 4));
		container.add(buttonsContainer);
		JButton[] buttons = new JButton[buttonNames.length];
		Font f = new Font("宋体", Font.BOLD, 23);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
//			buttons[i].setBorderPainted(false);
			buttons[i].setFont(f);
			buttonsContainer.add(buttons[i]);
		}
		this.setVisible(true);
		this.setResizable(false);
	}
}
