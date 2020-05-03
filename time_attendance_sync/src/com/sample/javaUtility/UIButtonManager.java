package com.sample.javaUtility;

import java.awt.*;
import javax.swing.*;
class UIButtonManager
{
	public static JButton setButtonFontForTestConnection(JButton button)
	{
		button.setFont(new Font("Roboto", Font.BOLD, 15));
		return button;
	}
	public static JButton setButtonFontForEditSettings(JButton button)
	{
		button.setFont(new Font("Roboto", Font.BOLD, 15));
		return button;
	}
	public static JButton setButtonSizeForTestConnection(JButton button)
	{
		button.setPreferredSize(new Dimension(200,40));
		return button;
	}
	public static JButton setButtonSizeForEditSettings(JButton button)
	{
		button.setPreferredSize(new Dimension(200,40));
		return button;
	}
	public static JButton setButtonForegroundColorForTestConnection(JButton button)
	{
		button.setForeground(Color.decode("#ffffff"));
		return button;
	}
	public static JButton setButtonForegroundColorForEditSettings(JButton button)
	{
		button.setForeground(Color.decode("#ffffff"));
		return button;
	}
	public static JButton setButtonBackgroundColorForTestConnection(JButton button)
	{
		button.setBackground(Color.decode("#008000"));
		return button;
	}
	public static JButton setBackgroundColorForEditSettings(JButton button)
	{
		button.setBackground(Color.decode("#707070"));
		return button;
	}
	public static JButton setFontForOtherButtons(JButton button)
	{
		button.setFont(new Font("Arial", Font.BOLD, 15));
		return button;
	}
	public static JButton setSizeForOtherButtons(JButton button)
	{
		button.setPreferredSize(new Dimension(120,40));
		return button;
	}
	public static JButton setForegroundColorOtherButtons(JButton button)
	{
		button.setForeground(Color.decode("#ffffff"));
		return button;
	}
	public static JButton setBackgroundColorForOtherButtons(JButton button)
	{
		button.setBackground(Color.decode("#707070"));
		return button;
	}
}