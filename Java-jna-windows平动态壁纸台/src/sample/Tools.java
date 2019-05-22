package sample;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.URL;
import java.net.URLDecoder;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public  class Tools {
     private static class getTools{
    	 static final Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
    	 static final Stage s2=new Stage();
    	 static final GcBg bg=new GcBg(size.width,size.height); 
    	 static final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean(); 
      }
     public static Image[] getDefImages() {
    	 Image images[]= {new Image("file:///"+Main.class.getResource("/sample/bg.jpg").getPath())};
    	
			return images;
		}   
		public static Dimension getSize() {
			return getTools.size;
		}
		public static long getMemory() {
			return getTools.memoryMXBean.getHeapMemoryUsage().getUsed();
		}
		public static Stage getS2() {
			return getTools.s2;
		}
		public static GcBg getBg() {
			return getTools.bg;
		}
		

		public static Image[] getImageArray(String path){
			File file = null;
			try {
				file = new File(URLDecoder.decode(path,System.getProperty("file.encoding")).toString());
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			 Image img[];
			if(file==null) {
				return getDefImages();
			}
			File fs[]=null;
			fs=file.listFiles(new FilenameFilter() {	
				@Override
				public boolean accept(File dir, String name) {
					File file = new File(dir,name);
					
					return name.matches("[\\w\\W]*[(.png)|(.jpeg)|(.jpg)]$") && file.isFile();
				}
			});
			if(fs==null||fs.length==0) {
				
				return getDefImages();
			}
			img=new Image[fs.length];
		        for (int i=0;i<img.length;i++){
		        	img[i]=new Image(fs[i].toURI().toString());
		        }
		        return img;
		    }
		
}

