package academic.model;

/**
 * @author 12S22038 Ade Yohana Azeka Siahaan
 * @author NIM Nama
 */
public class CourseOpening {
    // course-open#12S1101#2020/2021#odd#IUS
    //12S1101|Dasar Sistem Informasi|3|D|2020/2021|odd|IUS (iustisia.simbolon@del.ac.id)
    private String code;
    private String academicYear;
    private String semester;
    private String initial;

    private String matkul;
    private int credit;
    private String passingGrade;
    private String email;
    
    public CourseOpening(String code, String academicYear, String semester, String initial) {
        this.code = code;
        this.academicYear = academicYear;
        this.semester = semester;
        this.initial = initial;
        this.matkul = "";
        this.credit = 0;
        this.passingGrade = "";
        this.email = "";  
    }

    public String getCode() {
        return this.code;
    }

    public String getAcademicYear() {
        return this.academicYear;
    }

    public String getSemester() {
        return this.semester;
    }

    public String getInitial() {
        return this.initial;
    }

    public String getMatkul() {
        return this.matkul;
    }

    public int getCredit() {
        return this.credit;
    }

    public String getPassingGrade() {
        return this.passingGrade;
    }

    public String getEmail() {
        return this.email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}