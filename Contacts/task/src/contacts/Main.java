package contacts;

public class Main{
    public static void main(String[] args) {
        Controller controller = null;
        if(args.length==0){
            controller = new Controller(null);
        }else {
            controller = new Controller(args[0]);
        }
       controller.engine();
    }
}