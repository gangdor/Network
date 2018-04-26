package day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Http1 {

	public static void main(String[] args){
		/*InetAddress ia = null;
		ia = InetAddress.getByName("localhost");
		System.out.println(ia.getHostAddress());
		System.out.println(ia.getHostName());
*/		
		URL url = null;
		String address = "http://localhost:8000/index.html";
		
		try {
			url = new URL(address);	
		}
		catch(MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader in = null; //단어 단위
		BufferedReader br = null; //행단위
		StringBuffer sb= new StringBuffer();//StringBuffer는 StringBuffer의 내용을 지속변경 가능하다
		String str = null;
		
		try {
			in = new InputStreamReader(url.openStream());
			br = new BufferedReader(in);
			while(true) {
				str = br.readLine();
				if(str == null)
					break;
				sb.append(str+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close(); //반드시 finally에서 close
			} catch (IOException e) {
				e.printStackTrace();
			} //
		}
		System.out.println(sb.toString());
		
		
	}

}
