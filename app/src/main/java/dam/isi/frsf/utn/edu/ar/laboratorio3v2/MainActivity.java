package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.text.Layout;
import android.view.Menu;//Lo agregué yo porque no me veía los Menu
import android.view.MenuItem;//Lo agregué yo porque no me veía los MenuItem
//import android.view.ContextMenu;
//import android.widget.AdapterView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.lang.String;
import java.util.Arrays;
import java.util.Random;
import dam.isi.frsf.utn.edu.ar.laboratorio3v2.AdaptadorOfLaboral;

public class MainActivity extends AppCompatActivity {

    /**********************************Declaración de Variables************************************/
    //Se crea una objeto tipo ListView
    private ListView listVw;

    //Se crea un ArrayList de tipo Trabajo con Todos los trabajos
    private ArrayList<Trabajo> listaTrabajos;

    //Se crea un objeto de tipo AdaptadorDias
    private AdaptadorOfLaboral adaptador;



    /****************************************** Métodos *******************************************/

    /*-----------------------------------------On Create------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // llamamos a un Inicializar listas trabajos que cargue cada trabajo dentro de cada categoria
        inicializarListasTrabajo();

        //llamamos a un método que hace una lista sola con todas las listas trabajo de las distintas categorías
        listaTrabajos = new ArrayList<Trabajo>();
        listaTotalTrabajos();

        //Trabajo[] jobs = Trabajo.TRABAJOS_MOCK;
        //listaTrabajos.addAll(Arrays.asList(jobs));/

        //Se define un nuevo adaptador de tipo AdaptadorOfLaboral donde se le pasa como argumentos el contexto de la actividad y el arraylist de los trabajos
        AdaptadorOfLaboral adapter= new AdaptadorOfLaboral(this,listaTrabajos );//getApplicationContext(),Arrays.asList(jobs)

        listVw = (ListView) findViewById(R.id.listview);

        //Se establece el adaptador en la Listview
        listVw.setAdapter(adapter);

        //Esto es mas que nada es a nivel de diseño con el objetivo de crear unas lineas mas anchas entre item y item
        listVw.setDividerHeight(3);

     }

    /*----------------------- On Create Option Menu (creación Menú Ppal)--------------------------*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;

    }

    /*------------------- On Options Item Selected (Listener del Menú Ppal)-----------------------*/
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.idMenu1:
                Intent tarea= new Intent(this,NuevaOfLaboral.class);
                tarea.putExtra("cantidadTrabajos",Integer.toString(listaTrabajos.size()));
                startActivity(tarea);
                return true;
            case R.id.idMenu2:
                 // do whatever
                 return true;
            case R.id.idMenu3:
                // do whatever
                return true;
            case R.id.idMenu4:
                // do whatever
                return true;
            default:
                 return super.onOptionsItemSelected(item);
        }
    }


    /*---------------------------- On Context Item Selected --------------------------------------*/
    public boolean onContextItemSelected(MenuItem item) {
        return false;
    }

    /*----------------------------------InicializarListasTrabajo----------------------------------*/
    //Inicializa las listas de cada Categoría con los trabajos que le pertenecen
    private void inicializarListasTrabajo() {

        int idCat;
        int idTrab = Trabajo.TRABAJOS_MOCK.length;

        for (int i = 0; i < idTrab; i++) {
            idCat = (Trabajo.TRABAJOS_MOCK[i]).getCategoria().getId();
        //    Toast.makeText(getBaseContext(), "Id Categoria: "+idCat, Toast.LENGTH_LONG).show();
            Categoria.CATEGORIAS_MOCK[idCat-1].addTrabajo(Trabajo.TRABAJOS_MOCK[i]); // inserta en la lista de Trabajos de la Categoría, el trabajo TRABAJOS_MOCK[i]
       }
    }

    /*--------------------------------verTrabajosCategoría----------------------------------------*/
   /* public void verTrabajosCategoría(Categoria c){

        Toast.makeText(getBaseContext(),"Categoría: "+c.getDescripcion(),Toast.LENGTH_SHORT).show();
        ArrayList<Trabajo> listaTrabajos = c.getTrabajo();
        for (int i = 0; i < listaTrabajos.size(); i++){
            Toast.makeText(getBaseContext(), "Trabajo: "+listaTrabajos.get(i).getDescripcion(), Toast.LENGTH_SHORT).show();
        }
    }*/

    /*------------------------------------listaTotalTrabajos--------------------------------------*/
    protected void listaTotalTrabajos() {

        for(int i = 0; i < Categoria.CATEGORIAS_MOCK.length; i++){
            listaTrabajos.addAll(Categoria.CATEGORIAS_MOCK[i].getTrabajo());
        }
    }

    /*------------------------------------getListaTrabajos--------------------------------------*/
    public ArrayList<Trabajo> getListaTrabajos(){
        return listaTrabajos;
    }

}




//Se le aplica un Listener donde ira lo que tiene que hacer en caso de que sea pulsado
    /*    listVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onLongClickListener(){}
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                //En caso de que la posicion seleccionada gracias a "arg2" sea true que lo cambie a false
               /* if (trabajos.get(arg2).isChekeado()) {
                    trabajos.get(arg2).setChekeado(false);
                } else {
                    //aqui al contrario que la anterior, que lo pase a true.
                    trabajos.get(arg2).setChekeado(true);
                }*/
//Se notifica al adaptador de que el ArrayList que tiene asociado ha sufrido cambios (forzando asi a ir al metodo getView())
     /*           adaptador.notifyDataSetChanged();

            }
        });*/