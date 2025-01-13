package Services;

import Data.IDataBaseEnrollment;
import Data.DataManager;
import Model.Enrollment;

import java.util.List;

public class EnrollmentService extends BaseService implements IDataBaseEnrollment {


    public EnrollmentService(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Enrollment getByCode(String code) {
        return super.dataManager.getDataEnrollment().getByCode(code);
    }

    @Override
    public void save(Enrollment enrollment) {
        dataManager.getDataEnrollment().save(enrollment);
    }

    @Override
    public List<Enrollment> getAll() {
        return super.dataManager.getDataEnrollment().getAll();
    }

    @Override
    public List<Enrollment> getByStudent(String studentCi) {
        return super.dataManager.getDataEnrollment().getByStudent(studentCi);
    }

    @Override
    public List<Enrollment> getBySubject(String subjectCode) {
        return super.dataManager.getDataEnrollment().getBySubject(subjectCode);
    }
}
