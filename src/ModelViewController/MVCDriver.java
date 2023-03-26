package ModelViewController;

public class MVCDriver {


    public static void main(String[]args){

        PicrossModel model = new PicrossModel(4,4);
        PicrossView view = new PicrossView(model.getDimX(),model.getDimY());





    }
}
