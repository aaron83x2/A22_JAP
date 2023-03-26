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

    public Controls(PicrossView view,
                    JMenuItem newClick, JMenuItem solutionClick, JMenuItem exitClick,
                    JMenuItem colorsClick, JMenuItem aboutClick,
                    JMenuItem saveClick, JMenuItem loadClick,
                    JButton markClick, JButton[][] gridClicks, JButton resetClick){

        this.view = view;
        this.newClick = newClick;
        this.solutionClick = solutionClick;
        this.exitClick = exitClick;
        this.colorsClick = colorsClick;
        this.aboutClick = aboutClick;
        this.markClick = markClick;
        this.saveClick = saveClick;
        this.loadClick = loadClick;
        this.resetClick = resetClick;
        this.gridClicks = gridClicks;
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

            if(e.getSource() == view.reset){
                for(int  i = 0; i<view.row; i++) {
                    for (int j = 0; j < view.col; j++) {
                        view.buttons[i][j].setBackground(view.getBackground());
                    }
                }
                view.pointsCounter = 0;
                view.pointsTitle.setText("Points: "+ view.pointsCounter);
                view.historyArea.append("Reset Clicked." + "\n");
            }

            if(e.getSource() == view.about){
                JOptionPane.showMessageDialog(null,"Use the numbers as hints to select" +
                        "the correct button. Green means the button selected is correct and Red means that the button selected is " +
                        "incorrect.","Picross",JOptionPane.INFORMATION_MESSAGE);
            }
    }
}
