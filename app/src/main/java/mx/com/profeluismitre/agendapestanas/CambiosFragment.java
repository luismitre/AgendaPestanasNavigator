package mx.com.profeluismitre.agendapestanas;

import android.app.AlertDialog;
import android.content.ContentValues;
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

public class CambiosFragment extends Fragment {
    EditText txtNombre;
    EditText txtApellido;
    EditText txtEdad;
    EditText txtTelefono;
    EditText txtCorreo;
    Button btnCancelar;
    Button btnModificar;
    Button btnBuscar;
    MiBaseDatos basedatos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cambios, container, false);
    }
    public void onStart(){
        super.onStart();

        txtNombre=(EditText)getView().findViewById(R.id.txtNombreCambios);
        txtApellido=(EditText)getView().findViewById(R.id.txtApellidoCambios);
        txtEdad=(EditText)getView().findViewById(R.id.txtEdadCambios);
        txtTelefono=(EditText)getView().findViewById(R.id.txtTelefonoCambios);
        txtCorreo=(EditText)getView().findViewById(R.id.txtCorreoCambios);
        btnCancelar=(Button)getView().findViewById(R.id.btnCancelarCambios);
        btnModificar =(Button)getView().findViewById(R.id.btnEliminarCambios);
        btnBuscar=(Button)getView().findViewById(R.id.btnBuscarCambios);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            buscarTelefono();

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

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Deseas Actualizar el Registro ?");
                dialogo1.setCancelable(false);

                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        modificar();
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
    }
    public void buscarTelefono()
    {
        //Se inicializa la clase.
        basedatos = new MiBaseDatos(getContext());
        //Se establecen permisos de lectura
        SQLiteDatabase sqlite = basedatos.getReadableDatabase();
        //Columnas que devolverá la consulta.
        String[] columnas = {
                EstructuraDatos.Estructura._ID,
                EstructuraDatos.Estructura.COLUMN_NAME_NOMBRE,
                EstructuraDatos.Estructura.COLUMN_NAME_APELLIDO,
                EstructuraDatos.Estructura.COLUMN_NAME_EDAD,
                EstructuraDatos.Estructura.COLUMN_NAME_CORREO,
                EstructuraDatos.Estructura.COLUMN_NAME_TELEFONO

        };

        //Cláusula WHERE para buscar por producto
        String telefono = EstructuraDatos.Estructura.COLUMN_NAME_TELEFONO + " LIKE '" +  txtTelefono.getText().toString() + "'";
        //Orden de los resultados devueltos por Producto, de forma Descendente alfabéticamente
        String ordenSalida = EstructuraDatos.Estructura.COLUMN_NAME_TELEFONO + " DESC";

        if(telefono.equals(""))
        {
            Toast.makeText(getContext(), "Debe indicar el Telefono a buscar en la base de datos.", Toast.LENGTH_LONG).show();
        }else
        {
            //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla, columnas, producto y orden de los resultados.
            Cursor cursor = sqlite.query(EstructuraDatos.Estructura.TABLE_NAME, columnas, telefono,null , null, null, null);

            if(cursor.getCount() != 0)
            {
                cursor.moveToFirst();

                //long identificador = cursor.getLong(cursor.getColumnIndex(Estructura._ID));
                txtNombre.setText(cursor.getString(cursor.getColumnIndex(EstructuraDatos.Estructura.COLUMN_NAME_NOMBRE)));
                txtApellido.setText(cursor.getString(cursor.getColumnIndex(EstructuraDatos.Estructura.COLUMN_NAME_APELLIDO)));
                txtEdad.setText(cursor.getString(cursor.getColumnIndex(EstructuraDatos.Estructura.COLUMN_NAME_EDAD)));
                txtCorreo.setText(cursor.getString(cursor.getColumnIndex(EstructuraDatos.Estructura.COLUMN_NAME_CORREO)));

            }
            else
            {
                Toast.makeText(getContext(), "El Telefono '" + txtTelefono.getText().toString()  + "' no existe en la Base de Datos.", Toast.LENGTH_LONG).show();
            }

        }
        //Se cierra la conexión abierta a la Base de Datos
        sqlite.close();

    }
    public void modificar()
    {
        //Se inicializa la clase.
        basedatos = new MiBaseDatos(getContext());

        //Se establecen permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();


        String nombre_modificar = txtNombre.getText().toString();
        String apellido_modificar = txtApellido.getText().toString();
        String edad_modificar = txtEdad.getText().toString();
        String telefono_modificar = txtTelefono.getText().toString();
        String correo_modificar = txtCorreo.getText().toString();

        ContentValues content = new ContentValues();
        //Se añaden los valores introducidos de cada campo mediante clave(columna)/valor(valor introducido en el campo de texto)
        content.put(Estructura.COLUMN_NAME_NOMBRE, nombre_modificar);
        content.put(Estructura.COLUMN_NAME_APELLIDO, apellido_modificar);
        content.put(Estructura.COLUMN_NAME_EDAD, edad_modificar);
        content.put(Estructura.COLUMN_NAME_TELEFONO, telefono_modificar);
        content.put(Estructura.COLUMN_NAME_CORREO, correo_modificar);
        if(nombre_modificar.equals("") || apellido_modificar.equals("")
                || edad_modificar.equals("")|| telefono_modificar.equals("")
                || correo_modificar.equals(""))
        {
            Toast.makeText(getContext(), "Revise los datos introducidos. Todos los campos son obligatorios.", Toast.LENGTH_LONG).show();
        }else
        {
            //Se establece la condición del _id del producto a modificar
            String selection = Estructura.COLUMN_NAME_TELEFONO + " LIKE " + telefono_modificar;

            //Se llama al método update pasándole los parámetros para modificar el producto con el identificado como condición de busqueda
            int count = sqlite.update(
                    Estructura.TABLE_NAME,
                    content,
                    selection,
                    null);
            Toast.makeText(getContext(), "Se ha actualizado el Contacto: " + telefono_modificar +
                    ". Registros modificados: " + count, Toast.LENGTH_LONG).show();
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
