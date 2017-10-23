/**
 * Panel class for project which controls most features
 * of the program, including initializing the student and
 * creature objects. Also handles the dialog windows for
 * user choices during game play along with calling any other
 * methods that need to be called during the health calculating
 * process.
 * @author Ryan Kelley, Dan Coburn, Jake Ward, Marcos Martinez, Nelson Herrera, Michelle Monet
 * @version CSC305 Project 1.0
 */
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class ProjectPanel extends JPanel{
    // ****************Data Fields****************
    private boolean initializationOver = false;
    
    // ****************Constructors****************
    public ProjectPanel() {
        
    }// end constructor

    // ****************Methods****************
    @Override
    public void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        student1.draw(pen);
        creature1.draw(pen);
        // tests if first user choice has happened yet
        // wanted to display student and creature in background
        // before first user choice without the book
        if(initializationOver){
            myBook.move();
            myBook.checkBounds(pen);
            myBook.draw(pen);
        }
        
    }
    
    /**
     * Main method that starts the simulation of
     * fighting against ignorance, throws an exception because of the sleep
     * thread inside minor while loops that assist in drawing the thrown books
     * each round.
     * @throws InterruptedException 
     */
    public void launch() throws InterruptedException{
}
