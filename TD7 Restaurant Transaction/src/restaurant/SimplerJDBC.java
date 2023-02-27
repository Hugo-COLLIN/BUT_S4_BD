package restaurant;
import java.sql.*;

/**
 * SimplerJDBC
 * Hugo COLLIN, 27/02/2023
 */
public class SimplerJDBC
{
    private Connection co;
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

    public String connect(String url, String lgn, String pwd) throws SQLException {
        this.co = DriverManager.getConnection(url, lgn, pwd);
        this.co.setAutoCommit(false);
        return "Successfully connected to the database :)";
    }

    /*
    --- QUERIES METHODS ---
     */
    public String select(String query, Object[] params) throws SQLException
    {
        return this.display(resultSelect(query, params));
    }

    public ResultSet resultSelect(String query, Object[] params) throws SQLException
    {
        this.query(query, params);
        ResultSet rs = this.pst.executeQuery();
        this.co.commit();
        return rs;
    }

    public String update(String query, Object[] params) throws SQLException
    {
        this.query(query, params);
        this.pst.executeUpdate();
        this.co.commit();
        return "Update done";
    }

    public void query(String query, Object[] params) throws SQLException
    {
        this.pst = co.prepareStatement(query, TYPE, MODE);
        for (int i = 0 ; i < params.length ; i ++)
            this.pst.setObject(i + 1, params[i]);
    }

    public String unique(String query) throws SQLException
    {
        ResultSet rs = this.resultSelect(query, new Object[]{});
        rs.last();
        return rs.getString(1);
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
                ", pst=" + pst +
                ", TYPE=" + TYPE +
                ", MODE=" + MODE +
                '}';
    }
}
