package forge.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import forge.negocio.Receta;

/**
 * Created by Ignacio on 20/10/2015.
 */
public class SQLControlador {
    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {

        dbhelper.close();
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBhelper.RECETA_ID,
                DBhelper.RECETA_NOMBRE,
                DBhelper.RECETA_HORA,
                DBhelper.RECETA_LAPSO,
                DBhelper.RECETA_DOSIS
        };
        Cursor c = database.query(DBhelper.TABLE_RECETA, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void insertarDatos(Receta receta) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.RECETA_NOMBRE, receta.getNombre());
        cv.put(DBhelper.RECETA_HORA, receta.getHora());
        cv.put(DBhelper.RECETA_LAPSO, receta.getLapso());
        cv.put(DBhelper.RECETA_DOSIS, receta.getDosis());
        database.insert(DBhelper.TABLE_RECETA, null, cv);
    }

    public int actualizarDatos(long recetaID, Receta receta) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.RECETA_NOMBRE, receta.getNombre());
        cvActualizar.put(DBhelper.RECETA_HORA, receta.getHora());
        cvActualizar.put(DBhelper.RECETA_LAPSO, receta.getLapso());
        cvActualizar.put(DBhelper.RECETA_DOSIS, receta.getDosis());
        int i = database.update(DBhelper.TABLE_RECETA, cvActualizar,
                DBhelper.RECETA_ID + " = " + recetaID, null);
        return i;
    }

    public void deleteData(long recetaID) {
        database.delete(DBhelper.TABLE_RECETA, DBhelper.RECETA_ID + "="
                + recetaID, null);
    }



}
