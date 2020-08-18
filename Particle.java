import javax.swing.*;
class Particle extends JComponent{
    double x = 0, y = 0, oldX =0, oldY=0;
    int size = 1;
    public Particle(int x, int y, int size){
        this.x = x;
        this.oldX= x;
        this.y = y;
        this.oldY= y;
        this.size = size;
    }

    public void integration() {
        double velX = (this.x - this.oldX);
        double velY = (this.y - this.oldY);
        this.oldX = this.x;
        this.oldY = this.y;
        
        this.x += velX;
        this.y += velY;
    }

    public void attract(int x, int y){
        double dx = x - this.x;
        double dy = y - this.y;
        double dist = Math.sqrt((dx*dx)+(dy*dy));
        
        if(dx == 0){dx += 1;}
        if(dy == 0) {dy += 1;}
        if(dist == 0){dist += 1;}
        
        if (Main.s == 0) {
            this.x += (dx)/(dist);
            this.y += (dy)/(dist);
        } else if (Main.s < 0) {
            this.x += (dx/(Main.s*-1))/(dist);
            this.y += (dy/(Main.s*-1))/(dist);
        }
        else {
            this.x += (dx*Main.s)/(dist);
            this.y += (dy*Main.s)/(dist);
        }
    }
}