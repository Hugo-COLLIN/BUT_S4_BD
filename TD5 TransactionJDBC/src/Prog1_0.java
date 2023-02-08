import java.sql.*;

class Prog1_0 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";

        String sql0 = "DROP TABLE Etudiant"; //IF EXISTS(select * from sysobjects where name='Etudiant') drop table Etudiant
        String sql1 = "CREATE TABLE Etudiant (id NUMBER(4) PRIMARY KEY, nom VARCHAR2(20), age NUMBER(3))";
        String sql2 = "SELECT * FROM Etudiant";


        Connection con1 = DriverManager.getConnection(url, args[0], args[1]);
        try
        {
            con1.setAutoCommit(false);
            Statement st0 = con1.createStatement();
            st0.execute(sql0);

            // Création de la table ETUDIANT
            Statement st1 = con1.createStatement();
            st1.execute(sql1);

            //con1.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Connection con2 = DriverManager.getConnection(url, args[0], args[1]);
        try
        {
            con2.setAutoCommit(false);

            // lancer select * from ETUDIANT;
            Statement st2 = con2.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            while (rs.next())
                System.out.println(
                        rs.getString(1) + "\t" +
                        rs.getString(2) + "\t" +
                        rs.getString(3) + "\t"
                        );

            //con2.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        con1.close();
        con2.close();
    }
}