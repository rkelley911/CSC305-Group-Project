/**
 * Represents how the program draws 
 * @author Ryan Kelley, Dan Coburn, Jake Ward, Marcos Martinez, Nelson Herrera, Michelle Monet
 * @version CSC305 Project 1.0
 * Note: This interface is most likely not actually going to be needed
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface DrawableInterface {
    /**
     * Returns the X position as an integer
     * @return X position as an integer
     */
    int getxPosition();
    
    /**
     * Returns the Y position as an integer
     * @return Y position as an integer
     */
    int getyPosition();
    
    /**
     * Returns the position as a point
     * @return position at a point
     */
    Point getLocation();
    
    /**
     * Move the Shape to a point
     * @param absX X position to move to
     * @param absY Y position to move to
     */
    void moveTo(int absX, int absY);
    
    /**
     * Move the Shape to a point
     * @param whereToGo Point to move to
     */
    void moveTo(Point whereToGo);
    
    /**
     * Move the shape by a certain amount
     * @param dX Move by a certain X amount
     * @param dY Move by a certain Y amount
     */
    void moveBy(int dX, int dY);
    
    /**
     * Moves the shape from its position by the <\br>
     * current X and Y velocities
     */
    void move();
    
    /**
     * Returns the X Velocity as a double
     * @return X velocity as a double
     */
    double getxVelocity();
    
    /**
     * Returns the Y Velocity as a double
     * @return Y velocity as a double
     */
    double getyVelocity();
    
    /**
     * Sets the X Velocity as a double as well as the Y Velocity
     * @param dvX Sets the X velocity as a double
     * @param dvY Sets the Y velocity as a double
     */
    void setVelocity(double dvX, double dvY);
    
    /** 
     * Returns the color
     * @return Returns the color
     */
    Color getColor();
    
    /** 
     * Set the color
     * @param theColor Sets a color value
     */
    void setColor(Color theColor);
    
    /** 
     * Draw the shape
     * @param pen Graphics object pen
     */
    void draw(Graphics pen);
}
