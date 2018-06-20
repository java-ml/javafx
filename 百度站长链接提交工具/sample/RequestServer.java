package sample;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.HashMap;

import java.util.Map;

public class RequestServer {
	private String uri=null;
	private HashMap<String, String > req=null;
	private static HttpURLConnection con=null;
	private  byte[] bs;
	private StringBuffer buf=new StringBuffer();
	private String charset="utf-8";
	private char ch[]=new char[4096];
	private static String method="POST";
	
	
	public void setCharset(String charset) {
		this.charset = charset;
	}

		
	public   void setMethod(String Method) {
		
		this.method=Method.toUpperCase();
	}
 
	public RequestServer(String Url,HashMap<String, String > req) throws Exception {
		this.uri=Url;
		this.req=req;
		URL url=new URL(uri);
		con=(HttpURLConnection) url.openConnection();

		if(req!=null){
			for(Map.Entry<String , String >kvset :req.entrySet())
			con.setRequestProperty(kvset.getKey(), kvset.getValue());
		}
		System.out.println("init");
		 
	}
	public void SetPutData(String str) {
		
		System.out.println("setputdata");
		bs= str.getBytes();
	}
	public StringBuffer Get( ) throws Exception {
		System.out.println("geting");
	 
	 int len=0;
	 con.setRequestMethod(method);
     con.setDoInput(true);
     con.setDoOutput(true);
   
	OutputStream out=con.getOutputStream();
	out.write( bs);
	
	InputStream in=con.getInputStream();
	InputStreamReader inr=new InputStreamReader(in,charset);
	buf.setLength(0);
	
	while ((len=inr.read(ch))!=-1) {
		buf.append(ch, 0, len);
	}
	System.out.println("end");
	in.close();
	out.close();
	con.disconnect();

	return buf;
	
 }
}
