package ModelViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;

public class Controls implements ActionListener {


    //Model and View
    private PicrossModel model;
    private PicrossView view;



    //Menu Items
    private JMenuItem newClick;
    private JMenuItem solutionClick;
    private JMenuItem exitClick;
    private JMenuItem colorsClick;
    private JMenuItem aboutClick;
    private JMenuItem loadClick;
    private JMenuItem saveClick;

    //Buttons
    JButton markClick;
    JButton gridClicks[][];
    JButton resetClick;



//    public Controls(){}

//    public Controls(JMenuItem newClick, JMenuItem solutionClick, JMenuItem exitClick, JMenuItem colorsClick, JMenuItem aboutClick, JMenuItem loadClick, JMenuItem saveClick){

    public Controls(PicrossView view, JMenuItem newClick, JMenuItem solutionClick, JMenuItem exitClick, JButton markClick, JButton[][] gridClicks){

        this.view = view;
        this.newClick = newClick;
        this.solutionClick = solutionClick;
        this.exitClick = exitClick;
        this.markClick = markClick;
        this.gridClicks = gridClicks;
//        this.colorsClick = colorsClick;
//        this.aboutClick = aboutClick;
//        this.loadClick = loadClick;
//        this.saveClick = saveClick;
    }


    public void timer(){}

    @Override
    public void actionPerformed(ActionEvent e) {

//        PicrossView view = new PicrossView();

        if (e.getSource() == newClick){
            view.dispose();
            new PicrossView(4,4);
        }

        if(e.getSource() == solutionClick){
            for(int i = 0; i<view.sol.length;i++){
                for(int j = 0; j<view.sol.length;j++){
                    view.historyArea.append("Pos: " + (i+1) +"," + (j+1) +": "+ String.valueOf(view.sol[i][j]) + "\n");
                }
            }
        }

        //Handling Exit Button
        if(e.getSource() == exitClick){
            view.dispose();
            MainMenu menu = new MainMenu();
        }

        //Handling Mark Button Click
        if(e.getSource() == view.markButton){
            for(int  i = 0; i<view.row; i++) {
                for (int j = 0; j < view.col; j++) {
                    if(view.buttons[i][j].getText().equals("false")){
                        view.buttons[i][j].setBackground(Color.YELLOW);
                    }
                }
            }
        }

            //Check for Clicks in the grid
            for(int  i = 0; i<view.row; i++) {
                for (int j = 0; j <view.col; j++) {

                    if(e.getSource() == gridClicks[i][j]) {
                        if(view.buttons[i][j].getText().equals("false")){
                            view.buttons[i][j].setBackground(Color.RED);
                        } else {
                            view.buttons[i][j].setBackground(view.defaultColor);
                            view.pointsCounter++;
                            view.pointsTitle.setText("Points: "+ view.pointsCounter);
                        }
                        view.historyArea.append("Pos: " + (i+1) +"," + (j+1) +" clicked" +"\n");
                    }

                }
            }


    }
}
