package forge.pillstime;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import forge.negocio.Receta;
import forge.persistencia.SQLControlador;

public class FormularioRecetas extends AppCompatActivity {
    private Button btnRGuardar, btnREditar, btnREliminar, read_bt, btnRBD ;
    private EditText etNombre, etHorah, etHoram, etLapsod, etLapsoh, etLapsom, etDosis, etDosisCantidad, etNotas;
    private SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_recetas);
        btnRGuardar = (Button) findViewById(R.id.btnGuardarReceta);
        btnREditar = (Button) findViewById(R.id.btnEditarReceta);
        btnREliminar = (Button) findViewById(R.id.btnEliminarReceta);
        btnRBD = (Button) findViewById(R.id.btnBaseRecetas);
        //Se abre la base de datos
        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        Intent i = getIntent();
        Receta receta=(Receta) i.getSerializableExtra("recetaclick");

        //si receta no es null es por que se dio click a un elemento de la base de datos para ser editada
        if(receta!=null){

            etNombre = (EditText) findViewById(R.id.etRecetaNombre);
            etHorah = (EditText) findViewById(R.id.etRecetaHorah);
            etHoram = (EditText) findViewById(R.id.etRecetaHoram);
            etLapsod = (EditText) findViewById(R.id.etRecetaLapsoDias);
            etLapsoh = (EditText) findViewById(R.id.etRecetaLapsoHora);
            etLapsom = (EditText) findViewById(R.id.etRecetaLapsoMinuto);
            etDosis = (EditText) findViewById(R.id.etRecetaDosis);
            etDosisCantidad = (EditText) findViewById(R.id.etRecetaDosisCantidad);
            etNotas = (EditText) findViewById(R.id.etRecetaNotas);

            etNombre.setText(receta.getNombre());
            etHorah.setText(receta.getHora());
            etHoram.setText(receta.getMinuto());
            etLapsod.setText(receta.getLapsoDia());
            etLapsoh.setText(receta.getLapsoHora());
            etLapsom.setText(receta.getLapsoMinuto());
            etDosis.setText(receta.getDosis());
            etDosisCantidad.setText(receta.getDosisCantidad());
            etNotas.setText(receta.getNotas());
            receta=null;
        }


    }

    /**
     * Metodo onClick del boton Guardar donde se guardara una Receta
     * @param v
     */
    public void onClickGuardar(View v){
        forge.negocio.Receta receta;
        Intent refrescar;
        Context context;
        CharSequence text;
        int duration;
        Toast toast;

        //Declaracion de componentes para agregar
        etNombre = (EditText) findViewById(R.id.etRecetaNombre);
        etHorah = (EditText) findViewById(R.id.etRecetaHorah);
        etHoram = (EditText) findViewById(R.id.etRecetaHoram);
        etLapsod = (EditText) findViewById(R.id.etRecetaLapsoDias);
        etLapsoh = (EditText) findViewById(R.id.etRecetaLapsoHora);
        etLapsom = (EditText) findViewById(R.id.etRecetaLapsoMinuto);
        etDosis = (EditText) findViewById(R.id.etRecetaDosis);
        etDosisCantidad= (EditText) findViewById(R.id.etRecetaDosisCantidad);
        etNotas = (EditText) findViewById(R.id.etRecetaNotas);
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
        etNombre = (EditText) findViewById(R.id.etRecetaNombre);
        etHorah = (EditText) findViewById(R.id.etRecetaHorah);
        etHoram = (EditText) findViewById(R.id.etRecetaHoram);
        etLapsod = (EditText) findViewById(R.id.etRecetaLapsoDias);
        etLapsoh = (EditText) findViewById(R.id.etRecetaLapsoHora);
        etLapsom = (EditText) findViewById(R.id.etRecetaLapsoMinuto);
        etDosis = (EditText) findViewById(R.id.etRecetaDosis);
        etDosisCantidad= (EditText) findViewById(R.id.etRecetaDosisCantidad);
        etNotas = (EditText) findViewById(R.id.etRecetaNotas);

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

    /**
     * Metodo onClick del boton base de datos, que te lleva a la actividad donde se podra obsevar
     * los datos de la tabla Receta.
     * @param view
     */
    public void onClickVoyBaseReceta(View view){
        Intent i = new Intent(this, RecetaBaseDatos.class);
        startActivity(i);
    }
}
