package dam.isi.frsf.utn.edu.ar.laboratorio3v2;


import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NuevaOfLaboral extends AppCompatActivity implements Serializable {

    String descripcion;
    EditText editTextDescripcion;
    Trabajo trabajo;

    Intent tarea;
    int totalTrabajos;

    String nameCategoria;
    String[] listaCategorias;
    Categoria categoria;
    Spinner spinnerCategorias;

    int horas;
    String stringHoras;
    EditText editTextHoras;

    double precio;
    String stringPrecio;
    EditText editTextPrecio;

    int moneda;
    String stringMoneda;
    RadioGroup radGrup;

    Date fin;
    String stringFin;
    EditText editTextFin;
    SimpleDateFormat df_fin;
    String stringDía;
    String[] listaDías;


    Switch switchIngles;
    Boolean ingles;
    Button btGuardar;
    Button btCancelar;

    /*-----------------------------------------On Create------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oflaboral);

        //inicializo variables

        // Manejo del Intent
        tarea = getIntent();
        String nombre=tarea.getStringExtra("cantidadTrabajos");
        totalTrabajos = Integer.valueOf(nombre);
        // Fin manejo del Intent

        trabajo = null;

        nameCategoria = null;
        categoria = new Categoria();
        listaCategorias = null;
        spinnerCategorias = (Spinner)findViewById(R.id.spinner);

        precio = 0.0;
        stringPrecio ="";
        editTextPrecio = (EditText) findViewById(R.id.editText3);

        descripcion ="";
        editTextDescripcion = (EditText) findViewById(R.id.editText);

        horas = 0;
        stringHoras ="";
        editTextHoras = (EditText) findViewById(R.id.editText2);

        moneda = 6;// representando el por defecto, que es desconocido*/
        stringMoneda ="";
        radGrup = (RadioGroup) findViewById(R.id.rdgroup);

        long ts =(long) (System.currentTimeMillis());
        fin = new Date(ts);

        stringFin = "";
        editTextFin = (EditText) findViewById(R.id.editText4);
        listaDías = null;
        stringDía = "";

        switchIngles = (Switch) findViewById(R.id.switch1);
        ingles = false;

        btGuardar = (Button) findViewById(R.id.buttonGuardar);
        btCancelar = (Button) findViewById(R.id.buttonCancelar);


        //--------------------------Manejo Spinner (Categoría seleccionada)-----------------------//
        // Setea el Spinner con los horarios posibles, cargados en el recurso arrays.xml
        listaCategorias = getResources().getStringArray(R.array.categorías);
        ArrayAdapter adaptadorCategorias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaCategorias);

        spinnerCategorias.setAdapter(adaptadorCategorias);

        // Listener Spinner
        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //Guardamos en una variable la Categoría seleccionada
                nameCategoria = (String) spinnerCategorias.getItemAtPosition(position);
                spinnerCategorias.clearFocus();
                categoria.setId(Categoria.CATEGORIAS_MOCK[0].getIdOf(nameCategoria));
                categoria.setDescripcion(nameCategoria);
                Toast.makeText(getBaseContext(), "Categoría seleccionada: "+categoria.getDescripcion()+" con id: "+categoria.getId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {  }

        });// Fin Listener


       // texto.setMovementMethod(new ScrollingMovementMethod());
        //-------------------------------------Fin del Spinner------------------------------------//

        //Manejo del Radio Group Vertical de Selección de Moneda de Pago
        radGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//1 US$ 2Euro 3 AR$- 4 Libra 5 R$

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton1://3 AR$
                        moneda = 3;
                        stringMoneda = "Pesos Argentinos";
                        break;
                    case R.id.radioButton2://4 Libra
                        moneda = 4;
                        stringMoneda = "Libra";
                        break;
                    case R.id.radioButton3://5 R$
                        moneda = 5;
                        stringMoneda = "Real";
                        break;
                    case R.id.radioButton4://2 Euro
                        moneda = 2;
                        stringMoneda = "Euro";
                        break;
                    case R.id.radioButton5://1 U$$
                        moneda = 1;
                        stringMoneda = "Dolar";
                        break;
                    default:
                        moneda = 6;
                        stringMoneda = "Desconocido";
                        break;
                }
            }
        });
        //------------------------Fin del Radio Group (Moneda de Pago)----------------------------//


        //va la captura de fin de fecha

        // Recuperación del valor del switch del Requerimiento o No de Ingles para el puesto

//


        btGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {

                //---- Extraemos los datos del campo "Descripción"
                descripcion = editTextDescripcion.getText().toString();
                if(descripcion.equals("")){ // si no ha ingresado descripción del puesto
                    Toast.makeText(getBaseContext(),R.string.warningDesc, Toast.LENGTH_SHORT).show();
                    editTextDescripcion.requestFocus();
                }

                //---- Extraemos los datos del campo "Horas de Trabajo"
                stringHoras = editTextHoras.getText().toString();
                if(stringHoras.equals("")){ // si no ha ingresado la cantidad de horas por trabajar
                    Toast.makeText(getBaseContext(),R.string.warningHoras, Toast.LENGTH_SHORT).show();
                    editTextHoras.requestFocus();
                }
                else horas = Integer.valueOf(stringHoras);

                //---- Extraemos los datos del campo "Precio por Hora"
                stringPrecio = editTextPrecio.getText().toString();
                if(stringPrecio.equals("")){ // si no ha ingresado el valor de la hora laboral
                    Toast.makeText(getBaseContext(),R.string.warningPrecio, Toast.LENGTH_SHORT).show();
                    editTextPrecio.requestFocus();
                }
                else precio = Double.valueOf(stringPrecio);

                //---- Extraemos los datos del campo "Fecha Fin Tarea"
                stringFin = editTextFin.getText().toString();
                if(stringFin.equals("")){ // si no ha ingresado la fecha de finalización
                    Toast.makeText(getBaseContext(),R.string.warningFin, Toast.LENGTH_SHORT).show();
                    editTextFin.requestFocus();
                }
                else {
                    df_fin = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                    try {
                        fin = (Date) df_fin.parse(stringFin);
                    } catch (Exception e) {
                        Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
                    }
                }// Fecha Fin Tarea

// !!!!!!!!!!!! Como no logramos tomar la fecha ingresada, ponemos una de prueba
                Date fechaPrueba = new Date();

               // ingles = switchIngles.isEnabled();

                // creo mi nueva instancia Trabajo
                trabajo = new Trabajo(totalTrabajos+1,descripcion,categoria);

                Toast.makeText(getBaseContext(), "Datos de instancia Trabajo: "+ "\n"+"Categoría: "+trabajo.getCategoria().getDescripcion()+"\n"+"Descripción: "+descripcion+"\n"+"Horas: "+horas+"\n"+"Precio: "+precio+"\n"+"Moneda: "+stringMoneda+"\n"+"Fecha de Finalización: "+fechaPrueba.toString()+"\n"+"Requiere Inglés: "+ingles+"\n", Toast.LENGTH_SHORT).show();

                // seteo los restantes valores ingresados en el Alta Trabajo
        /*        trabajo.setHorasPresupuestadas(horas);
                trabajo.setPrecioMaximoHora(precio);
               trabajo.setMonedaPago(moneda);
*/
        /*        String dtStart = "2010-10-15T09:27:37Z";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                try {
                    //Date date = format.parse(dtStart);
                    trabajo.setFechaEntrega(format.parse(dtStart));
                    System.out.println(date);
                } catch (ParseException e) {

                    e.printStackTrace();
                }
*/
         //       trabajo.setRequiereIngles(ingles);

                // incorporo el nuevo trabajo a la lista de trabajos de la categoría correspondiente
        //        trabajo.getCategoria().addTrabajo(trabajo);

               // adapter.notifyDataSetChanged();
                //finish();
            }
        });

       btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){

                Toast.makeText(getBaseContext(), "Boton Cancelar: ", Toast.LENGTH_SHORT).show();
                finish();
            }
       });
    }

}
