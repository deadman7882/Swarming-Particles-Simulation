import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
class Updater extends JComponent implements ActionListener{
    Timer t;
    boolean color = true;
    ArrayList<Particle> p = new ArrayList<Particle>();
    public Updater(ArrayList<Particle> p){
        t = new Timer(0, this);
        this.p = p;
    }
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Main.width,Main.height);
        int colGroups = 0;

        for(Particle pp : p){
            if(colGroups == 0){
                colGroups = 1;
                g.setColor(Main.c);
            }else if(colGroups == 1){
                colGroups = 0;
                g.setColor(Main.c2);
            }
            g.fillRect((int)pp.x,(int)pp.y,pp.size,pp.size);
        }
        g.setColor(Color.WHITE);
        g.drawString("Press + or - to change number of parties: " + Main.numP, 15, 15);           
        g.drawString("Press W or S to chnage dx and dy: " + Main.s, 15, 30);
        g.drawString("Press R to reset", 15, 45);
    }
    //action happens every tick of the timer
    public void actionPerformed(ActionEvent arg0){
        for(int i = 0; i < Main.numP; i++){           
            p.get(i).attract(Main.mouseX, Main.mouseY);
            p.get(i).integration();
        }
        repaint(); 
    }
}