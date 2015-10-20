package forge.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ignacio on 20/10/2015.
 */
public class DBhelper extends SQLiteOpenHelper{

    // Informacion de la tabla
    /*public static final String TABLE_LIBRO = "libros";
    public static final String LIBRO_ID = "_id";
    public static final String LIBRO_TITULO = "titulo";
    public static final String LIBRO_AUTOR = "autor";
    public static final String LIBRO_CALIFICACION = "calificacion";
    public static final String LIBRO_DESCRIPCION = "descripcion";*/

    // informacion del a base de datos
    static final String DB_NAME = "DBPILLSTIME";
    static final int DB_VERSION = 1;

    // Informacion de la base de datos
    /*private static final String CREATE_TABLE = "create table "
            + TABLE_LIBRO + "(" + LIBRO_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LIBRO_TITULO + " TEXT NOT NULL, "
            + LIBRO_AUTOR+" TEXT NOT NULL, "
            + LIBRO_CALIFICACION+" TEXT NOT NULL, "
            + LIBRO_DESCRIPCION+" TEXT NOT NULL "+");";*/

    public DBhelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRO);
        //onCreate(db);

    }
}
