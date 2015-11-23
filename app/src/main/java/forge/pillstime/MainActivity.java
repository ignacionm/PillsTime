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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import forge.negocio.Receta;
import forge.persistencia.DBhelper;
import forge.persistencia.SQLControlador;

/**
 * Actividad Principal de la aplicacion
 */
public class MainActivity extends ActionBarActivity implements ActionBar.TabListener , ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;

    private TabHost th;

    private ImageButton btnAF;
    private SQLControlador dbconeccion;
    private ListView listaReceta;
    private String data;
    private ArrayList<String> nombres, horas;


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
        btnAF = (ImageButton) findViewById(R.id.btnAbrirFormulario);


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

/*
        Cursor cursor = dbconeccion.leerDatosReceta();
        nombres = new ArrayList<String>();
        horas = new ArrayList<String>();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                 nombres.add(cursor.getString(cursor.getColumnIndex("nombre")));
                 horas.add(cursor.getString(cursor.getColumnIndex("hora")));
                // do what ever you want here
                cursor.moveToNext();
            }
        }
        cursor.close();
        String arregloNombres[] = new String[nombres.size()];
        arregloNombres = nombres.toArray(arregloNombres);
        listaReceta = (ListView) findViewById(R.id.listViewFMReceta);
        ArrayAdapter<String> adaptadorLista = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arregloNombres);

        listaReceta.setAdapter(adaptadorLista);*/


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
     * Metodo onClick que lleva a la actividad con el formulario para el menjo de recetas
     * @param view
     */
    public void onClickAbrirFormularioRecetas(View view){
        Intent i = new Intent(this, FormularioRecetas.class);
        startActivity(i);
    }



}
