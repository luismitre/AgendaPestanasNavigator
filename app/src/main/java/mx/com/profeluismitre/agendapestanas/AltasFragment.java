package mx.com.profeluismitre.agendapestanas;

import android.content.ContentValues;
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


public class AltasFragment extends Fragment {
    Button btnGrabar;
    Button btnCancelar;
    EditText txtNombre;
    EditText txtApellido;
    EditText txtEdad;
    EditText txtTelefono;
    EditText txtCorreo;
    MiBaseDatos basedatos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_altas, container, false);
    }

    public void onStart(){
        super.onStart();
        btnGrabar=(Button)getView().findViewById(R.id.btnGrabarAltas);
        btnCancelar=(Button)getView().findViewById(R.id.btnCancelarAltas);
        txtNombre=(EditText)getView().findViewById(R.id.txtNombreAltas);
        txtApellido=(EditText)getView().findViewById(R.id.txtApellidoAltas);
        txtEdad=(EditText)getView().findViewById(R.id.txtEdadAltas);
        txtTelefono=(EditText)getView().findViewById(R.id.txtTelAltas);
        txtCorreo=(EditText)getView().findViewById(R.id.txtCorreoAltas);

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            guardarContacto();
            }
        });
    }
    public void guardarContacto()
    {
        //Se inicializa la clase.
       basedatos = new MiBaseDatos(getContext());
        //Clase que permite llamar a los métodos para crear, eliminar, leer y actualizar registros. Se establecen permisos de escritura.
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String correo = txtCorreo.getText().toString();
        String edad = txtEdad.getText().toString();


        ContentValues content = new ContentValues();

        if(nombre.equals("") || apellido.equals("")
                || edad.equals("")|| telefono.equals("")
                || correo.equals(""))
        {
            Toast.makeText(getContext(), "Revise los datos introducidos. Todos los campos son obligatorios.", Toast.LENGTH_LONG).show();
        }else
        {
            //Se añaden los valores introducidos de cada campo mediante clave(columna)/valor(valor introducido en el campo de texto)
            content.put(Estructura.COLUMN_NAME_NOMBRE,nombre);
            content.put(Estructura.COLUMN_NAME_APELLIDO,apellido);
            content.put(Estructura.COLUMN_NAME_EDAD,edad);
            content.put(Estructura.COLUMN_NAME_TELEFONO,telefono);
            content.put(Estructura.COLUMN_NAME_CORREO,correo);
            sqlite.insert(Estructura.TABLE_NAME, null, content);
            Toast.makeText(getContext(), "Se grabo con Exito", Toast.LENGTH_LONG).show();
            txtNombre.setText("");
            txtApellido.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");
            txtEdad.setText("");
        }
        //Se cierra la conexión abierta a la Base de Datos
        sqlite.close();
    }
}
