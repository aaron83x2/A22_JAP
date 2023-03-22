package ModelViewController;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {


    private ImageIcon title;
    private JLabel imageLabel;


    private JPanel progressBorder;
    private JLabel progress;
    private JProgressBar progressBar;



    public SplashScreen(){

        //Initialize JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(570,200);
        this.setTitle("Picross");
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        //Image shown on the loading screen
        imageLabel = new JLabel("");
        title = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\PICROSS2.png");
        imageLabel.setIcon(title);
        this.add(imageLabel, BorderLayout.CENTER);


        //Main Progress Bar
        progressBorder = new JPanel();
        progressBorder.setLayout(new GridLayout(2,1));
        this.add(progressBorder, BorderLayout.SOUTH);


        //Progress Label
        progress = new JLabel();
        progress.setBackground(Color.YELLOW);
        progress.setHorizontalAlignment(JTextField.CENTER);
        progressBorder.add(progress);

        progressBar = new JProgressBar();
        progressBar.setForeground(Color.GREEN);
        progressBorder.add(progressBar);


    }

    public static void main(String[]args){

        //Splash Screen
        SplashScreen splash = new SplashScreen();

        try {
            //For loop used to count from 1 to 100 to make it load the game
            for (int i = 0; i <=100; i++) {
                splash.progressBar.setValue(i);
                Thread.sleep(50);
                splash.progress.setText("%" + i);

                //At 100 Open the Main Menu
                if(i == 100){
                    //Close the splash screen window and open main game.
                    splash.progress.setText("Loading...");
                    splash.dispose();
                    MainMenu menu = new MainMenu();

                }
            }
            //Catch Exception if Interrupted
        }catch(InterruptedException e){
            e.printStackTrace();

        }
    }
}
