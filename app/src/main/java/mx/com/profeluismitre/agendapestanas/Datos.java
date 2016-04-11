package mx.com.profeluismitre.agendapestanas;

/**
 * Created by LuisMitre on 04/04/2016.
 */
public class Datos {
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String edad;

    public Datos(String nombre, String apellido, String edad, String telefono,
                 String correo) {
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
        this.correo=correo;
        this.edad=edad;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setApellido(String apellido){
        this.apellido=apellido;
    }
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    public void setEdad(String edad){
        this.edad=edad;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getCorreo(){
        return correo;
    }
    public String getEdad(){
        return edad;
    }
}
