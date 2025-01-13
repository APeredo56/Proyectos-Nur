package Data;

import Model.StudentGraduateType;

import java.util.List;

public interface IDataBaseStudentGraduateType extends IDataBase<StudentGraduateType> {
    StudentGraduateType getByStudent (String studentCi);
}
