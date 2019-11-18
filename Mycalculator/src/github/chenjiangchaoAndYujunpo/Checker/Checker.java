package github.chenjiangchaoAndYujunpo.Checker;

import github.chenjiangchaoAndYujunpo.Service.Service;

import java.util.ArrayList;

public class Checker {
	public static Boolean checker(String expression)
	{
		boolean isNewNumber = true;//是否是新的数字标志位
		boolean hasPoint = false;//是否有小数点标志位

		//数字序列
		ArrayList<String> DigitList = new ArrayList<String>();
		for (int i = 0; i <= 9; i++)
		{
			DigitList.add(String.valueOf(i));
		}

		//基本运算符序列
		ArrayList<String> OperatorList = new ArrayList<String>()
		{
			{
				add("+");
				add("-");
				add("*");
				add("/");
			}
		};
		//遍历表达式
		for (int i = 0; i < expression.length(); i++)
		{
			//检测一个数字中是否存在多个点
			if(isNewNumber)//此标记表示一个新数字的开始
			{
				//重置标记位
				isNewNumber = false;
				//遍历数字中的每一个元素
				while(i < expression.length() - 1)
				{
					//若发现一个数字中存在点
					if (expression.charAt(i) == '.')
					{
						//这是第一个点,则设置hasPoint位
						if(!hasPoint)
						{
							hasPoint = true;
							//如果一个点的下一个位置不是数字
							if (!DigitList.contains(String.valueOf(expression.charAt(i+1))))
							{
								return false;//格式错误
							}
						}
						else//若已经存在其他的点
						{
							return false;//格式错误
						}
					}
					//若下一个位置属于OperatorList,则表示该数字结束,准备遍历下一个数字
					if(OperatorList.contains(String.valueOf(expression.charAt(i+1))))
					{
						i++;
						isNewNumber = true;
						break;
					}
					//若下一个字符是右括号,则意味着下一个是运算符
					if(expression.charAt(i+1) == ')')
					{
						i++;
						isNewNumber = false;
						break;
					}
					//若下一个字符是左括号,则意味着下一个是数字
					if(expression.charAt(i+1) == '(')
					{
						i++;
						isNewNumber = true;
						break;
					}
					i++;
				}
			}
		}
		//遍历结束没有问题,返回true;
		return true;
	}
	//检查是否是合法的高级运算单值
	public static boolean legalAdvancedCalculate(String operator,String expression)
	{
		Service service = new Service();
		double num = service.calculate("",expression);
		System.out.println(num);
		switch (operator)
		{
			case "√x":
			case "log":
			case "n!":
				if(num < 0)
				{
					return false;
				}
				break;
			case "1/x":
				if(num == 0)
				{
					return false;
				}
				break;
			default:
		}
		return true;
	}

}
