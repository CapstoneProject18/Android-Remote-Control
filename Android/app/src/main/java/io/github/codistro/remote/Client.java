package io.github.codistro.remote;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public String address = "192.168.43.117";
	public int port = 5432;

	public Client(){}

	public Client(String address){
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