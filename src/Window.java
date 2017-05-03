import javax.swing.*;
import java.awt.*;

/**
 * creates the window and the main program
 * @author Andrew
 *
 */
public class Window extends JFrame
{
	/**
	 * main program
	 * @param args
	 */
	public static void main(String[]args)
	{
		Window w=new Window();
	}
	
	/**
	 * creates the window constuctor
	 */
	public Window()
	{
		setBounds(100,100,700,700);//x,y,w,h of window
		setTitle("Drawing Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel p = new Panel();
		setContentPane(p);
		Thread t = new Thread(p);
		t.start();
		setVisible(true);
	}
	
}
