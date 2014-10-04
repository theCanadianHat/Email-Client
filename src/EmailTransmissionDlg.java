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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;


public class EmailTransmissionDlg
extends JDialog{
	
	

	public EmailTransmissionDlg(Frame owner, Object toEmail_in, Configuration config ){//, Object fromEmail_in){
		super(owner, "Email Transmission",true);
		this.create(toEmail_in, config);
	}

	private void create(Object toEmail_in, Configuration conf){
		final JFrame email = new JFrame("Email body");
        email.setLayout(new BorderLayout());
    	email.setSize(600,600);
        email.setVisible(true);
        
        //label area for north//
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel holder = new JPanel();
        JPanel middle = new JPanel();
        
        top.setLayout(new FlowLayout());
        middle.setLayout(new FlowLayout());
        bottom.setLayout(new FlowLayout());
        
        JSplitPane fields1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
    							top, holder);
        JSplitPane fields2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				middle, bottom);
        
        JLabel fromlabel = new JLabel("From:");
        JLabel tolabel = new JLabel("To:");
        JLabel subjlabel = new JLabel("Subject:");
        
        //----can you work your custom magic on this bit so the text areas look nice?----//
        JTextArea fromField = new JTextArea(""+ conf.getEmail());
        fromField.setMinimumSize(getMaximumSize());
        fromField.setEditable(false);
        JTextArea toField = new JTextArea((String) toEmail_in);
        toField.setMinimumSize(getMaximumSize());
        JTextArea subjField = new JTextArea("Subject");
        subjField.setMinimumSize(getMaximumSize());
      //----can you work your custom magic on this bit so the text areas look nice?----//
        
        top.add(fromlabel);
        top.add(fromField);
        middle.add(tolabel);
        middle.add(toField);
        bottom.add(subjlabel);
        bottom.add(subjField);

        holder.add(fields2);
        
        JLabel label = new JLabel("Sending an E-mail to:");
        email.add(fields1, "North");
        
        //text area for center//
		JTextArea emailbody = new JTextArea();
		emailbody.setLineWrap(true);
		emailbody.setWrapStyleWord(true);
		JScrollPane pane = new JScrollPane(emailbody);
		email.add(pane);
        
        //buttons for south panel//
        JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		JButton send = new JButton("Send");
		JButton cancel = new JButton("Cancel");

		//this does "save"
		ActionListener sendListen = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "You clicked Send.");
			}
		};

		//this does "edit"
		ActionListener cancelListen = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				email.dispose();
			}
		};
		
		send.addActionListener(sendListen);
		cancel.addActionListener(cancelListen);
		buttons.add(send);
		buttons.add(cancel);
        email.add(buttons, "South");
	};;
	

}
