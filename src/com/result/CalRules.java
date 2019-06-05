package com.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalRules {

	/**
	 * 该方法封装了一元运算符计算法则,可在该方法中增加操作符功能
	 * 
	 * @param stk1 操作符栈
	 * @param stk1 日志栈
	 * @param opt  操作符
	 */
	public void unaryOptRules(Stack<Double> stk1, Stack<List<Double>> stk2, String opt) throws Exception {
		double num = stk1.pop();
		switch (opt) {
		case "sqrt": 
			stk1.push(sqrt(num));
			stk2.push(getStack(stk1));
			break;
		default:
			throw new Exception("ERROR");
		}
	}

	/**
	 * 该方法封装了二元运算符计算法则，可在此方法中增加更多操作符，实现扩展。
	 * 
	 * @param stk1 操作符栈
	 * @param stk1 日志栈
	 * @param opt  操作符
	 */
	public void binaryOptRules(Stack<Double> stk1, Stack<List<Double>> stk2, String opt) throws Exception {

		double num2 = stk1.pop(); 
		double num1 = stk1.pop(); 
		switch (opt) {
		case "+":
			stk1.push(num1 + num2); 
			stk2.push(getStack(stk1)); 
			break;
		case "-":
			stk1.push(num1 - num2);
			stk2.push(getStack(stk1));
			break;
		case "*":
			stk1.push(num1 * num2);
			stk2.push(getStack(stk1));
			break;
		case "/":
			stk1.push(div(num1, num2));
			stk2.push(getStack(stk1));
			break;
		default:
			throw new Exception("ERROR");
		}
	}

	/**
	 * 该方法封装了功能操作符undo、clear的计算法则
	 * 
	 * @param stk1 操作数栈
	 * @param stk2 日志栈
	 * @param opt  操作符
	 */
	public void funcRules(Stack<Double> stk1, Stack<List<Double>> stk2, String opt) throws Exception {
		switch (opt) {
		case "undo": 
			while (!stk1.empty()) { 
				stk1.pop();
			}
			if (!stk2.empty()) { 
				stk2.pop();
				if (!stk2.empty()) { 
					List<Double> list1 = stk2.peek(); 
					for (int i = 0; i < list1.size(); i++) { 
						if (list1.get(i) != null) {
							stk1.push(list1.get(i));
						}
					}
				}
			}
			break;
		case "clear": 
			while (!stk1.empty()) { 
				stk1.pop();
			}
			List<Double> list2 = new ArrayList<>(); 
			list2.add(null);
			stk2.push(list2);
			break;
		default:
			throw new Exception("ERROR");
		}

	}

	/**
	 * 除法计算法则
	 * 
	 * @param a 操作数1
	 * @param b 操作数2
	 */
	private double div(double a, double b) throws Exception {
		if (b == 0) {
			throw new Exception("Divisor can't be 0!");
		}
		return a / b;
	}

	/**
	 * 开平方计算法则
	 * 
	 * @param f
	 */
	private double sqrt(double f) throws Exception {
		if (f < 0) {
			throw new Exception("Can't square the negative number!");
		}
		double a = (double) Math.sqrt(f);
		return a;
	}

	/**
	 * 该方法获取栈中数据，将其存在List集合中
	 * 
	 * @param stk
	 */
	public List<Double> getStack(Stack<Double> stk) {
		List<Double> getStk = new ArrayList<>();
		for (Double x : stk) {
			getStk.add(x);
		}
		return getStk;
	}
}

