import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class frame extends JFrame implements KeyListener {
    painter p = new painter();
    frame(){

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double height = size.getHeight();
        double width = size.getWidth();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)width,(int)height);
        this.addKeyListener(this);
        this.setTitle("Demo 3D");
        // this.setVisible(true);
    }
    public static void main(String args[]) {
        frame thisFrame = new frame();
        thisFrame.setBackground(Color.GRAY);
        thisFrame.add(thisFrame.p);
        thisFrame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char mover = e.getKeyChar();
        cameraMover(p.vCamera,1, mover, p);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void cameraMover(vector vCamera,double moveScale, char mover, painter p){
        double x = Math.cos(-p.yaw);
        double z = Math.sin(-p.yaw);
      //  System.out.println(x+" "+z);
        switch(mover){
            case 'a':
                vCamera.move(x*moveScale, 0,z*moveScale );
                break;
            case 'd':
                vCamera.move(-x*moveScale, 0, -z*moveScale);
                break;
            case 'w':
                vCamera.move(z*moveScale,0,x*moveScale);
                break;
            case 's':
                vCamera.move(-z*moveScale,0,-x*moveScale);
                break;
            case 'q':
                p.yaw-=moveScale/57.2958;
                break;
            case 'e':
                p.yaw+=moveScale/57.2958;
                break;
            case 'n':
                p.adder+=0.000001;
                break;
            case 'm':
                p.adder-=0.000001;
                break;
            case 't':
                vCamera.move(0,moveScale/3,0);
                break;
            case 'g':
                vCamera.move(0,-moveScale/3,0);
                break;
        }
    }
}
