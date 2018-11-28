import java.util.*;
import java.io.*;
import java.net.*;

public class Server implements Runnable{

	private Socket socket;

	public Server(){}

	public Server(Socket s){
		this.socket = s;
	}

	@Override
	public void run(){
		//System.out.println(Thread.currentThread().getId());
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String command = br.readLine();
			MainController.run(command);
			System.out.println("In Server "+command);
		}
		catch(Exception e){

			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(5432);
		System.out.println("Server running...");
		while(true){
			Socket socket = server.accept();
			Server s = new Server(socket);
			Thread t = new Thread(s);
			t.start();
		}
	}

}