import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class shape {
    private triangle[] allTriangles;
    public shape (String filepath){     // This constructor takes a string representing a filepath
        int triPointer = 0;             // to a .obj file and uses a scanner to populate the allTriangles array
        int triangleCount = 0;          // with the triangles that are described. NOTE: faces described in obj file
        int vertexCount = 0;            // must be triangulated, or this will throw an error.
        Scanner readFile = null;
        String message = "";
        String s;
        try {
            readFile = new Scanner(new File(filepath));
            while(readFile.hasNextLine()){
                s = readFile.nextLine();
                message+=s+"\n";
                if (s.charAt(0)=='v'){
                    vertexCount++;
                } else if (s.charAt(0)=='f'){
                    triangleCount++;
                }
            }
            triangle[] newTris = new triangle[triangleCount];
            vector[] newVectors = new vector[vertexCount];
            int vecPointer = 0;
            String[] lines = message.split("\n");
            String[][] tokens = new String[lines.length][4];
            for (int i = 0; i < lines.length; i++){
                tokens[i] = lines[i].split(" ");
                if(tokens[i][0].equals("v")){
                    double one = Double.parseDouble(tokens[i][1]);
                    double two = Double.parseDouble(tokens[i][2]);
                    double three = Double.parseDouble(tokens[i][3]);
                    newVectors[vecPointer] = new vector(one,two,three);
                    vecPointer++;
                } else if (tokens[i][0].equals("f")){
                    newTris[triPointer] = new triangle(newVectors[Integer.parseInt(tokens[i][1])-1] , newVectors[Integer.parseInt(tokens[i][2])-1] ,newVectors[Integer.parseInt(tokens[i][3])-1]);
                    triPointer++;
                }
            }
            allTriangles = new triangle[triPointer];
            for (int i =0; i <triPointer; i++){
                allTriangles[i] = newTris[i];
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File: "+ filepath +" not found :(");
            System.exit(1);
        }
    }

    public triangle getTriangleAt(int index){
        return allTriangles[index];
    }

    public triangle[] getAllTriangles(){
        return allTriangles;
    }


    }

