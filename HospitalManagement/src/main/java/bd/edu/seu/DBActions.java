package bd.edu.seu;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBActions {
    public DBActions() {}

    public Connection getConnection(){
        System.out.println("getConnection");
        Connection connection = null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost/hospital_management","root","Incorrect");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public boolean releasePatient(String id) {
        System.out.println("releasePatient");
        boolean isReleased=false;
        String query = String.format("select * from patient where id='%s'and status='Admitted';", id);
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            if ((resultSet.next())){
                String seatNo=resultSet.getString("seat_no");
                String query1=String.format("update seat set available=1 where seat_no='%s'",seatNo);
                String query2=String.format("update patient set status='Released',release_at='%s' where id=%s",LocalDate.now(),id);

                statement.executeUpdate(query1);
                statement.executeUpdate(query2);
                isReleased=true;
                }
        } catch (SQLException e) {
            System.out.println("isReleased error");
            e.printStackTrace();
        }
        return isReleased;
    }


    public boolean isEmployee(String id, String password) {

        System.out.println("isEmployee");
        boolean isEmployee=false;
        Connection connection = getConnection();
        String query = String.format("select password from Employee where id=%s;", id);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    isEmployee= true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isEmployee;
    }



    public boolean updatePassword(String id, String newPassword) {
        boolean isUpdated=false;
        System.out.println("updateTable");
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("update Employee set password='%s' where id=%s;", newPassword, id);
            statement.executeUpdate(query);
            isUpdated=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUpdated;
    }





    public ArrayList<Patient> getPatient(String key, String value) {
        ArrayList<Patient>patients = new ArrayList<>();
        String query=String.format("select * from patient,seat where patient.seat_no=seat.seat_no and patient.%s='%s';",key,value);
        return getPatients(patients, query);
    }
    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient>patients = new ArrayList<>();
        String query="select * from patient,seat where patient.seat_no=seat.seat_no;";
        return getPatients(patients, query);
    }

    private ArrayList<Patient> getPatients(ArrayList<Patient> patients, String query) {
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                String name=resultSet.getString("name");
                int id=resultSet.getInt("id");
                LocalDate admitDate= LocalDate.parse(resultSet.getString("admitted_at"));
                LocalDate releaseDate=null;
                String date=resultSet.getString("release_at");
                if (date != null){
                    releaseDate= LocalDate.parse(date);
                }
                String wardName=resultSet.getString("ward_name");
                int roomNo=resultSet.getInt("seat_no");
                int seatNo=resultSet.getInt("room_no");
                String status= resultSet.getString("status");

                Patient patient=new Patient(name,
                        id,
                        admitDate,
                        releaseDate,
                        wardName,
                        roomNo,
                        seatNo,
                        status);
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public ArrayList<Doctor> getDoctor(String key, String value) {
        System.out.println("getDoctor");
        ArrayList<Doctor>doctors = new ArrayList<>();
        String query= String.format("select * from doctor where %s='%s';",
                key,
                value);

        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                String name=resultSet.getString("name");
                int id=resultSet.getInt("id");
                String wardName=resultSet.getString("ward_name");
                LocalDate joinedAt= LocalDate.parse(resultSet.getString("joined_at"));
                String specialAt=resultSet.getString("special_at");
                Doctor doctor=new Doctor(name,id,specialAt,wardName,joinedAt);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public ArrayList<Doctor> getAllDoctors() {
        System.out.println("getAllDoctors");
        ArrayList<Doctor>doctors = new ArrayList<>();
        String query="select * from doctor;";
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                String name=resultSet.getString("name");
                int id=resultSet.getInt("id");
                String wardName=resultSet.getString("ward_name");
                LocalDate joinedAt= LocalDate.parse(resultSet.getString("joined_at"));
                String specialAt=resultSet.getString("special_at");
                Doctor doctor=new Doctor(name,id,specialAt,wardName,joinedAt);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }



    public ArrayList<String> getAllWardName(){
        System.out.println("getAllWard");
        ArrayList<String>wards=new ArrayList<>();
        String query="select * from ward;";
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                wards.add(resultSet.getString("ward_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wards;
    }
    public ArrayList<Seat> getAllSeats() {
        ArrayList<Seat>seats = new ArrayList<>();
        System.out.println("getAllSeats");
        String query="select * from seat where available=1;";
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                int seatNo=resultSet.getInt("seat_no");
                String wardName=resultSet.getString("ward_name");
                int roomNo=resultSet.getInt("room_no");
                Seat seat =new Seat(seatNo,roomNo,wardName);
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }
    public ArrayList<Seat> getSeat(String value) {
        System.out.println("getSeat");
        ArrayList<Seat>seats = new ArrayList<>();
        String query=String.format("select * from seat where available=1 and ward_name='%s';",value);
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                int seatNo=resultSet.getInt("seat_no");
                String wardName=resultSet.getString("ward_name");
                int roomNo=resultSet.getInt("room_no");
                Seat seat =new Seat(seatNo,roomNo,wardName);
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }
    public int admitPatient(Patient patient){
        int isAdmitted=-1;
        String query1= String.format("insert into patient (name,admitted_at,status,seat_no) values('%s','%s','%s','%d')",
                patient.getName(),
                patient.getAdmitDate(),
                patient.getStatus(),
                patient.getSeatNumber());

        String query2= String.format("update seat set available=0 where seat_no=%d", patient.getSeatNumber());

        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            ResultSet resultSet=statement.executeQuery("select max(id) from patient;");
            if(resultSet.next()) {
                isAdmitted = resultSet.getInt("max(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdmitted;
    }
    public int addEmployee(String name,String password){
        System.out.println("addEmployee");
        int isAdded=-1;
        String query= String.format("insert into employee (name,password) values('%s','%s');", name, password);
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
            ResultSet resultSet=statement.executeQuery("select max(id) from employee;");
            if(resultSet.next()){
                isAdded=resultSet.getInt("max(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdded;
    }
    public int addDoctor(Doctor doctor){
        System.out.println("addDoctor");
        int isAdded=-1;
        String query=String.format("insert into doctor (name,special_at,joined_at,ward_name) values('%s','%s','%s','%s')",
                doctor.getName(),
                doctor.getSpecialAt(),
                doctor.getJoinedAt(),
                doctor.getWardName());
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
            ResultSet resultSet=statement.executeQuery("select max(id) from doctor;");
            if(resultSet.next()){
                isAdded=resultSet.getInt("max(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdded;
    }
}
