package ModelViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorMenu extends JFrame implements ActionListener {





    //Displayed Colors
    JPanel correct;
    JPanel incorrect;
    JPanel marked;

    //JButton
    JButton correctButton ;
    JButton incorrectButton;
    JButton markedButton;

    //Color
    Color correctColor;
    Color incorrectColor;
    Color markedColor;


    PicrossView view;
    PicrossModel model = new PicrossModel();



    public ColorMenu(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700,250);
        this.setTitle("Color Model");
        this.setLayout(new BorderLayout());
        this.setVisible(true);


        //Where the colors are gonna be
        JPanel colorsPanel = new JPanel();
        colorsPanel.setPreferredSize(new Dimension(142,100));
        colorsPanel.setLayout(new GridLayout(1,3));
        this.add(colorsPanel, BorderLayout.NORTH);

        //Panels
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(142,100));
        buttonPanel.setLayout(new GridLayout(1,3));
        this.add(buttonPanel, BorderLayout.CENTER);


        //Correct
        correct = new JPanel();
        correct.setPreferredSize(new Dimension(100,60));
//        correct.setBackground(getCorrectColor());
        correct.setBackground(Color.GREEN);
        colorsPanel.add(correct);


        //Incorrect
        incorrect = new JPanel();
        incorrect.setPreferredSize(new Dimension(100,100));
        incorrect.setBackground(Color.RED);
        colorsPanel.add(incorrect);

        //Marked
        marked = new JPanel();
        marked.setPreferredSize(new Dimension(100,100));
        marked.setBackground(Color.YELLOW);
        colorsPanel.add(marked);


        //Change Color for Correct
        correctButton = new JButton("Color1: Correct");
        correctButton.setPreferredSize(new Dimension(100,60));
        correctButton.addActionListener(this);
        buttonPanel.add(correctButton);


        //Change Color for Incorrect
        incorrectButton = new JButton("Color2: Incorrect");
        incorrectButton.setPreferredSize(new Dimension(100,60));
        incorrectButton.addActionListener(this);
        buttonPanel.add(incorrectButton);

        //Change Color for Marked
        markedButton = new JButton("Color3: Marked");
        markedButton.setPreferredSize(new Dimension(100,60));
        markedButton.addActionListener(this);
        buttonPanel.add(markedButton);

    }


    public void setCorrectPanel(Color cc){
        this.correctColor = cc;
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

    public static void main(String[]args){
        ColorMenu model = new ColorMenu();
//        model.setCorrectColor(Color.GREEN);
//        model.setIncorrectColor(Color.RED);
//        model.setMarkedColor(Color.YELLOW);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == correctButton){

            correctColor = JColorChooser.showDialog(this, "Select a Color", correctColor);
            correct.setBackground(correctColor);

            view = new PicrossView(5,5);
            view.setCorrectColor(correctColor);

            this.dispose();
        }

        if(e.getSource() == incorrectButton){
            incorrectColor = JColorChooser.showDialog(this, "Select a Color", incorrectColor);
            incorrect.setBackground(incorrectColor);
//            this.dispose();
        }

        if(e.getSource() == markedButton){
            markedColor = JColorChooser.showDialog(this, "Select a Color", markedColor);
            marked.setBackground(markedColor);
//            this.dispose();
        }

    }
}