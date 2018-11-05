package sample;


import java.io.Serializable;
import java.net.CookieManager;


public class LoginType implements Serializable{
	private int code;
	private StringBuffer buffer;
	private CookieManager manager;
	private String uri;
	private String Exception;
	public LoginType(int code, StringBuffer buffer, CookieManager manager,String uri) {
		super();
		this.code = code;
		this.buffer = buffer;
		this.manager = manager;
		this.uri=uri;
	}
	public LoginType(int i, String Exception) {
		this.code = code;
		this.Exception=Exception;
	}
 
	public LoginType(int code, String uri, String exception) {
		super();
		this.code = code;
		this.uri = uri;
		Exception = exception;
	}
	public int getCode() {
		return code;
	}
	public StringBuffer getBuffer() {
		return buffer;
	}
	public CookieManager getManager() {
		return manager;
	}
	public String getUri() {
		return uri;
	}
	public String getException() {
		return Exception;
	}
	@Override
	public String toString() {
		return "LoginType [code=" + code + ", buffer=" + buffer + ", manager=" + manager + ", uri=" + uri
				+ ", Exception=" + Exception + "]";
	}
	 
}
