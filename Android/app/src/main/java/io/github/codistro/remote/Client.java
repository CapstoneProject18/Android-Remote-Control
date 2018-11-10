package io.github.codistro.remote;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public String address;
	public int port;

	public Client(String address, int port){
		this.address = address;
		this.port = port;
	}

	public void send(String message) throws Exception{
		Socket socket = new Socket(address, port);
		OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
		PrintWriter pw = new PrintWriter(os);
		pw.write(message);
		pw.flush();
		socket.close();
	}

	public static void main(String[] args) throws Exception {

	}
}