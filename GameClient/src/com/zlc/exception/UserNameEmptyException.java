package com.zlc.exception;

import javax.swing.JOptionPane;
/**
 * 用户名为空异常
 * @author 赵景晨
 *
 */
public class UserNameEmptyException extends ValueEmptyException {
	public void action() {
		JOptionPane.showMessageDialog(null, "请输入用户名！", "用户名错误",
				JOptionPane.ERROR_MESSAGE);
	}

}
