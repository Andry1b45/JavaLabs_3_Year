package dto;

import java.util.UUID;

public class RegisterDto {
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


    public RegisterDto(String username, String full_name, String email, String password,
                       String role, String city, String region, String school, int attestat_id, int exams_id) {
        this.username = username;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.city = city;
        this.region = region;
        this.school = school;
        this.attestat_id = attestat_id;
        this.exams_id = exams_id;
    }

    public RegisterDto(String username, String full_name, String email, String password,
                       String role) {
        this.username = username;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.role = role;
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
