import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class painter extends JPanel implements ActionListener {
    // MAC ONE
    shape myShape = new shape("src/teapot.obj");
    // WINDOWS ONE
    //shape myShape = new shape("src\\newCube.obj" );

    public vector vCamera = new vector (0,0,-20);
    public shape[] shapes;
    private Timer t = new Timer(1,this);
    double theta = 0.5;
    double adder = 0;
    double[][] projMat, xRotMat, cameraRotMat, zRotMat, worldMat, transMat, cameraMat, yRotMat, viewMat;
    Color color = Color.CYAN;
    public Color[] myShapeColors = new Color[myShape.getAllTriangles().length];
    public double[][] fillCords = new double [myShape.getAllTriangles().length][6];
    public double yaw;
    public double scaler = 100;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public double height = size.getHeight();
    public double width = size.getWidth();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.GRAY);
        for (int i = 0; i < myShape.getAllTriangles().length; i++) {
            theta += adder;
            fillTriangle(fillCords, i, g2, myShapeColors[i], true, false);
        }
        t.start();
    }

    public void actionPerformed(ActionEvent e) {
        vector oner = new vector(1,1,1);
        boolean checker;
        projMat = matrix.projection();
        yRotMat = matrix.rotation('y',theta);
        xRotMat = matrix.rotation('x',theta);
        zRotMat = matrix.rotation('z',theta);
        cameraRotMat = matrix.rotation('y',yaw);
        transMat = matrix.translation(0,0,0);
        worldMat = matrix.multiplyMatrixMatrix(xRotMat,zRotMat);
        worldMat = matrix.multiplyMatrixMatrix(worldMat,yRotMat);
        worldMat = matrix.multiplyMatrixMatrix(worldMat,transMat);
        vector vUp = new vector(0,1,0);
        vector vTarget = new vector(0,0,1);
        vector vLookDirection = vector.multiplyMatrix(vTarget,cameraRotMat);
        vTarget = vector.vectorAdd(vCamera,vLookDirection);
        cameraMat = matrix.pointAt(vCamera,vTarget,vUp);
        viewMat = matrix.quickInverse(cameraMat);
        int i =0;
        for (triangle currentTri: myShape.getAllTriangles()){
            double lightDp;
            vector line1 = oner;
            vector triCenter = oner;
            vector line2 = oner ;
            vector normal = oner;
            vector vCameraRay = oner;
            vector lightDirection = oner;
            triangle triTransformed, triViewed;
            triangle triProjected = new triangle(oner,oner,oner);
            triTransformed = triangle.multiplyMatrix(currentTri, worldMat);
            line1 = vector.subtract(triTransformed.getVec2(), triTransformed.getVec1());
            line2 = vector.subtract(triTransformed.getVec3(), triTransformed.getVec1());
            normal = vector.crossProduct(line1,line2);
            normal.normalize();
            triCenter = vector.vectorAdd(triTransformed.getVec1(),triTransformed.getVec2());
            triCenter = vector.vectorAdd(triCenter, triTransformed.getVec3());
            triCenter = vector.multiply(triCenter, 1.0/3);
            vCameraRay = vector.subtract(triCenter, vCamera);
            lightDirection = vector.subtract(triCenter,vCameraRay);
            lightDirection.normalize();
            lightDp = vector.dotProduct(lightDirection,normal);
            checker = (vector.dotProduct(normal,vCameraRay) < 0.0 );
           // checker = true;
            if (checker){
                if(lightDp<0){
                    lightDp*=- 1;
                }
                myShapeColors[i] = new Color((int)(color.getRed()*lightDp)%255,(int)(color.getGreen()*lightDp)%255,(int)(color.getBlue()*lightDp)%255);
                triViewed = triangle.multiplyMatrix(triTransformed,viewMat);
                triProjected = triangle.multiplyMatrix(triViewed,projMat);
                triProjected.scale(scaler);
                triProjected.move(width/2,height/2,0);
            }
            fillCords[i] = new double[]{triProjected.getVec1().getX(), triProjected.getVec1().getY(), triProjected.getVec2().getX(), triProjected.getVec2().getY(), triProjected.getVec3().getX(), triProjected.getVec3().getY()};
            i++;
        }
        repaint();
    }







    public static void fillTriangle(double[][] allCords,int i, Graphics2D g2d, Color color, boolean fill, boolean lines){
        double x1 = allCords[i][0];
        double y1 = allCords[i][1];
        double x2 = allCords[i][2];
        double y2 = allCords[i][3];
        double x3 = allCords[i][4];
        double y3 = allCords[i][5];
        int[] xPoints = {(int)x1,(int)x2,(int)x3};
        int[] yPoints = {(int)y1,(int)y2,(int)y3};
        Polygon myPoly = new Polygon(xPoints,yPoints,3);
        if (fill){
            g2d.setColor(color);
            g2d.fillPolygon(myPoly);
        }
        if (lines){
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(myPoly);
        }

    }
}
