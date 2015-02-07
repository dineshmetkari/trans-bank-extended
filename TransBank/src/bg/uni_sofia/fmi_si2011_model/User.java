package bg.uni_sofia.fmi_si2011_model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String mUsername;
	private List<String> mIBANlist;
	private List<String> mIBANlog;
	public String getmUsername() {
		return mUsername;
	}
	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	public List<String> getmIBANlist() {
		return mIBANlist;
	}
	public void setmIBANlist(List<String> mIBANlist) {
		this.mIBANlist = mIBANlist;
	}
	public List<String> getmIBANlog() {
		return mIBANlog;
	}
	public void setmIBANlog(List<String> mIBANlog) {
		this.mIBANlog = mIBANlog;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String mUsername, List<String> mIBANlist, List<String> mIBANlog) {
		super();
		this.mUsername = mUsername;
		this.mIBANlist = mIBANlist;
		this.mIBANlog = mIBANlog;
	}
	public static User getInstance() {
		List<String> myIBANs = new ArrayList<>();
		myIBANs.add("BG1234567890ZZ");
		myIBANs.add("BG1234567890ZA");
		myIBANs.add("BG1234567890ZB");
		myIBANs.add("BG1234567890ZC");
		myIBANs.add("BG1234567890ZD");

		List<String> IBANlog = new ArrayList<>();
		IBANlog.add("UK098765431AA");
		IBANlog.add("UK098765431AZ");
		IBANlog.add("UK098765431AY");
		IBANlog.add("UK098765431AX");
		IBANlog.add("UK098765431AW");

		return new User("Nikola", myIBANs, IBANlog);
	}
}
