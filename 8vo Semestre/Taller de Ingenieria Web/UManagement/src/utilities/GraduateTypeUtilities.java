package utilities;

import Model.Enums.GraduateType;

public class GraduateTypeUtilities {

    public static GraduateType getGraduateTypeForDisplay(int graduateTypeId){
        return switch (graduateTypeId) {
            case 1 -> GraduateType.MASTERS_DEGREE;
            case 2 -> GraduateType.DOCTORATE;
            case 3 -> GraduateType.DIPLOMA;
            default -> null;
        };
    }

    public static int getGraduateTypeForSave(GraduateType graduateType){
        return switch (graduateType) {
            case MASTERS_DEGREE -> 1;
            case DOCTORATE -> 2;
            case DIPLOMA -> 3;
        };
    }
}
