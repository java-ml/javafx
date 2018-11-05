package sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestServer {

	private String charset = "gb2312";

	private String method = "POST";
	private CookieManager manager = new CookieManager();

	public void setCharset(String charset) {
		this.charset = charset;
	}
public String getCharset() {
	return charset;
}
	public void setMethod(String Method) {

		this.method = Method.toUpperCase();
	}



	public RequestServer() {

		HttpURLConnection.setFollowRedirects(false);
		// 设置cookie策略，只接受与你对话服务器的cookie，而不接收Internet上其它服务器发送的cookie
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
		CookieHandler.setDefault(manager);
		
		System.out.println("init");

	}

	private byte[] SetPutData(String str) {

		System.out.println("setputdata");
		try {
			if (str == null) {
				return "".getBytes();
			}
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "".getBytes();
		}
	}

	public StringBuffer Get(String url, String data, HashMap<String, String> req) throws IOException  {
		HttpURLConnection con = null;
		InputStreamReader inr =null;
		System.out.println("geting");
		StringBuffer buf = new StringBuffer();
		char ch[] = new char[4096];
		byte bs[] = SetPutData(data);
		try {
			
			con = (HttpURLConnection) new URL(url).openConnection();

			if (req != null) {
				for (Map.Entry<String, String> kvset : req.entrySet())
					con.setRequestProperty(kvset.getKey(), kvset.getValue());
			}

			int len = 0;
			con.setRequestMethod(method);
			con.setDoInput(true);
			con.setDoOutput(true);
			OutputStream out = null;
			if (bs.length > 0) {
				out = con.getOutputStream();

				out.write(bs);
				out.close();
			}
			InputStream in = con.getInputStream();
			inr = new InputStreamReader(in, charset);
			 
			while ((len = inr.read(ch)) != -1) {
				buf.append(ch, 0, len);
			}

			return buf;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new StringBuffer("发生错误：").append(e.toString()).append("\t状态码:").append(con.getResponseCode());
		}finally {

			if(inr!=null&&con!=null) {
				inr.close();
				con.disconnect();
			}

			
		}

	}

	public LoginType Login(String uri, HashMap<String, String> req, String data) {
		HttpURLConnection conn = null;
		StringBuffer buf = new StringBuffer();
		char[] ch = new char[1024];
		URL url = null;
		int len = 0;
		InputStreamReader inr = null;
		try {

			url = new URL(uri);
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5000);
			if (req != null) {
				for (Map.Entry<String, String> kvset : req.entrySet())
					conn.setRequestProperty(kvset.getKey(), kvset.getValue());
			}
			OutputStream out = conn.getOutputStream();
			out.write(data.getBytes(charset));
			InputStream in = conn.getInputStream();
			inr = new InputStreamReader(in, charset);

			while ((len = inr.read(ch)) != -1) {
				buf.append(ch, 0, len);
			}
			return new LoginType(conn.getResponseCode(), buf, manager, uri);
		} catch (Exception e) {

			return new LoginType(-1, uri, e.toString());
		} finally {
			conn.disconnect();
			try {
				if (inr != null)
					inr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public CookieManager InitCookie(String uri, HashMap<String, String> req) {

		HttpURLConnection conn = null;

		try {

			URL url = new URL(uri);
			
			conn = (HttpURLConnection) url.openConnection();

			if (req != null) {
				for (Map.Entry<String, String> kvset : req.entrySet())
					conn.setRequestProperty(kvset.getKey(), kvset.getValue());
			}

			conn.getInputStream().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		conn.disconnect();
		return manager;
	}

	public CookieManager InitCookie(CookieManager manager) {
		CookieStore store = this.manager.getCookieStore();
		CookieStore mStore = manager.getCookieStore();
		for (URI uri : mStore.getURIs()) {
			List<HttpCookie> cookies = mStore.get(uri);
			for (HttpCookie cookie : cookies) {
				
				store.add(uri, cookie);
			}
		}
		return manager;
	}

	public CookieManager getCookieManager() {
		return manager;

	}
}
