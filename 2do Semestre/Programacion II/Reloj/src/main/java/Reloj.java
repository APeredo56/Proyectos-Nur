public class Reloj {

    //Atributos
    //Acceso - Tipo Dato - nombre
    private int hora = 12;
    private int minutos;
    private int segundos;

    private boolean formato24 = true;
    private boolean alarma;

    private int alarma_hora = 12;
    private int alarma_minutos;
    private int alarma_segundos;

    private int año, mes, dia;

    //Métodos
    //Acceso - retorno - nombre ()

    //validar hora ingresada
    public void configurarHora(int hora, int minutos, int segundos) {
        this.hora = hora % 24;
        this.minutos = minutos % 60;
        this.segundos = segundos % 60;
    }

    public void configurarAlarma(int hora, int minutos, int segundos) {
        this.alarma_hora = hora % 24;
        this.alarma_minutos = minutos % 60;
        this.alarma_segundos = segundos % 60;
    }


    public void configurarFecha(int año, int mes, int dia) {
        this.año = año;
        this.mes = mes;
        this.dia = dia;
    }

    public String verHora() {
        // Corregir formato de hora
        String tiempo = "";
        //A
        if (formato24) {
            if (hora < 10) {
                tiempo += "0" + hora;
            } else {
                tiempo += hora;
            }
            //B
            tiempo += ":" + (minutos < 10 ? "0" + minutos : minutos);
            //C
            return tiempo + ":" + (segundos < 10 ? "0" + segundos : segundos);
        } else {
            String periodo;
            if (hora < 12){
                periodo = " AM";
                if (hora < 10) {
                    tiempo += "0" + hora;
                } else {
                    tiempo += hora;
                }
                if (hora == 0){
                    tiempo = "12";
                }
            } else {
                periodo = " PM";
                if (hora == 12){
                    tiempo += hora;
                } else {
                    hora -= 12;
                    if (hora < 10) {
                        tiempo += "0" + hora;
                    } else {
                        tiempo += hora;
                    }
                }
            }
            tiempo += ":" + (minutos < 10 ? "0" + minutos : minutos);
            tiempo += ":" + (segundos < 10 ? "0" + segundos : segundos) + periodo;
            //"formato 12"; // 05:00 AM | PM ||||| 12 = 12 pm | 0 = 12 am | <12 = am | >12 = pm
            return tiempo;
        }
    }

    public void cambiarFormato() {
        // este metodo debe cambiar el reloj de formato 24 a 12 horas o viceversa
        formato24 = !formato24;
    }

    public void activarAlarma() {
        alarma = true;
    }

    public void apagarAlarma() {
        alarma = false;
    }

    public String avanzar() {
        segundos++;
        if (segundos == 60) {
            minutos++;
            segundos = 0;
        }
        if (minutos == 60) {
            hora++;
            minutos = 0;
        }
        if (hora == 24) {
            hora = 0;
        }
        if (alarma && hora == alarma_hora && minutos == alarma_minutos && segundos == alarma_segundos){
            System.out.println("DIDIDIDIDIIIIIIIIIIIIN DIDIDIDIDIDIDIDINNNNN");
        }
            return verHora();
    }


}
