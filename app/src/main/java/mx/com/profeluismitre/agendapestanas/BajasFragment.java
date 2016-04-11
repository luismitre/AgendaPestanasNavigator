package mx.com.profeluismitre.agendapestanas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import mx.com.profeluismitre.agendapestanas.EstructuraDatos.Estructura;

public class BajasFragment extends Fragment {
    EditText txtNombre;
    EditText txtApellido;
    EditText txtEdad;
    EditText txtTelefono;
    EditText txtCorreo;
    Button btnCancelar;
    Button btnEliminar;
    Button btnBuscar;
    MiBaseDatos basedatos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bajas, container, false);
    }

    public void onStart(){
        super.onStart();

        txtNombre=(EditText)getView().findViewById(R.id.txtNombreBajas);
        txtApellido=(EditText)getView().findViewById(R.id.txtApellidoBajas);
        txtEdad=(EditText)getView().findViewById(R.id.txtEdadBajas);
        txtTelefono=(EditText)getView().findViewById(R.id.txtTelefonoBajas);
        txtCorreo=(EditText)getView().findViewById(R.id.txtCorreoBajas);
        btnCancelar=(Button)getView().findViewById(R.id.btnCancelarBajas);
        btnEliminar=(Button)getView().findViewById(R.id.btnEliminarBajas);
        btnBuscar=(Button)getView().findViewById(R.id.btnBuscarBajas);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarTelefono();

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Deseas Eliminar el Registro ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        borrarProducto();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtEdad.setText("");
                        txtTelefono.setText("");
                        txtCorreo.setText("");
                    }
                });
                dialogo1.show();



            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombre.setText("");
                txtApellido.setText("");
                txtEdad.setText("");
                txtTelefono.setText("");
                txtCorreo.setText("");
            }
        });


    }
    public void buscarTelefono()
    {
        //Se inicializa la clase.
        basedatos = new MiBaseDatos(getContext());
        //Se establecen permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Columnas que devolverá la consulta.
        String[] columnas = {
                Estructura._ID,
                Estructura.COLUMN_NAME_NOMBRE,
                Estructura.COLUMN_NAME_APELLIDO,
                Estructura.COLUMN_NAME_EDAD,
                Estructura.COLUMN_NAME_CORREO,
                Estructura.COLUMN_NAME_TELEFONO

        };

        //Cláusula WHERE para buscar por producto
        String telefono = Estructura.COLUMN_NAME_TELEFONO + " LIKE '" +  txtTelefono.getText().toString() + "'";
        //Orden de los resultados devueltos por Producto, de forma Descendente alfabéticamente
        String ordenSalida = Estructura.COLUMN_NAME_TELEFONO + " DESC";

        if(telefono.equals(""))
        {
            Toast.makeText(getContext(), "Debe indicar el Telefono a buscar en la base de datos.", Toast.LENGTH_LONG).show();
        }else
        {
            //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla, columnas, producto y orden de los resultados.
            Cursor cursor = sqlite.query(Estructura.TABLE_NAME, columnas, telefono,null , null, null, null);

            if(cursor.getCount() != 0)
            {
                cursor.moveToFirst();

                //long identificador = cursor.getLong(cursor.getColumnIndex(Estructura._ID));
                txtNombre.setText(cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_NOMBRE)));
                txtApellido.setText(cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_APELLIDO)));
                txtEdad.setText(cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_EDAD)));
                txtCorreo.setText(cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_CORREO)));

            }
            else
            {
                Toast.makeText(getContext(), "El Telefono '" + txtTelefono.getText().toString()  + "' no existe en la Base de Datos.", Toast.LENGTH_LONG).show();
            }

        }
        //Se cierra la conexión abierta a la Base de Datos
        sqlite.close();

    }
    public void borrarProducto()
    {

        String telefono_Eliminar = txtTelefono.getText().toString();
        //Se inicializa la clase.
        basedatos = new MiBaseDatos(getContext());
        //Se establecen permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        if(telefono_Eliminar.equals("")||txtNombre.getText().toString().equals(""))
        {
            Toast.makeText(getContext(), "Debe buscar el Telefono a eliminar de la base de datos Primero.", Toast.LENGTH_LONG).show();
        }else
        {
            //Se especifica el campo Producto y el producto introducido en el campo de texto a eliminar
            String consulta = Estructura.COLUMN_NAME_TELEFONO + " LIKE '" + telefono_Eliminar + "'";
            //Se borra el producto indicado en el campo de texto
            sqlite.delete(Estructura.TABLE_NAME, consulta, null);
            Toast.makeText(getContext(), "Se ha eliminado el Telefono: " + telefono_Eliminar, Toast.LENGTH_LONG).show();
            txtNombre.setText("");
            txtApellido.setText("");
            txtEdad.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");
        }
        //Se cierra la conexión abierta a la Base de Datos
        sqlite.close();
    }
}
