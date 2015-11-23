package forge.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import forge.negocio.Receta;

/**
 * Clase donde se utiliza la base de datos y contiene metodos para guardar, editar y eliminar datos.
 */
public class SQLControlador {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    /**
     * Metodo que inicializa la base de datos
     * @return
     * @throws SQLException
     */
    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    /**
     * Metodo que cierra la base de datos
     */
    public void cerrar() {

        dbhelper.close();
    }


    /**
     * Metodo que lee todos los datos de la tabla Receta y regresa un cursor con los datos leidos.
     * @return cursor con los datos
     */
    public Cursor leerDatosReceta() {
        String[] todasLasColumnas = new String[] {
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
        Cursor c = database.query(DBhelper.TABLE_RECETA, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Metodo que agrega un elemento en la base de datos en la tabla Receta
     * @param receta
     */
    public void insertarDatosReceta(Receta receta) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.RECETA_NOMBRE, receta.getNombre());
        cv.put(DBhelper.RECETA_HORA, receta.getHora());
        cv.put(DBhelper.RECETA_MINUTOS, receta.getMinuto());
        cv.put(DBhelper.RECETA_LAPSODIA, receta.getLapsoDia());
        cv.put(DBhelper.RECETA_LAPSOHORA, receta.getLapsoHora());
        cv.put(DBhelper.RECETA_LAPSOMINUTO, receta.getLapsoMinuto());
        cv.put(DBhelper.RECETA_DOSIS, receta.getDosis());
        cv.put(DBhelper.RECETA_DOSISCANTIDAD, receta.getDosisCantidad());
        cv.put(DBhelper.RECETA_NOTAS, receta.getNotas());
        database.insert(DBhelper.TABLE_RECETA, null, cv);
    }

    /**
     * Metodo que actualiza un elemento de la base de datos en la tabla receta
     * @param recetaID
     * @param receta
     * @return
     */
    public int editarDatosReceta(long recetaID, Receta receta) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.RECETA_NOMBRE, receta.getNombre());
        cvActualizar.put(DBhelper.RECETA_HORA, receta.getHora());
        cvActualizar.put(DBhelper.RECETA_MINUTOS, receta.getMinuto());
        cvActualizar.put(DBhelper.RECETA_LAPSODIA, receta.getLapsoDia());
        cvActualizar.put(DBhelper.RECETA_LAPSOHORA, receta.getLapsoHora());
        cvActualizar.put(DBhelper.RECETA_LAPSOMINUTO, receta.getLapsoMinuto());
        cvActualizar.put(DBhelper.RECETA_DOSIS, receta.getDosis());
        cvActualizar.put(DBhelper.RECETA_DOSISCANTIDAD, receta.getDosisCantidad());
        cvActualizar.put(DBhelper.RECETA_NOTAS, receta.getNotas());
        int i = database.update(DBhelper.TABLE_RECETA, cvActualizar,
                DBhelper.RECETA_ID + " = " + recetaID, null);
        return i;
    }

    /**
     * Metodo que elimina un elemento en la base de datos de la tabla Receta
     * @param recetaID
     */
    public void eliminarDatosReceta(long recetaID) {
        database.delete(DBhelper.TABLE_RECETA, DBhelper.RECETA_ID + "="
                + recetaID, null);
    }



}
