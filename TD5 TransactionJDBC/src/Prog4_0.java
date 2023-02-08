import java.sql.*;

class Prog4_0 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";

        String sql0 = "DROP TABLE Etudiant";
        String sql1 = "CREATE TABLE Etudiant (id NUMBER(4) PRIMARY KEY, nom VARCHAR2(20), age NUMBER(3))";
        String sql2 = "INSERT INTO Etudiant VALUES (1, 'Bastien', 15)";
        String sql3 = "SELECT * FROM Etudiant";
        String sql4 = "INSERT INTO Etudiant VALUES (3, 'Groue', 9)";


        Connection con1 = DriverManager.getConnection(url, args[0], args[1]);
        Connection con2 = DriverManager.getConnection(url, args[0], args[1]);

        try
        {
            //--- CONNEXION 1 ---
            System.out.println("--------con1--------");
            con1.setAutoCommit(false);
            con1.rollback();

            Statement st1 = con1.createStatement();
            //st1.executeQuery(sql0);

            // Création de la table ETUDIANT
            st1.executeQuery(sql1);

            // Insérer un tuple différent dans ETUDIANT
            st1.executeQuery(sql2);

            // lancer select * from ETUDIANT;
            ResultSet rs3 = st1.executeQuery(sql3);
            while (rs3.next())
                System.out.println(
                        rs3.getString(1) + "\t" +
                        rs3.getString(2) + "\t" +
                        rs3.getString(3) + "\t"
                );
            System.out.println("--------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            //--- CONNEXION 2 ---

            System.out.println("--------con2--------");
            con2.setAutoCommit(false);
            con2.rollback();

            Statement st2 = con2.createStatement();

            // Insérer un tuple différent dans ETUDIANT
            st2.executeQuery(sql4);

            //annulation
            con2.rollback();

            // lancer select * from ETUDIANT;
            ResultSet rs = st2.executeQuery(sql3);
            while (rs.next())
                System.out.println(
                        rs.getString(1) + "\t" +
                                rs.getString(2) + "\t" +
                                rs.getString(3) + "\t"
                );
            System.out.println("--------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            Statement st0 = con1.createStatement();
            //supprimer la table etduiant
            st0.executeQuery(sql0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        con1.close();
        con2.close();
    }
}