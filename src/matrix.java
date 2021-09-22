import java.awt.*;

public class matrix {

    // identity
    public static double[][] identity(){
        double [][] matrix = new double[4][4];
        matrix[0][0] = 1;
        matrix[1][1] = 1;
        matrix[2][2] = 1;
        matrix[3][3] = 1;
        return matrix;
    }

    // projection
    public static double[][] projection(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double height = size.getHeight();
        double width = size.getWidth();
        double aspectRatio = height/width;
        double fov = 90;
        double fovRad = 1/ Math.tan((0.5*fov)/(180*3.14159));
        double near = 0.1;
        double far = 1000;
        double[][]matrix = new double[4][4];
        matrix[0][0] = aspectRatio*fovRad;
        matrix[1][1] = fovRad;
        matrix[2][2] = far/(far-near);
        matrix[2][3] = (-far * near)/(far-near);
        matrix[3][2] = 1.0;
        matrix[3][3] = 0;
        return matrix;
    }

    // rotation
    public static double[][] rotation(char axis, double theta){
        double[][]matrix = new double[4][4];
        switch(axis){
            case('x'):
                matrix[0][0] = 1;
                matrix[1][1] = Math.cos(theta*0.5);
                matrix[2][1] = Math.sin(theta*0.5);
                matrix[1][2] = -Math.sin(theta*0.5);
                matrix[2][2] = Math.cos(theta*0.5);
                matrix[3][3] = 1;
                break;
            case('y'):
                matrix[0][0] = Math.cos(theta);
                matrix[2][0] = Math.sin(theta);
                matrix[0][2] = -Math.sin(theta);
                matrix[1][1] = 1.0f;
                matrix[2][2] = Math.cos(theta);
                matrix[3][3] = 1.0f;
                break;
            case('z'):
                matrix[0][0] = Math.cos(theta);
                matrix[1][0] = Math.sin(theta);
                matrix[0][1] = -Math.sin(theta);
                matrix[1][1] = Math.cos(theta);
                matrix[2][2] = 1;
                matrix[3][3] = 1;
                break;
        }
        return matrix;
    }

    // translation
    public static double[][] translation(double x, double y, double z){
       double[][] matrix = new double[4][4];
        matrix[0][0] = 1;
        matrix[1][1] = 1;
        matrix[2][2] = 1;
        matrix[3][3] = 1;
        matrix[0][3] = x;
        matrix[1][3] = y;
        matrix[2][3] = z;
        return matrix;
        }

    // pointAt
    public static double [][]  pointAt(vector pos, vector target, vector up){
        double[][] matrix = new double [4][4];
        vector newForward = vector.subtract(target,pos);
        newForward.normalize();
        vector a = vector.multiply(newForward,vector.dotProduct(up,newForward));
        vector newUp = vector.subtract(up,a);
        newUp.normalize();
        vector newRight = vector.crossProduct(newUp,newForward);
        matrix[0][0] = newRight.getX();
        matrix[0][1] = newUp.getX();
        matrix[0][2] = newForward.getX();
        matrix[0][3] = pos.getX();
        matrix[1][0] = newRight.getY();
        matrix[1][1] = newUp.getY();
        matrix[1][2] = newForward.getY();
        matrix[1][3] = pos.getY();
        matrix[2][0] = newRight.getZ();
        matrix[2][1] = newUp.getZ();
        matrix[2][2] = newForward.getZ();
        matrix[2][3] = pos.getZ();
        matrix[3][0] = 0;
        matrix[3][1] = 0;
        matrix[3][2] = 0;
        matrix[3][3] = 1;
        return matrix;
    }

    // inverse
    public static double[][] quickInverse(double[][] matrix){
        double[][] newMatrix = new double[4][4];
        newMatrix[0][0] = matrix[0][0];
        newMatrix[0][1] = matrix[1][0];
        newMatrix[0][2] = matrix[2][0];
        newMatrix[1][0] = matrix[0][1];
        newMatrix[1][1] = matrix[1][1];
        newMatrix[1][2] = matrix[2][1];
        newMatrix[2][0] = matrix[0][2];
        newMatrix[2][1] = matrix[1][2];
        newMatrix[2][2] = matrix[2][2];
        newMatrix[3][0] = 0;
        newMatrix[3][1] = 0;
        newMatrix[3][2] = 0;
        newMatrix[0][3] = -((matrix[0][3]*newMatrix[0][0])+(matrix[1][3]*newMatrix[0][1])+(matrix[2][3]*newMatrix[0][2]));
        newMatrix[1][3] = -((matrix[0][3]*newMatrix[1][0])+(matrix[1][3]*newMatrix[1][1])+(matrix[2][3]*newMatrix[1][2]));
        newMatrix[2][3] = -((matrix[0][3]*newMatrix[2][0])+(matrix[1][3]*newMatrix[2][1])+(matrix[2][3]*newMatrix[2][2]));
        newMatrix[3][3] = 1;
        return newMatrix;
    }

    // multiply 2 mats
    public static double [][] multiplyMatrixMatrix(double[][] mat1, double[][] mat2){
        double[][] matrix = new double[4][4];
        for(int i = 0; i <4; i ++){
            for(int j = 0; j <4; j++){
                matrix[i][j] = mat1[0][j] * mat2[i][0] + mat1[1][j] * mat2[i][1] + mat1[2][j] * mat2[i][2] + mat1[3][j] * mat2[i][3];
            }
        }
        return matrix;
    }

    // print
    public static String printMat (double [][] toPrint) {
        String message = "";
        message+="\n[";
        for(int i = 0; i <4; i++){
            for (int j = 0; j<4; j++){
                message += toPrint[i][j] + " , ";
            }
            if (i == 3){
                message+="]";
            }
            message+="\n";
        }
        return message;
    }
}
