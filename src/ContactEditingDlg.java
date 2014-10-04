import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;


public class ContactEditingDlg 
extends JDialog{
	private JOptionPane window;
	//final ContactEditingDlg cntct;

	
	//this is for add
	public ContactEditingDlg(Contact cont, DataStore ds, Frame owner){
		super(owner,"Add Contact", true);
		//cntct = this;
		this.create(cont, owner, ds);
	}
	
	//this is for edit
	public ContactEditingDlg(Frame owner, Contact cont, DataStore ds){
		super(owner, "Edit Contact", true);
		//cntct = this;
		this.create(cont, owner, ds);
	}
		
	private void create(final Contact cont, final Frame owner, final DataStore datst){
		final JFrame cntct = new JFrame("poopy");
		//cntct = this;
		cntct.setSize(300, 300);
		cntct.setLocation(100, 100);
		
		
		/*JTextArea label = new JTextArea("ADD stuff");
		//"Change your configurations here."+ "\nChanges will only be affective if \"Save\" is clicked.");
		label.setEditable(false);
		label.setLineWrap(true);
		label.setWrapStyleWord(true);
		label.setBackground(this.getBackground());*/
		
		JPanel labels = new JPanel();
		SpringLayout custom = new SpringLayout();
		
		JLabel nameLabel = new JLabel("Name:");
		JLabel addLabel = new JLabel("Adress:");
		JLabel phoneLabel = new JLabel("Phone:");
		JLabel emailLabel = new JLabel("Email:");
		
		final JTextArea nameText = new JTextArea((String) cont.getName());
		final JTextArea addText = new JTextArea((String) cont.getAddress());
		final JTextArea phoneText = new JTextArea((String) cont.getPhone());
		final JTextArea emailText = new JTextArea((String) cont.getEmail());
		
		//label positions
		custom.putConstraint(SpringLayout.WEST, nameLabel, 5, SpringLayout.WEST, labels);
		custom.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, labels);
		
		custom.putConstraint(SpringLayout.WEST, nameText, 5, SpringLayout.EAST, addLabel);
		custom.putConstraint(SpringLayout.NORTH, nameText, 5, SpringLayout.NORTH, labels);
		custom.putConstraint(SpringLayout.EAST, nameText, 100, SpringLayout.WEST, nameText);
		
		custom.putConstraint(SpringLayout.WEST, addLabel, 5, SpringLayout.WEST, labels);
		custom.putConstraint(SpringLayout.NORTH, addLabel, 5, SpringLayout.SOUTH, nameLabel);
		
		custom.putConstraint(SpringLayout.WEST, addText	, 5, SpringLayout.EAST, addLabel);
		custom.putConstraint(SpringLayout.NORTH, addText, 5, SpringLayout.SOUTH, nameText);
		custom.putConstraint(SpringLayout.EAST, addText, 100, SpringLayout.WEST, addText);
		
		custom.putConstraint(SpringLayout.WEST, phoneLabel, 5, SpringLayout.WEST, labels);
		custom.putConstraint(SpringLayout.NORTH, phoneLabel, 5, SpringLayout.SOUTH, addLabel);
		
		custom.putConstraint(SpringLayout.WEST, phoneText, 5, SpringLayout.EAST, addLabel);
		custom.putConstraint(SpringLayout.NORTH, phoneText, 5, SpringLayout.SOUTH, addText);
		custom.putConstraint(SpringLayout.EAST, phoneText, 100, SpringLayout.WEST, phoneText);
		
		custom.putConstraint(SpringLayout.WEST, emailLabel, 5, SpringLayout.WEST, labels);
		custom.putConstraint(SpringLayout.NORTH, emailLabel, 5, SpringLayout.SOUTH, phoneLabel);
		
		custom.putConstraint(SpringLayout.WEST, emailText, 5, SpringLayout.EAST, addLabel);
		custom.putConstraint(SpringLayout.NORTH, emailText, 5, SpringLayout.SOUTH, phoneText);
		custom.putConstraint(SpringLayout.EAST, emailText, 100, SpringLayout.WEST, emailText);
		
		JButton save = new JButton("Save");
		JButton cancel = new JButton("Cancel");
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(save);
		ActionListener saveListen = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cont.setName(nameText.getText());
				cont.setAddress(addText.getText());
				cont.setPhone(phoneText.getText());
				cont.setEmail(emailText.getText());
				cntct.dispose();
				
			}
		};
		save.addActionListener(saveListen);
		
		buttons.add(cancel);
		ActionListener cancelListen = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cntct.dispose();
			}
		};
		cancel.addActionListener(cancelListen);
		
		labels.setLayout(custom);
		labels.add(nameLabel);
		labels.add(nameText);
		labels.add(addLabel);
		labels.add(addText);
		labels.add(phoneLabel);
		labels.add(phoneText);
		labels.add(emailLabel);
		labels.add(emailText);
		
		//cntct.getContentPane().setLayout(new BorderLayout());
		//cntct.add(label,"North");
		cntct.add(labels);
		cntct.add(buttons,"South");
		cntct.setVisible(true);
		//this.add(cntct);
		
	}
}
