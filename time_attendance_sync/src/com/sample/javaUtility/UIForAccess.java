package com.sample.javaUtility;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.time.LocalTime;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

class UIForAccess
{
	static boolean status = false;
	static JLabel labelConnectionUrl;
	static JTextField textFieldConnectionUrl;
	static JLabel labelDatabasePath;
	static JTextField textFieldDatabasePath;
	static JLabel labelAuthorization;
	static JTextField textFieldAuthorization;
	static JLabel labelSyncDate;
	static JTextField textFieldSyncDate;
	static JLabel labelSyncSettings;
	static JButton buttonTestConnection;
	static JButton buttonReset;
	static JButton buttonSave;
	static JButton buttonEdit;
	static JButton buttonNew;
	static String syncDate = null;
	public static JPanel setDataForTestConnection(JPanel panelTestConnection)
	{
		System.out.println("Inside method");
		panelTestConnection.setBackground(Color.decode("#ffffff"));
		labelConnectionUrl = new JLabel("Connection URL");
		UILabelManager.setLabelSizeForConnectionUrlAndUserNameAndAuthorization(labelConnectionUrl);
		UILabelManager.setLabelFont(labelConnectionUrl);
		panelTestConnection.add(labelConnectionUrl);
		textFieldConnectionUrl = new JTextField();
		UITextFieldManager.setTextFieldSize(textFieldConnectionUrl);
		UITextFieldManager.setTextFieldForegroundColor(textFieldConnectionUrl);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldConnectionUrl);
		UITextFieldManager.setTextFieldFont(textFieldConnectionUrl);
		panelTestConnection.add(textFieldConnectionUrl);
		String spaceRow1 = "                   ";
		panelTestConnection.add(new JLabel(spaceRow1));
		labelDatabasePath = new JLabel("Database Path");
		UILabelManager.setLabelFont(labelDatabasePath);
		panelTestConnection.add(labelDatabasePath);
		textFieldDatabasePath = new JTextField("");
		UITextFieldManager.setTextFieldSize(textFieldDatabasePath);
		UITextFieldManager.setTextFieldForegroundColor(textFieldDatabasePath);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldDatabasePath);
		UITextFieldManager.setTextFieldFont(textFieldConnectionUrl);
		panelTestConnection.add(textFieldDatabasePath);
		JPanel p1 = new JPanel();
		JLabel exampleConnectionUrl = new JLabel("(Example:- jdbc:ucanaccess://DATABASE_PATH)");
		p1.add(exampleConnectionUrl);
		p1.setBackground(Color.decode("#ffffff"));
		p1.setPreferredSize(new Dimension(1000,25));
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,140,0));
		panelTestConnection.add(p1);
		String spaceRow2 = "                                                                                                                                                                                                                                                       ";
		panelTestConnection.add(new JLabel(spaceRow2));
		
		buttonTestConnection = new JButton("TEST CONNECTION");
		UIButtonManager.setButtonFontForTestConnection(buttonTestConnection);
		UIButtonManager.setButtonSizeForTestConnection(buttonTestConnection);
		UIButtonManager.setButtonForegroundColorForTestConnection(buttonTestConnection);
		UIButtonManager.setButtonBackgroundColorForTestConnection(buttonTestConnection);
		panelTestConnection.add(buttonTestConnection);
		
		buttonTestConnection.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent exp) 
			{
				int counter = 0;
				System.out.println(textFieldConnectionUrl.getText());
				if(textFieldConnectionUrl.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Connection Url","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(textFieldDatabasePath.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Database Path","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				System.out.println("Inside Button Event");
				if(counter==0)
				{
					testConnection();
				}
			}
		});
		UIPanelManager.setPanelLayoutForLeftAssignedPanel(panelTestConnection);
		panelTestConnection.revalidate();
		panelTestConnection.repaint();
		return panelTestConnection;
	}
	public static void testConnection()
	{
		try
		{
			Connection connection = null;
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String databaseUrl = textFieldConnectionUrl.getText().replace("DATABASE_PATH", textFieldDatabasePath.getText());
			connection = DriverManager.getConnection(databaseUrl);
			if(connection!=null)
			{
				status =  true;
				JOptionPane.showMessageDialog(null,"Connection built Successfully.");
				//setSyncSettings(UIConfigureJson.panelSyncData);
				//startSyncing();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static JPanel setSyncSettings(JPanel panelSyncData)
	{
		panelSyncData.setBackground(Color.decode("#ffffff"));
		labelSyncSettings = new JLabel("Sync Settings");
		UILabelManager.setLabelFontForSyncSettings(labelSyncSettings);
		panelSyncData.add(labelSyncSettings);
		String row1 ="                                                                                                                                                                                                                                                                   ";
		panelSyncData.add(new JLabel(row1));
		
		labelSyncDate = new JLabel("Sync Date");
		UILabelManager.setLabelFont(labelSyncDate);
		UILabelManager.setLabelSizeForSyncDateInAccess(labelSyncDate);
		panelSyncData.add(labelSyncDate);
		textFieldSyncDate = new JTextField("");
		UITextFieldManager.setTextFieldSize(textFieldSyncDate);
		UITextFieldManager.setTextFieldForegroundColor(textFieldSyncDate);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldSyncDate);
		UITextFieldManager.setTextFieldFont(textFieldSyncDate);
		panelSyncData.add(textFieldSyncDate);
		String spaceRow5 = "                      ";
		panelSyncData.add(new JLabel(spaceRow5));
		
		labelAuthorization = new JLabel("Authorization");
		UILabelManager.setLabelFont(labelAuthorization);
		UILabelManager.setLabelSizeForAuthorizationInAccess(labelAuthorization);
		panelSyncData.add(labelAuthorization);
		textFieldAuthorization = new JTextField("");
		UITextFieldManager.setTextFieldSize(textFieldAuthorization);
		UITextFieldManager.setTextFieldForegroundColor(textFieldAuthorization);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldAuthorization);
		UITextFieldManager.setTextFieldFont(textFieldAuthorization);
		panelSyncData.add(textFieldAuthorization);
		JPanel p1 = new JPanel();
		JLabel exampleSyncDate = new JLabel("(Sync Date in:- DD/MMM/YYYY format)");
		p1.add(exampleSyncDate);
		p1.setBackground(Color.decode("#ffffff"));
		p1.setPreferredSize(new Dimension(1000,25));
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,103,0));
		panelSyncData.add(p1);
		UIPanelManager.setPanelLayoutForLeftAssignedPanel(panelSyncData);
		panelSyncData.revalidate();
		panelSyncData.repaint();
		return panelSyncData;
	}
	public static void getDataFromJson()
	{
		JSONParser parser = new JSONParser();
		try
		{
			JSONObject rootObject = (JSONObject) parser.parse(new FileReader("../src/Utils.json"));
			if(rootObject.containsKey("MICROSOFT_ACCESS"))
			{
				setDataForTestConnection(UIConfigureJson.panelTestConnection);
				//testConnection();
				setSyncSettings(UIConfigureJson.panelSyncData);
				JSONObject accessObj = (JSONObject)rootObject.get("MICROSOFT_ACCESS");
				textFieldConnectionUrl.setText((String)accessObj.get("connectionUrl"));
				textFieldDatabasePath.setText((String)accessObj.get("databasePath"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String temp = (String)accessObj.get("syncDate");
				LocalDate date = LocalDate.parse(temp,formatter);
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
				String formattedDate = date.format(myFormatObj);
				textFieldSyncDate.setText(formattedDate);
				textFieldAuthorization.setText((String)rootObject.get("authorization"));
				Component[] componentsOfPanelTestConnection = UIConfigureJson.panelTestConnection.getComponents();
				for(int i=0;i<componentsOfPanelTestConnection.length;i++)
				{
					componentsOfPanelTestConnection[i].setEnabled(false);
				}
				Component[] componentsOfPanelSyncData = UIConfigureJson.panelSyncData.getComponents();
				for(int i=0;i<componentsOfPanelSyncData.length;i++)
				{
					componentsOfPanelSyncData[i].setEnabled(false);
				}
				saveSettings();
				Component[] componentsOfPanelStartSyncing = UIConfigureJson.panelStartSyncing.getComponents();
				for(int i=0;i<componentsOfPanelStartSyncing.length;i++)
				{
					componentsOfPanelStartSyncing[i].setEnabled(false);
				}
				UIConfigureJson.buttonEditSettings.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)			
					{
						for(int i=0;i<componentsOfPanelTestConnection.length;i++)
						{
							componentsOfPanelTestConnection[i].setEnabled(true);
						}
						for(int i=0;i<componentsOfPanelSyncData.length;i++)
						{
							componentsOfPanelSyncData[i].setEnabled(true);
						}
						for(int i=0;i<componentsOfPanelStartSyncing.length;i++)
						{
							componentsOfPanelStartSyncing[i].setEnabled(true);
						}
					}
				});
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void saveSettings()
	{
		buttonReset = new JButton("RESET");
		buttonSave = new JButton("SAVE");
		UIButtonManager.setFontForOtherButtons(buttonReset);
		UIButtonManager.setSizeForOtherButtons(buttonReset);
		UIButtonManager.setForegroundColorOtherButtons(buttonReset);
		UIButtonManager.setBackgroundColorForOtherButtons(buttonReset);
		UIButtonManager.setFontForOtherButtons(buttonSave);
		UIButtonManager.setSizeForOtherButtons(buttonSave);
		UIButtonManager.setForegroundColorOtherButtons(buttonSave);
		UIButtonManager.setBackgroundColorForOtherButtons(buttonSave);
		UIConfigureJson.panelStartSyncing.add(buttonReset);
		UIConfigureJson.panelStartSyncing.add(buttonSave);
		UIPanelManager.setPanelBackgroundFor(UIConfigureJson.panelStartSyncing);
		UIPanelManager.setPanelSizeForDbTypeandSyncing(UIConfigureJson.panelStartSyncing);
		UIPanelManager.setPanelLayoutForRightAssignedPanel(UIConfigureJson.panelStartSyncing);
		
		buttonReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent exps)			
			{
				textFieldConnectionUrl.setText("");
				textFieldDatabasePath.setText("");
				textFieldSyncDate.setText("");
				textFieldAuthorization.setText("");
			}
		});
		buttonSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent exp) 
			{
				int counter = 0;
				if(textFieldSyncDate.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Pnter Sync Date","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(!isValidateDate(textFieldSyncDate.getText()))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Sync Date in given format","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(textFieldAuthorization.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Authorization Key","Error",JOptionPane.ERROR_MESSAGE);	
					counter++;
				}
				if(counter==0)
				{
					writeDataIntoJsonObject();
				}
			}
		});
	}
	public static void writeDataIntoJsonObject()
	{
		JSONObject rootObject = new JSONObject();
		JSONObject accessObj =  new JSONObject();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		syncDate = textFieldSyncDate.getText();
		System.out.println("m"+syncDate);
		LocalDate date = LocalDate.parse(syncDate,formatter);
		LocalTime time = LocalTime.now();
		LocalDateTime dateTime = time.atDate(date);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateTime.format(myFormatObj);
		accessObj.put("connectionUrl",textFieldConnectionUrl.getText());
		accessObj.put("databasePath",textFieldDatabasePath.getText());
		accessObj.put("syncDate",formattedDate);
		rootObject.put("MICROSOFT_ACCESS",accessObj);
		rootObject.put("authorization",textFieldAuthorization.getText());
		if(status==true)
		{
			UIConfigureJson.writeDataIntoJsonFile(rootObject);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Please Check the Connection.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static boolean isValidateDate(String textField)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		try 
		{
			 dateFormat.parse(textField);
			 return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
}
