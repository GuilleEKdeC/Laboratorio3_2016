package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categoria implements Serializable {

    //--------Declaración de Variables--------//
    private Integer id;
    private String descripcion;     //Nombre de la Categoría
    private List<Trabajo> trabajos; //Lista con todos los TRABAJOS de ésta categoría

    //--------Declaración de Constante--------//
    public static final Categoria[] CATEGORIAS_MOCK= new Categoria[]{
            new Categoria(1,"Arquitecto"),
            new Categoria(2,"Desarrollador"),
            new Categoria(3,"Tester"),
            new Categoria(4,"Analista"),
            new Categoria(5,"Mobile Developer")
    };

    //---------------------------------------Constructor------------------------------------------//
    public Categoria(){
        this.trabajos=new ArrayList<Trabajo>();
    }

    //---------------------------------------Constructor------------------------------------------//
    public Categoria(Integer id, String desc){
        this();
        this.id = id;
        this.descripcion = desc;
    }

    //---------------------------------------Gets y Sets------------------------------------------//
    public Integer getId() {
        return id;
    }
    //----------------------------------------//
    public void setId(Integer id) {
        this.id = id;
    }
    //----------------------------------------//
    public String getDescripcion() {
        return descripcion;
    }
    //----------------------------------------//
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //----------------------------------------//
    public List<Trabajo> getTrabajo() {
        return trabajos;
    }
    //----------------------------------------//
    public void setTrabajo(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    //---------------------------------------Add Trabajo------------------------------------------//
    public void addTrabajo(Trabajo t){
        this.trabajos.add(t);
    }

}