public class Main {


    public static void main(String[] args) {
        System.out.println(convertir(516111181));
        System.out.println(convertir(5161111));
        System.out.println(convertir(-516111181));
        System.out.println(convertir(-461618468));
        System.out.println(convertir(0));
    }

    private static int convertir(int n){
        return n & 0x7FFFFFFF;
    }
}
