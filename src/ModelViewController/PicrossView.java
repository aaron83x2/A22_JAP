package ModelViewController;

import javax.swing.*;
import javax.xml.transform.SourceLocator;
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
    protected int pointsCounter = 0;

    Random rand  = new Random();
    Boolean[][] sol;

    JButton markButton;
    JButton menu;




    //PicrossModel model = new PicrossModel(5,5);
    //Controller controller = new Controller(model,new View());
    //ColorMenu colrMenu = new ColorMenu();

    //public View(){}

//    Controls ctrl = new Controls();


    PicrossModel model = new PicrossModel();



    public PicrossView(int dimX, int dimY){

        col = dimY;
        row = dimX;

        buttons = new JButton[row][col];
        sol = new Boolean[row][col];

        //model.setCorrectColor(Color.GREEN);
//        defaultColor = model.getCorrectColor();
        defaultColor = Color.GREEN;

//        System.out.println(model.getCorrectColor());
        //historyArea.append("Color: " + model.getCorrectColor());

        PicrossModel model = new PicrossModel();


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


        New.addActionListener(new Controls(this,New, solution, exit, markButton, buttons));
       // New.addActionListener(new Controls(New,solution,exit, colors, about,load,save));
        game.add(New);

        //Adding Solution Item to Game
        solution = new JMenuItem("Solution");
        solIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piciconsol.gif");
        solution.setIcon(solIcon);
        solution.addActionListener(new Controls(this, New, solution, exit, markButton, buttons));
        game.add(solution);

        //Add Exit item to Game
        exit = new JMenuItem("Exit");
        exitIcon = new ImageIcon("C:\\Users\\aaron\\workspace1\\A22\\src\\piciconext.gif");
        exit.setIcon(exitIcon);
        exit.addActionListener(new Controls(this, New, solution, exit, markButton,buttons));
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
            nums[i].setBackground(Color.GRAY);
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
        markButton = new JButton("Mark");
        markButton.setSize(200,200);

//        markButton.addActionListener(e -> markCheck());
        markButton.addActionListener(new Controls(this,New,solution,exit,markButton, buttons));
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

        int countRows = 0;
        int countCols = 0;

        //Create Button Grid
        for(int  i = 0; i<row; i++){
            for(int j = 0; j<col;j++){

            buttons[i][j] = new JButton();
            buttons[i][j].setFocusable(false);
            sol[i][j] = newBool();
            buttons[i][j].setText(String.valueOf(sol[i][j]));

            if(buttons[i][j].getText().equals("true")){
                countCols++;
            }


            int finalI = i; // Passed in to find the position of the row
            int finalJ = j;// Passed in to find the position of the col
//            buttons[i][j].addActionListener(e -> buttonClick(finalI,finalJ));
                buttons[i][j].addActionListener(new Controls(this,New,solution,exit,markButton, buttons));

//               buttons[i][j].addActionListener(e-> Controller.handleButtonClick());
            cnt.add(buttons[i][j]);
            }
        }

        //System.out.println(countCols);

        this.add(cnt,BorderLayout.CENTER);

        pointsTitle = new JTextField();
        pointsTitle.setBorder(null);
        pointsTitle.setEditable(false);
        pointsTitle.setBackground(Color.LIGHT_GRAY);
        pointsTitle.setHorizontalAlignment(JTextField.CENTER);
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

        ///Menu Button
        menu  = new JButton();
//        menu.addActionListener(new Controls(menu));
        menu.setText("Menu");
        menu.setPreferredSize(new Dimension(140,50));
        bottom.add(menu);

    }

    public PicrossView() {}


    public void setCorrectColor(Color cc){
        defaultColor = cc;
    }

    public Color getCorrectColor(){
        return defaultColor;
    }


    public void resetGrid(){

        for(int  i = 0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                buttons[i][j].setBackground(getBackground());
            }
        }
        pointsCounter = 0;
        pointsTitle.setText("Points: "+ pointsCounter);
        historyArea.append("Reset Clicked." + "\n");
    }



    public boolean newBool(){
//        this.dispose();
        Random rand = new Random();
        boolean newBool = rand.nextBoolean();
        return newBool;
    }


    public void setNewColor(){
        ColorMenu menu = new ColorMenu();

//        menu.setCorrectColor(defaultColor);



       // markButton.setBackground(model.getCorrectColor());
//        System.out.println(model.getCorrectColor());

        //this.defaultColor = color;
    }

    public void setGridColor(Color color){


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



}
