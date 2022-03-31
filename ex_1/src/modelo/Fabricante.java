package modelo;

public class Fabricante implements Comparable<Fabricante> {
    private Integer codigo;
    private String nombre;

    public Fabricante(String nombre) {
        this.nombre = nombre;
    }

    public Fabricante() {
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

    @Override
    public int compareTo(Fabricante o) {
        return codigo.compareTo(o.codigo);
    }
}
