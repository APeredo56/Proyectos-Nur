public class Consola {
    public static void main(String[] args) {
        Botella cincoLitros = new Botella(5);
        Botella tresLitros = new Botella(3);
        System.out.println("Llenar la botella de 5 litros");
        cincoLitros.llenar();
        cincoLitros.verBotella();
        tresLitros.verBotella();
        System.out.println("Vaciar la botella de 5 litros en la de 3 litros");
        cincoLitros.vaciar(tresLitros);
        cincoLitros.verBotella();
        tresLitros.verBotella();
        System.out.println("Vaciar la botella de 3 litros");
        tresLitros.vaciar();
        cincoLitros.verBotella();
        tresLitros.verBotella();
        System.out.println("Vaciar la botella de 5 litros en la de 3 litros");
        cincoLitros.vaciar(tresLitros);
        cincoLitros.verBotella();
        tresLitros.verBotella();
        System.out.println("LLenar la botella de 5 litros");
        cincoLitros.llenar();
        cincoLitros.verBotella();
        tresLitros.verBotella();
        System.out.println("Vaciar la botella de 5 litros en la de 3 litros");
        cincoLitros.vaciar(tresLitros);
        cincoLitros.verBotella();
        tresLitros.verBotella();


    }
}
