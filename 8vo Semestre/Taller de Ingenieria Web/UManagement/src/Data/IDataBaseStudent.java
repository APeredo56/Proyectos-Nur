package Data;

import Model.Enums.StudentType;
import Model.Student;

import java.util.List;


public interface IDataBaseStudent extends IDataBase<Student>{
    List<Student> getByType(StudentType studentType);

}
