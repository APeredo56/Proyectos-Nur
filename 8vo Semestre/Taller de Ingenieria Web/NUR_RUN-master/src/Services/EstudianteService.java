package Services;

import Data.ManagerData;
import Interface.IEstudianteService;
import Interface.ServiceBase;
import Model.Estudiante;

import java.util.List;

public class EstudianteService extends ServiceBase implements IEstudianteService{


    public EstudianteService(ManagerData managerData) {
        super(managerData);
    }

    @Override
    public Estudiante getByCode(String code) {
        return super.managerData.getDataEstudiante().getByCode(code);
    }

    @Override
    public List<Estudiante> get() {
        return managerData.getDataEstudiante().getByTipo(Estudiante.TIPO_NORMAL);
    }

    @Override
    public void save(Estudiante estudiante) {
        estudiante.setTipo(Estudiante.TIPO_NORMAL);
        managerData.getDataEstudiante().save(estudiante);
    }
}
