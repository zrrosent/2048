import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

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
      
      while (!board[x][y].getText().equals(""))
      {
         x = randomDigit();
         y = randomDigit();
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
   
   public void gameOver()
   {
      for(int i = 0; i < board.length; i++)
      {
         for(int j = 0; j < board[0].length; j++)
         {
            if(board[i][j].getText().equals(""))
               return;
         }
      }
      JOptionPane.showMessageDialog(null, "Game Over :(");
      try
      {
        Thread.sleep(1000); 
      }
      catch(Exception e){}
      frame.dispose();
   }
   
   //keyPressed
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(keyCode == KeyEvent.VK_PAGE_UP || keyCode == KeyEvent.VK_UP)
      {
         toUp();
         e.consume();
         gameOver();
            
      }
      else if(keyCode == KeyEvent.VK_PAGE_DOWN || keyCode == KeyEvent.VK_DOWN)
      {
         toDown();
         e.consume();
         gameOver();
      }
      else if(keyCode == KeyEvent.VK_HOME || keyCode == KeyEvent.VK_LEFT)
      {
         toLeft();
         e.consume();
         gameOver();
      }
      else if(keyCode == KeyEvent.VK_END || keyCode == KeyEvent.VK_RIGHT)
      {
         toRight();
         e.consume();
         gameOver();
      }
      else
      {
         JOptionPane.showMessageDialog(null, "Invalid Key\n Use The Arrow Keys To Make A Move");
      }
   }
   
   //keyReleased
   public void keyReleased(KeyEvent e)
   {
      
   }
   
   //last method from interface
   public void keyTyped(KeyEvent e)
   {
      e.consume();
   }
   
   public static void main(String[] args)
   {
      GUI game = new GUI();
   }     
}