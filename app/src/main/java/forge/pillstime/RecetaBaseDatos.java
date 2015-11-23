package forge.pillstime;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import forge.negocio.Receta;
import forge.persistencia.DBhelper;
import forge.persistencia.SQLControlador;

/**
 *Actividad donde se muestra la informacion de la tabala Receta
 */
public class RecetaBaseDatos extends AppCompatActivity {

    private TextView tv_recetaID, tv_recetaNombre, tv_recetaHora,tv_recetaMinuto ,tv_recetaLapsoDia,
            tv_recetaLapsoHora, tv_recetaLapsoMinuto, tv_recetaDosis,tv_recetaDosisCantidad , tv_recetaNotas;

    private EditText etNombre, etHorah, etHoram, etLapsod, etLapsoh, etLapsom, etDosis, etDosisCantidad, etNotas;
    private SQLControlador dbconeccion;
    private ListView listaReceta;
    private boolean bandera = false;
    private Receta recetaIntent;

    /**
     * Metodo onCreate donde se llena un listView con los datos de la tabla Receta tomados de la
     * base de datos de la aplicacion.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_base_datos);

        //Se abre la base de datos
        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        //Declaracion de componentes para el ListView
        listaReceta = (ListView) findViewById(R.id.listViewBaseRecetas);

        // Tomar los datos desde la base de datos para poner en un cursor y despues en el listview
        try{
            Cursor cursor = dbconeccion.leerDatosReceta();
            String[] from = new String[] {

                    DBhelper.RECETA_ID,
                    DBhelper.RECETA_NOMBRE,
                    DBhelper.RECETA_HORA,
                    DBhelper.RECETA_MINUTOS,
                    DBhelper.RECETA_LAPSODIA,
                    DBhelper.RECETA_LAPSOHORA,
                    DBhelper.RECETA_LAPSOMINUTO,
                    DBhelper.RECETA_DOSIS,
                    DBhelper.RECETA_DOSISCANTIDAD,
                    DBhelper.RECETA_NOTAS

            };
            int[] to = new int[] {
                    R.id.receta_id,
                    R.id.receta_nombre,
                    R.id.receta_hora,
                    R.id.receta_minuto,
                    R.id.receta_lapsodia,
                    R.id.receta_lapsohora,
                    R.id.receta_lapsominuto,
                    R.id.receta_dosis,
                    R.id.receta_dosiscantidad,
                    R.id.receta_notas
            };

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.formato_listview, cursor, from, to);
            adapter.notifyDataSetChanged();
            listaReceta.setAdapter(adapter);

        }
        catch (Exception e){

        }
        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        listaReceta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                /*Se crea un intent con el id del elemento seleccionado el cual
                  se envia a la actividad principal para poder ser modificado o eliminado
                */

                tv_recetaID = (TextView) view.findViewById(R.id.receta_id);
                tv_recetaNombre = (TextView) view.findViewById(R.id.receta_nombre);
                tv_recetaHora = (TextView) view.findViewById(R.id.receta_hora);
                tv_recetaMinuto = (TextView) view.findViewById(R.id.receta_minuto);
                tv_recetaLapsoDia = (TextView) view.findViewById(R.id.receta_lapsodia);
                tv_recetaLapsoHora = (TextView) view.findViewById(R.id.receta_lapsohora);
                tv_recetaLapsoMinuto = (TextView) view.findViewById(R.id.receta_lapsominuto);
                tv_recetaDosis = (TextView) view.findViewById(R.id.receta_dosis);
                tv_recetaDosisCantidad = (TextView) view.findViewById(R.id.receta_dosiscantidad);
                tv_recetaNotas = (TextView) view.findViewById(R.id.receta_notas);

                Receta receta = new Receta(tv_recetaNombre.getText().toString(),
                        tv_recetaHora.getText().toString(),
                        tv_recetaMinuto.getText().toString(),
                        tv_recetaLapsoDia.getText().toString(),
                        tv_recetaLapsoHora.getText().toString(),
                        tv_recetaLapsoMinuto.getText().toString(),
                        tv_recetaDosis.getText().toString().toString(),
                        tv_recetaDosisCantidad.getText().toString(),
                        tv_recetaNotas.getText().toString());

                Intent intent = new Intent(RecetaBaseDatos.this,FormularioRecetas.class);
                intent.putExtra("id_receta", id);
                intent.putExtra("recetaclick", receta);
                startActivity(intent);
            }
        });



    }

}
