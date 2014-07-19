package com.zlc.exception;

import javax.swing.JOptionPane;
/**
 * 密码为空异常
 * @author 赵景晨
 *
 */
public class PassWordEmptyException extends ValueEmptyException {
	public void action() {
		JOptionPane.showMessageDialog(null, "请输入密码!", "密码错误",
				JOptionPane.ERROR_MESSAGE);
	}

}
