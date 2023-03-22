package ModelViewController;

import java.awt.*;

public class PicrossModel {

    /**
     *         Model: Checks the squares
     *         View: UI stuff everything from A12
     *         Controller: Check what component was clicked
     *         and passed to the model
     *
     *
     *         The model is in control of if the buttons are valid or not. It should have an array;
     *
     *
     *         Design mode: Make a little pattern to save
     *         Able to replay it later
     *         File Chooser -> Save the design to text file
     *
     *         Encode it as a string
     *         Consider Error Checking
     *
     *
     *         Colors: Set some different colors
     *
     *         Getters and setters are required
     *
     *         OOP
     *
     *         Button is pressed in view -> Calls model to validate -> calls control to deal with action
     */

    private Color correctColor;
    private Color incorrectColor;
    private Color markedColor;
    private int col;
    private int row;


    public PicrossModel(int col, int row){
        this.col = col;
        this.row = row;
    }


    public void setDimX(int columns){
        col = columns;
    }

    public int getDimX(){
        return col;
    }

    public void setDimY(int rows){
        row = rows;

    }

    public int getDimY(){
        return row;
    }

    public void setCorrectColor(Color correctColor){
        this.correctColor = correctColor;
    }

    public Color getCorrectColor(){
        return correctColor;
    }

//    public void setIncorrectColor(Color incorrectColor){
//        this.incorrectColor = incorrectColor;
//    }
//
//    public Color getIncorrectColor(){
//        return incorrectColor;
//    }
//
//    public void setMarkedColor(Color markedColor){
//        this.markedColor = markedColor;
//    }
//
//    public Color getMarkedColor(){
//        return markedColor;
//    }


}
