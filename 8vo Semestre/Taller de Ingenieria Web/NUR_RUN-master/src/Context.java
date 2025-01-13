import Data.ManagerData;
import Services.EstudianteIntercambioService;
import Services.EstudianteService;

public class Context {
    private ManagerData managerData;

    private EstudianteService estudianteService;
    private EstudianteIntercambioService estudianteIntercambioService;


    public Context(ManagerData managerData) {
        this.managerData = managerData;
        // Estudiante
        estudianteService = new EstudianteService(this.managerData);
        estudianteIntercambioService = new EstudianteIntercambioService(this.managerData);

    }

    public ManagerData getManagerData() {
        return managerData;
    }

    public EstudianteService getEstudianteService() {
        return estudianteService;
    }

    public EstudianteIntercambioService getEstudianteIntercambioService() {
        return estudianteIntercambioService;
    }

}
