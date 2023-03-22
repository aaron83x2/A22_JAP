package Test;

import java.util.Random;

public class RandomNumTest {


    private int dim = 5;

    public static void main(String[]args){
        Random rand = new Random();

        int num = (int) Math.pow(2,5);
//        System.out.println(num);

        int x = rand.nextInt(num-1)+0;

        System.out.println(Integer.toBinaryString(x));
//        System.out.println(Integer.toBinaryString(x));
//        System.out.println(Integer.toBinaryString());

    }

}
