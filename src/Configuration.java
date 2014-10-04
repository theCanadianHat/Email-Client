import java.io.Serializable;

public class Configuration 
implements Serializable{

	private static final long serialVersionUID = 4942171071421921726L;
	private String m_email = "Please Configure";
	private String m_smtpIP = "Please Configure";
	
	public Configuration(String email_in, String IP_in){
		m_email = email_in;
		m_smtpIP = IP_in;
	}
	
	public Configuration() {
		//default, only used to initialize datastore's vector
	}

	public String getEmail() {
		return m_email;
	}
	
	public void setEmail(String m_email) {
		this.m_email = m_email;
	}
	
	public String getIP() {
		return m_smtpIP;
	}
	
	public void setIP(String m_smtpIP) {
		this.m_smtpIP = m_smtpIP;
	}
	
}
