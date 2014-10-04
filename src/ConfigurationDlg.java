import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;



public class ConfigurationDlg
extends JDialog{
	
	private static final long serialVersionUID = 2262593219437177672L;

	final ConfigurationDlg conf;
	
	public ConfigurationDlg(Frame owner, Boolean vis, Configuration config){
		super(owner,"Configurations",true);
		conf = this;
		conf.create(vis, config);
	}
	
	private void create(Boolean vistest, final Configuration conf_in){
		
		final JFrame conf = new JFrame("poop");
		conf.setSize(250, 200);
		conf.setLocation(50, 50);
		//this.setResizable(false);
		conf.setVisible(vistest);
		
		/*this.setVisible(true);
		this.setModal(true);
		this.setSize(250, 100);
		this.setLocation(50, 50);*/
		
		
		
		JTextArea label = new JTextArea("Change your configurations here."+
								   "\nChanges will only be affective if \"Save\" is clicked.");
		label.setEditable(false);
		label.setLineWrap(true);
		label.setWrapStyleWord(true);
		label.setBackground(this.getBackground());
		
		JPanel labels = new JPanel();
		SpringLayout custom = new SpringLayout();
		
		JLabel ipLabel = new JLabel("IP:");
		JLabel emailLabel = new JLabel("Email:");
		
		final JTextArea ipText = new JTextArea("" + conf_in.getIP());
		final JTextArea emailText = new JTextArea("" + conf_in.getEmail());
		
		custom.putConstraint(SpringLayout.NORTH, labels, 5, SpringLayout.NORTH, label);
		custom.putConstraint(SpringLayout.WEST, ipLabel	, 5, SpringLayout.WEST, labels);
		custom.putConstraint(SpringLayout.NORTH, ipLabel, 5, SpringLayout.NORTH, labels);
		
		custom.putConstraint(SpringLayout.WEST, ipText, 5, SpringLayout.EAST, emailLabel);
		custom.putConstraint(SpringLayout.NORTH, ipText, 5, SpringLayout.NORTH, labels);
		custom.putConstraint(SpringLayout.EAST, ipText, 100, SpringLayout.WEST, ipText);
		
		custom.putConstraint(SpringLayout.WEST, emailLabel	, 5, SpringLayout.WEST, labels);
		custom.putConstraint(SpringLayout.NORTH, emailLabel, 5, SpringLayout.SOUTH, ipLabel);
		
		custom.putConstraint(SpringLayout.WEST, emailText, 5, SpringLayout.EAST, emailLabel);
		custom.putConstraint(SpringLayout.NORTH, emailText, 5, SpringLayout.SOUTH, ipText);
		custom.putConstraint(SpringLayout.EAST, emailText, 100, SpringLayout.WEST, emailText);
		
		labels.setLayout(custom);
		labels.add(emailLabel);
		labels.add(emailText);
		labels.add(ipLabel);
		labels.add(ipText);
		
		//start buttons
		JButton save = new JButton("Save");
		JButton cancel = new JButton("Cancel");
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(save);
		ActionListener saveListen = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conf_in.setIP(ipText.getText());
				conf_in.setEmail(emailText.getText());
				conf.dispose();
			}
		};
		save.addActionListener(saveListen);
		
		buttons.add(cancel);
		ActionListener cancelListen = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conf.dispose();
			}
		};
		cancel.addActionListener(cancelListen);
		//end buttons
		
		/*m_window.add(label,"North");
		m_window.add(text1);
		m_window.add(buttons,"South");*/
		
		
		conf.add(label,"North");
		conf.add(labels);
		conf.add(buttons,"South");
			
	}
	
	/*public void init(){
		this.setVisible(true);
	}*/
	
}
