package Services;

import Data.ManagerData;
import Interface.IEstudianteService;
import Interface.ServiceBase;
import Model.Estudiante;

import java.util.List;

public class EstudianteIntercambioService extends ServiceBase implements IEstudianteService {


    public EstudianteIntercambioService(ManagerData managerData) {
        super(managerData);
    }

    @Override
    public Estudiante getByCode(String code) {
        return managerData.getDataEstudiante().getByCode(code);
    }

    @Override
    public List<Estudiante> get() {
        return managerData.getDataEstudiante().getByTipo(Estudiante.TIPO_INTERCAMBIO);
    }

    @Override
    public void save(Estudiante estudiante) {
        estudiante.setTipo(Estudiante.TIPO_INTERCAMBIO);
        managerData.getDataEstudiante().save(estudiante);
    }
}
