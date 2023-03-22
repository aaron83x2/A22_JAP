package ModelViewController;

public class Controls {


//    Model model = null;
//    Color color = model.getCorrectColor();

//    View view = new View();


    private static PicrossModel model;
    private static PicrossView view;
//    private static View view = new View(model.getDimX(), model.getDimY());


    public Controls(PicrossModel model, PicrossView view){
        this.model = model;
        this.view = view;
    }


    public static void handleButtonClick(){

        PicrossView view = new PicrossView(4,4);

       view.historyArea.append("Menu Clicked");





        //System.out.println("Clicked");
       // View view = new View(5,5);
//        view.menu.setBackground(Color.GREEN);

    }


    public void timer(){

    }





}
