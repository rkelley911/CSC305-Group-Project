/**
 * Main class for "Pacman: Remade" program
 * @author Ryan Kelley, Dan Coburn, Jake Ward, Marcos Martinez, Nelson Herrera, Michelle Monet
 * @version CSC305 Project 1.0
 * 
 * ************************************Project Report************************************
 * ****************User's Manual****************
 * FIX ME :)
 */
import javax.swing.JFrame;

public class CSC305project {
    
  public static void main(String [] args)
  {
      Pacman c = new Pacman();
  } 

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        JFrame projectWindow = new JFrame();
        projectWindow.setSize(1024, 800); // width x height 
        projectWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        projectWindow.setTitle("Pacman: Remade");
        
        // This is the panel for this Project
        ProjectPanel playPanel = new ProjectPanel();
        projectWindow.add(playPanel); 
        
        projectWindow.setVisible(true); 
        
        // Launch the game
        playPanel.launch();
    }  
}
