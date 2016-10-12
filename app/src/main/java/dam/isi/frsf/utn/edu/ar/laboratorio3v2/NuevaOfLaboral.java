package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class NuevaOfLaboral extends AppCompatActivity implements Serializable {

    //Se crea un ArrayList de tipo Categorías
    //private ArrayList<String> listaCategorías;

    String categoria;
    String[] listaCategorias;

    /*-----------------------------------------On Create------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oflaboral);

        //inicializo variables
        categoria = null;
        listaCategorias = null;


        // Setea el Spinner con los horarios posibles, cargados en el recurso arrays.xml
        listaCategorias = getResources().getStringArray(R.array.categorías);
        ArrayAdapter adaptadorCategorias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaCategorias);
        Spinner spinnerCategorias;// = new Spinner();
        spinnerCategorias = (Spinner)findViewById(R.id.spinner);
        spinnerCategorias.setAdapter(adaptadorCategorias);

        //Manejo de la Selección de un Item (comida)
        spinnerCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Guardamos en una variable la Categoría seleccionada
              //  categoria = (String) spinnerCategorias.getItemAtPosition(position);
            }
        });
        //-------------------Fin del Spinner-----------------------//





    }
}
