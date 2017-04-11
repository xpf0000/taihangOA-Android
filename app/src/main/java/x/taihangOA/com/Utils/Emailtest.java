package x.taihangOA.com.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Emailtest {
	/**
	  * 验证邮箱地址是否正确
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	   String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	   Pattern regex = Pattern.compile(check);
	   Matcher matcher = regex.matcher(email);
	   flag = matcher.matches();
	  }catch(Exception e){
	   flag = false;
	  }
	  
	  return flag;
	 }
	public static boolean checkphone(String number){
		boolean flag = false;
		try{
		String check = "^(1)?[0-9]{10}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(number);
		flag = matcher.matches();
		}catch(Exception e) {
			flag = false;
		}
		return flag;
	}
	public static boolean checktelphone(String number){
		boolean flag = false;
		try{
			String check = "^(0){1}(\\d{2,3}){1}(\\d{6,8}){1}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(number);
			flag = matcher.matches();
		}catch(Exception e) {
			flag = false;
		}
		return flag;
	}

}
