package persistencia;

import modelo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoProducto extends DaoConexion{

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if(producto == null){
                throw new Exception("Objeto no cargado");
            }
            String sql = String.format("INSERT INTO Producto (nombre, precio, codigo_fabricante) " +
                    "VALUES(\'%s\',%f,%d);", producto.getNombre(), producto.getPrecio(), producto.getCodigo_fabricante().getCodigo());

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            if(producto == null){
                throw new Exception("Objeto no cargado");
            }
            String sql = String.format("UPDATE Producto SET nombre=\'%s\', precio=%f, codigo_fabricante=%d " +
                    "WHERE codigo=%d;", producto.getNombre(), producto.getPrecio(), producto.getCodigo_fabricante().getCodigo(), producto.getCodigo());

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(int idProducto) throws Exception {
        try {
            String sql = String.format("DELETE FROM Producto WHERE codigo=%d;",idProducto);

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto obtenerProducto(int idProducto) throws Exception {
        Producto producto = null;
        Fabricante fabricante = null;
        try {
            String sql = String.format("SELECT * FROM Producto WHERE codigo=%d;",idProducto);
            queryDBase(sql);

            while(result.next()){
                producto = new Producto();
                fabricante = new Fabricante();

                producto.setCodigo(result.getInt(1));
                producto.setNombre(result.getString(2));
                producto.setPrecio(result.getDouble(3));
                fabricante.setCodigo(result.getInt(4));
                producto.setCodigo_fabricante(fabricante);
            }

            desconnectDBase();
            return producto;

        } catch (Exception e) {
            desconnectDBase();
            throw e;
        }
    }

    public List<Producto> obtenerProductos(int op) throws Exception {
        Producto producto = null;
        Fabricante fabricante = null;
        List<Producto> productos = new ArrayList<>();
        String sql = null;

        try {

            switch (op){
                case 1:  sql = "SELECT * FROM Producto;"; break;
                case 2:  sql = "SELECT * FROM Producto WHERE precio BETWEEN 120 AND 202;"; break;
                case 3:  sql = "SELECT * FROM Producto WHERE nombre like 'Portatil%';"; break;
                case 4:  sql = "SELECT * FROM Producto ORDER BY precio ASC LIMIT 1;"; break;
            }

            queryDBase(sql);

            while(result.next()){
                producto = new Producto();
                fabricante = new Fabricante();

                producto.setCodigo(result.getInt(1));
                producto.setNombre(result.getString(2));
                producto.setPrecio(result.getDouble(3));
                fabricante.setCodigo(result.getInt(4));
                producto.setCodigo_fabricante(fabricante);

                productos.add(producto);
            }

            desconnectDBase();

            Collections.sort(productos);

            return productos;

        } catch (Exception e) {
            desconnectDBase();
            throw e;
        }
    }
}
