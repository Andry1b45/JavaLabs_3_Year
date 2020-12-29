package entity;

import dto.RegisterDto;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String full_name;
    private String email;
    private String password;
    private Boolean blocked;
    private String role;
    private String city;
    private String region;
    private String school;
    private int attestat_id;
    private int exams_id;

    public User(UUID id, String username, String full_name, String email, String password, Boolean blocked,
                String role, String city, String region, String school, int attestat_id, int exams_id) {
        this.id = id;
        this.username = username;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.role = role;
        this.city = city;
        this.region = region;
        this.school = school;
        this.attestat_id = attestat_id;
        this.exams_id = exams_id;
    }

    public User(UUID id, String username, String full_name, String email, String password, boolean blocked, int role,
                String city, String region, String school, int attestat_id, int exams_id) {
        this.id = id;
        this.username = username;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.role = convertRole(role);
        this.city = city;
        this.region = region;
        this.school = school;
        this.attestat_id = attestat_id;
        this.exams_id = exams_id;
    }

    public User(UUID id, String username, String full_name, String email, boolean blocked, int role){
        this.id = id;
        this.username = username;
        this.full_name = full_name;
        this.email = email;
        this.blocked = blocked;
        this.role = String.valueOf(role);
    }

    public User(Boolean blocked){
        this.blocked = blocked;
    }


    public static User fromRegisterData(RegisterDto data) {
        return new User(
                data.getId(),
                data.getUsername(),
                data.getFull_name(),
                data.getEmail(),
                data.getPassword(),
                data.getBlocked(),
                data.getRole(),
                data.getCity(),
                data.getRegion(),
                data.getSchool(),
                data.getAttestat_id(),
                data.getExams_id()
        );
    }

    public String convertRole (int Role){
        String result;
        switch (Role){
            case (1):{
                result = "STUDENT";
            break;}
            case (2):{
                result = "ADMIN";
                break;}
            default:
                result = null;
                break;
        }
        return result;
    }

    public String getConvertedBlocked(){
        if(blocked.equals(true)) return "Blocked";
        else return "Not blocked";
    }

    public String getConvertedRole(){
        String result = "";
        if(role.equals(1) | role.equals(("1")))
            result = "Student";
        else if(role.equals(2) | role.equals("2"))
            result = "Admin";
        return result;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public String getRole() {
        return role;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getSchool() {
        return school;
    }

    public int getAttestat_id() {
        return attestat_id;
    }

    public int getExams_id() {
        return exams_id;
    }
}
