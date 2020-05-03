package com.sample.javaUtility;

import java.awt.*;
import javax.swing.*;
class UITextFieldManager
{
	public static JTextField setTextFieldSize(JTextField textField)
	{
		textField.setPreferredSize(new Dimension(300,40));
		return textField;
	}
	public static JTextField setTextFieldForegroundColor(JTextField textField)
	{
		textField.setForeground(Color.decode("#000000"));
		return textField;
	}
	public static JTextField setTextFieldBackgroundColor(JTextField textField)
	{
		textField.setBackground(Color.decode("#d9d9d9"));
		return textField;
	}
	public static JTextField setTextFieldFont(JTextField textField)
	{
		textField.setFont(new Font("Arial",Font.PLAIN,15));
		return textField;
	}
}
