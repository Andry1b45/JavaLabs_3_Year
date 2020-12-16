package dto;

import java.util.UUID;

public class AppliedStudentDto {
    private UUID studentID;
    private int facultyID;
    private int budget;

    public AppliedStudentDto(UUID studentID, int facultyID, int budget) {
        this.studentID = studentID;
        this.facultyID = facultyID;
        this.budget = budget;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public int getBudget() {
        return budget;
    }
}
