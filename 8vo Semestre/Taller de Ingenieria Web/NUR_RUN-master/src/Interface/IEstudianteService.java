package Interface;

import Model.Estudiante;

import java.util.List;

public interface IEstudianteService {

    public Estudiante getByCode(String code);
    public List<Estudiante> get();
    public void save(Estudiante est);

}
