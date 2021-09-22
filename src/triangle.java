public class triangle {
    private vector vec1;
    private vector vec2;
    private vector vec3;
    public triangle (vector vec1,vector vec2, vector vec3){
        this.vec1 = vec1;
        this.vec2 = vec2;
        this.vec3 = vec3;
    }

    public vector getVec1(){
        return vec1;
    }
    public vector getVec2(){
        return vec2;
    }
    public vector getVec3(){
        return vec3;
    }

    public static triangle multiplyMatrix(triangle tri1, double[][] matrix){
        triangle tri2;
        vector first = tri1.getVec1();
        vector second = tri1.getVec2();
        vector third = tri1.getVec3();
        first = (vector.multiplyMatrix(first,matrix));
        second = (vector.multiplyMatrix(second,matrix));
        third = (vector.multiplyMatrix(third,matrix));
        tri2 = new triangle(first,second,third);
        return tri2;
    }

    public void scale (double scaler){
        vec1 = vector.multiply(vec1, scaler);
        vec2 = vector.multiply(vec2, scaler);
        vec3 = vector.multiply(vec3, scaler);

    }
    public void move (double x, double y , double z){
        vec1 = new vector(vec1.getX()+x, vec1.getY()+y,vec1.getZ()+z );
        vec2 = new vector(vec2.getX()+x, vec2.getY()+y,vec2.getZ()+z );
        vec3 = new vector(vec3.getX()+x, vec3.getY()+y,vec3.getZ()+z );

    }

    public String toString(){
        String message = "";
        message+=this.vec1;
        message+=this.vec2;
        message+=this.vec3;
        return message;
    }
}
