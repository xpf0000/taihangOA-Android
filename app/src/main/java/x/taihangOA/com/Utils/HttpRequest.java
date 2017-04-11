package x.taihangOA.com.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import util.XNetUtil;

public class HttpRequest {
	public String doGet(String urlStr)  {

		XNetUtil.APPPrintln("url: "+urlStr);

		String state = "";
		URL url;
		try {


			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(15000);
			// 设置发送请求的方式
			conn.setRequestMethod("GET");
			// 设置返回信息的格式类型
			conn.connect();
			// 正常时返回的状态码为200
			if (conn.getResponseCode() == 200) {
				// 获取返回的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String line;
				// 输出返回的信息
				while ((line = br.readLine()) != null) {
					state += line;
					
				}
				br.close();
			} else {
				state="网络超时";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			state="网络超时";
			e.printStackTrace();
		}
		
		return state;
	}
}