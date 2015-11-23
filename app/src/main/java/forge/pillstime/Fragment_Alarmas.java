package forge.pillstime;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import forge.persistencia.DBhelper;
import forge.persistencia.SQLControlador;

/**
 * Fragmento donde se muestra la informacion de Alarmas
 */
public class Fragment_Alarmas extends Fragment{
    private ImageButton btnAF;
    private SQLControlador dbconeccion;
    private ListView listaReceta;
    private String data;
    private ArrayList<String> nombres, horas;

    private View rootView;

    /**
     * Metodo que inicializa la vista a su respectivo fragmento
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return vista fragmento alarmas
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fm_alarmas, container, false);
        return rootView;
    }

    /**
     * esto es llamado después del metodo onCreate de la actividad completada. Se llama después de onCreateView (), y se utiliza principalmente
     * para inicializaciones finales ( por ejemplo, la modificación de elementos de la interfaz de usuario) .
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dbconeccion = new SQLControlador(getActivity().getApplicationContext());
        dbconeccion.abrirBaseDeDatos();

        Cursor cursor = dbconeccion.leerDatosReceta();
        nombres = new ArrayList<String>();
        horas = new ArrayList<String>();


        if (cursor.moveToFirst()){
            //ciclo donde se agregan los valores del cursor a una lista
            while(!cursor.isAfterLast()){
                nombres.add(cursor.getString(cursor.getColumnIndex("nombre")));
                horas.add(cursor.getString(cursor.getColumnIndex("hora")));
                cursor.moveToNext();
            }
        }
        cursor.close();
        String arregloNombres[] = new String[nombres.size()];
        arregloNombres = nombres.toArray(arregloNombres);
        //se inicializa la lista
        listaReceta = (ListView) rootView.findViewById(R.id.listViewFMReceta);

        // Tomar los datos desde la base de datos para poner en un cursor y despues en el listview
        try{
            Cursor cursor1 = dbconeccion.leerDatosReceta();
            String[] from = new String[] {

                    DBhelper.RECETA_ID,
                    DBhelper.RECETA_NOMBRE,
                    DBhelper.RECETA_HORA,

            };
            int[] to = new int[] {
                    R.id.txtReceta_id,
                    R.id.txtRecetaNombre,
                    R.id.txtRecetaHora,
            };
            //se agregan los datos a la lista utilizando la variable rootView
            listaReceta.setAdapter(new ArrayAdapter<String>(rootView.getContext(),
                    android.R.layout.simple_list_item_1 , arregloNombres));

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(rootView.getContext(), R.layout.formato_recetas, cursor1, from, to);
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
                Intent intent = new Intent(rootView.getContext(),FormularioRecetas.class);
                intent.putExtra("id_receta", id);
                startActivity(intent);
            }
        });
    }
}
