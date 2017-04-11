package x.taihangOA.com.Utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	/*
	 * 截取图片地址
	 * Imageurl为解析到的的URL地址若不为空开始截取若为空不截取反回一个空的list
	 * Mark 截取的标识
	 */
	public List<String> getImageurl(String Imageurl,String Mark) {
		List<String> list  =new ArrayList<>();;
		 if(Imageurl!=null){
			 String[] imageul = Imageurl.split(Mark);
			 for (int i = 0; i < imageul.length; i++) {
				
				 String url=imageul[i];
				 list.add(url);
			}
		 }
		return list;
	}

}
