
/**
 * Swarming particle demo.
 * 
 * Brendan Gallagher
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Main {
    public static int mouseX = 0, mouseY = 0;
    public static int numP = 100000;
    public static int size = 1;
    public static int s = 0;
    public static int width, height;
    public static Color c = new Color(1,1,1);
    public static Color c2 = new Color(1,1,1);
    
    public static Updater update;
    public static ArrayList<Particle> particles = new ArrayList<Particle>();

    public static void main(String args[]){
        JFrame frame = new JFrame("Particle Swarm Demo");
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        numP = Integer.parseInt(JOptionPane.showInputDialog("Enter number of particles:", numP));
        size = Integer.parseInt(JOptionPane.showInputDialog("Enter size of particles:", size));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)screenSize.getWidth();
        height = ((int)screenSize.getHeight()) - 50;
        
        frame.setSize(width, height);
       
        frame.addKeyListener(new KeyListener() {
        public void keyTyped(KeyEvent e) {
            //change dx and dy
            if (e.getKeyChar() == 'w') {s++;}
            else if (e.getKeyChar() == 's') {s--;}
            
            //change numP
            if(e.getKeyChar() == '='){
                int increment = 5;
                numP+=increment;
                for(int i = 0; i < increment; i++){
                    Particle p = new Particle((int) (Math.random()*width), (int) (Math.random()*height), size);
                    particles.add(p);
                }
            }else if(e.getKeyChar() == '-'){
                int decrement = 500;
                numP-=decrement;
                for(int i = 0; i < decrement; i++){
                    particles.remove(i);
                }
            }
            
            //reset
            if(e.getKeyChar() == 'r'){
                for(int i = 0; i < numP; i++){
                    int x = (int)(Math.random()*width), y = (int)(Math.random()*height);
                    particles.set(i,new Particle(x,y,size));
                }
            }
        }
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        });
        frame.addMouseMotionListener(new MouseMotionListener(){
        public void mouseDragged(MouseEvent arg0){}
        public void mouseMoved(MouseEvent e){
                    mouseX = e.getX();
                    mouseY = e.getY();
        }
        });
           
            for(int i = 0; i < numP;i++){
            Particle p = new Particle((int) (Math.random()*width), (int) (Math.random()*height), size);
            particles.add(p);
        }
        
        Thread colorThread = new Thread(new Runnable(){
            public void run(){
                for(;;){
                    for (float i = 0; i < 1; i+=0.001){
                        try{Thread.sleep(40);} catch(Exception e){}
                        c = Color.getHSBColor(i, 1, 1);
                    }
                }
            }
        });
        Thread colorThread2 = new Thread(new Runnable(){
            public void run(){
                for(;;){
                    for (float i = 0; i < 1; i+=0.01){
                        try{Thread.sleep(40);} catch(Exception e){}
                        c2 = Color.getHSBColor(i , 1, 1);
                    }
                }
            }
        });
        colorThread.start();
        colorThread2.start();        
        
        update = new Updater(particles);
        update.t.start();
        frame.add(update);
        frame.setVisible(true);
    }
}
