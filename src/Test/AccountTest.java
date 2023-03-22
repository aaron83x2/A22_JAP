package Test;

import javax.swing.*;
import java.awt.*;

public class AccountTest {



    String name;
    Color color;


    public static void main(String[]args){

        AccountTest acc = new AccountTest();
        acc.setName("Aaron");
        acc.setColor(Color.CYAN);



        acc.setName("Ron");
        acc.setColor(null);

        acc.printDetails();



    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;

    }


    public void printDetails(){
        System.out.println(getName() + ": " + getColor());
    }












}
