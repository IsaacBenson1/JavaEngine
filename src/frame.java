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
        thisFrame.setBackground(Color.BLACK);
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
        //  System.out.println(e.getKeyChar());

    }
    public void cameraMover(vector vCamera,double moveScale, char mover, painter p){
        switch(mover){
            case 'a':
                vCamera.move(moveScale,0,0);
                break;
            case 'd':
                vCamera.move(-moveScale,0,0);
                break;
            case 'w':
                vCamera.move(0,0,moveScale*10);
                break;
            case 's':
                vCamera.move(0,0,-moveScale*10);
                break;
            case 'q':
                p.yaw+=moveScale/30;
                break;
            case 'e':
                p.yaw-=moveScale/30;
                break;
        }
    }
}
