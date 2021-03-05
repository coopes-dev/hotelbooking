package database.hibernate;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DatabaseConfigDialog {
	private Dialog d;
	
	public DatabaseConfigDialog() {
   	DatabaseConfig config=new DatabaseConfig();
	JFrame f= new JFrame();  
    d = new Dialog(f , "Configure Database", true);  
    
    f.setLocationRelativeTo(null);
    d.setLayout(new GridLayout(0,2) );  
    d.setLocation(100,100);
    JPanel jp=new JPanel();
    jp.add( new JLabel ("Failed to connect to MySQL database, please set connection details"));  
    d.add(jp);
    d.add( new JLabel (""));  

    jp=new JPanel();
    jp.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    JLabel caption=new JLabel(expand("Connection URL :",30));
    caption.setAlignmentX(Component.LEFT_ALIGNMENT);
    jp.add(caption);
    
    JTextField tfURL=new JTextField();
    tfURL.setMargin(new Insets(6,6,6,6));
    tfURL.setText(expand(config.getConnectionURL(),50));
    caption.setAlignmentX(Component.LEFT_ALIGNMENT);
    tfURL.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    jp.add(tfURL);
    
    d.add(jp);
    d.add( new JLabel ("")); 
    
    jp=new JPanel();
    jp.setLayout(new FlowLayout(FlowLayout.LEFT));
    caption=new JLabel(expand("Connection Username :",30));
    jp.add(caption);
    JTextField tfUsername=new JTextField();
    tfUsername.setMargin(new Insets(6,6,6,6));
    tfUsername.setText(expand(config.getUsername(),50));    
    jp.add(tfUsername);
    caption.setAlignmentX(Component.LEFT_ALIGNMENT);
    tfUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
    JLabel status=new JLabel();

    d.add(jp);
    d.add( new JLabel ("")); 
    
    jp=new JPanel();
    jp.setLayout(new FlowLayout(FlowLayout.LEFT));
    caption=new JLabel(expand("Connection Password :",30));
    jp.add(caption);
    JTextField tfPassword=new JTextField();
    tfPassword.setMargin(new Insets(6,6,6,6));
    tfPassword.setText(expand(config.getPassword(),50));    
    jp.add(tfPassword);
    d.add(jp);
    
    
    d.add( new JLabel (""));  
    
    d.add( new JLabel ("")); 
    
    d.add( new JLabel (""));  
    d.add( new JLabel (""));  
    d.add( new JLabel (""));  
    d.add( new JLabel (""));  
    
    jp=new JPanel();
    JButton b = new JButton ("OK");  
    b.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
    b.setBackground(Color.LIGHT_GRAY);
    b.setForeground(Color.BLACK);
    d.addWindowListener(new WindowAdapter() {
    	public void windowClosing(WindowEvent e) {
    		d.setVisible(false);
    		System.exit(1);	// close down application user has given up
    	}
    });
    	
    b.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
           // d.setVisible(false);  
        	// Closing 
        	
        	config.writeConfig(tfURL.getText(), tfUsername.getText(), tfPassword.getText());
        	d.setVisible(false);
    		System.exit(1);	// close down application user has given up
        	
        }  
    });  
    jp.add(b);
    b = new JButton ("Test Connection");
    b.addActionListener ( new ActionListener()  
    {  
        public void actionPerformed( ActionEvent e )  
        {  
            System.out.println("Testing connection for "+tfURL.getText());
            DatabaseConfig databaseConfig=new DatabaseConfig(tfURL.getText(),tfUsername.getText(),tfPassword.getText());
            ConnectionStatus  cstatus=DatabaseConnector.testConnection(databaseConfig.getHibernateConfig());
            if (cstatus.isOk()) {
            	status.setText("Connection passed");
            } else {
            	status.setText("Connection failed "+cstatus.getMessage());
            }
        }  
    });  
    
    b.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
    b.setBackground(Color.LIGHT_GRAY);
    b.setForeground(Color.BLACK);
    jp.add(b);
    
    d.add(jp);   
    d.add(status);  
    
    d.pack();
    d.setVisible(true);  
	}

	private String expand(String string, int len) {
		if (string.length()<len) {
			string=string+"                                                        ".substring(0,len-string.length());
		}
		return(string);
	}

}
