package forge.pillstime;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import forge.negocio.Receta;
import forge.persistencia.DBhelper;
import forge.persistencia.SQLControlador;

/**
 * Actividad Principal de la aplicacion
 */
public class MainActivity extends ActionBarActivity implements ActionBar.TabListener , ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private EditText etNombre, etHorah, etHoram, etLapsod, etLapsoh, etLapsom, etDosis, etDosisCantidad, etNotas;
    private TabHost th;
    private Button btnRGuardar, btnREditar, btnREliminar, read_bt, btnRBD;
    private SQLControlador dbconeccion;
    private ListView listaReceta;


    /**
     * Metodo onCreate de la actividad principal donde se inicializa la base de datos de PillsTime y se crean todos los elementos
     * de la aplicacion.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se abre la base de datos
        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        //Se inicializan los botones para manejar la tabla receta
        btnRGuardar = (Button) findViewById(R.id.btnGuardarReceta);
        btnREditar = (Button) findViewById(R.id.btnEditarReceta);
        btnREliminar = (Button) findViewById(R.id.btnEliminarReceta);
        btnRBD = (Button) findViewById(R.id.btnBaseRecetas);

        //Se crean los tabs de la aplicacion en donde se colocaran los fragments
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().setIcon(getResources().getDrawable(R.drawable.alarm)).setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setIcon(getResources().getDrawable(R.drawable.history)).setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setIcon(getResources().getDrawable(R.drawable.eco)).setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setIcon(getResources().getDrawable(R.drawable.shop)).setTabListener(this);
        actionBar.addTab(tab);


    }

    //implements on pager selected
    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        getSupportActionBar().setSelectedNavigationItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    //implements tab listener
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    /**
     * Metodo onClick del boton base de datos, que te lleva a la actividad donde se podra obsevar
     * los datos de la tabla Receta.
     * @param view
     */
    public void onClickVoyBaseReceta(View view){
        Intent i = new Intent(this, RecetaBaseDatos.class);
        startActivity(i);
    }

    /**
     * Metodo onClick del boton Guardar donde se cuardara una Receta
     * @param v
     */
    public void onClickGuardar(View v){
        Receta receta;
        Intent refrescar;
        Context context;
        CharSequence text;
        int duration;
        Toast toast;

        //Declaracion de componentes para agregar
        etNombre = (EditText) findViewById(R.id.etRNombre);
        etHorah = (EditText) findViewById(R.id.etRHorah);
        etHoram = (EditText) findViewById(R.id.etRHoram);
        etLapsod = (EditText) findViewById(R.id.etRLapsoDias);
        etLapsoh = (EditText) findViewById(R.id.etRLapsoHora);
        etLapsom = (EditText) findViewById(R.id.etRLapsoMinuto);
        etDosis = (EditText) findViewById(R.id.etRDosis);
        etDosisCantidad= (EditText) findViewById(R.id.etRDosisCantidad);
        etNotas = (EditText) findViewById(R.id.etRNotas);
        receta = new Receta(etNombre.getText().toString(),etHorah.getText().toString(), etHoram.getText().toString(),
                etLapsod.getText().toString(),etLapsoh.getText().toString(), etLapsom.getText().toString(),
                etDosis.getText().toString(), etDosisCantidad.getText().toString(),etNotas.getText().toString());

        dbconeccion.insertarDatosReceta(receta);
        //Mensaje que avisa que se agrego una Receta
        context = getApplicationContext();
        text = "Haz agregado una Receta";
        duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
        toast.show();
        //Se refresca la actividad
        refrescar = new Intent(this, MainActivity.class);
        startActivity(refrescar);
        this.finish(); //
    }

    /**
     * Metodo onClick del boton editar donde se actulizara un elemento de la tabla Receta
     * @param v
     */
    public void onClickEditar(View v){
        Receta receta;
        Intent refrescar;
        Context context;
        CharSequence text;
        int duration;
        Toast toast;
        etNombre = (EditText) findViewById(R.id.etRNombre);
        etHorah = (EditText) findViewById(R.id.etRHorah);
        etHoram = (EditText) findViewById(R.id.etRHoram);
        etLapsod = (EditText) findViewById(R.id.etRLapsoDias);
        etLapsoh = (EditText) findViewById(R.id.etRLapsoHora);
        etLapsom = (EditText) findViewById(R.id.etRLapsoMinuto);
        etDosis = (EditText) findViewById(R.id.etRDosis);
        etDosisCantidad= (EditText) findViewById(R.id.etRDosisCantidad);
        etNotas = (EditText) findViewById(R.id.etRNotas);

        receta = new Receta(etNombre.getText().toString(),etHorah.getText().toString(), etHoram.getText().toString(),
                etLapsod.getText().toString(),etLapsoh.getText().toString(), etLapsom.getText().toString(),
                etDosis.getText().toString(), etDosisCantidad.getText().toString(),etNotas.getText().toString());
        long receta_id=0;
        receta_id = getIntent().getLongExtra("id_receta", receta_id);

        dbconeccion.editarDatosReceta(receta_id, receta);
        //Mensaje que avisa que se actualizo una Receta
        context = getApplicationContext();
        text = "Haz actualizado una Receta";
        duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
        toast.show();
        //Se refresca la actividad
        refrescar= new Intent(this, MainActivity.class);
        startActivity(refrescar);
        this.finish();
    }

    /**
     * Metodo onClick del boton eliminar donde se eliminara un elemento de la tabla Receta
     * @param view
     */
    public void onClickEliminar(View view){
        Receta receta;
        Intent refrescar;
        Context context;
        CharSequence text;
        int duration;
        Toast toast;

        long receta_idE=0;
        receta_idE = getIntent().getLongExtra("id_receta", receta_idE);
        dbconeccion.eliminarDatosReceta(receta_idE);
        //Mensaje que avisa que se elimino una Receta
        context = getApplicationContext();
        text = "Haz eliminado una Receta";
        duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
        toast.show();
        //Se refresca la actividad
        refrescar= new Intent(this, MainActivity.class);
        startActivity(refrescar);
        this.finish();
    }

}
