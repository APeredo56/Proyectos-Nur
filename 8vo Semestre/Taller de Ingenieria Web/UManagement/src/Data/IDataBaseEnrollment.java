package Data;

import Model.Enrollment;

import java.util.List;

public interface IDataBaseEnrollment extends IDataBase<Enrollment> {
    List<Enrollment> getByStudent (String studentCi);
    List<Enrollment> getBySubject (String subjectCode);
}
