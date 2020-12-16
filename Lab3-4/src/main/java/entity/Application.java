package entity;

import dto.ApplicationDto;

import java.util.UUID;

public class Application {
    private int facultyID;
    private UUID studentID;
    private int mathGrade;
    private int ukrainianGrade;
    private int englishGrade;
    private int historyGrade;

    public Application(int facultyID, UUID studentID, int mathGrade, int ukrainianGrade, int englishGrade, int historyGrade) {
        this.facultyID = facultyID;
        this.studentID = studentID;
        this.mathGrade = mathGrade;
        this.ukrainianGrade = ukrainianGrade;
        this.englishGrade = englishGrade;
        this.historyGrade = historyGrade;
    }

    public static Application fromApplicationData(ApplicationDto data){
        return new Application(
                data.getFacultyID(),
                data.getStudentID(),
                data.getMathGrade(),
                data.getUkrainianGrade(),
                data.getEnglishGrade(),
                data.getHistoryGrade()
        );
    }

    @Override
    public String toString() {
        return "facultyID: " + facultyID + ", studentID: " + studentID + ", mathGrade: " + mathGrade +
                ", ukrainianGrade: " + ukrainianGrade + ", englishGrade: " + englishGrade +
                ", historyGrade: " + historyGrade;
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
