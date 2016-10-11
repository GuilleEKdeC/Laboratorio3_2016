package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

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
import java.util.Arrays;
import java.util.Random;
import dam.isi.frsf.utn.edu.ar.laboratorio3v2.AdaptadorOfLaboral;

public class MainActivity extends AppCompatActivity {

    /**********************************Declaración de Variables************************************/
    //Se crea una objeto tipo ListView
    private ListView listVw;

    //Se crea un ArrayList de tipo Trabajo
    private ArrayList<Trabajo> trabajos;

    //Se crea un objeto de tipo AdaptadorDias
    private AdaptadorOfLaboral adaptador;






    /****************************************** Métodos *******************************************/

    /*-----------------------------------------On Create------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listVw = (ListView) findViewById(R.id.listview);
        //this.inicializarTrabajos();
        Trabajo[] jobs = Trabajo.TRABAJOS_MOCK;

     /*   String s = jobs[18].getCategoria().getDescripcion()+"\n"+
                jobs[18].getDescripcion()+"\n"+
                jobs[18].getHorasPresupuestadas().toString()+" -- "+ String.format("%.2f", jobs[0].getPrecioMaximoHora())+" -- "+jobs[0].getMonedaPago()+"\n"+
                jobs[18].getFechaEntrega()+" -- "+jobs[0].getRequiereIngles();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
*/
        //Se define un nuevo adaptador de tipo AdaptadorOfLaboral donde se le pasa como argumentos el contexto de la actividad y el arraylist de los trabajos
        //adaptador = new AdaptadorOfLaboral(this, trabajos);
        ArrayList<Trabajo> listaTrabajos = new ArrayList<Trabajo>();
        listaTrabajos.addAll(Arrays.asList(jobs));
        AdaptadorOfLaboral adapter= new AdaptadorOfLaboral(this,listaTrabajos );//getApplicationContext(),Arrays.asList(jobs)

       //Se establece el adaptador en la Listview
        listVw.setAdapter(adapter);       //listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice , android.R.id.text1,colorArrayList);


     /*   String s = jobs[18].getCategoria().getDescripcion()+"\n";/*+
                jobs[18].getDescripcion()+"\n"+
                jobs[18].getHorasPresupuestadas().toString()+" -- "+ String.format("%.2f", jobs[0].getPrecioMaximoHora())+" -- "+jobs[0].getMonedaPago()+"\n"+
                jobs[18].getFechaEntrega()+" -- "+jobs[0].getRequiereIngles();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();*/

        //Esto es mas que nada es a nivel de diseño con el objetivo de crear unas lineas mas anchas entre item y item
//        listVw.setDividerHeight(3);

        //Se le aplica un Listener donde ira lo que tiene que hacer en caso de que sea pulsado
     /*   listVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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

    }

    /*----------------------------- On Create Option Menu ----------------------------------------*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /*---------------------------- On Context Item Selected --------------------------------------*/
    public boolean onContextItemSelected(MenuItem item) {
        return false;
    }


    /*------------------------------- Inicializar Trabajos ---------------------------------------*/
    public void inicializarTrabajos(){

        this.trabajos = new ArrayList<Trabajo>();

        for(int i = 0; i < Trabajo.TRABAJOS_MOCK.length; i++){
            trabajos.add(Trabajo.TRABAJOS_MOCK[i]);
        }
    }

}
