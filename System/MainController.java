import java.awt.Robot; 
import java.awt.event.KeyEvent;

public class MainController{

	public static void run(String command){
		
		int c = Integer.parseInt(command);
		Robot robot = null;
		Runtime r = Runtime.getRuntime();
		try{
			robot = new Robot();
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

		try{
			switch(c)
			{
				case 1:
					// Shutdown system
					System.out.println(1);
	 				//r.exec("shutdown -s");
					break;
				case 2:
					 // Restart system
					System.out.println(2);
	 				//r.exec("shutdown -r");
					break;
				case 3:
					// Hibernate system
					System.out.println(3);
					//r.exec("shutdown -h");
					break;
				case 4:
					System.out.println(4);
					// Sleep system
					//r.exec("");
			}
		}
		catch(Exception e){

		}

		if(c == 1){
			//Process proc = runtime.exec("shutdown -s -t 0");
			//robot.keyPress(KeyEvent.VK_ALT);
	        //robot.keyPress(KeyEvent.VK_TAB);
		}
		else{
			//robot.keyRelease(KeyEvent.VK_ALT);
	        //robot.keyRelease(KeyEvent.VK_TAB);
		}
	}

	public static void nav(String Command){

	}

	public static void editor(String Command){

	}

	public static void browser(String Command){

	}

	public static void mouse(String Command){
		 
	}
}