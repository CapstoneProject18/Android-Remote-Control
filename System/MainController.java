import java.awt.Robot; 
import java.awt.event.KeyEvent;

public class MainController{

	public static void run(String command){
		
		int c = Integer.parseInt(command);
		Robot robot = null;
		try{
			robot = new Robot();
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

		if(c == 1){
			robot.keyPress(KeyEvent.VK_ALT);
	        robot.keyPress(KeyEvent.VK_TAB);
		}
		else{
			robot.keyRelease(KeyEvent.VK_ALT);
	        robot.keyRelease(KeyEvent.VK_TAB);
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