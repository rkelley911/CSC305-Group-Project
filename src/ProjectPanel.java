/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelley, Dan Coburn, Jake Ward, Marcos Martinez, Nelson Herrera, Michelle Monet
 */   
import java.awt.geom.Point2D;
import javax.swing.JApplet;

/* This class contains the entire game... most of the game logic is in the Board class but this
   creates the gui and captures mouse and keyboard input, as well as controls the game states */
public class ProjectPanel extends JApplet implements MouseListener, KeyListener {

  /* These timers are used to kill title, game over, and victory screens after a set idle period (5 seconds)*/
  long titleTimer = -1;
  long timer = -1;

  /* Create a new board */
  Level lv=new Level(); 

  /* This timer is used to do request new frames be drawn*/
  javax.swing.Timer frameTimer;
 

  /* This constructor creates the entire game essentially */   
  public ProjectPanel()
  {
    lv.requestFocus();

    /* Create and set up window frame*/
    JFrame f=new JFrame(); 
    f.setSize(420,460);

    /* Add the board to the frame */
    f.add(lv,BorderLayout.CENTER);

    /*Set listeners for mouse actions and button clicks*/
    lv.addMouseListener(this);  
    lv.addKeyListener(this);  

    /* Make frame visible, disable resizing */
    f.setVisible(true);
    f.setResizable(false);

    /* Set the New flag to 1 because this is a new game */
    lv.New=1;

    /* Manually call the first frameStep to initialize the game. */
    stepFrame(true);

    /* Create a timer that calls stepFrame every 30 milliseconds */
    frameTimer = new javax.swing.Timer(30,new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          stepFrame(false);
        }
      });

    /* Start the timer */
    frameTimer.start();

    lv.requestFocus();
  }

  /* This repaint function repaints only the parts of the screen that may have changed.
     Namely the area around every player ghost and the menu bars
  */
  public void repaint()
  {
    if (lv.player.teleport)
    {
      lv.repaint(lv.player.lastX-20,lv.player.lastY-20,80,80);
      lv.player.teleport=false;
    }
    lv.repaint(0,0,600,20);
    lv.repaint(0,420,600,40);
    lv.repaint(lv.player.x-20,lv.player.y-20,80,80);
    lv.repaint(lv.ghost1.x-20,lv.ghost1.y-20,80,80);
    lv.repaint(lv.ghost2.x-20,lv.ghost2.y-20,80,80);
    lv.repaint(lv.ghost3.x-20,lv.ghost3.y-20,80,80);
    lv.repaint(lv.ghost4.x-20,lv.ghost4.y-20,80,80);
  }

  /* Steps the screen forward one frame */
  public void stepFrame(boolean New)
  {
    /* If we aren't on a special screen than the timers can be set to -1 to disable them */
    if (!lv.titleScreen && !lv.winScreen && !lv.overScreen)
    {
      timer = -1;
      titleTimer = -1;
    }

    /* If we are playing the dying animation, keep advancing frames until the animation is complete */
    if (lv.dying>0)
    {
      lv.repaint();
      return;
    }

    /* New can either be specified by the New parameter in stepFrame function call or by the state
       of b.New.  Update New accordingly */ 
    New = New || (lv.New !=0) ;

    /* If this is the title screen, make sure to only stay on the title screen for 5 seconds.
       If after 5 seconds the user hasn't started a game, start up demo mode */
    if (lv.titleScreen)
    {
      if (titleTimer == -1)
      {
        titleTimer = System.currentTimeMillis();
      }

      long currTime = System.currentTimeMillis();
      if (currTime - titleTimer >= 5000)
      {
        lv.titleScreen = false;
        lv.demo = true;
        titleTimer = -1;
      }
      lv.repaint();
      return;
    }
 
    /* If this is the win screen or game over screen, make sure to only stay on the screen for 5 seconds.
       If after 5 seconds the user hasn't pressed a key, go to title screen */
    else if (lv.winScreen || lv.overScreen)
    {
      if (timer == -1)
      {
        timer = System.currentTimeMillis();
      }

      long currTime = System.currentTimeMillis();
      if (currTime - timer >= 5000)
      {
        lv.winScreen = false;
        lv.overScreen = false;
        lv.titleScreen = true;
        timer = -1;
      }
      lv.repaint();
      return;
    }


    /* If we have a normal game state, move all pieces and update pellet status */
    if (!New)
    {
      /* The pacman player has two functions, demoMove if we're in demo mode and move if we're in
         user playable mode.  Call the appropriate one here */
      if (lv.demo)
      {
        lv.player.demoMove();
      }
      else
      {
        lv.player.move();
      }

      /* Also move the ghosts, and update the pellet states */
      lv.ghost1.move(); 
      lv.ghost2.move(); 
      lv.ghost3.move(); 
      lv.ghost4.move(); 
      lv.player.updatePellet();
      lv.ghost1.updatePellet();
      lv.ghost2.updatePellet();
      lv.ghost3.updatePellet();
      lv.ghost4.updatePellet();
    }

    /* We either have a new game or the user has died, either way we have to reset the board */
    if (lv.stopped || New)
    {
      /*Temporarily stop advancing frames */
      frameTimer.stop();

      /* If user is dying ... */
      while (lv.dying >0)
      {
        /* Play dying animation. */
        stepFrame(false);
      }

      /* Move all game elements back to starting positions and orientations */
      lv.player.currDirection='L';
      lv.player.direction='L';
      lv.player.desiredDirection='L';
      lv.player.x = 200;
      lv.player.y = 300;
      lv.ghost1.x = 180;
      lv.ghost1.y = 180;
      lv.ghost2.x = 200;
      lv.ghost2.y = 180;
      lv.ghost3.x = 220;
      lv.ghost3.y = 180;
      lv.ghost4.x = 220;
      lv.ghost4.y = 180;

      /* Advance a frame to display main state*/
      lv.repaint(0,0,600,600);

      /*Start advancing frames once again*/
      lv.stopped=false;
      frameTimer.start();
    }
    /* Otherwise we're in a normal state, advance one frame*/
    else
    {
      repaint(); 
    }
  }  

  /* Handles user key presses*/
  public void keyPressed(KeyEvent e) 
  {
    /* Pressing a key in the title screen starts a game */
    if (lv.titleScreen)
    {
      lv.titleScreen = false;
      return;
    }
    /* Pressing a key in the win screen or game over screen goes to the title screen */
    else if (lv.winScreen || lv.overScreen)
    {
      lv.titleScreen = true;
      lv.winScreen = false;
      lv.overScreen = false;
      return;
    }
    /* Pressing a key during a demo kills the demo mode and starts a new game */
    else if (lv.demo)
    {
      lv.demo=false;
      /* Stop any pacman eating sounds */
      lv.sounds.nomNomStop();
      lv.New=1;
      return;
    }

    /* Otherwise, key presses control the player! */ 
    switch(e.getKeyCode())
    {
      case KeyEvent.VK_LEFT:
       lv.player.desiredDirection='L';
       break;     
      case KeyEvent.VK_RIGHT:
       lv.player.desiredDirection='R';
       break;     
      case KeyEvent.VK_UP:
       lv.player.desiredDirection='U';
       break;     
      case KeyEvent.VK_DOWN:
       lv.player.desiredDirection='D';
       break;     
    }

    repaint();
  }

  /* This function detects user clicks on the menu items on the bottom of the screen */
  public void mousePressed(MouseEvent e){
    if (lv.titleScreen || lv.winScreen || lv.overScreen)
    {
      /* If we aren't in the game where a menu is showing, ignore clicks */
      return;
    }

    /* Get coordinates of click */
    int x = e.getX();
    int y = e.getY();
    if ( 400 <= y && y <= 460)
    {
      if ( 100 <= x && x <= 150)
      {
        /* New game has been clicked */
        lv.New = 1;
      }
      else if (180 <= x && x <= 300)
      {
        /* Clear high scores has been clicked */
        lv.clearHighScores();
      }
      else if (350 <= x && x <= 420)
      {
        /* Exit has been clicked */
        System.exit(0);
      }
    }
  }
  
 
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}
}
