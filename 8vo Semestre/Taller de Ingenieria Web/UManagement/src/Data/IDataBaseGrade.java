package Data;

import Model.Grade;

import java.util.List;

public interface IDataBaseGrade extends IDataBase<Grade> {
    List<Grade> getByStudentAndSubject(String studentCi, String subjectCode);
}
