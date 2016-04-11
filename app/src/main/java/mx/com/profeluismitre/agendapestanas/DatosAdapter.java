package mx.com.profeluismitre.agendapestanas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by LuisMitre on 04/04/2016.
 */
public class DatosAdapter extends ArrayAdapter<Datos> {

    public DatosAdapter(Context context) {
        super(context, R.layout.elementos_lista, R.id.txtApellidosConsultas);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
       // TextView lblNombre = (TextView) view.findViewById(R.id.txtNombreConsultas);
        TextView lblApellido= (TextView) view.findViewById(R.id.txtApellidosConsultas);
        TextView lblTelefono=(TextView) view.findViewById(R.id.txtTelConsultas);
       // TextView lblCorreo=(TextView) view.findViewById(R.id.txtCorreoConsultas);
       // TextView lbledad=(TextView) view.findViewById(R.id.txtEdadConsultas);

        Datos datos = getItem(position);

        //lblNombre.setText(datos.getNombre());
        lblApellido.setText(datos.getApellido());
        lblTelefono.setText(datos.getTelefono());
        //lblCorreo.setText(datos.getCorreo());
        //lbledad.setText(datos.getEdad());
        return view;
    }
}
