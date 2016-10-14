package dam.isi.frsf.utn.edu.ar.laboratorio3v2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
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
 /*   int moneda;
    double precio;*/

    /*



        EditText editTextPrecio;
        EditText editTextFin;
        RadioGroup radGrup;
        Switch switchIngles;
        Boolean ingles;*/
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
      //  Toast.makeText(getBaseContext(), "Total Trabajos String: "+nombre, Toast.LENGTH_LONG).show();
      //  Toast.makeText(getBaseContext(), "Total Trabajos Integer: "+totalTrabajos, Toast.LENGTH_LONG).show();
        // Fin manejo del Intent
        trabajo = null;

        nameCategoria = null;
        categoria = new Categoria();
        listaCategorias = null;
        spinnerCategorias = (Spinner)findViewById(R.id.spinner);
  /*      precio = 0.0;
        moneda = 6;// representando el por defecto, que es desconocido*/



        descripcion ="";
        editTextDescripcion = (EditText) findViewById(R.id.editText);

        horas = 0;
        stringHoras ="";
        editTextHoras = (EditText) findViewById(R.id.editText2);
    /*    editTextPrecio = (EditText) findViewById(R.id.editText3);
        editTextFin = (EditText) findViewById(R.id.editText4);

        radGrup = (RadioGroup) findViewById(R.id.rdgroup);

        switchIngles = (Switch) findViewById(R.id.switch1);*/

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



        // Manejo EditText (Precio por Hora)
      /*  editTextPrecio.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                precio = Double.parseDouble(s.toString());
            //    Toast.makeText(getBaseContext(), "Precio por Hora: "+precio, Toast.LENGTH_LONG).show();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });*/
        // Fin Listener
        //-----------------------------Fin del EditText Precio por Hora---------------------------//

/*        descripcion = editTextDescripcion.toString();
        Toast.makeText(getBaseContext(), "Descripción: "+descripcion, Toast.LENGTH_LONG).show();
*/

        //Manejo del Radio Group Vertical de Selección de Moneda de Pago
    /*    radGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton1:
                        moneda.setImageResource(R.drawable.ar);
                        break;
                    case R.id.radioButton2:
                        moneda.setImageResource(R.drawable.uk);
                        break;
                    case R.id.radioButton3:
                        moneda.setImageResource(R.drawable.br);
                        break;
                    case R.id.radioButton4:
                        moneda.setImageResource(R.drawable.eu);
                        break;
                    case R.id.radioButton5:
                        moneda.setImageResource(R.drawable.us);
                        break;
                    case R.id.radioButton6:
                        moneda.setImageResource(R.drawable.desconocido);
                        break;
                }
            }
        });*/
        //------------------------Fin del Radio Group (Moneda de Pago)----------------------------//

        //va la captura de fin de fecha

        // Recuperación del valor del switch del Requerimiento o No de Ingles para el puesto

//        ingles = switchIngles.isEnabled();


        btGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
              //  Toast.makeText(getBaseContext(), "Boton Guardar", Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getBaseContext(), "Cantidad de Trabajos: "+totalTrabajos, Toast.LENGTH_SHORT).show();

                // Extraemos los datos del campo "Descripción"
                descripcion = editTextDescripcion.getText().toString();
                if(descripcion.equals("")){ // si no ha ingresado descripción del puesto
                    Toast.makeText(getBaseContext(),"Ingrese DESCRIPCIÓN de la Oferta", Toast.LENGTH_SHORT).show();
                    editTextDescripcion.requestFocus();
                }

                // Extraemos los datos del campo "Horas de Trabajo"
                stringHoras = editTextHoras.getText().toString();
                if(stringHoras.equals("")){ // si no ha ingresado descripción del puesto
                    Toast.makeText(getBaseContext(),"Ingrese las HORAS de TRABAJO", Toast.LENGTH_SHORT).show();
                    editTextHoras.requestFocus();
                }
                horas = Integer.valueOf(stringHoras);

               // Double.parseDouble(d.getText().toString())

              //  Toast.makeText(getBaseContext(), "Descripción: "+descripcion, Toast.LENGTH_SHORT).show();
                // creo mi nueva instancia Trabajo
                trabajo = new Trabajo(totalTrabajos+1,descripcion,categoria);

                Toast.makeText(getBaseContext(), "Datos de instancia Trabajo: "+ "\n"+"Categoría: "+trabajo.getCategoria().getDescripcion()+"\n"+"Descripción: "+descripcion+"\n"+"Horas: "+horas+"\n", Toast.LENGTH_SHORT).show();

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

    /*--------------------------------------cantidadTrabajos--------------------------------------*/
   /* protected int cantidadTrabajos(){

        int i = 0;
        int countId = 0;
        while(i < Categoria.CATEGORIAS_MOCK.length){
            Toast.makeText(getBaseContext(), "Entré al CT: ", Toast.LENGTH_SHORT).show();
            countId = (Categoria.CATEGORIAS_MOCK[i]).getTrabajo().size() + countId;
            i++;
        }
        return countId;
    }*/
}
