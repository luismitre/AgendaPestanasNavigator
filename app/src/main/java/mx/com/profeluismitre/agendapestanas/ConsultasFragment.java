package mx.com.profeluismitre.agendapestanas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import mx.com.profeluismitre.agendapestanas.EstructuraDatos.Estructura;


public class ConsultasFragment extends Fragment {
    MiBaseDatos basedatos;
    ListView lista;
    DatosAdapter adaptador;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_consultas, container, false);

    }

    public void onStart(){
        super.onStart();
        lista=(ListView)getView().findViewById(R.id.listaConsultas);

        adaptador=new DatosAdapter(getContext());
        buscar();
        adaptador.notifyDataSetChanged();
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle paquete=new Bundle();
                Datos contacto=(Datos) lista.getItemAtPosition(position);
                paquete.putString("Nombre",contacto.getNombre());
                paquete.putString("Apellido",contacto.getApellido());
                paquete.putString("Edad",contacto.getEdad());
                paquete.putString("Telefono",contacto.getTelefono());
                paquete.putString("Correo",contacto.getCorreo());
                Intent miIntento=new Intent(getContext(),DetallesActivity.class);
                miIntento.putExtras(paquete);
                startActivity(miIntento);
            }
        });
    }
    public void buscar()
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
                Estructura.COLUMN_NAME_TELEFONO,
                Estructura.COLUMN_NAME_CORREO
        };


        //Ejecuta la sentencia devolviendo los resultados de los parámetros pasados de tabla, columnas, producto y orden de los resultados.
        Cursor cursor = sqlite.query(Estructura.TABLE_NAME, columnas, null,null , null, null, null);

            if(cursor.getCount() != 0)
            {
                cursor.moveToFirst();
                int x=0;
                while(cursor.moveToNext()){
                    adaptador.add(new Datos(cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_NOMBRE)),
                   cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_APELLIDO)),
                   cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_EDAD)),
                   cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_TELEFONO)),
                   cursor.getString(cursor.getColumnIndex(Estructura.COLUMN_NAME_CORREO))));
                x++;
                }


            }
            else
            {
                Toast.makeText(getContext(), "Esta Vacia la Base de DAtos", Toast.LENGTH_LONG).show();
            }


        //Se cierra la conexión abierta a la Base de Datos
        sqlite.close();

    }
}
