import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataStore 
implements Serializable{

	private static final long serialVersionUID = 7368861865781879497L;
	private Vector<Configuration> m_configs = new Vector<Configuration>();
	private Vector<Contact> m_conts = new Vector<Contact>();
	private File dataFile;
	
	//----------------Singleton---------------//
	private static DataStore m_singleton = null;
	
	public static DataStore getInstance() {
		if(m_singleton == null) {
			m_singleton = new DataStore();
			m_singleton.m_configs.add(new Configuration());
			m_singleton.dataFile = new File("info.dat");
		}
		return(m_singleton);
	}
	//-------------End Singleton--------------//
	
	
	public void save() 
	throws IOException{
		FileOutputStream f_out = new FileOutputStream(m_singleton.dataFile);
		ObjectOutputStream ob_out = new ObjectOutputStream(f_out);
		ob_out.writeObject(m_singleton);
		ob_out.close();
		System.out.println("SAVED");
	}

	
	public void load() 
			 throws IOException, ClassNotFoundException{
			 	 
			 	 if (m_singleton.dataFile.exists()) {
			 	  
			 	 FileInputStream f_in = new FileInputStream(m_singleton.dataFile);
			 	 ObjectInputStream ob_in = new ObjectInputStream(f_in);
			 	 m_singleton = (DataStore) ob_in.readObject();
			 	 ob_in.close();
			 	 System.out.println("lOADED");
			 	 }else{
			 		 m_singleton.save();
			 		 m_singleton.load();
			 	 }
			 	

			 }
	
	public void addContact(String name,
						   String add,
						   String phone,
						   String email){
		Contact temp = new Contact(name, add, phone, email);
		m_conts.add(temp);
	}
	
	public void addContact(Contact ct){
		m_conts.add(ct);
	}
	
	public void addConfig(String mail,
						  String IP){
		Configuration temp = new Configuration(mail, IP);
		m_configs.add(temp);
	}
	
	public void removeContact(Contact other){
		m_conts.remove(other);
	}
	
	/*public Contact getContact(Contact person){
		return (m_conts.get(m_conts.lastIndexOf(person)));
	}*/
	public Contact getContact(int index){
		return (m_conts.get(index));
	}
	public Configuration getConfig(){
		return(m_configs.get(0));
	}
	
	public Configuration getConfig(Configuration config){
		return (m_configs.get(m_configs.lastIndexOf(config)));
	}
	
	public int getConfSize(){
		return(m_configs.size());
	}
	
	public int getContSize(){
		return(m_conts.size());
	}
	
}
	
