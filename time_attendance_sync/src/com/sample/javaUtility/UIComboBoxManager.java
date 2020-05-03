package com.sample.javaUtility;

import java.awt.*;
import javax.swing.*;
class UIComboBoxManager
{
	public static JComboBox setComboBoxSize(JComboBox comboBox)
	{
		comboBox.setPreferredSize(new Dimension(300,40));
		return comboBox;
	}
	public static JComboBox setComboBoxForegroundColor(JComboBox comboBox)
	{
		comboBox.setForeground(Color.decode("#000000"));
		return comboBox;
	}
	public static JComboBox setComboBoxBackgroundColor(JComboBox comboBox)
	{
		comboBox.setBackground(Color.decode("#d9d9d9"));
		return comboBox;
	}
}