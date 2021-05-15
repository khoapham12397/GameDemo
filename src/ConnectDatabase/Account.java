package ConnectDatabase;

import java.util.ArrayList;

public class Account {
	private String username;
	private String password;
	private float maxpoint;
	private ArrayList<Float> scores;
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
	public float getMaxpoint() {
		return maxpoint;
	}
	public void setMaxpoint(float maxpoint) {
		this.maxpoint = maxpoint;
	}
	public ArrayList<Float> getScores() {
		return scores;
	}
	public void setScores(ArrayList<Float> scores) {
		this.scores = scores;
	}
	
	public Account(String username,String password) {
		this.username=new String(username);
		this.password=new String(password);
	}
}
