package Data;

import Data.Cache.*;
import Data.Mysql.*;

public class DataManager {

    private IDataBaseSubject dataSubject;
    private IDataBaseStudent dataStudent;
    private IDataBaseTeacher dataTeacher;
    private IDataBaseGrade dataGrade;
    private IDataBaseEnrollment dataEnrollment;
    private IDataBaseStudentGraduateType dataStudentGraduateType;

    public DataManager(String type) {
        if(type.equals("cache")){
            dataSubject = new CacheSubjectImp();
            dataStudent = new CacheStudentImp();
            dataTeacher = new CacheTeacherImp();
            dataGrade = new CacheGradeImp();
            dataEnrollment = new CacheEnrollmentImp();
            dataStudentGraduateType = new CacheStudentGraduateTypeImp();
        }else if(type.equals("mysql")) {
            dataSubject = new MysqlSubjectImp();
            dataStudent = new MysqlStudentImp();
            dataTeacher = new MysqlTeacherImp();
            dataGrade = new MysqlGradeImp();
            dataEnrollment = new MysqlEnrollmentImp();
            dataStudentGraduateType = new MysqlStudentGraduateTypeImp();
        }
    }


    public IDataBaseSubject getDataSubject() {
        return dataSubject;
    }

    public IDataBaseStudent getDataStudent() {
        return dataStudent;
    }

    public IDataBaseTeacher getDataTeacher() {
        return dataTeacher;
    }

    public IDataBaseGrade getDataGrade() {
        return dataGrade;
    }

    public IDataBaseEnrollment getDataEnrollment() {
        return dataEnrollment;
    }

    public IDataBaseStudentGraduateType getDataStudentGraduateType() {
        return dataStudentGraduateType;
    }
}
