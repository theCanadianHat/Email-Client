import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;


public class SystemInformationDlg
extends JDialog{
	
	//final SystemInformationDlg about;
	
	SystemInformationDlg(Frame owner){
		super(owner,"STMP Client About",true);
		//about = this;
		this.make();
	}

	public void make(){
	 SpringLayout layout = new SpringLayout();
	 
	 
	 this.setSize(300, 300);
	 this.setResizable(false);
	 this.setLocation(300,300);
	 //about.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
	 
	 JTextArea info = new JTextArea("STMP Client version 1.4\n"+
	                          "Created by: Daniel Willard and Noah Herron");
	 info.setEditable(false);
	 info.setBackground(this.getBackground());
	 info.setWrapStyleWord(true);
	 info.setLineWrap(true);
	 this.add(info);
	 layout.putConstraint(SpringLayout.NORTH, info, 150, SpringLayout.NORTH, this);
	 layout.putConstraint(SpringLayout.WEST, info, 5, SpringLayout.WEST, this);
	 layout.putConstraint(SpringLayout.EAST, info, 5, SpringLayout.EAST, this);
	 
	 
	 this.setVisible(true);
	}
	
}
