package forge.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
        /*String[] todasLasColumnas = new String[] {
               DBhelper.LIBRO_ID,
                DBhelper.LIBRO_TITULO,
                DBhelper.LIBRO_AUTOR,
                DBhelper.LIBRO_CALIFICACION,
                DBhelper.LIBRO_DESCRIPCION
        };
        Cursor c = database.query(DBhelper.TABLE_LIBRO, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;*/
        return null;
    }

    /*public void insertarDatos(Libro libro) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.LIBRO_TITULO, libro.getTitulo());
        cv.put(DBhelper.LIBRO_AUTOR, libro.getAutor());
        cv.put(DBhelper.LIBRO_CALIFICACION, libro.getCalificacion());
        cv.put(DBhelper.LIBRO_DESCRIPCION, libro.getDescripcion());
        database.insert(DBhelper.TABLE_LIBRO, null, cv);
    }*/

    /*public int actualizarDatos(long libroID, Libro libro) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.LIBRO_TITULO, libro.getTitulo());
        cvActualizar.put(DBhelper.LIBRO_AUTOR, libro.getAutor());
        cvActualizar.put(DBhelper.LIBRO_CALIFICACION, libro.getCalificacion());
        cvActualizar.put(DBhelper.LIBRO_DESCRIPCION, libro.getDescripcion());
        int i = database.update(DBhelper.TABLE_LIBRO, cvActualizar,
                DBhelper.LIBRO_ID + " = " + libroID, null);
        return i;
    }*/

    /*public void deleteData(long libroID) {
        database.delete(DBhelper.TABLE_LIBRO, DBhelper.LIBRO_ID + "="
                + libroID, null);
    }*/



}
