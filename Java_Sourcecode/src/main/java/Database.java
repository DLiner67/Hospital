import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private   Connection c;
    private final String url="jdbc:mysql://127.0.0.1:3306/hospital";
    private final String user="root";
    private final String password="";//UNSICHER! Nicht empfehlenswert
    public Database(){

        try {

             c= DriverManager.getConnection(url,user,password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    /**
     *
     * @throws SQLException If connection closure fails then throw an exception
     */
    public void getAllDoctors() throws SQLException {
       Statement st= c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM hospital.aerztin");
      try {

          while (rs.next()) {
              int id = rs.getInt(1);
              String name = rs.getString(2);
              Doctor doc=new Doctor(id,name);
              System.out.println("id:" + id + " name:" + name);
          }
      }catch(Exception e){
          e.printStackTrace();
      }finally{
          rs.close();
          closeAll();
      }


    }

    /**
     *
     *
     *
     * @param name The name of the patient where we want the medical id from the database
     * @throws SQLException If the table name is incorrect ot the query is wrong
     */
    public List<String> getPatientWithName(String name) throws SQLException {

        System.out.println("UNSAFE:");
        Statement st= c.createStatement();

        //System.out.println("SELECT * FROM hospital.patientin where name ='"+name+"'");

        ResultSet rs = st.executeQuery("SELECT * FROM hospital.patientin where name ='"+name+"'");
        //ResultSet rs = st.executeQuery("SELECT * FROM hospital.patientin where name ='"+name+"'");
        //';INSERT INTO `patientin` (`sv-nummer`, `name`) VALUES ('373908071978', 'Nusko');
        //;INSERT INTO `patientin` (`sv-nummer`, `name`) VALUES ('373908071978', 'Nusko');
        //');INSERT INTO `patientin` (`sv-nummer`, `name`) VALUES ('373908071978', 'Nusko');
        //'; INSERT INTO patientin (sv-nummer, name) VALUES ('373908071978', 'Nusko') #Name

        //`; DROP TABLE hospital.patientin #
        //`; INSERT INTO `patientin` (`sv-nummer`, `name`) VALUES (`373908071978`, `Nusko`) #

        //'; INSERT INTO `patientin` (`sv-nummer`, `name`) VALUES ('373908071978', 'Nusko') #Name
        //'; INSERT INTO patientin (sv-nummer, name) VALUES (373908071978, 'Nusko') #Name
        //'; INSERT INTO patientin (sv-nummer, name) VALUES (373908071978, 'Nusko') #
        //'; INSERT INTO patientin (sv-nummer, name) VALUES (373908071978, Nusko) #
       // '; INSERT INTO patientin (sv-nummer, name) VALUES ('373908071978',' Nusko') #
        //'; INSERT INTO patientin ('sv-nummer',' name') VALUES ('373908071978',' Nusko') #
        //'; INSERT INTO patientin ('sv-nummer','name') VALUES ('373908071978',' Nusko') #
        //'; INSERT INTO patientin (sv-nummer,name) VALUES ('373908071978',' Nusko') #
        //'
        List<String>returnList=new ArrayList<>();

        while (rs.next()) {
            long svNummer = rs.getLong(1);
            String nameInTable = rs.getString(2);
           returnList.add("id:" + svNummer + " name:" + nameInTable);
        }
        return returnList;
    }
    /**
     *
     *
     *
     * @param name The name of the patient where we want the medical id from the database
     * @throws SQLException If the table name is incorrect ot the query is wrong
     */
    public List<String> getPatientWithNamePreparedStatement(String name) throws SQLException {
        //SELECT * FROM hospital.PatientIn where name='Egal'or 1=1; #';
        System.out.println("SAFE:");
        PreparedStatement st= c.prepareStatement("SELECT * FROM hospital.patientin where name =?");
        st.setString(1,name);

        ResultSet rs = st.executeQuery();
        List<String>returnList=new ArrayList<>();

        while (rs.next()) {
            long svNummer = rs.getLong(1);
            String nameInTable = rs.getString(2);
            returnList.add("id:" + svNummer + " name:" + nameInTable);
           // System.out.println("id:" + svNummer + " name:" + nameInTable);
        }
        return returnList;
        // closeAll();
    }
    /*Spaltenanzahl einer Tabelle herausfinden*/
    /*Patienten einfügen*/



    /**
     *
     *
     * @throws SQLException Is thrown when connection closure fails
     */
    public void closeAll() throws SQLException {

        c.close();
    }
}
