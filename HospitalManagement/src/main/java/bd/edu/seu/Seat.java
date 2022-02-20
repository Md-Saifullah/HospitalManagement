package bd.edu.seu;

public class Seat {
    private int seatNumber;
    private int roomNumber;
    private String wardName;

    public Seat() {}

    public Seat(int seatNumber, int roomNumber, String wardName) {
        this.seatNumber = seatNumber;
        this.roomNumber = roomNumber;
        this.wardName = wardName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
