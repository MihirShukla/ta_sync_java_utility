package com.sample.javaUtility;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.time.LocalTime;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;

class UIForSQLServer
{
	static boolean status = false;
	static JLabel labelConnectionUrl;
	static JTextField textFieldConnectionUrl;
	static JLabel labelServerName;
	static JTextField textFieldServerName;
	static JLabel labelUsername;
	static JTextField textFieldUsername;
	static JLabel labelPassword;
	static JTextField textFieldPassword;
	static JLabel labelDatabaseName;
	static JTextField textFieldDatabaseName;
	static JLabel labelSyncSettings;
	static JLabel labelAuthorization;
	static JTextField textFieldAuthorization;
	static JLabel labelSyncDate;
	static JTextField textFieldSyncDate;
	static JButton buttonTestConnection;
	static JButton buttonReset;
	static JButton buttonSave;
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
		String spaceRow1 = "                         ";
		panelTestConnection.add(new JLabel(spaceRow1));
		labelServerName = new JLabel("Server Name");
		UILabelManager.setLabelFont(labelServerName);
		panelTestConnection.add(labelServerName);
		textFieldServerName = new JTextField();
		UITextFieldManager.setTextFieldSize(textFieldServerName);
		UITextFieldManager.setTextFieldForegroundColor(textFieldServerName);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldServerName);
		UITextFieldManager.setTextFieldFont(textFieldServerName);
		panelTestConnection.add(textFieldServerName);
		
		JPanel p1 = new JPanel();
		JLabel exampleConnectionUrl = new JLabel("(Example:- jdbc:sqlserver://SERVER_NAME)");
		JLabel exampleServerName = new JLabel("(Example:- DESKTOP-76126CN)");
		p1.add(exampleConnectionUrl);
		p1.add(new JLabel(" "));
		p1.add(exampleServerName);
		p1.setBackground(Color.decode("#ffffff"));
		p1.setPreferredSize(new Dimension(1000,25));
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,140,0));
		panelTestConnection.add(p1);
		
		labelUsername = new JLabel("Username");
		UILabelManager.setLabelSizeForConnectionUrlAndUserNameAndAuthorization(labelUsername);
		UILabelManager.setLabelFont(labelUsername);
		panelTestConnection.add(labelUsername);
		textFieldUsername = new JTextField();
		UITextFieldManager.setTextFieldSize(textFieldUsername);
		UITextFieldManager.setTextFieldForegroundColor(textFieldUsername);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldUsername);
		UITextFieldManager.setTextFieldFont(textFieldUsername);
		panelTestConnection.add(textFieldUsername);
		String spaceRow2 = "                          ";
		panelTestConnection.add(new JLabel(spaceRow2));
		labelPassword = new JLabel("Password");
		UILabelManager.setLabelSizeForPasswordAndSyncdate(labelPassword);
		UILabelManager.setLabelFont(labelPassword);
		panelTestConnection.add(labelPassword);
		textFieldPassword = new JPasswordField();
		UITextFieldManager.setTextFieldSize(textFieldPassword);
		UITextFieldManager.setTextFieldForegroundColor(textFieldPassword);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldPassword);
		UITextFieldManager.setTextFieldFont(textFieldPassword);
		panelTestConnection.add(textFieldPassword);
		
		String spaceRow3 = "                                                                                                                                                                                                                                                          ";
		panelTestConnection.add(new JLabel(spaceRow3));
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
					JOptionPane.showMessageDialog(null,"Please Enter Connection URL","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(textFieldServerName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Server Name","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(textFieldUsername.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter User Name","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(textFieldPassword.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Password","Error",JOptionPane.ERROR_MESSAGE);
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
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			String databaseUrl = textFieldConnectionUrl.getText().replace("SERVER_NAME",textFieldServerName.getText());
			connection = DriverManager.getConnection(databaseUrl,textFieldUsername.getText(),textFieldPassword.getText());
			if(connection!=null)
			{
				status =  true;
				JOptionPane.showMessageDialog(null,"Connection built Successfully.");
				//setSyncSettings(UIConfigureJson.panelSyncData);
				//saveSettings();
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
		
		labelDatabaseName = new JLabel("Database name");
		UILabelManager.setLabelSizeForConnectionUrlAndUserNameAndAuthorization(labelDatabaseName);
		UILabelManager.setLabelFont(labelDatabaseName);
		panelSyncData.add(labelDatabaseName);
		textFieldDatabaseName = new JTextField();
		UITextFieldManager.setTextFieldSize(textFieldDatabaseName);
		UITextFieldManager.setTextFieldForegroundColor(textFieldDatabaseName);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldDatabaseName);
		UITextFieldManager.setTextFieldFont(textFieldDatabaseName);
		panelSyncData.add(textFieldDatabaseName);
		String spaceRow5 = "                          ";
		panelSyncData.add(new JLabel(spaceRow5));
		
		labelSyncDate = new JLabel("Sync Date");
		UILabelManager.setLabelSizeForPasswordAndSyncdate(labelSyncDate);
		UILabelManager.setLabelFont(labelSyncDate);
		panelSyncData.add(labelSyncDate);
		textFieldSyncDate = new JTextField();
		UITextFieldManager.setTextFieldSize(textFieldSyncDate);
		UITextFieldManager.setTextFieldForegroundColor(textFieldSyncDate);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldSyncDate);
		UITextFieldManager.setTextFieldFont(textFieldSyncDate);
		panelSyncData.add(textFieldSyncDate);
		
		JLabel formatSyncDate = new JLabel("(Sync Date in:- DD/MMM/YYYY format)");
		JPanel p1 = new JPanel();
		p1.add(formatSyncDate);
		p1.setBackground(Color.decode("#ffffff"));
		p1.setPreferredSize(new Dimension(1000,25));
		p1.setLayout(new FlowLayout(FlowLayout.RIGHT,130,0));
		panelSyncData.add(p1);
		labelAuthorization = new JLabel("Authorization");
		UILabelManager.setLabelSizeForConnectionUrlAndUserNameAndAuthorization(labelAuthorization);
		UILabelManager.setLabelFont(labelAuthorization);
		panelSyncData.add(labelAuthorization);
		textFieldAuthorization = new JTextField();
		UITextFieldManager.setTextFieldSize(textFieldAuthorization);
		UITextFieldManager.setTextFieldForegroundColor(textFieldAuthorization);
		UITextFieldManager.setTextFieldBackgroundColor(textFieldAuthorization);
		UITextFieldManager.setTextFieldFont(textFieldAuthorization);
		panelSyncData.add(textFieldAuthorization);

		syncDate = textFieldSyncDate.getText();
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
			if(rootObject.containsKey("MICROSOFT_SQL_SERVER"))
			{
				setDataForTestConnection(UIConfigureJson.panelTestConnection);
				setSyncSettings(UIConfigureJson.panelSyncData);
				System.out.println("s"+syncDate);
				
				JSONObject sqlServerObj = (JSONObject)rootObject.get("MICROSOFT_SQL_SERVER");
				textFieldConnectionUrl.setText((String)sqlServerObj.get("connectionUrl"));
				textFieldServerName.setText((String)sqlServerObj.get("serverName"));
				textFieldUsername.setText((String)sqlServerObj.get("userName"));
				textFieldPassword.setText((String)sqlServerObj.get("password"));
				textFieldDatabaseName.setText((String)sqlServerObj.get("databaseName"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String temp = (String)sqlServerObj.get("syncDate");
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
				textFieldServerName.setText("");
				textFieldUsername.setText("");
				textFieldPassword.setText("");
				textFieldDatabaseName.setText("");
				textFieldSyncDate.setText("");
				textFieldAuthorization.setText("");
			}
		});
		buttonSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent exp) 
			{
				int counter = 0;
				if(textFieldDatabaseName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Database Name","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(textFieldSyncDate.getText().equals(""))
				{
					System.out.println("Mihir");
					JOptionPane.showMessageDialog(null,"Please Enter Sync Date","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(!isValidateDate(textFieldSyncDate.getText()))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Sync Date in given format","Error",JOptionPane.ERROR_MESSAGE);
					counter++;
				}
				else if(textFieldAuthorization.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter Authorization key","Error",JOptionPane.ERROR_MESSAGE);	
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
		JSONObject sqlServerObj =  new JSONObject();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		syncDate = textFieldSyncDate.getText();
		System.out.println("m"+syncDate);
		LocalDate date = LocalDate.parse(syncDate,formatter);
		LocalTime time = LocalTime.now();
		LocalDateTime dateTime = time.atDate(date);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateTime.format(myFormatObj);
		sqlServerObj.put("connectionUrl",textFieldConnectionUrl.getText());
		sqlServerObj.put("serverName",textFieldServerName.getText());
		sqlServerObj.put("userName",textFieldUsername.getText());
		sqlServerObj.put("password",textFieldPassword.getText());
		sqlServerObj.put("databaseName",textFieldDatabaseName.getText());
		sqlServerObj.put("syncDate",formattedDate);
		rootObject.put("MICROSOFT_SQL_SERVER",sqlServerObj);
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