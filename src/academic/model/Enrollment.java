package academic.model;
/**
 * @author NIM Nama
 * @author NIM Nama
 */

 public class Enrollment extends Course {
    private String nim;
    private String thauncourse;
    private String semester;
    private String grade = "None";
    private String gradelast;
 

    public Enrollment(String code, String _nim, String thauncourse, String semester) {
        super(code, "", 0, "");
        this.nim = _nim;
        this.thauncourse = thauncourse;
        this.semester = semester;
        this.grade = "";
        this.gradelast = "";
        
    }


    public String get_nim() {
        return this.nim;
    }

    public String getThauncourse() {
        return this.thauncourse;
    }

    public String getSemester() {
        return this.semester;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getGradelast() {
        return this.gradelast;
    }

    public void set_nim(String _nim) {
        this.nim = _nim;
    }

    public void setThauncourse(String thauncourse) {
        this.thauncourse = thauncourse;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGrade(String gradelama, String gradebaru){
        if (gradelast.equals("") && gradelast.equals("None")){
            this.grade = gradebaru;
            this.gradelast = gradelama;
        }
    }

    public void setGradelast(String gradelast) {
        this.gradelast = gradelast;
    }
}