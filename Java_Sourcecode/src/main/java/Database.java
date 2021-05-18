

import java.sql.*;

public class Database {
    private   Connection c;
    private final String url="jdbc:mysql://127.0.0.1:3306/hospital";
    private final String user="root";
    private final String password="";
    public Database(){

        try {

             c= DriverManager.getConnection(url,user,password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
          st.close();

      }
      closeAll();

    }
    public void getAllPatients() throws SQLException {


            closeAll();
    }
    public void getPatientWithName(String name) throws SQLException {//wenn name
        //SELECT * FROM hospital.PatientIn where name='Kleines Kino'or 1=1; #';
        Statement st= c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM hospital.patientin where patientin.name ='"+name+"'");
        //Normal: in name="Anna Mustermann"
        //Injection: name=""

        while (rs.next()) {
            int svNummer = rs.getInt(1);
            String nameInTable = rs.getString(2);

            System.out.println("id:" + svNummer + " name:" + nameInTable);
        }
        closeAll();
    }
    public void getPatientsWithMedicine(String name) throws SQLException {
        Statement st= c.createStatement();
        //SELECT * FROM hospital.patientin gets medikament WHERE patientIn_sv_nummer = 1234 OR 1=1;
        ResultSet rs = st.executeQuery("SELECT * FROM hospital.patientin gets medikament where patientin gets medikament.medikament_name="+name);
        //Beheben durch PreparedStatements
        //PreparedStatement ps = connection.prepareStatement (
        //"SELECT * FROM hospital.patientin gets medikament where patientin gets medikament.medikament_name="+name);
    }

    public void closeAll() throws SQLException {

        c.close();
    }
}
