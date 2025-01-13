import Data.DataManager;
import Services.*;
import Services.GradeCalculation.AverageGradeCalculationService;
import Services.GradeCalculation.WeightedGradeCalculationService;
import Services.Interfaces.IGradeCalculationService;

public class Context {
    private DataManager dataManager;

    private StudentService studentService;
    private TeacherService teacherService;
    private SubjectService subjectService;
    private GradeService gradeService;
    private EnrollmentService enrollmentService;
    private IGradeCalculationService averageCalcService;
    private IGradeCalculationService weightedCalcService;
    private StudentGraduateTypeService studentGraduateTypeService;



    public Context(DataManager dataManager) {
        this.dataManager = dataManager;
        studentService = new StudentService(this.dataManager);
        teacherService = new TeacherService(this.dataManager);
        subjectService = new SubjectService(this.dataManager);
        gradeService = new GradeService(this.dataManager);
        enrollmentService = new EnrollmentService(this.dataManager);
        averageCalcService = new AverageGradeCalculationService();
        weightedCalcService = new WeightedGradeCalculationService();
        studentGraduateTypeService = new StudentGraduateTypeService(this.dataManager);
    }

    public DataManager getManagerData() {
        return dataManager;
    }

    public StudentService getStudentService() {
        return studentService;
    }


    public TeacherService getTeacherService() {
        return teacherService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public GradeService getGradeService() {
        return gradeService;
    }

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }

    public IGradeCalculationService getAverageCalcService() {
        return averageCalcService;
    }

    public IGradeCalculationService getWeightedCalcService() {
        return weightedCalcService;
    }

    public StudentGraduateTypeService getStudentGraduateTypeService() {
        return studentGraduateTypeService;
    }
}
