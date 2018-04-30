package day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {

	String ip;
	int port;

	Socket socket;
	InputStream in;
	InputStreamReader inr;
	BufferedReader br;

	public Client1() {

	}

	public Client1(String ip, int port) throws UnknownHostException, IOException {
		this.ip = ip;
		this.port = port;
		connect();
		// MAIN에서 실행하ㄷㄴ
		startClient();
	}

	private void connect() {
		while (true) {
			try {
				socket = new Socket(ip, port);
				if (socket != null && socket.isConnected()) {
					break;
				}
			} catch (IOException e) {
				System.out.println("Re-Try");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) { }
			}

		}
		System.out.println("Connected");

	}

	public void startClient() throws UnknownHostException, IOException {
		try {
			in = socket.getInputStream();
			inr = new InputStreamReader(in);
			br = new BufferedReader(inr);
			String str = br.readLine();
			System.out.println(str);
		} catch (UnknownHostException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			br.close();
			socket.close();
		}
	}

	public static void main(String[] args) {
		Client1 client = null;

		try {
			client = new Client1("70.12.114.130", 7777);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
