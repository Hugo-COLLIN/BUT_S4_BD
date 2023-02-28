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
        sj.modify(query, new Object[]{bookingNumber, numTable, date + " " + time, nbPers});
        return "Ok, table booked!";
    }

    public String lastBookingNumber() throws SQLException
    {
        String query = "SELECT max(numres) FROM reservation";
        return sj.unique(query);
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
        sj.modify(query, new Object[]{numRes, numPlat, qte});
        return "Ok, meal ordered!";
    }

    public String listBookings() throws SQLException
    {
        String query = "SELECT * FROM reservation";
        return sj.select(query, new String[]{});
    }

    //Q3.a
    public String listWaiters() throws SQLException
    {
        String query = "SELECT * FROM Serveur\n" +
                "WHERE grade = 'serveur'";
        return sj.select(query, new String[]{});
    }

    public String selectWaiter(int numServ) throws SQLException
    {
        String query = "SELECT nomserv, email FROM Serveur\n" +
                "WHERE numServ = ?";
        ResultSet rs = sj.resultSelect(query, new Object[]{numServ});
        if (rs.next())
            return sj.display(rs);
        return "No waiter found";
    }

    public String updateWaiter(int waiterNum, int choice, String value) throws SQLException
    {
        return switch (choice) {
            case 1 -> updateWaiterName(waiterNum, value);
            case 2 -> updateWaiterEmail(waiterNum, value);
            case 3 -> updateWaiterPassword(waiterNum, value);
            default -> "Invalid choice";
        };
    }

    public String updateWaiterEmail(int numServ, String email) throws SQLException
    {
        String query = "UPDATE Serveur\n" +
                "SET email = ?\n" +
                "WHERE numServ = ?\n" +
                "AND grade = 'serveur'";
        sj.modify(query, new Object[]{email, numServ});
        return "Ok, server email updated!";
    }

    public String updateWaiterName(int numServ, String name) throws SQLException
    {
        String query = "UPDATE Serveur\n" +
                "SET nomServ = ?\n" +
                "WHERE numServ = ?\n" +
                "AND grade = 'serveur'";
        sj.modify(query, new Object[]{name, numServ});
        return "Ok, server name updated!";
    }

    public String updateWaiterPassword(int numServ, String password) throws SQLException
    {
        String query = "UPDATE Serveur\n" +
                "SET passwd = ?\n" +
                "WHERE numServ = ?\n" +
                "AND grade = 'serveur'";
        sj.modify(query, new Object[]{password, numServ});
        return "Ok, server password updated!";
    }

    public String newWaiter(String name, String email, String password) throws SQLException
    {
        int numServ = Integer.parseInt(lastWaiterNumber());
        numServ++;
        System.out.println(numServ);
        String query = "INSERT INTO Serveur (numServ, nomServ, email, passwd, grade) VALUES (?, ?, ?, ?, 'serveur')";
        sj.modify(query, new Object[]{numServ, name, email, password});
        return "Ok, server added!";
    }

    private String lastWaiterNumber() throws SQLException {
        String query = "SELECT max(numServ) FROM Serveur";
        return sj.unique(query);
    }



    //Q3.b
    public String listTables() throws SQLException
    {
        String query = "SELECT * FROM Tabl";
        return sj.select(query, new String[]{});
    }

    public String assign(int numServ, int numTab, String dataff) throws SQLException
    {
        String query = "INSERT INTO Affectation (numServ, numTab, dataff) VALUES (?, ?, ?)";
        sj.modify(query, new Object[]{numServ, numTab, dataff});
        return "Ok, server assigned to a table!";
    }


    public String listAssignments() throws SQLException
    {
        String query = "SELECT * FROM Affectation";
        return sj.select(query, new String[]{});
    }


    //Q3.c
    public String selectMeal(int numPlat) throws SQLException
    {
        String query = "SELECT * FROM Plat\n" +
                "WHERE numPlat = ?";
        ResultSet rs = sj.resultSelect(query, new Object[]{numPlat});
        if (rs.next())
            return sj.display(rs);
        return "No meal found";
    }

    public String updateMeal(int choice, int i2, String p1) throws SQLException {
        return switch (choice) {
            case 1 -> updateMealLabel(i2, p1);
            case 2 -> updateMealType(i2, p1);
            case 3 -> updateMealPrice(i2, p1);
            case 4 -> updateMealServedQuantity(i2, p1);
            default -> "Invalid choice";
        };
    }

    public String updateMealLabel(int numPlat, String libelle) throws SQLException
    {
        String query = "UPDATE Plat\n" +
                "SET libelle = ?\n" +
                "WHERE numPlat = ?";
        sj.modify(query, new Object[]{libelle, numPlat});
        return "Ok, server email updated!";
    }

    public String updateMealType(int numPlat, String type) throws SQLException
    {
        String query = "UPDATE Serveur\n" +
                "SET type = ?\n" +
                "WHERE numPlat = ?";
        sj.modify(query, new Object[]{type, numPlat});
        return "Ok, server name updated!";
    }

    public String updateMealPrice(int numPlat, String price) throws SQLException
    {
        String query = "UPDATE Serveur\n" +
                "SET passwd = ?\n" +
                "WHERE numPlat = ?";
        sj.modify(query, new Object[]{price, numPlat});
        return "Ok, server password updated!";
    }

    public String updateMealServedQuantity(int numPlat, String qteservie) throws SQLException
    {
        String query = "UPDATE Serveur\n" +
                "SET passwd = ?\n" +
                "WHERE numPlat = ?";
        sj.modify(query, new Object[]{qteservie, numPlat});
        return "Ok, server password updated!";
    }

    public String newMeal(String libelle, String type, int prixunit, int qteservie) throws SQLException
    {
        int numPlat = Integer.parseInt(lastMealNumber());
        numPlat ++;
        System.out.println(numPlat);
        String query = "INSERT INTO Plat VALUES (?, ?, ?)";
        sj.modify(query, new Object[]{numPlat, libelle,type, prixunit, qteservie});
        return "Ok, server added!";
    }

    private String lastMealNumber() throws SQLException {
        String query = "SELECT max(numPlat) FROM Plat";
        return sj.unique(query);
    }

    //Q3.d
    public String bookingAmount(int numRes) throws SQLException
    {
        String query = """
                SELECT sum(plat.prixUnit * commande.quantite) FROM reservation
                INNER JOIN Commande ON Reservation.numRes = commande.numres
                INNER JOIN Plat ON commande.numplat = plat.numPlat
                WHERE Reservation.numRes = ?
                """;
        return sj.unique(query, new Object[]{numRes});
    }

    public String updateBookingAmount(int numRes) throws SQLException
    {
        String query = """
                UPDATE reservation
                SET montcom = ?
                WHERE Reservation.numRes = ?
                """;
        sj.modify(query, new Object[]{bookingAmount(numRes), numRes});
        return "Ok, booking amount updated!";
    }

    //Q3.e
    public String listOrdersByWaiter(String startDate, String endDate) throws SQLException
    {
        String query = """
                SELECT serveur.nomserv, sum(plat.prixunit * commande.quantite) AS Chiffre_DAffaire, count(*) AS Nb_Commandes FROM Serveur
                INNER JOIN Affecter ON serveur.numserv = affecter.numServ
                INNER JOIN Reservation ON affecter.numtab = reservation.numtab
                INNER JOIN Commande ON commande.numres = reservation.numres
                INNER JOIN Plat ON plat.numPlat = commande.numplat
                WHERE reservation.datres >= TO_DATE(?, 'DD/MM/YYYY')
                    AND reservation.datres <= TO_DATE(?, 'DD/MM/YYYY')
                GROUP BY Serveur.nomServ, Serveur.numServ
                """;
        return sj.select(query, new Object[]{startDate, endDate});
    }

    //Q3.f
public String noTurnoverWaiters(String startDate, String endDate) throws SQLException
    {
        String query = """
                 SELECT serveur.nomServ FROM Serveur
                 MINUS
                 SELECT serveur.nomServ FROM Serveur
                 INNER JOIN Affecter ON serveur.numserv = affecter.numServ
                 INNER JOIN Reservation ON affecter.numtab = reservation.numtab
                 INNER JOIN Commande ON commande.numres = reservation.numres
                 INNER JOIN Plat ON plat.numPlat = commande.numplat
                 WHERE reservation.datres >= TO_DATE(?, 'DD/MM/YYYY')
                     AND reservation.datres <= TO_DATE(?, 'DD/MM/YYYY')
                 GROUP BY Serveur.nomServ, Serveur.numServ
                """;
        return sj.select(query, new Object[]{startDate, endDate});
    }
}