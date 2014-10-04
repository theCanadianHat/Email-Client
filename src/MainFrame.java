import java.awt.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class MainFrame 
extends JFrame{

	private static final long serialVersionUID = 2269971701250845501L;

	//JTextArea email_body;
	DataStore dstore;
	String initial = "Please Configure";

	final MainFrame main = this;
    ConfigurationDlg config;// = new ConfigurationDlg(main, false, dstore.getConfig());
    SystemInformationDlg about;
    //Configuration config;
    
	
	public MainFrame(String caption, DataStore ds) {
		super(caption);
		dstore = ds;
		//dstore.addConfig(initial, initial);
		//config = new ConfigurationDlg(main, false, dstore.getConfig());
		
		initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("mb.png");
		this.setIconImage(icon.getImage());
		this.setSize(800, 600);
		this.setup_menu();
	
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		this.fillLayout(dstore);
		
	}
	
	private void setup_menu(){
		JMenuBar menubar = new JMenuBar();
		  JMenu filemenu = new JMenu("File");
		  JMenu editmenu = new JMenu("Configuration");
		  JMenu helpmenu = new JMenu("Help");
		  
		  //close MainFrame when exit is clicked
		  JMenuItem fileItem1 = new JMenuItem("Exit");
		  fileItem1.addActionListener(new java.awt.event.ActionListener(){
			 public void actionPerformed(java.awt.event.ActionEvent e){
				/* try {
					dstore.save();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				 menuFileExitClicked(e);*/
				 try {
					   main.saveNquit();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception exc) {
						// TODO Auto-generated catch block
						exc.printStackTrace();
					}
			 }
		  });
		  
		  JMenuItem editItem1 = new JMenuItem("Configuration");
		  editItem1.addActionListener(new java.awt.event.ActionListener(){
			 public void actionPerformed(java.awt.event.ActionEvent e){
				Configuration conf = dstore.getConfig();
				config = new ConfigurationDlg(main, true, conf);
			 }
		  });
		  
		  JMenuItem helpItem1 = new JMenuItem("About");
		  helpItem1.addActionListener(new java.awt.event.ActionListener(){
			 public void actionPerformed(java.awt.event.ActionEvent e){
				 about = new SystemInformationDlg(main);
			 }
		  });
		  
		  
		  filemenu.add(fileItem1);
		  editmenu.add(editItem1);
		  helpmenu.add(helpItem1);
		  menubar.add(filemenu);
		  menubar.add(editmenu);
		  menubar.add(helpmenu);
		  this.setJMenuBar(menubar);
	}

	/*private static void menuFileExitClicked(java.awt.event.ActionEvent e){
		try{
			main.saveNquit();
		}catch(Exception excp){
			
		}
	}*/
	
	private void saveNquit()
	throws Exception{
		dstore.save();
		System.exit(0);
	}
	
	/*private static void menuEditConfigClicked(java.awt.event.ActionEvent e){
		new ConfigurationDlg(main);
	}*/
	
	private void fillLayout(final DataStore ds){
		
	    TableModel dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;
			public int getColumnCount() { return 4; }
	    	public int getRowCount() { return ds.getContSize();}
	    	public Object getValueAt(int row, int col) {
	        		Object temp = null;
	        		if (col == 0)
	        			temp = ds.getContact(row).getName();
	        		if (col == 1)
	        			temp = ds.getContact(row).getAddress();
	        		if (col == 2)
	        			temp = ds.getContact(row).getPhone();
	        		if (col == 3)
	        			temp = ds.getContact(row).getEmail();
	        				
	        		return temp;
	        	}
	    	//----New Code----//
	    	public void setValueAt(){
	    		
	    	}
	    	//----New Code----//
	    	
	    	String[] col_names = {"Name", "Address", "Phone", "Email"};
	    	
	    	public String getColumnName(int col) {
	            return col_names[col];
	        }
	      };
	      final JTable table = new JTable(dataModel);
	      //----NEW CODE----Does some of this need it's own class?----//
	      
	      //this part handles the doubleclick
	      //i'm thinking about making a new class just for this email window
	      //so i can pass the datavalues back and forth with a simple constructor.
	      //UPDATE:  made new class, seems to work so far
	      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      table.addMouseListener(new MouseAdapter() {	  
	          public void mouseClicked(MouseEvent e) {
	        	  if (e.getClickCount() == 2 && !e.isConsumed()) {
	        		  int selectedrow = table.getSelectedRow();
	        		  Object target = table.getValueAt(selectedrow, 3);
	        		  e.consume();
	        		  Configuration conf = dstore.getConfig();
	        		  EmailTransmissionDlg email = new EmailTransmissionDlg(main, target, conf);				
	        	 }
	          }
	          
	      //----END NEW CODE----//
	      });
	      JPanel buttons = new JPanel();
			buttons.setLayout(new FlowLayout());
			JButton add = new JButton("Add");
			JButton edit = new JButton("Edit");
			JButton del = new JButton("Delete");
			
			//this does "add"
			ActionListener addListen = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Contact cont = new Contact("", "", "", "");
					ContactEditingDlg addcontact = new ContactEditingDlg(cont, dstore, main);
					dstore.addContact(cont);
					table.clearSelection();
					table.revalidate();
					table.repaint();
				}
			};
			
			//this does "edit"
			ActionListener editListen = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.isRowSelected(table.getSelectedRow())){
					int selectedrow = table.getSelectedRow();
					Contact cont;
					cont = dstore.getContact(selectedrow);
					ContactEditingDlg editcontact = new ContactEditingDlg(main, cont, dstore);
					table.clearSelection();
					table.revalidate();
					table.repaint();
					}
				}
			};
			
			//this does "delete"
			ActionListener delListen = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.isRowSelected(table.getSelectedRow())){
						String[] choices = {"Yes","No"};
						//[0]this orginally
						int ans = JOptionPane.showOptionDialog(main, "Are you sure you want to delete that!!!", "HOLD UP!",
									           				   0, JOptionPane.WARNING_MESSAGE, null, choices, "No");
						//[2]tried using right side of above ans line in place of ans in if
						if(ans == JOptionPane.YES_OPTION){
							int selectedRow = table.getSelectedRow();
							Contact cont = dstore.getContact(selectedRow);
							dstore.removeContact(cont);
							table.clearSelection();
							table.revalidate();
							table.repaint();
							
						}
					}
				}
			};
			
			add.addActionListener(addListen);//(new JButton("Add"));
			edit.addActionListener(editListen);
			del.addActionListener(delListen);
			buttons.add(add);
			buttons.add(edit);
			buttons.add(del);
			this.add(buttons, "South");
			
	      JScrollPane scrollpane = new JScrollPane(table);
	      
	      //horizontal scrolling here?
	      this.add(scrollpane);
	}
	

}
