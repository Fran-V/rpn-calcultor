package com.result;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class CalForResult {

	private Stack<Double> numbers = new Stack<Double>(); 
	private Stack<List<Double>> numlogs = new Stack<>(); 

	/**
	 * 该方法对RPN表达式进行计算
	 * 
	 * @param rpn 为用户输入的RPN表达式
	 */
	public void calRpnExpression(String rpn) throws Exception {
		String[] arpn = rpn.split(" "); 
		int i = 0;
		int apLength = arpn.length; 
		while (i < apLength) { 
			CalRules cr = new CalRules(); 
			int n = numbers.size(); 
			if (strToDigit(arpn[i]) != null) { 
				numbers.push(strToDigit(arpn[i]));
				numlogs.push(getStack(numbers)); 
			} else { 
				String opt = arpn[i];
				if ("undo".equals(opt) || "clear".equals(opt)) { 
					cr.funcRules(numbers, numlogs, opt);
				} else if ("sqrt".equals(opt)) { 
					if (n > 0) { 
						cr.unaryOptRules(numbers, numlogs, opt);
					} else { 
						System.out.print("operator" + opt + "(position:" + (2 * i - 1) + "):insufficient parameters ");
						break;
					}

				} else if ("+".equals(opt) || "-".equals(opt) || "*".equals(opt) || "/".equals(opt)) { 
					if (n > 1) { 
						cr.binaryOptRules(numbers, numlogs, opt);
					
					} else { 
						System.out.print("operator" + opt + "(position:" + (2 * i + 1) + "):insufficient parameters ");
						break;
					}

				} else {
					throw new Exception("Input expression is illegal!");
				}
			}
			i++;
		}
		displayStack(numbers);

	}

	/**
	 * 该方法将字符串转换为数字类型Double
	 * 
	 * @param str
	 */
	private Double strToDigit(String str) {
		try {
			double num = Double.valueOf(str);
			return num;
		} catch (Exception e) { 
			return null;
		}
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

	/**
	 * 该方法将栈中的数据显示出来，从底层开始
	 * 
	 * @param stk
	 */
	public void displayStack(Stack<Double> stk) {
		if (stk.size() != 0) {
			System.out.print("stack:");
			for (Double x : stk) {
				System.out.print(outputFormat(x) + " ");
			}
		} else {
			System.out.println("stack:");
		}
		System.out.println();
	}

	/**
	 * 该方法设置运算结果的显示格式，最多显示10位精度
	 * 
	 * @param value  运算结果
	 */
	public String outputFormat(double value) {
		DecimalFormat numformat = new DecimalFormat("##########.##########");
		String output = numformat.format(value);
		return output;
	}

	
	public static void main(String[] args) {
		CalForResult cf = new CalForResult();
		try {
			while (true) {
				System.out.println("Please enter the number and operator:");
				Scanner scan = new Scanner(System.in);
				String rpn = scan.nextLine();
				cf.calRpnExpression(rpn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
