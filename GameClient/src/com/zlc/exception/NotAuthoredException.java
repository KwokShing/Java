package com.zlc.exception;

import javax.swing.JOptionPane;
/**
 * 认证失败异常，用户未登录就使用某些功能
 * @author 赵景晨
 *
 */
public class NotAuthoredException extends Exception {
	public void action() {
		JOptionPane.showMessageDialog(null, "请先登录！", "认证失败",
				JOptionPane.ERROR_MESSAGE);
	}
}
