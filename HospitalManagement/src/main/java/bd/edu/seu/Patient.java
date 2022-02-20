package bd.edu.seu;

import java.time.LocalDate;

public class Patient {
    private String name;
    private int id;
    private LocalDate admitDate;
    private LocalDate releasedDate;
    private String wardName;
    private int roomNumber;
    private int seatNumber;
    private String status;

    public Patient() {
    }

    public Patient(String name,
                   int id,
                   LocalDate admitDate,
                   LocalDate releasedDate,
                   String wardName,
                   int roomNumber,
                   int seatNumber,
                   String status) {
        this.name = name;
        this.id = id;
        this.admitDate = admitDate;
        this.releasedDate = releasedDate;
        this.wardName = wardName;
        this.roomNumber = roomNumber;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDate getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(LocalDate admitDate) {
        this.admitDate = admitDate;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
