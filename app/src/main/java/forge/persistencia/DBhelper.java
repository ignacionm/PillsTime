package forge.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joverd e Ignacio on 20/10/2015.
 */
public class DBhelper extends SQLiteOpenHelper{

    // Informacion de la tabla
    public static final String TABLE_RECETA = "receta";
    public static final String RECETA_ID = "_id";
    public static final String RECETA_NOMBRE = "nombre";
    public static final String RECETA_HORA = "hora";
    public static final String RECETA_LAPSO = "lapso";
    public static final String RECETA_DOSIS = "dosis";

    // informacion del a base de datos
    static final String DB_NAME = "DBPILLSTIME";
    static final int DB_VERSION = 1;

    // Informacion de la base de datos
    private static final String CREATE_TABLE = "create table "
            + TABLE_RECETA + "(" + RECETA_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RECETA_NOMBRE + " TEXT NOT NULL, "
            + RECETA_HORA+" TEXT NOT NULL, "
            + RECETA_LAPSO+" TEXT NOT NULL, "
            + RECETA_DOSIS+" TEXT NOT NULL "+");";

    public DBhelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECETA);
        onCreate(db);

    }
}
