package sample;

import javafx.concurrent.Task;

import java.util.HashMap;

public class LoginTask extends Task  {

    String num, pass;
    private  RequestServer req = new RequestServer();
    static HashMap<String, String> map = new HashMap<>();
    static {

        map.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Mobile Safari/537.36");
        map.put("Accept", "text/html,application/xhtml+xml,"
                + "application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        map.put("Connection", "keep-alive");
        map.put("Upgrade-Insecure-Requests", "1");
        map.put("Accept-Language", "zh-CN,zh;q=0.8,en-GB;q=0.6,en;q=0.4");
        map.put("Date", "Mon, 29 Oct 2018 17:07:06 GMT");
        map.put("Transfer-Encoding", "chunked");
        map.put("Vary", "Accept-Encoding");

    }

    public void LoginTask(String num, String pass) {

        this.num = num;
        this.pass = pass;
    }

    @Override
    protected Object call() throws Exception {
          loginType(num, pass);

        return null;
    }

    public RequestServer loginType(String num, String pass) {
        try {
            req.setMethod("POST");
            req.setCharset("gb2312");
            req.InitCookie("http://219.148.85.172:7788", map);
            LoginType cookies = req.Login("http://219.148.85.172:7788/loginAction.do", map, "zjh=" + num + "&mm=" + pass);
            if(cookies.getException()!=null)updateMessage(cookies.getException());
            else  if (cookies.toString().indexOf("学分制综合教务") != -1) updateMessage("登录成功");
            else if (cookies.toString().indexOf("你输入的证件号不存在，请您重新输入") != -1) updateMessage("你输入的证件号不存在，请您重新输入");
            else updateMessage("账号或者密码错误!");

            return req;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public synchronized RequestServer getType() {
        return req;
    }
}
