package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;//Lo agregué yo porque no me veía los Menu
import android.view.MenuItem;//Lo agregué yo porque no me veía los MenuItem

public class MainActivity extends AppCompatActivity {

    /*-----------------------------------------On Create-----------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /*------------------------------On Create Option Menu-----------------------------------------*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /*-----------------------------On Context Item Selected---------------------------------------*/
    public boolean onContextItemSelected(MenuItem item) {
        return false;
    }
}
