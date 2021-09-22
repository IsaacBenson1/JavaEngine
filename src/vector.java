public class vector {
    private double x;
    private double y;
    private double z;
    private double w;
    public vector (double x, double y, double z, double w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public vector (double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public double getX() {
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    public double getW() {
        return w;
    }

    public void setX(double x ){
        this.x = x;
    }
    public void setY(double y ){
        this.y = y;
    }
    public void setZ(double z ){
        this.z = z;
    }
    public void setW(double w ){
        this.w = w;
    }
    public static vector vectorAdd(vector vec1, vector vec2){
        double newX = vec1.getX()+vec2.getX();
        double newY = vec1.getY()+vec2.getY();
        double newZ = vec1.getZ()+vec2.getZ();
        return new vector(newX,newY,newZ);
    }

    public static vector subtract(vector vec1, vector vec2){
        double newX = vec1.getX()-vec2.getX();
        double newY = vec1.getY()-vec2.getY();
        double newZ = vec1.getZ()-vec2.getZ();
        return new vector(newX,newY,newZ);
    }

    public static double dotProduct(vector vec1, vector vec2){
        return ((vec1.getX()*vec2.getX()) + (vec1.getY()*vec2.getY()) + (vec1.getZ()*vec2.getZ()));
    }

    public static vector crossProduct(vector vec1, vector vec2){
        double newX = vec1.getY()*vec2.getZ() - vec1.getZ()*vec2.getY();
        double newY = vec1.getZ()*vec2.getX() - vec1.getX()*vec2.getZ();
        double newZ = vec1.getX()*vec2.getY() - vec1.getY()*vec2.getX();
        return new vector(newX,newY,newZ);
    }

    public static vector multiply(vector vec1,double scale){
        double x = vec1.getX();
        double y = vec1.getY();
        double z = vec1.getZ();
        x*=scale;
        y*=scale;
        z*=scale;
        return new vector(x,y,z);
    }

    public double getLength(){
        return Math.sqrt(vector.dotProduct(this,this));
    }

    public void normalize(){
        double l =  this.getLength();
        double newX = this.getX();
        double newY = this.getY();
        double newZ = this.getZ();
        this.setX(newX/l);
        this.setY(newY/l);
        this.setZ(newZ/l);
    }

    public static vector multiplyMatrix(vector first, double[][] matrix){
        double newX1 = (first.getX() * matrix[0][0]) + (first.getY()* matrix[0][1]) + (first.getZ()*matrix[0][2]) + first.getW()*matrix[0][3];
        double newY1 = (first.getX() * matrix[1][0]) + (first.getY()* matrix[1][1]) + (first.getZ()*matrix[1][2]) + first.getW()*matrix[1][3];
        double newZ1 = (first.getX() * matrix[2][0]) + (first.getY()* matrix[2][1]) + (first.getZ()*matrix[2][2]) + first.getW()*matrix[2][3];
        double newW1 = (first.getX() * matrix[3][0]) + (first.getY()* matrix[3][1]) + (first.getZ()*matrix[3][2]) + first.getW()*matrix[3][3];
        vector returner = new vector(newX1,newY1,newZ1,newW1);
        return returner;
    }

    public void move(double x, double y, double z){
        this.x+=x;
        this.y+=y;
        this.z+=z;
    }

    public String toString(){
        return ("[ X: "+this.getX()+" , Y: "+this.getY()+ " , Z: "+this.getZ()+" ] \n");
    }

}
