import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class App extends JFrame {
static Scanner sc=new Scanner(System.in);

public App() {
    setTitle("GUI");
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    Container pane = getContentPane();
    GroupLayout gl = new GroupLayout(pane);
    pane.setLayout(gl);

    JButton button = new JButton("Exit");
    button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });

    gl.setVerticalGroup(gl.createSequentialGroup().addComponent(button));
    gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(button));

    gl.setAutoCreateContainerGaps(true);
}

public static void main(String[] args) throws SQLException {
    EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            App a = new App();
            a.setVisible(true);
        }
    });



        Database db=new Database();

        System.out.println("Geben Sie den Namen der PatientIn ein");
        String name=sc.next();
        //SELECT * FROM `patientin` where name=''
    //abc'or 1=1 #
    //--
        try {
          //  db.getAllDoctors();
            db.getPatientWithName(name);
        }catch(Exception e){
            e.printStackTrace();
            db.closeAll();
        }
    }
}
