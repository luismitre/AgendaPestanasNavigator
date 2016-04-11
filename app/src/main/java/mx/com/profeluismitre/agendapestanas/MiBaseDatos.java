package mx.com.profeluismitre.agendapestanas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import mx.com.profeluismitre.agendapestanas.EstructuraDatos.Estructura;

/**
 * Created by LuisMitre on 04/04/2016.
 */
public class MiBaseDatos extends SQLiteOpenHelper {
    //Se declaran e inicializan las variables encargadas de almacenar las consultas para crear la tabla 'Datos',
    //y las consultas de eliminar/crear la Base de Datos 'Agenda.sqlite'.
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura.TABLE_NAME + " (" +
                    Estructura._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Estructura.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    Estructura.COLUMN_NAME_APELLIDO + TEXT_TYPE + COMMA_SEP +
                    Estructura.COLUMN_NAME_EDAD + TEXT_TYPE + COMMA_SEP +
                    Estructura.COLUMN_NAME_TELEFONO + TEXT_TYPE + COMMA_SEP +
                    Estructura.COLUMN_NAME_CORREO + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Agenda.sqlite";


    public MiBaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Método para crear la Tabla que recibe la consulta Transact-SQL
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    //Método que elimina la tabla y vuelve a llamar al método que la crea
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
