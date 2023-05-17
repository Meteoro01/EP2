public class usuario {
    private int IDUsuario;
    private String nombres;
    private String fecha_nacimiento;
    private String correo;
    private String clave;

    public usuario(int idUsuario, String nombres, String fnacimiento, String correo, String clave) {
        this.IDUsuario = idUsuario;
        this.nombres = nombres;
        this.fecha_nacimiento = fnacimiento;
        this.correo = correo;
        this.clave = clave;
    }

    // Métodos getter y setter


    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
