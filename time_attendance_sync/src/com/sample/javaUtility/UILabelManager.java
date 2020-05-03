package com.sample.javaUtility;

import java.awt.*;
import javax.swing.*;
class UILabelManager
{
	public static JLabel setLabelFont(JLabel label)
	{
		label.setFont(new Font("Roboto",Font.BOLD,17));
		return label;
	}
	public static JLabel setLabelForegroundColorForConnectionActive(JLabel label)
	{
		label.setForeground(Color.decode("#000000"));
		return label;
	}
	public static JLabel setLabelSizeForHappierworkLogo(JLabel label)
	{
		label.setPreferredSize(new Dimension(200,50));
		return label;
	}
	public static JLabel setLabelFontForSyncSettings(JLabel label)
	{
		label.setFont(new Font("Roboto",Font.BOLD,22));
		return label;
	}
	public static JLabel setLabelSizeForConnectionUrlAndUserNameAndAuthorization(JLabel label)
	{
		label.setPreferredSize(new Dimension(130,23));
		return label;
	}
	public static JLabel setLabelSizeForPasswordAndSyncdate(JLabel label)
	{
		label.setPreferredSize(new Dimension(97,23));
		return label;
	}
	public static JLabel setLabelSizeForDatabaseType(JLabel label)
	{
		label.setPreferredSize(new Dimension(130,23));
		return label;
	}
	public static JLabel setLabelSizeForSyncDateInAccess(JLabel label)
	{
		label.setPreferredSize(new Dimension(117,23));
		return label;
	}
	public static JLabel setLabelSizeForAuthorizationInAccess(JLabel label)
	{
		label.setPreferredSize(new Dimension(117,23));
		return label;
	}
}