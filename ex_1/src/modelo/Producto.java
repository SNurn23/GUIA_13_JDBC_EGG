package modelo;

public class Producto implements Comparable<Producto> {
    private Integer codigo;
    private String nombre;
    private double precio;
    private Fabricante codigo_fabricante;

    public Producto(String nombre, double precio, Fabricante codigo_fabricante) {
        this.nombre = nombre;
        this.precio = precio;
        this.codigo_fabricante = codigo_fabricante;
    }

    public Producto() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Fabricante getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(Fabricante codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }

    @Override
    public int compareTo(Producto o) {
        return codigo.compareTo(o.codigo);
    }
}
