package ModelViewController;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {


    //Title
    private ImageIcon picrossIcon;
    private JLabel imageLabel;

    //Game Options
    JPanel titlePanel;
    JComboBox gridOptions;


    //Languages
    JRadioButton lang1;
    JRadioButton lang2;


    JButton play;




    public MainMenu(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(630,600);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        imageLabel = new JLabel("");
        picrossIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piccross.png");
        imageLabel.setIcon(picrossIcon);
        this.add(imageLabel, BorderLayout.NORTH);



        //Where all the bottom components will go
        JPanel bottomPanel = new JPanel();
        bottomPanel.setSize(140,100);
//        bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(new FlowLayout());

        //Grid Sizes
        String[] gridOption = {"4x4","5x5","6x6","7x7"};
        gridOptions = new JComboBox(gridOption);
        //gridOptions.addActionListener(this);
        bottomPanel.add(gridOptions);
        this.add(bottomPanel, BorderLayout.CENTER);


        //Language Options
        lang1 = new JRadioButton("English");
        lang2 = new JRadioButton("French");
        bottomPanel.add(lang1);
        bottomPanel.add(lang2);


        play = new JButton();

        this.pack();

    }


    public static void main(String[]args){

//        SplashScreen splashScreen = new SplashScreen();
        MainMenu menu = new MainMenu();

    }









}
