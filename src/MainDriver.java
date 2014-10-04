import java.io.IOException;


public class MainDriver {

	public static void main(String[] args) {
		
		DataStore dstore = DataStore.getInstance();
		
		//dstore.addContact("","","","");
		//dstore.addConfig("Please Configure", "Please Configure");
		
		try {
			dstore.load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MainFrame frame = new MainFrame("SMTP Client", dstore);
		frame.setVisible(true);
		
	}

}
