package github.chenjiangchaoAndYujunpo.Bean;

import java.util.ArrayList;

public class Operator {
	private String operator_1 = "+";
	private String operator_2 = "-";
	private String operator_3 = "*";
	private String operator_4 = "/";
	
	private ArrayList<String> operators = new ArrayList<>();
	
	public Operator() {
		operators.add(operator_1);
		operators.add(operator_2);
		operators.add(operator_3);
		operators.add(operator_4);
	}
	
	/**
	 * 判断一个字符串是否是基本运算符
	 * @param op 用户需要判断的字符串
	 * @return 如果是基本运算操作符则返回true，否则返回false
	 */
	public boolean contains(String op) {
		return operators.contains(op);
	}
}
