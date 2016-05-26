import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class GUI extends JFrame implements KeyListener
{
   private static JButton[][] board;
   private static int score, highscore;
   private static JLabel s = new JLabel("Score: " + score);
   //private static JLabel hs =  new JLabel("Highscore: " + highscore);
   private static JFrame frame = new JFrame();
   private static JPanel panel = new JPanel();
   
   private static String w = "";
      
   
   public static String getDigit()//returString ans a 2 or a 4
   {
      String[] g = new String[8];
      for (int i = 0; i <= 6; i++)
      { 
         g[i] = "2";
      }
            
      g[7] = "4";
      
      return g[(int)(Math.random()*8)];
   }
   
   public static int randomDigit()//returString ans a random number between 0 and 3 inclusive
   {
      return (int)(Math.random()*4);
   }
   
   public GUI()
   {
      super("2048");
      panel.setLayout(new GridLayout(5,4));//Creates title, layout, and background
		panel.setBackground(Color.WHITE);
      
      board = new JButton[4][4];
      int r1, r2, r3, r4;
      //r1 = 0; r2 = r1;
   
      r1 = randomDigit();
      r2 = randomDigit();
		
		for (int i = 0; i < board.length; i++)
      {
         for (int j = 0; j < board[0].length; j++)
         {
            board[i][j] = new JButton("");//creates a 2D array of buttoString ans
            board[i][j].addKeyListener(this);
            panel.add(board[i][j]);//adds them to the panel
         }
      }
      
      board[r1][r2].setText(getDigit()+"");//sets a random button with a value of 2 or 4  
      
      r3 = r1; r4 = r2;  
      
      while (r3 == r1 && r4 == r2) 
      {
         r3 = randomDigit();
         r4 = randomDigit();
         
         board[r3][r4].setText(getDigit()+"");
      }
      
      setColor();
      
      panel.add(s);
      //panel.add(hs);
      
      frame.add(panel);//adds frame to panel
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
   }
   
   public static void addNum()//adds a 2 or a 4 to a blank button
   {
      int x = randomDigit();
      int y = randomDigit();
      int i = 0;
      while (!board[x][y].getText().equals(""))
      {
         if(i > 100)
         {
            gameOver();
         }
         x = randomDigit();
         y = randomDigit();
         i++;
      }
      
      board[x][y].setText(getDigit()+"");
   }	
   
   public static void moveUp()
   {
      for (int i = 1; i <= board.length-1; i++)
      {
         for (int j = 0; j <= board[0].length-1; j++)
         {
    		   if (!board[i][j].getText().equals(""))
				{                     
               if (board[i-1][j].getText().equals(""))
					{
				      board[i-1][j].setText(board[i][j].getText()+"");
						board[i][j].setText("");
				   }	    
            }
         }
      }
   }
    
    public static void toUp()
    {
      
      moveUp();
      {
         for (int i = 1; i < board.length; i++)
   	   {
      	   for (int j = board[0].length-1; j >= 0; j--)
      	   {
               if (!board[i][j].getText().equals(""))
               {
                  if (board[i][j].getText().equals(board[i-1][j].getText()))
                  {
                     int k = Integer.parseInt(board[i][j].getText());
                     k = k*2;
                     String ans = Integer.toString(k);
                     board[i-1][j].setText(ans);
                     score += k;
                  }
               }
               moveUp();
            }
         } 
      } 
      addNum();
      s.setText("Score: " + score);
      //hs.setText("Highscore: " + highscore);
   }

      
   public static void moveLeft()
   {
      for (int i = 0; i <= 3; i++)
      {
         for (int j = board[0].length-1; j >= 1; j--)
         {
            if (!board[i][j].getText().equals(""))
            {                     
               if (board[i][j-1].getText().equals(""))
               {
                  board[i][j-1].setText(board[i][j].getText()+"");
                  board[i][j].setText("");
               }       
            }
         }
      }
  }

   public static void toLeft()
   {
      moveLeft();
      {
         for (int i = 0; i < board.length; i++)
         {
            for (int j = board[0].length-1; j >= 1; j--)
            {
               if (!board[i][j].getText().equals(""))
               {
                  if (board[i][j].getText().equals(board[i][j-1].getText()))
                  {
                     int k = Integer.parseInt(board[i][j].getText());
                     k = k*2;
                     String ans = Integer.toString(k);
                     board[i][j-1].setText(ans);
                     board[i][j].setText(ans);
                     score += k;
                  }
               }
               moveLeft();
            }
         } 
      } 
      addNum();
      s.setText("Score: " + score);
      //hs.setText("Highscore: " + highscore);
   }      
   
   public static void moveRight()
	{
   
	   for (int i = 0; i < board.length; i++)
      {
         for (int j = 2; j >= 0; j--)
         {
    		   if (!board[i][j].getText().equals(""))
			   {                     
               if (board[i][j+1].getText().equals(""))
				   {
		   	      board[i][j+1].setText(board[i][j].getText()+"");
						board[i][j].setText("");
               }	                     		  	        					
         	}
      	}
		}		
	}
   
   public static void toRight()
   {
      moveRight();
      {
         for (int i = 0; i < board.length; i++)
   	   {
      	   for (int j = board[0].length-1; j > 0; j--)
      	   {
               if (!board[i][j].getText().equals(""))
               {
                  if (board[i][j].getText().equals(board[i][j-1].getText()))
                  {
                     int k = Integer.parseInt(board[i][j].getText());
                     k = k*2;
                     String ans = Integer.toString(k);
                     board[i][j].setText(ans);
                     board[i][j-1].setText("");
                     score += k;
                  }
               }
               moveRight();
            }
         } 
      } 
      addNum();
      s.setText("Score: " + score);
      //hs.setText("Highscore: " + highscore);
   }
   
   
   public static void moveDown()
   {
      for (int i = 0; i < board.length-1; i++)
      {
            for (int j = 0; j <= board[0].length-1; j++)
            {
               if (!board[i][j].getText().equals(""))
               {                     
                  if (board[i+1][j].getText().equals(""))
                  {
                     board[i+1][j].setText(board[i][j].getText()+"");
                     board[i][j].setText("");
                  }       
               }
            }
      }
   }

   public static void toDown()
   {
      moveDown();
      {
         for (int i = 0; i < board.length-1; i++)
         {
            for (int j = 0; j <= board[0].length-1; j++)
            {
               if (!board[i][j].getText().equals(""))
               {
                  if (board[i][j].getText().equals(board[i+1][j].getText()))
                  {
                     int k = Integer.parseInt(board[i][j].getText());
                     k = k*2;
                     String ans = Integer.toString(k);
                     board[i+1][j].setText(ans);
                     board[i][j].setText("");
                     score += k;
                  }
               }
               moveDown();
            }
         } 
      } 
      addNum();
      s.setText("Score: " + score);
      //hs.setText("Highscore: " + highscore);
   }
   
   public static void gameOver()
   {
      JOptionPane.showMessageDialog(null, "Game Over :(");
      frame.dispose();
      System.exit(0);
   }
   
   public void setColor()
   {
      for(int i = 0; i < board.length; i++)
      {
         for(int j = 0; j < board[0].length; j++)
         {
            String value = board[i][j].getText();
            switch(value)
            {
               case "2" : 
                  board[i][j].setBackground(new Color(238,232,170));
                  break;
               case "4" : 
                  board[i][j].setBackground(new Color(240,230,140));
                  break;
               case "8" : 
                  board[i][j].setBackground(new Color(255,127,80));
                  break;
                case "16" : 
                  board[i][j].setBackground(new Color(255,165,0));
                  break;
                case "32" : 
                  board[i][j].setBackground(new Color(255,99,71));
                  break;
                case "64" : 
                  board[i][j].setBackground(new Color(255,0,0));
                  break;
                case "128" : 
                  board[i][j].setBackground(new Color(218,165,32));
                  break;
                case "256" : 
                  board[i][j].setBackground(new Color(200,140,20));
                  break;
                case "512" : 
                  board[i][j].setBackground(new Color(184,134,11));
                  break;
                case "1024" : 
                  board[i][j].setBackground(new Color(220,180,0));
                  break;
                case "2048" : 
                  board[i][j].setBackground(new Color(255,215,0));
                  break;
                case "4096" : 
                  board[i][j].setBackground(new Color(128,128,128));
                  break;
                case "8192" : 
                  board[i][j].setBackground(new Color(0,0,0));
                  break;
                default: 
                  board[i][j].setBackground(new Color(255,255,255));
            }
         }
      }
   }
   
   //keyPressed
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(keyCode == KeyEvent.VK_PAGE_UP || keyCode == KeyEvent.VK_UP)
      {
         toUp();
         
         e.consume();         
            
      }
      else if(keyCode == KeyEvent.VK_PAGE_DOWN || keyCode == KeyEvent.VK_DOWN)
      {
         
         toDown();
         e.consume();         
      }
      else if(keyCode == KeyEvent.VK_HOME || keyCode == KeyEvent.VK_LEFT)
      {
         
         toLeft();
         e.consume();         
      }
      else if(keyCode == KeyEvent.VK_END || keyCode == KeyEvent.VK_RIGHT)
      {
         
         toRight();
         e.consume();         
      }
   }
   
   //keyReleased
   public void keyReleased(KeyEvent e)
   {
      setColor();
      soundFX();
   }
   
   //last method from interface
   public void keyTyped(KeyEvent e)
   {
      e.consume();
   }
   
   public static void soundFX()
   {
      File audio = new File("SlideSound.WAV");
      try
      {
         Thread thread2 = new Thread();
         Clip clip = AudioSystem.getClip();
         clip.open(AudioSystem.getAudioInputStream(audio));
         clip.start();
         
         //thread2.sleep(clip.getMicrosecondLength()/1000);
         thread2.stop();
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null, "ERROR: SOUND FILE UNAVAILABLE");
      }
   }
   
   public static void main(String[] args)
   {
      GUI game = new GUI();
   }     
}