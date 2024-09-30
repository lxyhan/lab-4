package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;
import org.json.JSONException;

import java.util.List;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        float sum = 0;
        int count = 0;

        // Get the team from the grade database
        final Team team = gradeDataBase.getMyTeam();

        // Loop through each team member
        for (String member : team.getMembers()) {
            // Retrieve the grade for the course for each member
            Grade grade = gradeDataBase.getGrade(member, course);

            // If the grade is valid, add it to the sum and increase the count
            if (grade != null) {
                sum += grade.getGrade();
                count++;
            }
        }

        // If no grades were found, return 0
        if (count == 0) {
            return 0;
        }

        // Return the average grade
        return sum / count;
    }

}
