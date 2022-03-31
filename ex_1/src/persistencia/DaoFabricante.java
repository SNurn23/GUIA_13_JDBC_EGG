package persistencia;

import modelo.Fabricante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoFabricante extends DaoConexion{
    public void guardarFabricante(Fabricante Fabricante) throws Exception {
        try {
            if(Fabricante == null){
                throw new Exception("Objeto no cargado");
            }
            String sql = String.format("INSERT INTO Fabricante (nombre) " +
                    "VALUES(\'%s\');", Fabricante.getNombre());

           insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarFabricante(Fabricante Fabricante) throws Exception {
        try {
            if(Fabricante == null){
                throw new Exception("Objeto no cargado");
            }
            String sql = String.format("UPDATE Fabricante SET nombre=%s " +
                    "WHERE codigo=%d;", Fabricante.getNombre(), Fabricante.getCodigo());

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricante(int idFabricante) throws Exception {
        try {
            String sql = String.format("DELETE FROM Fabricante WHERE codigo=%d;",idFabricante);

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante obtenerFabricante(int idFabricante) throws Exception {
        Fabricante fabricante = null;
        try {
            String sql = String.format("SELECT * FROM Fabricante WHERE codigo=%d;",idFabricante);
            queryDBase(sql);

            while(result.next()){
                fabricante = new Fabricante();
                fabricante.setCodigo(result.getInt(1));
                fabricante.setNombre(result.getString(2));
            }

            desconnectDBase();
            return fabricante;

        } catch (Exception e) {
            desconnectDBase();
            throw e;
        }
    }

    public List<Fabricante> obtenerFabricantes() throws Exception {
        Fabricante fabricante = null;
        List<Fabricante> fabricantes = new ArrayList<Fabricante>();

        try {
            String sql = "SELECT * FROM Fabricante;";
            queryDBase(sql);

            while(result.next()){
                fabricante = new Fabricante();
                fabricante.setCodigo(result.getInt(1));
                fabricante.setNombre(result.getString(2));

                fabricantes.add(fabricante);
            }

            desconnectDBase();

            Collections.sort(fabricantes);

            return fabricantes;

        } catch (Exception e) {
            desconnectDBase();
            throw e;
        }
    }
}
