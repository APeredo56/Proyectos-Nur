import Data.ManagerData;
import Model.Estudiante;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ManagerData managerData = new ManagerData("cache");
        Context ctx = new Context(managerData);

        // Guardado de estudiante normal
        Estudiante estNormal1 = new Estudiante("001", "Pedro", "Torrez", "78456555");
        Estudiante estNormal2 = new Estudiante("002", "Maria", "Eguez", "77369625");
        Estudiante estNormal3 = new Estudiante("003", "Jorge", "Mamani", "77787454");
        Estudiante estNormal4 = new Estudiante("004", "Esteban", "Suarez", "77256454");
        Estudiante estNormal5 = new Estudiante("005", "Ana", "Mercado", "77364645");

        ctx.getEstudianteService().save(estNormal1);
        ctx.getEstudianteService().save(estNormal2);
        ctx.getEstudianteService().save(estNormal3);
        ctx.getEstudianteService().save(estNormal4);
        ctx.getEstudianteService().save(estNormal5);



        System.out.println("Estudiantes normales");
        ctx.getEstudianteService().get().forEach(modelEst -> {
            System.out.println(modelEst.toString());
        });


        Estudiante est2 = new Estudiante("100", "Jose", "Torrez", "78456555");
        ctx.getEstudianteIntercambioService().save(est2);

        System.out.println("Estudiantes de intercambio");
        ctx.getEstudianteIntercambioService().get().forEach(intercambio -> {
            System.out.println(intercambio.toString());
        } );


    }
}