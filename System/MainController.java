import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.lang.reflect.Field;
import javax.swing.*;

public class MainController{

	public static void run(String command){

		String type = command.split("")[0];
		
		try{
			switch(type){
				case Service.POWER:
						power(command);
						break;
				case Service.KEYBOARD:
						keyboard(command);
						break;
				case Service.BROWSER:
						browser(command);
						break;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}

	public static void power(String command){
		Runtime r = Runtime.getRuntime();
		try{
			switch(command)
			{
				case Service.POWER_TURN_OFF:
					System.out.println("I am in "+command);
	 				//r.exec("shutdown -s");
					break;
				case Service.POWER_RESTART:
					System.out.println("I am in "+command);					
	 				//r.exec("shutdown -r");
					break;
				case Service.POWER_HIBERNATE:
					System.out.println("I am in "+command);					
					//r.exec("shutdown -h");
					break;
				case "4":
					System.out.println("I am in "+command);					
					//r.exec("");
			}
		}
		catch(Exception e){

		}
	}

	public static void browser(String command) throws Exception{
		Robot robot = new Robot();
		switch (command) {
			case Service.BROWSER_TAB_NAV_LEFT:
				robot.keyPress(KeyEvent.VK_CONTROL); 
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_TAB);

				robot.keyRelease(KeyEvent.VK_CONTROL); 
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_TAB);
				break;
			case Service.BROWSER_TAB_NAV_RIGHT:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_TAB);

				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_TAB);
				break;
			case Service.BROWSER_NEW_TAB:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_T);

				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_T);
				break;
			case Service.BROWSER_CLOSE_TAB:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_W);

				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_W);
				break;
			case Service.BROWSER_SEARCH:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_E);

				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_E);
				break;
		}
	}

	public static void keyboard(String command) throws Exception{
		Robot robot = new Robot();
		String text = "";
		if(command.split("")[1].equals("1")){
			text = command.split("")[2];
			text = text.toUpperCase();
			command = Service.KEYBOARD_TEXT;
		}
		switch (command) {
			case Service.KEYBOARD_UP:
				robot.keyPress(KeyEvent.VK_UP);

				robot.keyRelease(KeyEvent.VK_UP);
				break;
			case Service.KEYBOARD_DOWN:
				robot.keyPress(KeyEvent.VK_DOWN);

				robot.keyRelease(KeyEvent.VK_DOWN);
				break;
			case Service.KEYBOARD_TEXT:
				typeCharacter(robot, text);
				break;
			case Service.KEYBOARD_ENTER:
				robot.keyPress(KeyEvent.VK_ENTER);

				robot.keyRelease(KeyEvent.VK_ENTER);
				break;
			case Service.KEYBOARD_TAB:
				robot.keyPress(KeyEvent.VK_TAB);

				robot.keyRelease(KeyEvent.VK_TAB);
				break;
			case Service.KEYBOARD_ALT_TAB:
				robot.keyPress(KeyEvent.VK_ALT);
				
				robot.keyRelease(KeyEvent.VK_TAB);
				break;
			case Service.KEYBOARD_BACKSPACE:
				robot.keyPress(KeyEvent.VK_BACK_SPACE);

				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				break;

		}
	}


	public static void typeCharacter(Robot robot, String letter)
    {
        try
        {
            boolean upperCase = Character.isUpperCase( letter.charAt(0) );
            String variableName = "VK_" + letter.toUpperCase();
 
            KeyEvent ke = new KeyEvent(new JTextField(), 0, 0, 0, 0, ' ');
            Class clazz = ke.getClass();
            Field field = clazz.getField( variableName );
            int keyCode = field.getInt(ke);
 
            robot.delay(1000);
 
            if (upperCase) robot.keyPress( KeyEvent.VK_SHIFT );
 
            robot.keyPress( keyCode );
            robot.keyRelease( keyCode );
 
            if (upperCase) robot.keyRelease( KeyEvent.VK_SHIFT );
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}