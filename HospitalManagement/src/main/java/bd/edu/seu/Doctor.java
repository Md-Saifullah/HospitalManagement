package bd.edu.seu;

import java.time.LocalDate;

public class Doctor {
    private String name;
    private int id;
    private String specialAt;
    private String wardName;
    private LocalDate joinedAt;

    public Doctor() {
    }

    public Doctor(String name, int id, String specialAt, String wardName, LocalDate joinedAt) {
        this.name = name;
        this.id = id;
        this.specialAt = specialAt;
        this.wardName = wardName;
        this.joinedAt = joinedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialAt() {
        return specialAt;
    }

    public void setSpecialAt(String specialAt) {
        this.specialAt = specialAt;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    }
}
