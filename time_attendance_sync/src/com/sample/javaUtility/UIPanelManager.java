package com.sample.javaUtility;

import java.awt.*;
import javax.swing.*;

class UIPanelManager
{
	public static JPanel setPanelSizeForDbTypeandSyncing(JPanel panel)
	{
		panel.setPreferredSize(new Dimension(1000,75));
		return panel;
	}
	public static JPanel setPanelBackgroundForDbType(JPanel panel)
	{
		panel.setBackground(Color.decode("#ffffff"));
		return panel;
	}
	public static JPanel setPanelSizeForConnectionandSettings(JPanel panel)
	{
		panel.setPreferredSize(new Dimension(1000,220));
		return panel;
	}
	public static JPanel setPanelBackgroundForConnectionandSettings(JPanel panel)
	{
		panel.setBackground(Color.decode("#ffffff"));
		return panel;
	}
	public static JPanel setPanelLayoutForLeftAssignedPanel(JPanel panel)
	{
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,15,15));
		return panel;
	}
	public static JPanel setPanelLayoutForRightAssignedPanel(JPanel panel)
	{
		panel.setLayout((new FlowLayout(FlowLayout.RIGHT,20,15)));
		return panel;
	}
	public static JPanel setPanelLayoutForEditSettings(JPanel panel)
	{
		panel.setLayout((new FlowLayout(FlowLayout.RIGHT,15,15)));
		return panel;
	}
	public static JPanel setPanelBackgroundForRightAssignedPanel(JPanel panel)
	{
		panel.setBackground(Color.decode("#ffffff"));
		return panel;
	}
	public static JPanel setPanelBackgroundForEditSettings(JPanel panel)
	{
		panel.setBackground(Color.decode("#ffffff"));
		return panel;
	}
	public static JPanel setPanelBackgroundFor(JPanel panel)
	{
		panel.setBackground(Color.decode("#ffffff"));
		return panel;
	}
	public static JPanel setPanelSizeForEditSettings(JPanel panel)
	{
		panel.setPreferredSize(new Dimension(1000,75));
		return panel;
	}
	
}