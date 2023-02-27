package restaurant;
import java.sql.*;

public class AccessData
{
    private Connection co;
    private Statement st;
    private PreparedStatement pst;
    private final int TYPE = ResultSet.TYPE_SCROLL_INSENSITIVE;
    private final int MODE = ResultSet.CONCUR_UPDATABLE;

    /*
    --- SYSTEM METHODS ---
     */
    public String loadDriver() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return "Driver loaded";
    }

    public String connection(String lgn, String pwd) throws SQLException {
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        this.co = DriverManager.getConnection(url, lgn, pwd);
        this.co.setAutoCommit(false);
        return "Successfully connected to the database :)";
    }

    /*
    --- FEATURES --
     */
    //Identification
    public int loginUser(String login, String password) {
        try {
            this.pst = this.co.prepareStatement("SELECT * FROM Serveur WHERE email = ? AND passwd = ?", TYPE, MODE);
            this.pstSet(pst, new String[]{login, password});
            ResultSet rs = this.pst.executeQuery();
            this.co.commit();

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
    {/*
        this.co.commit();
        this.pst = this.co.prepareStatement("SELECT t1.numtab FROM tabl t1\n" +
                        "MINUS\n" +
                        "SELECT t2.numtab FROM tabl t2\n" +
                        "INNER JOIN Reservation\n" +
                        "ON Reservation.numtab = t2.numTab\n" +
                        "WHERE to_char(datres, 'dd/mm/yyyy hh24:mi') = ?\n",
                TYPE, MODE);

        this.pstSet(pst, new String[]{date + " " + time});
        return this.displayPst();*/
        String query = "SELECT t1.numtab FROM tabl t1\n" +
                "MINUS\n" +
                "SELECT t2.numtab FROM tabl t2\n" +
                "INNER JOIN Reservation\n" +
                "ON Reservation.numtab = t2.numTab\n" +
                "WHERE to_char(datres, 'dd/mm/yyyy hh24:mi') = ?\n";
        return selectQuery(query, new String[]{date + " " + time});
    }

    //Q2.b
    public String bookTable(String numTable, String date, String time, String nbPers) throws SQLException
    {
        int bookingNumber = Integer.parseInt(lastBookingNumber());
        bookingNumber++;
        System.out.println(bookingNumber);
        this.pst = this.co.prepareStatement("INSERT INTO reservation (numres, numtab, datres, nbpers) VALUES (?,?,to_date(?, 'dd/mm/yyyy hh24:mi'),?)", TYPE, MODE);
        this.pstSet(pst, new Object[]{bookingNumber, numTable, date + " " + time, nbPers});
        pst.executeUpdate();
        this.co.commit();
        return "Ok, table booked!";
    }

    public String lastBookingNumber() throws SQLException
    {
        this.st = co.createStatement(TYPE, MODE);
        ResultSet rs = st.executeQuery("SELECT max(numres) FROM reservation");
        rs.next();
        return rs.getString(1);
    }

    //Q2.c TODO
    public String listMeals() throws SQLException
    {
        this.co.commit();
        this.pst = this.co.prepareStatement("select plat.numPlat, plat.libelle, plat.qteservie - sum(commande.quantite) AS Quantité_restante from plat\n" +
                        "INNER JOIN commande\n" +
                        "ON Commande.numPlat = plat.numPlat\n" +
                        "GROUP BY commande.Numplat, plat.qteservie, plat.libelle",
                TYPE, MODE);

        //this.pstSet(pst, new String[]{date + " " + time});
        return this.displayPst();
    }

    //Q2.d
    public String orderMeal(int numRes, int numPlat, int qte) throws SQLException
    {
        this.pst = this.co.prepareStatement("INSERT INTO Commande VALUES (?, ?, ?)", TYPE, MODE);
        this.pstSet(pst, new Object[]{numRes, numPlat, qte});
        pst.executeUpdate();
        this.co.commit();
        return "Ok, dish ordered!";
    }

    public String listBookings() throws SQLException
    {
        this.co.commit();
        this.pst = this.co.prepareStatement("select * from reservation",
                TYPE, MODE);

        //this.pstSet(pst, new String[]{date + " " + time});
        return this.displayPst();
    }

    //Q3.a


    /*
    --- QUERIES METHODS ---
     */
    public String selectQuery(String query, Object[] params) throws SQLException
    {
        this.co.commit();
        this.pst = co.prepareStatement(query, TYPE, MODE);
        for (int i = 0 ; i < params.length ; i ++)
            this.pst.setObject(i + 1, params[i]);
        return this.displayPst();
    }

    /*
    --- DISPLAYING METHODS ---
     */
    public String display(ResultSet rs) throws SQLException
    {
        ResultSetMetaData rSMeta = rs.getMetaData();
        final int NUM = rSMeta.getColumnCount();

        StringBuilder sb = new StringBuilder();
        sb.append(showColsName(rSMeta, NUM));
        while (rs.next())
            sb.append(showRow(rs, NUM));
        return sb.toString();
    }

    public String displayPst() throws SQLException
    {
        ResultSet rs = pst.executeQuery();
        this.co.commit();
        return display(rs);
    }

    public void pstSet(PreparedStatement pst, Object[] params) throws SQLException
    {
        for (int i = 0 ; i < params.length ; i ++)
            pst.setObject(i + 1, params[i]);
    }

    public String showRow(ResultSet rS, final int NUM) throws SQLException
    {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= NUM ; i ++)
            res.append(rS.getString(i) + "\t");
        res.append("\n");
        return res.toString();
    }

    public String showColsName(ResultSetMetaData rSMeta, final int NUM) throws SQLException
    {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= NUM ; i ++)
            res.append(rSMeta.getColumnName(i) + "\t");
        res.append("\n");
        return res.toString();
    }

    /*
    --- GETTERS, SETTERS ANS REDEFINITIONS ---
     */
    @Override
    public String toString() {
        return "AccessData{" +
                "co=" + co +
                ", st=" + st +
                ", pst=" + pst +
                ", TYPE=" + TYPE +
                ", MODE=" + MODE +
                '}';
    }
}
