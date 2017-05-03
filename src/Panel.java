import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * pannel class
 * @author Andrew
 *
 */
public class Panel extends JPanel implements MouseListener,MouseMotionListener,KeyListener,Runnable
{
	/**
	 * the ball object used in the game
	 */
	private Ball ball;
	
	/**
	 * the paddle object used in the game
	 */
	private Paddle paddle;
	
	/**
	 * the array of block objects used in the game
	 */
	private Block[] blocks= new Block[84];
	
	/**
	 * direction of the ball in the x direction
	 */
	int mx;
	
	/**
	 * direction of the ball in the y direction
	 */
	int my;
	
	/**
	 * indicator whether the mouse is being dragged or not
	 */
	boolean mouseDragged;
	
	/**
	 * indicator whether or not the left mouse button is being clicked
	 */
	boolean leftClick;
	
	/**
	 * how many balls that are left after the initial ball
	 */
	int ballsLeft=2;
	
	//ends the game
	boolean end = false;
	
	/**
	 * image used for the background
	 */
	BufferedImage img;
	
	/**
	 * constructor for panel object
	 */
	public Panel()
	{
		//creates mouse listener to detect mouse clicks
		addMouseListener(this);
		
		//creates mouse motion listener to detect mouse movement
		addMouseMotionListener(this);
		
		//creates key listener to detect key actions
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		//sets the background of the panel blue
		setBackground(Color.BLUE);
		
		//initialize the ball object
		ball=new Ball(250,250,5,Color.WHITE,6);
		
		//creates and loads bricks into array
		int index=0;
		
		//Creates top row
		for(int i=1;i<13;i++)
		{
			blocks[index]=new Block(i*50,50,20,50,Color.MAGENTA);
			index++;
		}
		
		//creates second row of bricks
		for(int i=1;i<13;i++)
		{
			blocks[index]=new Block(i*50,70,20,50,Color.MAGENTA);
			index++;
		}
		
		//creates third row of bricks
		for(int i=1;i<13;i++)
		{
			blocks[index]=new Block(i*50,90,20,50,Color.GRAY);
			index++;
		}
		
		//creates fourth row of bricks
		for(int i=1;i<13;i++)
		{
			blocks[index]=new Block(i*50,110,20,50,Color.GRAY);
			index++;
		}
		
		//creates fifth row of bricks
		for(int i=1;i<13;i++)
		{
			blocks[index]=new Block(i*50,130,20,50,Color.GREEN);
			index++;
		}
		
		//creates sixth row of bricks
		for(int i=1;i<13;i++)
		{
			blocks[index]=new Block(i*50,150,20,50,Color.ORANGE);
			index++;
		}
		
		//creates last row of bricks
		for(int i=1;i<13;i++)
		{
			blocks[index]=new Block(i*50,170,20,50,Color.GREEN);
			index++;
		}
		
		//plays the song
		play("cantina.wav");
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
		
		//reads image file to be used for the background
		try 
		{
            img = ImageIO.read(new File("obi.jpg"));
        } 
		catch(IOException e) 
		{
            e.printStackTrace();
        }
        
		//sets the background as the image file
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		
		//creates and paints the paddle
		paddle= new Paddle(mx,getHeight()-40, 100, 20, Color.WHITE);
		g.setColor(Color.DARK_GRAY);
		paddle.draw(g);
		
		//paints the blocks
		for(int i = 0;i<blocks.length;i++)
		{
			if(blocks[i].getDest()==false)
			{
				blocks[i].draw(g);
			}
		}
		
		//creates and paints the ball
		ball.draw(g);
		if(leftClick==true)
			{
				ball.move(getWidth(), getHeight(),paddle,blocks);
				ball.draw(g);
			}
		
		//tells the user they lost if there are no balls left and terminates the program
		if(ballsLeft==0)
		{
			if(ball.getY()>getHeight())
			{
				leftClick=false;
				ball=new Ball(250,250,5,Color.WHITE,6);
				JOptionPane.showMessageDialog(this, "loser");
				System.exit(0);
			}
			
		}
		
		//resets the ball and takes a life away 
		else if(ball.getMinY()>getHeight()&&ballsLeft>0)
		{
			
			ball=new Ball(250,250,5,Color.WHITE,6);
			leftClick=false;
			if(leftClick==true)
			{
				ball.move(getWidth(), getHeight(),paddle,blocks);
				ball.draw(g);
			}
			ballsLeft--;
		}
		
		//checking for a winner
		//if blockcount = 0, there are no blocks left and the user has one
		int blockCount=84;
		for(int i=0;i<blocks.length;i++)
		{
			if(blocks[i].getDest()==true)
			{
				blockCount--;
			}
		}
		
		//if the blockcount = 0 tell the user that they won and terminate the program
		if(blockCount<=0)
		{
			//sets all the blocks to be not destroyed
			//so that an infinite loop doesn't occur when the blockcount = 0
			for(int i=0;i<blocks.length;i++)
			{
				blocks[i].setDest(false);
			}
			end = true;
			leftClick=false;
			JOptionPane.showMessageDialog(this, "winner");
			System.exit(0);
			
			
		}
		
	}
	
	@Override
	public void run() 
	{
		while(end!=true)
		{
			repaint();
			try
			{
				Thread.sleep(16);//60fps
			}
			catch(InterruptedException e)
			{
				
			}
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mx=e.getX()-10;
		my=e.getY()-10;
		mouseDragged=true;
		e.consume();
	}
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mx=e.getX()-10;
		my=e.getY()-10;
		mouseDragged=false;
		
		e.consume();
	}
	
	/**
	 * moves the paddle left
	 */
	public void left()
	{
		if(mx>0)
		{
			mx-=20;
		}
	}
	
	/**
	 * moves the paddle right
	 */
	public void right()
	{
		if(mx<getWidth())
		{
			mx+=20;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{	
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_LEFT)
		{
			left();
		}
		if(code==KeyEvent.VK_RIGHT)
		{
			right();
		}
		if(code==KeyEvent.VK_SPACE)
		{
			leftClick=true;	
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) 
	{
	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		int buttonClicked = e.getButton();
		if(buttonClicked==MouseEvent.BUTTON1)
		{
			leftClick=true;
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
			
	}
	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		
	}
	/**
	 * plays sounds for the panel
	 * @param filename file name to play
	 */
	public static void play(String filename)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();	
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(LineUnavailableException e)
		{
			System.out.println("Audio Error");
		}
		catch(IOException e)
		{
			System.out.println("file not found");
		}
		catch(UnsupportedAudioFileException e)
		{
			System.out.println("wrong file type");
		}
	}
	
}
