public class Consola {
    public static void main(String[] args) {
        Metodos metodo = new Metodos();

        System.out.println(metodo.letrasEnPalabra("palabra", 'a'));
        System.out.println(metodo.eliminarPares("13246789643"));
        System.out.println(metodo.repetirVocales("HOlA mundo", 5));
    }
}
