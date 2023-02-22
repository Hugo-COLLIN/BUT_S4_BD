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

    //Q1.a
    public String listTables(String date, String time) throws SQLException
    {
        this.co.commit();
        this.pst = this.co.prepareStatement("SELECT t1.numtab FROM tabl t1\n" +
                        "MINUS\n" +
                        "SELECT t2.numtab FROM tabl t2\n" +
                        "INNER JOIN Reservation\n" +
                        "ON Reservation.numtab = t2.numTab\n" +
                        "WHERE to_char(datres, 'dd/mm/yyyy hh24:mi') = ?\n",
                TYPE, MODE);

        this.pstSet(pst, new String[]{date + " " + time});
        return this.displayPst();
    }

    //Q1.b
    public String bookTable(String numTable, String date, String time, String nbPers) throws SQLException
    {
        String bookingNumber = lastBookingNumber();
        bookingNumber = String.valueOf(Integer.parseInt(bookingNumber) + 1);
        this.pst = this.co.prepareStatement("INSERT INTO reservation (numres, numtab, datres, nbpers) VALUES (?,?,to_date(?, 'dd/mm/yyyy hh24:mi'),?)", TYPE, MODE);
        this.pstSet(pst, new String[]{bookingNumber, numTable, date + " " + time, nbPers});
        pst.executeUpdate();
        return this.displayPst();
    }

    public String lastBookingNumber() throws SQLException
    {
        this.st = co.createStatement(TYPE, MODE);
        ResultSet rs = st.executeQuery("SELECT max(numres) FROM reservation");
        rs.next();
        System.out.println(rs.getString(1));
        return rs.getString(1);
    }

    public String plateCalendar(String plate) throws SQLException
    {
        this.pst = this.co.prepareStatement("SELECT * FROM Calendrier WHERE no_imm = ?", TYPE, MODE);
        this.pstSet(pst, new String[]{plate});
        return "Booking calendar for the vehicle with this plate :\n" + this.displayPst();
    }

    public String majCal(String plate, String stDate, String endDate, int loc) throws SQLException
    {
        String locParam;
        this.pst = this.co.prepareStatement("UPDATE Calendrier \n" +
                "SET paslibre = ?\n" +
                "WHERE no_imm = ?\n" +
                "    AND datejour BETWEEN ? AND ?", TYPE, MODE);

        if (loc == 1) locParam = "x";
        else locParam = null;

        this.pstSet(pst, new String[]{locParam, plate, stDate, endDate});
        pst.executeUpdate();
        return plateCalendar(plate);
    }



    public String locAmount(String model, String locDuration) throws SQLException
    {
        this.pst = this.co.prepareStatement("SELECT tarif.code_tarif, tarif_jour * MOD(?,7) + tarif_hebdo * FLOOR(?/7) AS Montant_location \n" +
                        "FROM tarif\n" +
                        "WHERE tarif.code_tarif = (\n" +
                        "    SELECT categorie.code_tarif FROM Vehicule, Categorie\n" +
                        "    WHERE vehicule.modele = ?\n" +
                        "        AND Vehicule.code_categ = categorie.code_categ\n)",
                TYPE, MODE);

        this.pstSet(pst, new String[]{locDuration, locDuration, model});
        return this.displayPst();
    }



    public String cliList2Models() throws SQLException
    {
        this.st = co.createStatement(TYPE, MODE);
        ResultSet rs = st.executeQuery("SELECT nom, ville, codpostal\n" +
                "FROM Client, Dossier, Vehicule\n" +
                "WHERE Client.code_cli = Dossier.code_cli\n" +
                "    AND Dossier.no_imm = Vehicule.no_imm\n" +
                "GROUP BY nom, ville, codpostal, modele\n" +
                "HAVING count(modele) = 2");

        return display(rs);
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
