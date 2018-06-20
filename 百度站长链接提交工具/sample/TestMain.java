package sample;

import java.util.HashMap;

public class TestMain  {

	static HashMap<String , String> map =new HashMap<>();
static {
	map.put("User-Agent", "curl/7.12.1");
	map.put("Host", "data.zz.baidu.com");
	map.put("Content-Type", "text/plain");
}
	static RequestServer req = null;
	String url=null,str=null;



	public void setStr(String str) {
		this.str = str;
	}

	public void setUrl(String url) {
		this.url = url;
	}




	public StringBuffer start() {


			try {
				req = new RequestServer(url, map);

				req.setMethod("POST");
				req.SetPutData(str);

				return  req.Get();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return new StringBuffer("ex");

	}



}
