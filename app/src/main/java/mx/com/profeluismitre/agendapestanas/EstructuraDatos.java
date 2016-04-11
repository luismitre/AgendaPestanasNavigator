package mx.com.profeluismitre.agendapestanas;

import android.provider.BaseColumns;

/**
 * Created by LuisMitre on 04/04/2016.
 */
public class EstructuraDatos {
    public EstructuraDatos(){}

    public static abstract class Estructura implements BaseColumns
    {
        public static final String TABLE_NAME = "Agenda";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_EDAD = "edad";
        public static final String COLUMN_NAME_CORREO = "correo";

    }
}
