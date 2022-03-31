package principal;

import services.FabricanteService;
import services.ProductoService;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        FabricanteService fabricanteService = new FabricanteService();
        Scanner input = new Scanner(System.in, StandardCharsets.ISO_8859_1).useDelimiter("\n").useLocale(Locale.US);

        try {
            int op;
            do{
              System.out.println("Elija una opcion:");
              System.out.println("1- Ingresar un producto");
              System.out.println("2- Ingresar un fabricante");
              System.out.println("3- Modificar un producto");
              System.out.println("4- Listar el nombre de todos los productos");
              System.out.println("5- Listar los nombres y los precios de todos los productos");
              System.out.println("6- Listar los productos que su precio esté entre 120 y 202.");
              System.out.println("7- Listar todos los Portátiles");
              System.out.println("8- Listar el nombre y el precio del producto más barato");
              System.out.println("9- Salir");
              System.out.println("Opcion: "); op = input.nextInt();

              switch (op){
                  case 1:
                      productoService.guardarProducto();
                      break;
                  case 2:
                      fabricanteService.guardarFabricante();
                      break;
                  case 3:
                      productoService.modificarProducto();
                      break;
                  case 4:
                      productoService.obtenerNombreDeProductos();
                      break;
                  case 5:
                      productoService.obtenerNombresyPreciosDeProductos();
                      break;
                  case 6:
                      productoService.obtenerProductosxPrecio();
                      break;
                  case 7:
                      productoService.obtenerPortatiles();
                      break;
                  case 8:
                      productoService.obtenerProductosMasBarato();
                      break;
                  case 9:
                      System.out.println("Saliendo, BYE!");
                      break;
                  default:
                      System.out.println("Elija una opcion correcta");
                      break;
              }
          }while (op != 9);

        }catch (Exception e) {
          e.printStackTrace();
        }
    }
}
