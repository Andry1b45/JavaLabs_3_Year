package dto;

import java.util.UUID;

public class ApplicationDto {
    private int facultyID;
    private UUID studentID;
    private int mathGrade;
    private int ukrainianGrade;
    private int englishGrade;
    private int historyGrade;


    public ApplicationDto(int facultyID, UUID studentID, int mathGrade, int ukrainianGrade, int englishGrade, int historyGrade) {
        this.facultyID = facultyID;
        this.studentID = studentID;
        this.mathGrade = mathGrade;
        this.ukrainianGrade = ukrainianGrade;
        this.englishGrade = englishGrade;
        this.historyGrade = historyGrade;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public int getMathGrade() {
        return mathGrade;
    }

    public int getUkrainianGrade() {
        return ukrainianGrade;
    }

    public int getEnglishGrade() {
        return englishGrade;
    }

    public int getHistoryGrade() {
        return historyGrade;
    }
}
