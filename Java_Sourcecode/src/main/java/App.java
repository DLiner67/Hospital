public class App {

    public static void main(String[] args) {
        Database db=new Database();
        try {
            db.getAllDoctors();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
