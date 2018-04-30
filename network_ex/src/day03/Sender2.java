package day03;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

class Sender2 implements Runnable {
	Socket socket;
	OutputStream out = null;
	OutputStreamWriter outw = null;
	
	public Sender2(Socket socket) throws IOException {
		this.socket = socket;
		out = socket.getOutputStream();
		outw = new OutputStreamWriter(out);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);						
			outw.write("�ȳ�");
		} catch (Exception e) {

		} finally {
			// thread�ȿ����� Exception�� throw�� ���� �� ���� ������
			// try-catch�� ó���ؾ��Ѵ�.
			if (outw != null)
				try {
					outw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}