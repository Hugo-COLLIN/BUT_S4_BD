import java.sql.*;

class Prog9 {
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

            Statement st1 = con1.createStatement();
            //st1.executeQuery(sql0);

            // Création de la table ETUDIANT
            st1.executeQuery(sql1);

            System.out.println("--------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        con1.close();

        //--- CONNEXION 2 ---
        try {
            System.out.println("--------con2--------");
            con2.setAutoCommit(false);

            Statement st2 = con2.createStatement();

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
        con2.close();


        //--- Annuler les modifiactions sur la base ---
        Connection con0 = DriverManager.getConnection(url, args[0], args[1]);
        try
        {
            //Retirer les verrous
            //con1.rollback();
            //con2.rollback();

            con0.setAutoCommit(false);
            Statement st0 = con0.createStatement();
            //supprimer la table etudiant
            st0.execute(sql0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        con0.close();
    }
}