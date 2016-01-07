package forge.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que ayuda a la creacion de la base de datos.
 */
public class DBhelper extends SQLiteOpenHelper{

    // Informacion de la tabla
    public static final String TABLE_RECETA = "receta";
    public static final String RECETA_ID = "_id";
    public static final String RECETA_NOMBRE = "nombre";
    public static final String RECETA_HORA = "hora";
    public static final String RECETA_MINUTOS = "minutos";
    public static final String RECETA_LAPSODIA = "lapsoDia";
    public static final String RECETA_LAPSOHORA = "lapsoHora";
    public static final String RECETA_LAPSOMINUTO = "lapsoMinuto";
    public static final String RECETA_DOSIS= "dosis";
    public static final String RECETA_DOSISCANTIDAD= "dosisCantidad";
    public static final String RECETA_NOTAS= "notas";

    // informacion del a base de datos
    static final String DB_NAME = "DBPILLSTIME";
    static final int DB_VERSION = 1;

    // Se crea la tabla Receta
    private static final String CREATE_TABLE = "create table "
            + TABLE_RECETA + "(" + RECETA_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RECETA_NOMBRE + " TEXT NOT NULL, "
            + RECETA_HORA+" TEXT NOT NULL, "
            + RECETA_MINUTOS+" TEXT NOT NULL, "
            + RECETA_LAPSODIA+" TEXT NOT NULL, "
            + RECETA_LAPSOHORA+" TEXT NOT NULL, "
            + RECETA_LAPSOMINUTO+" TEXT NOT NULL, "
            + RECETA_DOSIS+" TEXT NOT NULL, "
            + RECETA_DOSISCANTIDAD+" TEXT NOT NULL, "
            +RECETA_NOTAS+");";

    /**
     * Constructor que inicializa de la base de datos
     * @param context
     */
    public DBhelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Metodo onCreate donde se crea la tabla
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    /**
     * Metodo donde se borra la tabla si existe
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECETA);
        onCreate(db);

    }
}
