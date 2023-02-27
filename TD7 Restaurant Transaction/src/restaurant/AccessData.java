package restaurant;
import java.sql.*;

public class AccessData
{
    private SimplerJDBC sj;

    public AccessData() {
        this.sj = new SimplerJDBC();
    }

    public String connection(String lgn, String pwd) throws SQLException, ClassNotFoundException
    {
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        return sj.loadDriver() +
                sj.connect(url, lgn, pwd);
    }

    /*
    --- FEATURES --
     */
    //Identification
    public int loginUser(String login, String password) {
        try {
            String query = "SELECT * FROM Serveur WHERE email = ? AND passwd = ?";
            ResultSet rs = sj.resultSelect(query, new String[]{login, password});

            if (rs.next())
            {
                if (rs.getString(5).equals("gestionnaire"))
                    return 2;
                return 1;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Q2.a
    public String listTables(String date, String time) throws SQLException
    {
        String query = "SELECT t1.numtab FROM tabl t1\n" +
                "MINUS\n" +
                "SELECT t2.numtab FROM tabl t2\n" +
                "INNER JOIN Reservation\n" +
                "ON Reservation.numtab = t2.numTab\n" +
                "WHERE to_char(datres, 'dd/mm/yyyy hh24:mi') = ?\n";
        return sj.select(query, new String[]{date + " " + time});
    }

    //Q2.b
    public String bookTable(String numTable, String date, String time, String nbPers) throws SQLException
    {
        int bookingNumber = Integer.parseInt(lastBookingNumber());
        bookingNumber++;
        System.out.println(bookingNumber);
        String query = "INSERT INTO reservation (numres, numtab, datres, nbpers) VALUES (?, ?, to_date(?, 'dd/mm/yyyy hh24:mi'), ?)";
        sj.update(query, new Object[]{bookingNumber, numTable, date + " " + time, nbPers});
        return "Ok, table booked!";
    }

    public String lastBookingNumber() throws SQLException
    {
        String query = "SELECT max(numres) FROM reservation";
        ResultSet rs = sj.resultSelect(query, new String[]{});
        rs.next();
        return rs.getString(1);
    }

    //Q2.c TODO
    public String listMeals() throws SQLException
    {
        String query = "select plat.numPlat, plat.libelle, plat.qteservie - sum(commande.quantite) AS Quantité_restante from plat\n" +
                "INNER JOIN commande\n" +
                "ON Commande.numPlat = plat.numPlat\n" +
                "GROUP BY plat.Numplat, plat.qteservie, plat.libelle";
        return sj.select(query, new String[]{});
    }

    //Q2.d
    public String orderMeal(int numRes, int numPlat, int qte) throws SQLException
    {
        String query = "INSERT INTO Commande (numRes, numPlat, quantite) VALUES (?, ?, ?)";
        sj.update(query, new Object[]{numRes, numPlat, qte});
        return "Ok, meal ordered!";
    }

    public String listBookings() throws SQLException
    {
        String query = "SELECT * FROM reservation";
        return sj.select(query, new String[]{});
    }

    //Q3.a
    public String listServeurs() throws SQLException
    {
        String query = "SELECT * FROM Serveur\n" +
                "WHERE grade = 'serveur'";
        return sj.select(query, new String[]{});
    }


    //Q3.b
}