package dto;

import java.util.UUID;

public class ApplicationDto {
    private int facultyID;
    private UUID studentID;
    private String studentEmail;
    private int mathGrade;
    private int ukrainianGrade;
    private int englishGrade;
    private int historyGrade;

    private String facultyName;
    private String studentUsername;

    public ApplicationDto(int facultyID, UUID studentID, int mathGrade, int ukrainianGrade, int englishGrade, int historyGrade) {
        this.facultyID = facultyID;
        this.studentID = studentID;
        this.mathGrade = mathGrade;
        this.ukrainianGrade = ukrainianGrade;
        this.englishGrade = englishGrade;
        this.historyGrade = historyGrade;
    }

    public ApplicationDto(String facultyID, UUID studentID, String mathGrade, String ukrainianGrade, String englishGrade, String historyGrade) {
        this.facultyID = Integer.parseInt(facultyID);
        this.studentID = studentID;
        this.mathGrade = Integer.parseInt(mathGrade);
        this.ukrainianGrade = Integer.parseInt(ukrainianGrade);
        this.englishGrade = Integer.parseInt(englishGrade);
        this.historyGrade = Integer.parseInt(historyGrade);
    }

    public ApplicationDto(int facultyID, String studentEmail, String mathGrade, String ukrainianGrade, String englishGrade, String historyGrade) {
        this.facultyID = facultyID;
        this.studentEmail = studentEmail;
        this.mathGrade = Integer.parseInt(mathGrade);
        this.ukrainianGrade = Integer.parseInt(ukrainianGrade);
        this.englishGrade = Integer.parseInt(englishGrade);
        this.historyGrade = Integer.parseInt(historyGrade);
    }

    public ApplicationDto(String facultyName, String studentUsername, String mathGrade, String ukrainianGrade, String englishGrade, String historyGrade) {
        this.facultyName = facultyName;
        this.studentUsername = studentUsername;
        this.mathGrade = Integer.parseInt(mathGrade);
        this.ukrainianGrade = Integer.parseInt(ukrainianGrade);
        this.englishGrade = Integer.parseInt(englishGrade);
        this.historyGrade = Integer.parseInt(historyGrade);
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

    public String getStudentEmail() {
        return studentEmail;
    }
}
