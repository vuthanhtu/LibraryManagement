package bookstoreonline.model;
import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class User implements Serializable{
	protected int id;
	protected String username;
	protected String password;
	protected String name;
	protected String dob;
	protected String email;
	protected String dt;
	protected boolean checkadmin;
	public User() {
		
	}
	public User(String username,String password,String name,String dob,String email,String dt,boolean checkadmin) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.dt = dt;
		this.checkadmin = checkadmin;
	}
	public User(int id,String username,String password,String name,String dob,String email,String dt,boolean checkadmin) throws ParseException {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
		this.dob = new SimpleDateFormat("dd/MM/yyyy").format(date);
		this.email = email;
		this.dt = dt;
		this.checkadmin = checkadmin;
	}
	public User(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public boolean isCheckadmin() {
		return checkadmin;
	}
	public void setCheckadmin(boolean checkadmin) {
		this.checkadmin = checkadmin;
	}
}
