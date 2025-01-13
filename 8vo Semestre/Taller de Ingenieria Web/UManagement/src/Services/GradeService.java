package Services;

import Data.IDataBaseGrade;
import Data.DataManager;
import Model.Grade;

import java.util.List;

public class GradeService extends BaseService implements IDataBaseGrade {
    public GradeService(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public List<Grade> getAll() {
        return dataManager.getDataGrade().getAll();
    }

    @Override
    public Grade getByCode(String code) {
        return super.dataManager.getDataGrade().getByCode(code);
    }

    @Override
    public void save(Grade grade) {
        dataManager.getDataGrade().save(grade);
    }

    @Override
    public List<Grade> getByStudentAndSubject(String studentCi, String subjectCode) {
        return dataManager.getDataGrade().getByStudentAndSubject(studentCi, subjectCode);
    }
}
