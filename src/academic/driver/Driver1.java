package academic.driver;

import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;
import academic.model.Lecturer;
import academic.model.Student;

/**
 * @author 12s22038 Ade Yohana Azeka Siahaan
 * @author NIM Nama
 */

import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collection;


public class Driver1 {
    static ArrayList<Lecturer> lecturers = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Enrollment> enrollments = new ArrayList<>();
    static ArrayList<CourseOpening> courseOpenings = new ArrayList<>();

    public static void main(String[] _args) {
        try {
            MySQLDatabase db = new MySQLDatabase("jdbc:mysql://localhost:3306/T09", "root", "root");
            // Scanner scanner = new Scanner(System.in);
            // while (scanner.hasNextLine()) {
            //     String input = scanner.nextLine();
            //     if (input.equals("---")) {
            //         break;
            //     }
            //     String[] inputSegments = input.split("#");
            //     if (inputSegments[0].equals("lecturer-add")) {
            //         db.addLecturer(inputSegments);
            //     } else if (inputSegments[0].equals("course-add")){
            //         db.addCourse(inputSegments);
            //     } else if (inputSegments [0].equals("student-add")){
            //         db.addStudent(inputSegments);
            //     } else if (inputSegments[0].equals("enrollment-add")){
            //         db.addEnrollment(inputSegments);
            //     }
            // }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("---")) {
                break;
            }
            String[] inputSegments = input.split("#");
            if (inputSegments[0].equals("lecturer-add")) {
                String nidn = inputSegments[1];
                String name = inputSegments[2];
                String initial = inputSegments[3];
                String email = inputSegments[4];
                String prodi = inputSegments[5];
                boolean isDuplicate = false;
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getEmail().equals(email)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    Lecturer lecturer = new Lecturer(nidn, name, initial, email, prodi); // Assuming Lecturer class is defined
                    lecturers.add(lecturer);
                    String[] lecturerData = {"", nidn, name, initial, email, prodi};
                    db.addLecturer(lecturerData);
                }
            } else if (inputSegments[0].equals("course-add")){
                String code = inputSegments[1];
                String matkul = inputSegments[2];
                int credit = Integer.parseInt(inputSegments[3]);
                String passingGrade = inputSegments[4];
        //         String initial = inputSegments[5];
        //         {
        //             String[] initialSegments = initial.split(",");{
        //                 for (int i = 0; i < initialSegments.length; i++){
        //                     String courseInitial = initialSegments[i];
        //                     for (int j = 0; j < lecturers.size(); j++) {
        //                         String lecturerInitial = lecturers.get(j).getInitial();
        //                         if (courseInitial.equals(lecturerInitial)) {
        //                             String lecturerEmail = lecturers.get(j).getEmail();
        //                             if (i == initialSegments.length - 1) {
        //                                 initialSegments[i] = courseInitial + " (" + lecturerEmail + ")";
        //                             } else {
        //                                 initialSegments[i] = courseInitial + " (" + lecturerEmail + ");";
        //                             }
        //                             break;
        //                 }
        //             }       
        //     }
        //     initial = String.join("", initialSegments);
                boolean isDuplicate = false;
                for (Course course : courses) {
                    if (course.getcode().equals(code)) {
                        isDuplicate = true;
                        break;
                    }

                }
             if (!isDuplicate) {
                Course course = new Course(code, matkul, credit, passingGrade); // Assuming Course class is defined
                courses.add(course);// Declare the prodi variable
                String[] courseData = {"", code, matkul, String.valueOf(credit), passingGrade};
                db.addCourse(courseData);
            }

            } else if (inputSegments[0].equals("student-add")){ 
                String nim = inputSegments[1];
                String nama = inputSegments[2];
                int year = Integer.parseInt(inputSegments[3]);
                String prodi = inputSegments[4];
                boolean isDuplicate = false;
                for (Student student : students) {
                    if (student.getNim().equals(nim)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate){
                    Student student = new Student(nim, nama, year, prodi);
                    students.add(student);
                    String[] studentData = {"", nim, nama, String.valueOf(year), prodi};
                    db.addStudent(studentData);
                }


            } else if (inputSegments[0].equals("enrollment-add")){
                String code = inputSegments[1];
                String nim = inputSegments[2];
                String thauncourse = inputSegments[3];
                String semester = inputSegments[4];


                boolean cekstu = false;
                for (Student student : students) {
                    if (student.getNim().equals(nim)) {
                        cekstu = true;
                        break;
                    }
                }
                boolean cekcourse = false;
                for (Course course : courses) {
                    if (course.getcode().equals(code)) {
                        cekcourse = true;
                        break;
                    }
                }

                if (!cekcourse) {
                    System.out.println("invalid course|" + code);
                } else if (!cekstu) {
                    System.out.println("invalid student|" + nim);
                }

                if (cekcourse && cekstu){
                    Enrollment enrollment = new Enrollment(code, nim, thauncourse, semester);
                    enrollments.add(enrollment);
                    String[] enrollmentData = {"",code, nim, thauncourse, semester, "None"};
                    db.addEnrollment(enrollmentData);
                }
            } else if (inputSegments[0].equals("enrollment-grade")){
                String code = inputSegments[1];
                String nim = inputSegments[2];
                String thauncourse = inputSegments[3];
                String semester = inputSegments[4];
                String grade = inputSegments[5];

                for(Enrollment enrollment : enrollments){
                    if (enrollment.getcode().equals(code) && enrollment.get_nim().equals(nim) && enrollment.getThauncourse().equals(thauncourse) && enrollment.getSemester().equals(semester)){
                        enrollment.setGrade(grade);
                        String[] enrollmentGradeData = {"", code, nim, thauncourse, semester, grade};
                        db.addEnrollmentGrade(enrollmentGradeData);
                    }
             }
            } else if (inputSegments[0].equals("enrollment-remedial")){
                String code = inputSegments[1];
                String nim = inputSegments[2];
                String thauncourse = inputSegments[3];
                String semester = inputSegments[4];
                String lastgrade = inputSegments[5];


                for (Enrollment enrollment : enrollments){
                    if (enrollment.getcode().equals(code) && enrollment.get_nim().equals(nim) && enrollment.getThauncourse().equals(thauncourse) && enrollment.getSemester().equals(semester)){
                        if(enrollment.getGradelast().equals("") && !enrollment.getGrade().equals(""))
                            enrollment.setGradelast(lastgrade); 
                             String[] enrollmentGradeDataremet = {"", code, nim, thauncourse, semester, lastgrade};
                             db.addEnrollmentRemedial(enrollmentGradeDataremet);
                     }

            }


        } else if (inputSegments[0].equals("student-details")) {
                String nim = inputSegments[1];
                int totalCredit = 0;
                float gpa = 0;
                double kaligrade = 0.00;
                String studentdetail = "";

                List<String>courseid= new ArrayList<>();
                List<String>coursegrade = new ArrayList<>();

                for (Enrollment enrollmen : enrollments) {
                    if (enrollmen.get_nim().equals(nim) && enrollmen.getGrade() != "None" && enrollmen.getGradelast().equals("")){
                        courseid.add(enrollmen.getcode());
                        coursegrade.add(enrollmen.getGrade());
                    } else if (enrollmen.get_nim().equals(nim) && !enrollmen.getGradelast().equals("")) {
                        courseid.add(enrollmen.getcode());
                        coursegrade.add(enrollmen.getGradelast());
                    }
                }

                for (int i = 0; i < courseid.size(); i++) {
                    for(int j = i + 1; j < courseid.size(); j++) {
                        if(courseid.get(i).equals(courseid.get(j))) {
                            courseid.remove(i);
                            coursegrade.remove(i);
                        }
                    }
                }                                   
                    for (int i = 0; i < courseid.size(); i++) {
                        for (int j = 0; j < courses.size(); j++) {
                            if (courseid.get(i).equals(courses.get(j).getcode())) {
                                totalCredit += courses.get(j).getCredit();
                                kaligrade += gradeAsNumber(coursegrade.get(i)) * courses.get(j).getCredit();
                            }
                        }
                    }

                        // Calculate GPA
                        gpa = (kaligrade == 0 && totalCredit == 0) ? 0 : (float) (kaligrade / totalCredit);
                        if (gpa == 0) {
                            totalCredit = 0;
                        }

                // Get student details
                for (Student student : students) {
                    if (student.getNim().equals(nim)) {
                        studentdetail = student.getNim() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getProdi();
                        break;
                    }
                }

                System.out.println( studentdetail + "|" + String.format("%.2f", gpa) + "|" + totalCredit);

            } else if (inputSegments[0].equals("course-open")) {
                    String code = inputSegments[1];
                    String academicYear = inputSegments[2];
                    String semester = inputSegments[3];
                    String initial = inputSegments[4];
                
                    CourseOpening courseOpening = new CourseOpening(code, academicYear, semester, initial);
                    courseOpenings.add(courseOpening);
                
                    for (Course course : courses) {
                        if (course.getcode().equals(code)) {
                            courseOpening.setMatkul(course.getmatkul());
                            courseOpening.setCredit(course.getCredit());
                            courseOpening.setPassingGrade(course.getPassingGrade());
                        }
                    }
                
                    for (Lecturer lecturer : lecturers) {
                        if (lecturer.getInitial().equals(initial)) {
                            courseOpening.setEmail(lecturer.getEmail());
                        }
                    }
                    String[] courseOpeningData = {"", code, courseOpening.getMatkul(), String.valueOf(courseOpening.getCredit()), 
                                                courseOpening.getPassingGrade(), academicYear, 
                                                  semester, initial,
                                                  courseOpening.getEmail()};
                    db.addCourseOpening(courseOpeningData);
                

            } else if (inputSegments[0].equals("course-history")){
                String code = inputSegments[1];

                Collections.sort(courseOpenings, Comparator.comparing(CourseOpening :: getSemester ).reversed().thenComparing(CourseOpening :: getAcademicYear));
                for (CourseOpening courseOpening : courseOpenings) {
                    if (courseOpening.getCode().equals(code)) {
                        System.out.println(courseOpening.getCode() + "|" + courseOpening.getMatkul() + "|" + courseOpening.getCredit() + "|" + courseOpening.getPassingGrade() + "|" + courseOpening.getAcademicYear() + "|" + courseOpening.getSemester() + "|" + courseOpening.getInitial() +  " (" + courseOpening.getEmail() + ")");
                    }
                    for (Enrollment enrollment : enrollments) {
                        if (courseOpening.getCode().equals(code) && enrollment.getcode().equals(code) && enrollment.getThauncourse().equals(courseOpening.getAcademicYear()) && enrollment.getSemester().equals(courseOpening.getSemester())) {
                            if (!enrollment.getGrade().equals("None") && enrollment.getGradelast().equals("")) {
                                System.out.println(enrollment.getcode() + "|" + enrollment.get_nim() + "|" + enrollment.getThauncourse() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
                            } else if (!enrollment.getGradelast().equals("")) {
                                System.out.println(enrollment.getcode() + "|" + enrollment.get_nim() + "|" + enrollment.getThauncourse() + "|" + enrollment.getSemester() + "|" + enrollment.getGradelast() + "(" + enrollment.getGrade() + ")");
                            }
                        }
                    }
                    continue;
                }

            } else if (inputSegments[0].equals("student-transcript")) {
                String nim = inputSegments[1];
                int totalCredit = 0;
                float gpa = 0;
                double kaligrade = 0.00;
                String studentdetail = "";

                // Local class untuk menyimpan pendaftaran terakhir untuk setiap kursus
                class LastEnrollmentMap {
                    Map<String, Enrollment> map = new LinkedHashMap<>();
                    void addEnrollment(Enrollment enrollment) {
                        if (!map.containsKey(enrollment.getcode())) {
                            map.put(enrollment.getcode(), enrollment);
                        } else {
                            Enrollment existingEnrollment = map.get(enrollment.getcode());
                            if (enrollment.getThauncourse().compareTo(existingEnrollment.getThauncourse()) > 0) {
                                map.put(enrollment.getcode(), enrollment);
                            }
                        }
                    }


                    Collection<Enrollment> getEnrollments() {
                        return map.values();
                    }
                }

                LastEnrollmentMap lastEnrollmentMap = new LastEnrollmentMap();

                for (Enrollment enrollment : enrollments) {
                    if (enrollment.get_nim().equals(nim)) {
                        lastEnrollmentMap.addEnrollment(enrollment);
                    }
                }

                List<String> courseid = new ArrayList<>();
                List<String> coursegrade = new ArrayList<>();

                for (Enrollment enrollment : lastEnrollmentMap.getEnrollments()) {
                    if (enrollment.get_nim().equals(nim) && enrollment.getGrade() != "None" && enrollment.getGradelast().equals("")) {
                        courseid.add(enrollment.getcode());
                        coursegrade.add(enrollment.getGrade());
                    } else if (enrollment.get_nim().equals(nim) && !enrollment.getGradelast().equals("")) {
                        courseid.add(enrollment.getcode());
                        coursegrade.add(enrollment.getGradelast());
                    }
                }

                for (int i = 0; i < courseid.size(); i++) {
                    for (int j = 0; j < courses.size(); j++) {
                        if (courseid.get(i).equals(courses.get(j).getcode())) {
                            totalCredit += courses.get(j).getCredit();
                            kaligrade += gradeAsNumber(coursegrade.get(i)) * courses.get(j).getCredit();
                        }
                    }
                }

                // Calculate GPA
                gpa = (kaligrade == 0 && totalCredit == 0) ? 0 : (float) (kaligrade / totalCredit);
                if (gpa == 0) {
                    totalCredit = 0;
                }

                for (Student student : students) {
                    if (student.getNim().equals(nim)) {
                        studentdetail = student.getNim() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getProdi();
                        break;
                    }
                }
                System.out.println(studentdetail + "|" + String.format("%.2f", gpa) + "|" + totalCredit);
                for (Enrollment enrollment : lastEnrollmentMap.getEnrollments()) {
                    if (enrollment.get_nim().equals(nim)) {
                        if (enrollment.getGradelast() == "" || enrollment.getGradelast() == null) {
                            System.out.println(enrollment.getcode() + "|" + enrollment.get_nim() + "|" + enrollment.getThauncourse() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
                        } else {
                            System.out.println(enrollment.getcode() + "|" + enrollment.get_nim() + "|" + enrollment.getThauncourse() + "|" + enrollment.getSemester() + "|" + enrollment.getGradelast() + "(" + enrollment.getGrade() + ")");
                        }
                    }
                }
            }            
    }


        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getNidn() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getProdi());
        }
        for (Course course : courses) {
            System.out.println(course.getcode() + "|" + course.getmatkul() + "|" + course.getCredit() + "|" + course.getPassingGrade());
        }
        for (Student student : students) {
            System.out.println(student.getNim() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getProdi());
        }
        for (Enrollment enrollment : enrollments) {
            if(enrollment.getGradelast() == "" || enrollment.getGradelast() == null){
                System.out.println(enrollment.getcode() + "|" + enrollment.get_nim() + "|" + enrollment.getThauncourse() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
            } else if (enrollment.getGradelast() != "" && enrollment.getGradelast() != null){
                System.out.println(enrollment.getcode() + "|" + enrollment.get_nim() + "|" + enrollment.getThauncourse() + "|" + enrollment.getSemester() + "|" + enrollment.getGradelast() + "(" + enrollment.getGrade() + ")");
            }
        }  db.shutdown();
        scanner.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }  
} 

    private static double gradeAsNumber(String grade) {
        switch (grade) {
            case "A":
                return 4.00;
            case "AB":
                return 3.50;
            case "B":
                return 3.00;
            case "BC":
                return 2.50;
            case "C":
                return 2.00;
            case "D":
                return 1.00;
            case "E":
                return 0.00;
            default:
                return 0.00;
        }
    }
}