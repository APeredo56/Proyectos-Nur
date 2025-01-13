package Services.Interfaces;

import Model.Grade;

import java.util.List;

public interface IGradeCalculationService {
    double calculateFinalGrade(List<Grade> grades);
}
