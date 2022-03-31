package services;

import modelo.Fabricante;
import modelo.Producto;
import persistencia.DaoFabricante;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FabricanteService {

    private DaoFabricante daoFabricante;
    Scanner input = new Scanner(System.in, StandardCharsets.ISO_8859_1).useDelimiter("\n").useLocale(Locale.US);

    public FabricanteService() {
        this.daoFabricante = new DaoFabricante();
    }

    public Fabricante crearFabricante() throws Exception {

        System.out.println("Ingrese el nombre del fabricante:");
        String nombre = input.next();

        return new Fabricante(nombre);
    }


    public void guardarFabricante() throws Exception {
        Fabricante fabricante =  crearFabricante();
        daoFabricante.guardarFabricante(fabricante);
        System.out.println("Fabricante agregado!");
    }

    public void modificarFabricante() throws Exception {

        System.out.println("Ingrese el nombre del fabricante a modificar:");
        Fabricante fabricante  = buscarFabricante(input.next());

        if (fabricante != null) {
            System.out.println("Ingrese el nuevo nombre del fabricante:");
            fabricante.setNombre(input.next());

            daoFabricante.modificarFabricante(fabricante);
            System.out.println("Fabricante modificado!");
        }else{
            System.out.println("No se encontro el fabricante");
        }

    }

    public void eliminarFabricante() throws Exception {
        System.out.println("Ingrese el nombre del fabricante que desea eliminar:");
        Fabricante fabricante  = buscarFabricante(input.next());

        if (fabricante != null){
            daoFabricante.eliminarFabricante(fabricante.getCodigo());
            System.out.println("Fabricante eliminado!");
        }else{
            System.out.println("No se encontro el fabricante");
        }
    }

    public Fabricante buscarFabricante(String nombre) throws Exception {
        List<Fabricante> fabricantes = daoFabricante.obtenerFabricantes();

        if (!fabricantes.isEmpty()) {
            for (Fabricante f : fabricantes){
                if(f.getNombre().equalsIgnoreCase(nombre)){
                    return f;
                }
            }
        }else{
            System.out.println("No hay registros cargados");
        }
        return null;
    }

    public void obtenerFabricantes() throws Exception {
        List<Fabricante> fabricantes = daoFabricante.obtenerFabricantes();

        System.out.println("FABRICANTES");
        System.out.println("---------------------------");
        System.out.printf("%-10s%-35s\n", "ID", "NOMBRE");

        if(!fabricantes.isEmpty()) {
            for (Fabricante f : fabricantes) {
                System.out.printf("%-10s%-35s\n",f.getCodigo(), f.getNombre());
            }
        }else{
            System.out.println("No hay registros cargados");
        }

    }
}
