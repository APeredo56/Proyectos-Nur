package Services.GradeCalculation;

import Model.Grade;
import Services.Interfaces.IGradeCalculationService;

import java.util.List;

public class WeightedGradeCalculationService implements IGradeCalculationService {
    @Override
    public double calculateFinalGrade(List<Grade> grades) {
        return grades.stream()
                .mapToDouble(grade -> grade.getGrade() * grade.getWeight())
                .sum();
    }
}
