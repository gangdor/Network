package day04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
	ServerSocket serversocket;
	boolean flag = true;
	ArrayList<DataOutputStream> list = new ArrayList<>();
	
	public Server() {
		//ServerSocket Create
	}
	
	@Override
	public void run() {
		// Accept Client Connection
		
	}

	public static void main(String[] args) {
		//Server for Multichatting [2018. 05. 01]
		
	}
	
	//Inner Class Receiver & Sender
	class Receiver extends Thread{
		//여러 클라이언트가 서버에 들어오면 가장 먼저 거치는 스레드
		InputStream in;
		DataInputStream din;
		OutputStream out;
		DataOutputStream dout;
		
		public Receiver(Socket socket) {
			try {
				in = socket.getInputStream();
				din = new DataInputStream(in);
				out = socket.getOutputStream();
				dout = new DataOutputStream(dout);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end Receiver
	}

	class Sender extends Thread{
		
	}
}
