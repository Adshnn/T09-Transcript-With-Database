package academic.driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;


public class MySQLDatabase extends AbstractDatabase {
    public MySQLDatabase(String url, String username, String  password ) throws SQLException {
        super(url, username, password);
    }

    @Override
    protected void createTable() throws SQLException {
        // Buat tabel untuk entitas Lecturer
        String Lecturert = "CREATE TABLE IF NOT EXISTS Lecturer ("
                + "nidn VARCHAR(30) PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "initial VARCHAR(10) NOT NULL,"
                + "email VARCHAR(100) NOT NULL,"
                + "prodi VARCHAR(100) NOT NULL"
                + ");";

        // Buat tabel untuk entitas Course
        String Courset = "CREATE TABLE IF NOT EXISTS Course ("
                + "code VARCHAR(30) PRIMARY KEY,"
                + "matkul VARCHAR(100) NOT NULL,"
                + "credit INT NOT NULL,"
                + "passingGrade VARCHAR(4) NOT NULL"
                + ");";

        // Buat tabel untuk entitas Student
        String StudentT = "CREATE TABLE IF NOT EXISTS Student ("
                + "nim VARCHAR(20) PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "year INT NOT NULL,"
                + "prodi VARCHAR(100) NOT NULL"
                + ");";

        // Buat tabel untuk entitas Enrollment
        String EnrollmentT = "CREATE TABLE IF NOT EXISTS Enrollment ("
                + "code VARCHAR(20),"
                + "nim VARCHAR(20),"
                + "thauncourse VARCHAR(20),"
                + "semester VARCHAR(20),"
                + "grade VARCHAR(4),"
                + "lastgrade VARCHAR(4)"
                + ");";
        
        String enrollmentgrade = "CREATE TABLE IF NOT EXISTS EnrollmentGrade ("
                + "code VARCHAR(20),"
                + "nim VARCHAR(20),"
                + "thauncourse VARCHAR(20),"
                + "semester VARCHAR(20),"
                + "grade VARCHAR(4)"
                + ");";

        String enrollmentremedial = "CREATE TABLE IF NOT EXISTS EnrollmentRemedial ("
                + "code VARCHAR(20),"
                + "nim VARCHAR(20),"
                + "thauncourse VARCHAR(20),"
                + "semester VARCHAR(20),"
                + "lastgrade VARCHAR(4)"
                + ");";
        String courseopen = "CREATE TABLE IF NOT EXISTS CourseOpening ("
                + "code VARCHAR(20),"
                + "matkul VARCHAR(100),"
                + "credit INT,"
                + "passingGrade VARCHAR(4),"
                + "academicYear VARCHAR(20),"
                + "semester VARCHAR(20),"
                + "initial VARCHAR(20),"
                + "email VARCHAR(100)"
                + ");";



                Statement statement = getConnection().createStatement();
        statement.execute(Lecturert);
        statement.execute(Courset);
        statement.execute(StudentT);
        statement.execute(EnrollmentT);
        statement.execute(enrollmentgrade);
        statement.execute(enrollmentremedial);
        statement.execute(courseopen);

        statement.close();
    }

    protected void seedTable() throws SQLException {
        String clearup[] = {
            "DELETE FROM Lecturer",
            "DELETE FROM Course",
            "DELETE FROM Student",
            "DELETE FROM Enrollment",
            "DELETE FROM EnrollmentGrade",
            "DELETE FROM EnrollmentRemedial",
            //"DELETE FROM StudentDetails",
            "DELETE FROM CourseOpening"
        };

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            for (String cleanupQuery : clearup) {
                statement.execute(cleanupQuery);
            }
        // String[] lecturerData = {"", "", "", "", ""};
        // addLecturer(lecturerData);
    }
 }

 public void addLecturer(String[] inputSegments) throws SQLException {
    String Lecturer = "INSERT INTO Lecturer (nidn, name, initial, email, prodi) VALUES ('" +
                      inputSegments[1] + "', '" + // nidn
                      inputSegments[2] + "', '" + // name
                      inputSegments[3] + "', '" + // initial
                      inputSegments[4] + "', '" + // email
                      inputSegments[5] + "')";    // prodi

    try (Connection connection = DriverManager.getConnection(url, username, password);
         Statement statement = connection.createStatement()) {
        statement.executeUpdate(Lecturer);
        //System.out.println(inputSegments[1] + "|" + inputSegments[2] + "|" + inputSegments[3] + "|" + inputSegments[4] + "|" + inputSegments[5]);
    }
}
public void addCourse(String[] inputSegments) throws SQLException {
    String Course = "INSERT INTO Course (code, matkul, credit, passingGrade) VALUES ('" +
                    inputSegments[1] + "', '" + // code
                    inputSegments[2] + "', '" + // matkul
                    inputSegments[3] + "', '" + // credit
                    inputSegments[4] + "')";    // passingGrade

    try (Connection connection = DriverManager.getConnection(url, username, password);
         Statement statement = connection.createStatement()) {
        statement.executeUpdate(Course);
        //System.out.println(inputSegments[1] + "|" + inputSegments[2] + "|" + inputSegments[3] + "|" + inputSegments[4]);
    }
}   
public void addStudent(String[] inputSegments) throws SQLException {
    String Student = "INSERT INTO Student (nim, name, year, prodi) VALUES ('" +
                     inputSegments[1] + "', '" + // nim
                     inputSegments[2] + "', '" + // name
                     inputSegments[3] + "', '" + // year
                     inputSegments[4] + "')";    // prodi

    try (Connection connection = DriverManager.getConnection(url, username, password);
         Statement statement = connection.createStatement()) {
        statement.executeUpdate(Student);
        //System.out.println(inputSegments[1] + "|" + inputSegments[2] + "|" + inputSegments[3] + "|" + inputSegments[4]);
    }   
}
public void addEnrollment(String[] inputSegments) throws SQLException {
    String Enrollment = "INSERT INTO Enrollment (code, nim, thauncourse, semester, grade) VALUES ('" +
                        inputSegments[1] + "', '" + // code
                        inputSegments[2] + "', '" + // nim
                        inputSegments[3] + "', '" + // thauncourse
                        inputSegments[4] + "', '" + // semester
                        "None')"; // grade

    String updateGradeQuery = "UPDATE Enrollment e " +
                              "INNER JOIN EnrollmentGrade eg ON e.code = eg.code AND e.nim = eg.nim AND e.thauncourse = eg.thauncourse AND e.semester = eg.semester " +
                              "SET e.grade = eg.grade";

    String updateGradeBaruQuery = "UPDATE Enrollment e " +
                              "INNER JOIN EnrollmentRemedial er ON e.code = er.code AND e.nim = er.nim AND e.thauncourse = er.thauncourse AND e.semester = er.semester " +
                              "SET e.lastgrade = e.grade, e.grade = er.lastgrade";


    try (Connection connection = DriverManager.getConnection(url, username, password);
         Statement statement = connection.createStatement()) {
        // Menyisipkan data baru ke dalam tabel Enrollment
        statement.executeUpdate(Enrollment);

        // Memperbarui kolom grade di tabel Enrollment dari tabel EnrollmentGrade
        statement.executeUpdate(updateGradeQuery);

        // Memperbarui kolom gradelast di tabel Enrollment dari tabel EnrollmentRemedial
        statement.executeUpdate(updateGradeBaruQuery);
    }
}

public void addEnrollmentGrade(String[] inputSegments) throws SQLException {
    String EnrollmentGrade = "INSERT INTO EnrollmentGrade (code, nim, thauncourse, semester, grade) VALUES ('" +
                             inputSegments[1] + "', '" + // code
                             inputSegments[2] + "', '" + // nim
                             inputSegments[3] + "', '" + // thauncourse
                             inputSegments[4] + "', '" + // semester
                             inputSegments[5] + "')";    // grade

    try (Connection connection = DriverManager.getConnection(url, username, password);
         Statement statement = connection.createStatement()) {
        statement.executeUpdate(EnrollmentGrade);
    }
}
public void addEnrollmentRemedial(String[] inputSegments) throws SQLException {
    String EnrollmentRemedial = "INSERT INTO EnrollmentRemedial (code, nim, thauncourse, semester, lastgrade) VALUES ('" +
                                 inputSegments[1] + "', '" + // code
                                 inputSegments[2] + "', '" + // nim
                                 inputSegments[3] + "', '" + // thauncourse
                                 inputSegments[4] + "', '" + // semester
                                 //inputSegments[5] + "', '" + // grade
                                 inputSegments[5] + "')";    // gradebaru

    try (Connection connection = DriverManager.getConnection(url, username, password);
         Statement statement = connection.createStatement()) {
        statement.executeUpdate(EnrollmentRemedial);
    }
}
public void addCourseOpening(String[] inputSegments) throws SQLException {
    String CourseOpening = "INSERT INTO CourseOpening (code, matkul, credit, passingGrade, academicYear, semester, initial, email) VALUES ('" +
                           inputSegments[1] + "', '" + // code
                           inputSegments[2] + "', '" + // academicYear
                           inputSegments[3] + "', '" + // semester
                           inputSegments[4] + "', '" + // initial
                           inputSegments[5] + "', '" + // matkul
                           inputSegments[6] + "', '" + // credit
                           inputSegments[7] + "', '" + // email
                           inputSegments[8] + "')";    // passingGrade

    try (Connection connection = DriverManager.getConnection(url, username, password);
         Statement statement = connection.createStatement()) {
        statement.executeUpdate(CourseOpening);
    }
}

// public void addStudentDetails(String[] inputSegments) throws SQLException {
//     String studentNIM = inputSegments[1];

//     // Query untuk menambahkan data dari tabel Student ke tabel StudentDetails berdasarkan NIM
//     String addStudentDetailsQuery = "INSERT INTO StudentDetails (nim, name, year, prodi) " +
//                                      "SELECT nim, name, year, prodi " +
//                                      "FROM Student " +
//                                      "WHERE nim = '" + studentNIM + "'";

//     try (Connection connection = DriverManager.getConnection(url, username, password);
//          Statement statement = connection.createStatement()) {
//         statement.executeUpdate(addStudentDetailsQuery);
//     }
// }
}
