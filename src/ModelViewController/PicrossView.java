package ModelViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;



public class PicrossView extends JFrame  {


    //Menu Bar
    private JMenu game;
    private JMenuItem New;
    private JMenuItem solution;
    private JMenuItem exit;

    //Help Menu Options
    JMenu help;
    JMenuItem colors;
    JMenuItem about;

    //File Menu Options
    private JMenu file;
    private JMenuItem save;
    private JMenuItem load;

    //Image Icons for Game Menu Options
    private ImageIcon newIcon;
    private ImageIcon solIcon;
    private ImageIcon exitIcon;

    //Image Icons for Help Menu Options
    ImageIcon colorsIcon;
    ImageIcon aboutIcon;

    int col;
    int row;
    JButton buttons[][];

    Color defaultColor;
    Color correctColor;
    Color incorrectColor;
    Color markedColor;

    Checkbox mark;
    JComboBox combo;
    //Number Panels
    JTextField[] nums = new JTextField[10];

    JButton reset;
    JTextArea historyArea;

    //Points
    JTextField pointsTitle;
    private int pointsCounter = 0;

    Random rand  = new Random();
    Boolean[][] sol;




    JButton markButton;




    PicrossModel model = new PicrossModel(5,5);
    //Controller controller = new Controller(model,new View());
    //ColorMenu colrMenu = new ColorMenu();

    //public View(){}


    public PicrossView(int dimX, int dimY){

        col = dimY;
        row = dimX;

        buttons = new JButton[row][col];
        sol = new Boolean[row][col];

        model.setCorrectColor(Color.GREEN);
        defaultColor = model.getCorrectColor();

//        System.out.println(model.getCorrectColor());
        //historyArea.append("Color: " + model.getCorrectColor());

        //Window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setSize(1000,700);
        this.setLayout(new BorderLayout());
        this.setVisible(true);


        //Menu Bar
        JMenuBar menuBar = new JMenuBar();

        //Game Tab
        game = new JMenu("Game");

        //Adding New Item to Game
        New = new JMenuItem("New");

        //New Icon
        newIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piciconnew.gif");
        New.setIcon(newIcon);
//        New.addActionListener(e->newGame());
//        New.addActionListener(Controller::handleButtonClick);

        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controls.handleButtonClick();
            }
        });
        game.add(New);

        //Adding Solution Item to Game
        solution = new JMenuItem("Solution");
        solIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piciconsol.gif");
        solution.setIcon(solIcon);
        solution.addActionListener(e->showSolution());
        game.add(solution);

        //Add Exit item to Game
        exit = new JMenuItem("Exit");
        exitIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piciconext.gif");
        exit.setIcon(exitIcon);
        exit.addActionListener(e->exit());
        game.add(exit);


        //Help Tab
        help = new JMenu("Help");

        //Adding Colors item to Help
        colors = new JMenuItem("Colors");
        colorsIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piciconcol.gif");
        colors.setIcon(colorsIcon);
        colors.addActionListener(e-> setNewColor());
        help.add(colors);

        //Adding About item to Help
        about = new JMenuItem("About");
        aboutIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piciconabt.gif");
        about.setIcon(aboutIcon);
        about.addActionListener(e -> about());
        help.add(about);


        //File Tab
        file = new JMenu("File");

        //Adding load to File
        load = new JMenuItem("Load");
        load.addActionListener(e -> loadFile());
        file.add(load);

        //Adding Save to File
        save = new JMenuItem("Save");
        save.addActionListener(e-> {
            try {
                saveFile();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        file.add(save);


        //Add Options
        menuBar.add(game);
        menuBar.add(help);
        menuBar.add(file);

        //Set Menu Bar to the top
        this.setJMenuBar(menuBar);


        //Left Side
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(142,100));
        left.setLayout(new GridLayout(row,1));


        for(int  i = 0; i<row;i++){
            nums[i] = new JTextField();
            nums[i].setEditable(false);
            nums[i].setText("1");
            nums[i].setHorizontalAlignment(JTextField.CENTER);
//            nums[i].setBorder(null);
            left.add(nums[i]);
        }
        //West Panel
        this.add(left, BorderLayout.WEST);

        //Top
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(100,100));
        top.setLayout(new GridLayout(1,col));




        JPanel space = new JPanel();
        space.setSize(140,100);
        space.setBackground(Color.LIGHT_GRAY);

//        String[] gridOption = {"4x4","5x5","6x6","7x7"};
//        combo = new JComboBox(gridOption);
////        combo.addActionListener(this);
//
//        space.add(combo);
//        top.add(space);

        //Mark Checkbox
        markButton = new JButton();
        markButton.setSize(200,200);
        markButton.addActionListener(e -> markCheck());
        space.add(markButton);
        top.add(space);


        mark = new Checkbox();
        mark.setFocusable(false);
//        mark.setText("");

//        mark.setSize(100,100);
//        //mark.setBackground(Color.PINK);
//        space.add(mark);
//        top.add(space);







        for(int i = 0; i<col;i++){
            nums[i] = new JTextField();
            nums[i].setEditable(false);
            nums[i].setText("1");
            nums[i].setHorizontalAlignment(JTextField.CENTER);
//            nums[i].setBorder(null);
            top.add(nums[i]);
        }

        //Right Side
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(141,100));

        //History Text Area
        historyArea = new JTextArea(21,10);
//        historyArea = new JTextArea();
        JScrollPane sp = new JScrollPane(historyArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        historyArea.setPreferredSize(new Dimension(120,360));
        historyArea.setEditable(false);
        right.add(sp);


        //Time
        JTextArea time = new JTextArea();
        time.setBackground(Color.WHITE);
        time.setPreferredSize(new Dimension(100,35));
        time.setEditable(false);
        time.setText("Time: " + 0);
        right.add(time);

        this.add(right, BorderLayout.EAST);

        //Center Buttons
        JPanel cnt = new JPanel();
        cnt.setPreferredSize(new Dimension(100,100));
        cnt.setBackground(Color.WHITE);
        cnt.setLayout(new GridLayout(col, row));

        //Create Button Grid
        for(int  i = 0; i<row; i++){
            for(int j = 0; j<col;j++){

            buttons[i][j] = new JButton();
            buttons[i][j].setFocusable(false);
            sol[i][j] = newBool();
            buttons[i][j].setText(String.valueOf(sol[i][j]));

            int finalI = i; // Passed in to find the position of the row
            int finalJ = j;// Passed in to find the position of the col
            buttons[i][j].addActionListener(e -> buttonClick(finalI,finalJ));

//               buttons[i][j].addActionListener(e-> Controller.handleButtonClick());
            cnt.add(buttons[i][j]);
            }
        }
        this.add(cnt,BorderLayout.CENTER);

        pointsTitle = new JTextField();
        pointsTitle.setBorder(null);
        pointsTitle.setEditable(false);
        pointsTitle.setBackground(Color.PINK);
        pointsTitle.setText("Points: " + pointsCounter);
        top.add(pointsTitle);

        this.add(top,BorderLayout.NORTH);

        //Bottom Panel
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(100,100));
        bottom.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Reset Button
        reset = new JButton();
        reset.setText("Reset");
        reset.addActionListener(e-> resetGrid());
        reset.setPreferredSize(new Dimension(70,20));
        right.add(reset);
        this.add(bottom, BorderLayout.SOUTH);

    }




    public void buttonClick(int xPos, int yPos){

        if( buttons[xPos][yPos].getText().equals("false")){
            buttons[xPos][yPos].setBackground(Color.RED);
        } else {
            buttons[xPos][yPos].setBackground(defaultColor);
            pointsCounter++;
            pointsTitle.setText(String.valueOf(pointsCounter));
        }
        historyArea.append("Pos: " + (xPos+1) +"," + (yPos+1) +" clicked" +"\n");
    }

//    public void newGame(){
//        this.dispose();
//        new View(5,5);
//    }

    public void markCheck(){
//        historyArea.append( " " + "\n");

        if(markButton.isSelected()){

            System.out.println("Selected");
        }
        for(int  i = 0; i<row; i++) {
            for (int j = 0; j < col; j++) {

                if(buttons[i][j].getText().equals("false")){
                    buttons[i][j].setBackground(Color.YELLOW);
                }
//
            }
        }


    }



    public void resetGrid(){

        for(int  i = 0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                buttons[i][j].setBackground(getBackground());
            }
        }
        pointsCounter = 0;
        pointsTitle.setText(String.valueOf(pointsCounter));
        historyArea.append("Reset Clicked." + "\n");
    }



    public void newGame(){
        this.dispose();
        new PicrossView(5,5);
    }

    public boolean newBool(){
//        this.dispose();
        Random rand = new Random();
        boolean newBool = rand.nextBoolean();
        return newBool;
    }

    public void showSolution(){
        for(int i = 0; i<sol.length;i++){
            for(int j = 0; j<sol.length;j++){
                historyArea.append("Pos: " + (i+1) +"," + (j+1) +": "+ String.valueOf(sol[i][j]) + "\n");
            }
        }
    }


    public void exit(){
        this.dispose();
        MainMenu menu = new MainMenu();
    }



    public void setNewColor(){
        ColorMenu menu = new ColorMenu();
        menu.setBackground(model.getCorrectColor());

        //this.defaultColor = color;
    }

    public void about(){
        JOptionPane.showMessageDialog(null,"Use the numbers as hints to select" +
                "the correct button. Green means the button selected is correct and Red means that the button selected is " +
                "incorrect.","Picross",JOptionPane.INFORMATION_MESSAGE);

    }


    public void loadFile(){
        JFileChooser fileChooser = new JFileChooser();

       // int response = fileChooser.showOpenDialog(null);//Select file to open
        int response = fileChooser.showSaveDialog(null);//select file to open


        if(response == JFileChooser.APPROVE_OPTION){
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
        }

    }

    public void saveFile() throws FileNotFoundException {
        PrintWriter outputFile = new PrintWriter("GridSolution.txt");

        for(int i = 0; i<sol.length;i++){
            for(int j = 0; j<sol.length;j++){
               // outputFile.println(sol);
                String.valueOf(sol[i][j]);
            }

        }
        outputFile.close();

        System.out.println("saved");


    }


    public void resizeTable(){

    }




//    @Override
    public void actionPerformed(ActionEvent e) {





        /*
        //If button is clicked
        for(int  i = 0; i<row; i++){
            for(int j = 0; j<col;j++) {

                if (e.getSource() == buttons[i][j]) {
                    //Light Button at index i Cyan
                    buttons[i][j].setBackground(defaultColor);
                    historyArea.append("Pos: " + (i+1) +"," + (j+1) +" clicked" +"\n");

                    pointsCounter = pointsCounter + 1;
                    pointsTitle.setText("Points: " + pointsCounter);

                }
            }
        }
        //If the reset button is pressed
        if(e.getSource() == reset){
            historyArea.append("Reset Clicked \n");
            //All button colors reset back to white
            for(int  i = 0; i<row; i++) {
                for (int j = 0; j <col; j++) {
                    buttons[i][j].setBackground(getBackground());
                }
            }
            pointsCounter = 0;
            pointsTitle.setText("Points: " + pointsCounter);

        }

        if(e.getSource() == colors){
            ColorMenu menu = new ColorMenu();

        }

        if(e.getSource() == about){
            JOptionPane.showMessageDialog(null,"Use the numbers as hints to select" +
                    "the correct button. Green means the button selected is correct and Red means that the button selected is " +
                    "incorrect.","Picross",JOptionPane.INFORMATION_MESSAGE);
        }




        if(e.getSource() == combo){
            String selectedGrid = (String) combo.getSelectedItem();


            switch(selectedGrid){

                case "4x4":
                    this.dispose();
                    new View(4,4);
                    historyArea.setText("4x4 Selected");
                    break;

                case "5x5":
                    this.dispose();
                    new View(5,5);
                    historyArea.setText("5x5 Selected");
                    break;

                case "6x6":
                    this.dispose();
                    new View(6,6);
                    historyArea.append("6x6 Selected \n");
                    break;

                case "7x7":
                    this.dispose();
                    new View(7,7);
                    historyArea.append("7x7 Selected \n");
                    break;
            }

        }

        */
    }
}
