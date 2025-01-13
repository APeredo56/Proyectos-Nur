package Services.GradeCalculation;

import Model.Grade;
import Services.Interfaces.IGradeCalculationService;

import java.util.List;

public class AverageGradeCalculationService implements IGradeCalculationService {
    @Override
    public double calculateFinalGrade(List<Grade> grades) {
        return grades.stream().mapToDouble(Grade::getGrade).average().orElse(0);
    }
}
