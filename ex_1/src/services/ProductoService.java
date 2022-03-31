package services;

import modelo.Fabricante;
import modelo.Producto;
import persistencia.DaoProducto;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ProductoService {
    private DaoProducto daoProducto;
    private FabricanteService fabriService;
    Scanner input = new Scanner(System.in, StandardCharsets.ISO_8859_1).useDelimiter("\n").useLocale(Locale.US);


    public ProductoService() {
        this.daoProducto = new DaoProducto();
        this.fabriService = new FabricanteService();
    }

    public Producto crearProducto() throws Exception {
        Fabricante fabricante = null;

        System.out.println("Ingrese el nombre del producto:");
        String nombre = input.next();
        System.out.println("Ingrese el precio:");
        double precio = input.nextDouble();

        fabricante = obtenerFabricante();

        return new Producto(nombre,precio,fabricante);
    }

    public Fabricante obtenerFabricante() throws Exception {
        Fabricante fabricante;

        do{
            System.out.println("Ingrese el nombre del fabricante: ");
            String nombre = input.next();
            fabricante = fabriService.buscarFabricante(nombre);

            if(fabricante == null) {
                System.out.println("El nombre del fabricante ingresado no esta registrado. Ingrese otro fabricante");
            }

        }while(fabricante == null);

        return fabricante;
    }

    public void guardarProducto() throws Exception {
        Producto producto = crearProducto();
        daoProducto.guardarProducto(producto);
        System.out.println("Producto agregado!");
    }

    public Producto buscarProducto(String nombre) throws Exception {
        List<Producto> productos = daoProducto.obtenerProductos(1);

        if(!productos.isEmpty()){
            for (Producto p : productos) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    return p;
                }
            }
        }else{
            System.out.println("No hay registros cargados");
        }
        return null;
    }

    public void modificarProducto() throws Exception {
        Fabricante fabricante = null;
        int op;

        System.out.println("Ingrese el nombre del producto a modificar:");
        Producto producto = buscarProducto(input.next());

        if (producto != null) {
            do{
                System.out.println("Elija el campo que desea modificar:");
                System.out.println("1- Nombre");
                System.out.println("2- Precio");
                System.out.println("3- Fabricante");
                System.out.println("Opcion: ");
                op = input.nextInt();

                switch (op) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre del producto:");
                        producto.setNombre(input.next());
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo precio:");
                        producto.setPrecio(input.nextDouble());
                        break;
                    case 3:
                        fabricante = obtenerFabricante();
                        producto.setCodigo_fabricante(fabricante);
                        break;
                    default:
                        System.out.println("Elija una opcion correcta");
                        break;
                }

            }while(op <= 3);

            daoProducto.modificarProducto(producto);
            System.out.println("Producto modificado!");
        }else{
            System.out.println("No se encontro el producto");
        }

    }

    public void eliminarProducto() throws Exception {
        System.out.println("Ingrese el producto que desea eliminar:");
        Producto producto = buscarProducto(input.next());

        if (producto != null){
            daoProducto.eliminarProducto(producto.getCodigo());
            System.out.println("Producto eliminado!");
        }else{
            System.out.println("No se encontro el producto");
        }
    }

    public void obtenerNombreDeProductos() throws Exception {
        List<Producto> products = daoProducto.obtenerProductos(1);

        System.out.println("PRODUCTOS");
        System.out.println("---------------------------");
        System.out.printf("%-10s%-35s\n","ID","NOMBRE");

        if(!products.isEmpty()) {
            for (Producto producto : products) {
                System.out.printf("%-10s%-35s\n",producto.getCodigo(), producto.getNombre());
            }
        }else{
            System.out.println("No hay registros cargados");
        }
    }

    public void obtenerProductos() throws Exception {
        List<Producto> products = daoProducto.obtenerProductos(1);

        System.out.println("PRODUCTOS");
        System.out.println("---------------------------");
        System.out.printf("%-10s%-35s%-15s%-20s\n", "ID", "NOMBRE", "PRECIO", "CODIGO DEL FABRICANTE");

        if(!products.isEmpty()) {
            for (Producto producto : products) {
                System.out.printf("%-10s%-35s%-15s%-20s\n",producto.getCodigo(), producto.getNombre(),producto.getPrecio(),producto.getCodigo_fabricante().getCodigo());
            }
        }else{
            System.out.println("No hay registros cargados");
        }
    }

    public void obtenerNombresyPreciosDeProductos() throws Exception {
        List<Producto> products = daoProducto.obtenerProductos(1);

        System.out.println("PRODUCTOS");
        System.out.println("---------------------------");
        System.out.printf("%-10s%-35s%-15s\n", "ID", "NOMBRE", "PRECIO");

        if(!products.isEmpty()) {
            for (Producto producto : products) {
                System.out.printf("%-10s%-35s%-15s\n",producto.getCodigo(), producto.getNombre(),producto.getPrecio());
            }
        }else{
            System.out.println("No hay registros cargados");
        }
    }

    public void obtenerProductosxPrecio() throws Exception {
        List<Producto> products = daoProducto.obtenerProductos(2);

        System.out.println("PRODUCTOS");
        System.out.println("---------------------------");
        System.out.printf("%-10s%-35s%-15s%-20s\n", "ID", "NOMBRE", "PRECIO", "CODIGO DEL FABRICANTE");

        if(!products.isEmpty()) {
            for (Producto producto : products) {
                System.out.printf("%-10s%-35s%-15s%-20s\n",producto.getCodigo(), producto.getNombre(),producto.getPrecio(),producto.getCodigo_fabricante().getCodigo());
            }
        }else{
            System.out.println("No hay registros cargados");
        }
    }

    public void obtenerPortatiles() throws Exception {
        List<Producto> products = daoProducto.obtenerProductos(3);

        System.out.println("PORTATILES");
        System.out.println("---------------------------");
        System.out.printf("%-10s%-35s%-15s%-20s\n", "ID", "NOMBRE", "PRECIO", "CODIGO DEL FABRICANTE");

        if(!products.isEmpty()) {
            for (Producto producto : products) {
                System.out.printf("%-10s%-35s%-15s%-20s\n",producto.getCodigo(), producto.getNombre(),producto.getPrecio(),producto.getCodigo_fabricante().getCodigo());
            }
        }else{
            System.out.println("No hay registros cargados");
        }
    }

    public void obtenerProductosMasBarato() throws Exception {
        List<Producto> products = daoProducto.obtenerProductos(4);

        System.out.println("PRODUCTO MAS BARATO");
        System.out.println("---------------------------");
        System.out.printf("%-10s%-35s%-15s\n", "ID", "NOMBRE", "PRECIO");

        if(!products.isEmpty()) {
            for (Producto producto : products) {
                System.out.printf("%-10s%-35s%-15s\n",producto.getCodigo(), producto.getNombre(),producto.getPrecio());
            }
        }else{
            System.out.println("No hay registros cargados");
        }
    }

}
