package service;

import javax.servlet.http.Cookie;

import org.owasp.esapi.ESAPI;

public class DecryptorService {
	
    public static String decryptCookie(Cookie cookie) {
    	String decrypted = "";
    	try {
			decrypted = ESAPI.encryptor().decrypt(cookie.getValue());
			System.out.println("Decrypted value is: " + decrypted);
			}catch(Exception e) {
				System.out.println("I cri");
			}
    	return decrypted;
    }
}
