package persistencia;


import java.sql.*;

public abstract class DaoConexion {
    protected Connection conexion = null;
    protected ResultSet result = null;
    protected Statement sentencia = null;

    private final String USER = "sofia";
    private final String PASSWORD = "2305";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    protected  void connectDBase() throws SQLException, ClassNotFoundException {

        try{
            Class.forName(DRIVER);
            String url = "jdbc:mysql://localhost:3306/"+DATABASE+"?useSSL=false";
            conexion = DriverManager.getConnection(url,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    protected void desconnectDBase() throws SQLException {
        try {
            if(result != null){
                result.close();
            }
            if(sentencia != null){
                sentencia.close();
            }
            if(conexion != null){
                conexion.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    protected void insertModifyDelete(String sql) throws SQLException, ClassNotFoundException {
        try {
            connectDBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            conexion.rollback();
            throw e;
        }finally {
            desconnectDBase();
        }
    }

    protected void queryDBase(String sql) throws SQLException, ClassNotFoundException {
        try {
            connectDBase();
            sentencia = conexion.createStatement();
            result =  sentencia.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

}
