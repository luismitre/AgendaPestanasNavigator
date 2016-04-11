package mx.com.profeluismitre.agendapestanas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetallesActivity extends AppCompatActivity {

    TextView txtNombre;
    TextView txtApellido;
    TextView txtEdad;
    TextView txtTelefono;
    TextView txtCorreo;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtNombre=(TextView)findViewById(R.id.txtNombreDetalles);
        txtApellido=(TextView)findViewById(R.id.txtApellidosDetalles);
        txtEdad=(TextView)findViewById(R.id.txtEdadDetalles);
        txtTelefono=(TextView)findViewById(R.id.txtTelDetalles);
        txtCorreo=(TextView)findViewById(R.id.txtCorreoDetalles);
        btnRegresar=(Button)findViewById(R.id.btnRegresarDetalles);

        Bundle paquete=getIntent().getExtras();
        txtNombre.setText(paquete.getString("Nombre"));
        txtApellido.setText(paquete.getString("Apellido"));
        txtEdad.setText(paquete.getString("Edad"));
        txtTelefono.setText(paquete.getString("Telefono"));
        txtCorreo.setText(paquete.getString("Correo"));


    }
    public void onStart(){
        super.onStart();
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
