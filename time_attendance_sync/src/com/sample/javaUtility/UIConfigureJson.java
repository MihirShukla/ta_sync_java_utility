package com.sample.javaUtility;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class UIConfigureJson extends JFrame
{
	static JFrame frame;
	static JPanel panelDatabseType;
	static JPanel panelTestConnection;
	static JPanel panelSyncData;
	static JPanel panelStartSyncing;
	static JPanel panelEditSettings;
	static JPanel panelForButtonTestConnection;
	static JLabel labelHappierWork;
	static JLabel labelDatabaseType;
	static JComboBox comboBoxDatbaseType;
	static JLabel labelConnectionActive;
	static JLabel labelLastSyncedInfo;
	static JButton buttonEditSettings;
	static String databaseType = null;
	static String listDatabaseType[] = {"Select Database Type","Microsoft SQL Server","Microsoft Access"};
		
	public UIConfigureJson()
	{
		frame = new JFrame("happierWork");
		panelDatabseType = new JPanel();
		panelTestConnection = new JPanel();
		panelSyncData = new JPanel();
		panelStartSyncing = new JPanel();
		panelEditSettings = new JPanel();
		
		setHappierWorkLogo();
		setPanelDatabasetype(panelDatabseType);
		setPanelEditSettings(panelEditSettings);
		//frame.add(labelHappierWork);
		frame.add(panelDatabseType);
		frame.add(panelTestConnection);
		frame.add(panelSyncData);
		frame.add(panelStartSyncing);
		
		UIPanelManager.setPanelSizeForConnectionandSettings(panelTestConnection);
		
		UIPanelManager.setPanelSizeForConnectionandSettings(panelSyncData);
		
		UIPanelManager.setPanelSizeForDbTypeandSyncing(panelStartSyncing);
		
		frame.setLayout(new FlowLayout(FlowLayout.LEFT,55,10));
		frame.getContentPane().setBackground(Color.decode("#009999"));
		frame.setSize(1115,850);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void setHappierWorkLogo()
	{
		URL url = AttendanceSync.class.getResource("happierWork.png");
		labelHappierWork = new JLabel(new ImageIcon(url));
		UILabelManager.setLabelSizeForHappierworkLogo(labelHappierWork);
		frame.add(labelHappierWork);
	}
	public static void setPanelEditSettings(JPanel panelEditSettings)
	{
		//panelEditSettings = new JPanel();
		labelConnectionActive = new JLabel("");
		UILabelManager.setLabelFont(labelConnectionActive);
		UILabelManager.setLabelForegroundColorForConnectionActive(labelConnectionActive);
		labelLastSyncedInfo = new JLabel("");
		UILabelManager.setLabelFont(labelLastSyncedInfo);
		buttonEditSettings = new JButton("EDIT SETTINGS");
		UIButtonManager.setButtonFontForEditSettings(buttonEditSettings);
		UIButtonManager.setButtonSizeForEditSettings(buttonEditSettings);
		UIButtonManager.setButtonForegroundColorForEditSettings(buttonEditSettings);
		UIButtonManager.setBackgroundColorForEditSettings(buttonEditSettings);
		
		File newFile = new File("../src/Utils.json");
		if(newFile.length()==0)
		{
			panelTestConnection.setBackground(Color.decode("#009999"));
			panelSyncData.setBackground(Color.decode("#009999"));
			panelStartSyncing.setBackground(Color.decode("#009999"));
			
			comboBoxDatbaseType.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					databaseType = (String)comboBoxDatbaseType.getSelectedItem();
					System.out.println(databaseType);
					if(databaseType.equals("Microsoft SQL Server"))
					{
						System.out.println("SQL Server Successful");
						UIForSQLServer.setDataForTestConnection(panelTestConnection);
						UIForSQLServer.setSyncSettings(panelSyncData);
						UIForSQLServer.saveSettings();
					}
					else if(databaseType.equals("Microsoft Access"))
					{
						System.out.println("Access Successful");
						UIForAccess.setDataForTestConnection(panelTestConnection);
						UIForAccess.setSyncSettings(panelSyncData);
						UIForAccess.saveSettings();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Select either Microsoft SQL Server or Microsoft Access");
						System.out.println("Select either Microsoft SQL Server or Microsoft Access");
					}
				}
			});
		}
		else
		{
			String connectionActive=null;
			String status = null;
			UIPanelManager.setPanelBackgroundForConnectionandSettings(panelTestConnection);
		
			UIPanelManager.setPanelBackgroundForConnectionandSettings(panelSyncData);
		
			UIPanelManager.setPanelBackgroundForConnectionandSettings(panelStartSyncing);
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from mihir ORDER BY Id DESC LIMIT 1");
		
				while(rs.next())
				{
					System.out.println("Mihir2");
					//String logdate = rs.getString("logdate");
					String logdate = "2020-04-20 16:00:00";
					//status = rs.getString("status");
					status = "0";
					DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = inFormat.parse(logdate);
					DateFormat outFormat = new SimpleDateFormat( "dd/MMM/yyyy hh:mm aa");
					connectionActive = outFormat.format(date);
					System.out.println(connectionActive);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			panelEditSettings.add(labelConnectionActive);
			panelEditSettings.add(labelLastSyncedInfo);
			labelConnectionActive.setText("Connection Active-");
			if(status.equals("1"))
			{
				labelLastSyncedInfo.setText("Last Synced: "+connectionActive);
				panelEditSettings.add(new JLabel("                                                                                                        "));
			}
			else
			{
				labelLastSyncedInfo.setText("Last Sync Failed: "+connectionActive);
				labelLastSyncedInfo.setForeground(Color.decode("#ff0000"));
				panelEditSettings.add(new JLabel("                                                                                       "));
			}
			panelEditSettings.add(buttonEditSettings);
			UIPanelManager.setPanelSizeForEditSettings(panelEditSettings);
			panelEditSettings.setLayout(new FlowLayout(FlowLayout.LEFT,15,15));
			
			JSONParser parser = new JSONParser();
			try
			{
				JSONObject rootObject = (JSONObject) parser.parse(new FileReader("../src/Utils.json"));
				Component[] componentsOfPanelDatabaseType = panelDatabseType.getComponents();
				for(int i=0;i<componentsOfPanelDatabaseType.length;i++)
				{
					componentsOfPanelDatabaseType[i].setEnabled(false);
				}
				if(rootObject.containsKey("MICROSOFT_SQL_SERVER"))
				{
					System.out.println("Inside if....");
					comboBoxDatbaseType.setSelectedItem("Microsoft SQL Server");
					UIForSQLServer.getDataFromJson();
				}
				else
				{
					System.out.println("Inside else....");
					comboBoxDatbaseType.setSelectedItem("Microsoft Access");
					UIForAccess.getDataFromJson();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			frame.add(panelEditSettings);
		}
		
	}
	public static void setPanelDatabasetype(JPanel panelDatabseType)
	{
		labelDatabaseType = new JLabel("Database Type");
		UILabelManager.setLabelFont(labelDatabaseType);
		UILabelManager.setLabelSizeForDatabaseType(labelDatabaseType);
		panelDatabseType.add(labelDatabaseType);
		comboBoxDatbaseType = new JComboBox(listDatabaseType);
		
		UIComboBoxManager.setComboBoxSize(comboBoxDatbaseType);
		UIComboBoxManager.setComboBoxForegroundColor(comboBoxDatbaseType);
		UIComboBoxManager.setComboBoxBackgroundColor(comboBoxDatbaseType);
		panelDatabseType.add(comboBoxDatbaseType);
		UIPanelManager.setPanelSizeForDbTypeandSyncing(panelDatabseType);
		UIPanelManager.setPanelBackgroundForDbType(panelDatabseType);
		UIPanelManager.setPanelLayoutForLeftAssignedPanel(panelDatabseType);
	}
	public static void writeDataIntoJsonFile(JSONObject rootObject)
	{
		try(FileWriter file = new FileWriter("../src/Utils.json"))
		{
			rootObject.put("timeIntervalForSyncInSeconds","900");
			rootObject.put("attendancePushUrl","https://happierhr.appspot.com/time-and-attendance/push");
			file.write(rootObject.toJSONString());
			file.flush();
			System.out.println("Data successfully inserted in Json file");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
