public class Usuario {
    private String nombre;
    private boolean estado;
    private String cuenta;
    private String contraseña;

    public Usuario (String nombre, String usuario, String contraseña){
        this.nombre = nombre;
        this.cuenta = usuario;
        this.contraseña = contraseña;
    }
    public void autenticar (String usuario, String contraseña){
        if (!this.cuenta.equals(usuario) && !this.contraseña.equals(contraseña)){
            estado = false;
        } else {
            estado = true;
        }
    }
    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCuenta() {
        return cuenta;
    }

}
