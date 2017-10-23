/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelley, Dan Coburn, Jake Ward, Marcos Martinez, Nelson Herrera, Michelle Monet
 */
public class Pacman 
        implements DrawableInterface
        
        
    
    
    public void paintPacman(pacmanGraphic)
    {
        
        
        if (keyDownPushed) //calls paint then move method, moves it down the screen (positive integer),
        {
            //need to call method that paints pacman facing down
            void moveBy(0, int dY)
        }

        if (keyUpPushed) //calls paint then move method, moves it up the screen (negative integer)
        {
            //need to call method that paints pacman facing up
            void moveBy(0, int -dY)
        }
        
        if (keyLeftPushed) //calls paint then move method, moves it left (negative integer)
        {
            //need to call method that paints pacman facing up
            void moveBy(int -dX, 0)
        }

        if (keyRightPushed) //calls paint then move method, moves it right (positive integer)
        {
            //need to call method that paints pacman facing up
            void moveBy(int dX, int 0)
        }
        
    }
    
    
}
