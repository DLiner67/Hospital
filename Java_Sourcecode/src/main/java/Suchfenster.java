import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author:Julia Nusko
 * @version 1.0
 *Connects to database and shows results in console
 *
 */
public class Suchfenster extends JFrame {

   private final Database db=new Database();
//INSERT INTO `patientin` (`sv-nummer`, `name`) VALUES ('', ''), ('123456789', 'Hacked User')
public Suchfenster() {

    setTitle("SQL Injection");
    setSize(300, 200);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.addWindowListener(new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        /**
         *
         * @param e is fired when the x-button is clicked
         */
        @Override
        public void windowClosing(WindowEvent e) {


            try {
                db.closeAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    });
    this.setLayout(new GridLayout(3,2));


    JTextField txtField=new JTextField("Name");
    txtField.setSize(this.getWidth()-10,20);

    JButton unsafeButton = new JButton("Suche (Unsicher)");
    unsafeButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                db.getPatientWithName(txtField.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }

        }
    });

    JButton safeButton = new JButton("Suche (Sicher)");
    safeButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                db.getPatientWithNamePreparedStatement(txtField.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }

        }
    });


    JButton clearButton = new JButton("Konsole löschen");
    clearButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          for(int i=0;i<50;i++){
              System.out.println("");
              //Löscht die Konsole
              //Schreibt einfach 50 leere Zeilen

          }
        }
    });

    this.add(txtField);
    this.add(clearButton);
    this.add(unsafeButton);
    this.add(safeButton);
    this.setVisible(true);

}
//Problem mit Scanner ->auf GUI umgestellt

    /**
     *
     *
     * @param args userinput from comandline.This program does not react on it.
     * @throws SQLException If connection to database fails
     */
    public static void main(String[] args) throws SQLException {

            Suchfenster a = new Suchfenster();

    }
}
