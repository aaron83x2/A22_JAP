package Test;

import java.util.Random;

public class BooleanArrayTest {


    private static int dimX = 4;
    private static int dimY = 4;


    public static void main(String[]args){

        boolean[][] sol = new boolean[dimX][dimY];

        // creating Random object
        //System.out.println(rd.nextBoolean()); // displaying a random boolean

        for(int i = 0; i<sol.length;i++){
            for(int j = 0; j<sol.length;j++){
                sol[i][j] = retriveRandomBool();
                System.out.print(sol[i][j] + " ");
            }
        }

    }



    public static boolean retriveRandomBool(){
        Random rand = new Random();
        boolean x = rand.nextBoolean();
        return x;
    }
}
