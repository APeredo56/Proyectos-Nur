public class Numeros {
    private String conversion = "";
    private String numero;
    private int digito;

    private void setNumero(String numero) {
        numero = numero.trim();
        int longitud = numero.length();
        if (longitud == 4) {
            this.numero = numero;
        } else {
            this.numero = "0".repeat(4 - longitud) + numero;
        }
    }

    private void digitoMil (){
        digito = Integer.parseInt(numero.substring(0,1));
        conversion += "m".repeat(digito);
    }
    private void digitoCien () {
        digito = Integer.parseInt(numero.substring(1, 2));
        switch (digito){
            case 1:
            case 2:
            case 3:
                conversion += "c".repeat(digito);
                break;
            case 4:
                conversion += "cd";
                break;
            case 5:
                conversion += "d";
                break;
            case 6:
            case 7:
            case 8:
                conversion += "d" + "c".repeat(digito - 5);
                break;
            case 9:
                conversion += "cm";
                break;
        }
    }
    private void digitoDiez (){
        digito = Integer.parseInt(numero.substring(2, 3));
        switch (digito) {
            case 1:
            case 2:
            case 3:
                conversion += "x".repeat(digito);
                break;
            case 4:
                conversion += "xl";
                break;
            case 5:
                conversion += "l";
                break;
            case 6:
            case 7:
            case 8:
                conversion += "l" + "x".repeat(digito - 5);
                break;
            case 9:
                conversion += "xc";
                break;
        }
    }
    private void digitoUno (){
        digito = Integer.parseInt(numero.substring(3, 4));
        switch (digito){
            case 1:
            case 2:
            case 3:
                conversion += "i".repeat(digito) + "\n";
                break;
            case 4:
                conversion += "iv" + "\n";
                break;
            case 5:
                conversion += "v" + "\n";
                break;
            case 6:
            case 7:
            case 8:
                conversion += "v" + "i".repeat(digito - 5) + "\n";
                break;
            case 9:
                conversion += "ix" + "\n";
                break;
            case 0:
                conversion += "\n";
                break;
        }
    }
    public void convertirNumeros (String numeroEntero){
        setNumero(numeroEntero);
        digitoMil();
        digitoCien();
        digitoDiez();
        digitoUno();
    }
    public String getConversion() {
        return conversion;
    }
}
