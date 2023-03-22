package ModelViewController;

public class MVCDriver {


    public static void main(String[]args){

        PicrossModel model = new PicrossModel(5,5);
        PicrossView view = new PicrossView(model.getDimX(),model.getDimY());


    }
}
