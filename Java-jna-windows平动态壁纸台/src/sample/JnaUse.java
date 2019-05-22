package sample;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;

public class JnaUse {
    private final LPARAM lp = new LPARAM(0);
    private final WPARAM wp = new WPARAM(0);
    private static final User32 user32 = User32.INSTANCE;
    private static  HWND workerw;
    public  boolean setPreantMyjna(String title){

        if(DllGetSysInfo.INSTANCE.setWin(title)==0)return false;
        return true;
    }
    public int setPreant(final String title){
    	int lable=1;
        final HWND hwnd = user32.FindWindow("Progman","Program Manager");
        user32.SendMessageTimeout(hwnd, 0x052c, wp, lp, 500, 0x3e8, null);
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            public boolean callback(HWND hWnd, Pointer arg1) {
                HWND defview = user32.FindWindowEx(hWnd, null, "SHELLDLL_DefView", null);
                if (defview != null) {
                    workerw = user32.FindWindowEx(null, hWnd, "WorkerW", null);
                }
                return true;
            }
        }, null);
       
        HWND h3 = user32.FindWindow(null, title);
        user32.ShowWindow(h3, 1);
        if(workerw==null){
            user32.SendMessageTimeout(hwnd, 0x052c, wp, lp, 500, 0x3e8, null);
            lable+=2;
        }else user32.ShowWindow(workerw, 0);
        if (h3==null) {
			lable+=4;
		}
        user32.SetParent(h3, hwnd);
        return lable;
    }
    public static void stop() {
		user32.ShowWindow(workerw, 1);
	}
}

interface DllGetSysInfo extends Library {
    static  DllGetSysInfo INSTANCE = (DllGetSysInfo) Native.load("/sample/myjna.dll",DllGetSysInfo.class);
    public int  setWin(String str);

}