import java.io.Serializable;


public class Contact 
implements Serializable{

	private static final long serialVersionUID = 6997205342443653942L;
	private String m_name;
	private String m_address;
	private String m_phone;
	private String m_email;
	
	public Contact(String name_in, String add_in, String phone_in, String email_in){
		m_name = name_in;
		m_address = add_in;
		m_phone = phone_in;
		m_email = email_in;
	}
	
	public String getName() {
		return m_name;
	}
	
	public void setName(String name_in) {
		this.m_name = name_in;
	}
	
	public String getAddress() {
		return m_address;
	}
	
	public void setAddress(String add_in) {
		this.m_address = add_in;
	}
	
	public String getPhone() {
		return m_phone;
	}
	
	public void setPhone(String phone_in) {
		this.m_phone = phone_in;
	}
	
	public String getEmail() {
		return m_email;
	}
	
	public void setEmail(String email_in) {
		this.m_email = email_in;
	}
	
}
