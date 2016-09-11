package ph.edu.usjr.team2.itrace.web.core;

import java.security.MessageDigest;

public class PasswordEncyrptor {

	private String password;
	
	public PasswordEncyrptor(){}
	
	public PasswordEncyrptor(String password){
		this.password = password;
	}
	
	public String start(String password){
		return byteArrayToHexString(encrypt(password));
	}
	
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] encrypt(String x) {
		MessageDigest d = null;
		try {
			d = MessageDigest.getInstance("SHA-1");
			d.reset();
			d.update(x.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return d.digest();
	}
}
