package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton start = new JButton("Start");
    JButton reset = new JButton("Reset");
    JLabel timeLabel = new JLabel();

    int elapsedTime = 0; //Miliseconds
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d",seconds);
    String minutes_string = String.format("%02d",minutes);
    String hours_string = String.format("%02d",hours);

    //Action preformed method
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            String seconds_string = String.format("%02d",seconds);
            String minutes_string = String.format("%02d",minutes);
            String hours_string = String.format("%02d",hours);
            timeLabel.setText(hours_string +":"+minutes_string+":"+seconds_string);


        }
    });




    Stopwatch(){

        timeLabel.setText(hours_string + ":"+minutes_string + ":"+seconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("", Font.PLAIN, 35));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        frame.add(timeLabel);

        start.setBounds(100,200,100,50);
        start.setFont(new Font("", Font.PLAIN,20));
        start.setFocusable(false);
        start.addActionListener(this);
        frame.add(start);

        reset.setBounds(200,200,100,50);
        reset.setFont(new Font("", Font.PLAIN,20));
        reset.setFocusable(false);
        reset.addActionListener(this);
        frame.add(reset);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    void start(){
        timer.start();

    }
    void stop(){
        timer.stop();
    }

    void reset(){
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours=0;

        seconds_string = String.format("%02d",seconds);
        minutes_string = String.format("%02d",minutes);
        hours_string = String.format("%02d",hours);
        timeLabel.setText(hours_string +":"+minutes_string+":"+seconds_string);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == start){

            if(started == false){
                started = true;
                start.setText("Stop");
                start();
            }else {
                started = false;
                start.setText("Start");
                stop();
            }
        }


        if(e.getSource() == reset){
            started=false;
            start.setText("Start");
            reset();

        }


    }

    public static void main(String[]args){
        Stopwatch stopwatch = new Stopwatch();
    }
}