package academic.model;

/**
 * @author 12S22038 Ade Yohana Azeka Siahaan
 */
public class Course  { 
    //course-add#12S1101#Dasar Sistem Informasi#3#D
    protected String code;
    protected String matkul;
    protected int credit;
    protected String passingGrade;
    protected String initial;

    public Course(String code, String matkul, int credit, String passingGrade) {
        this.code = code;
        this.matkul = matkul;
        this.credit = credit;
        this.passingGrade = passingGrade;
        this.initial = "";
    }

    public String getcode() {
        return this.code;
    }
    
    public String getmatkul() {
        return this.matkul;
    }

    public int getCredit() {
        return this.credit;
    }   

    public String getPassingGrade() {
        return this.passingGrade;
    }

    public String getInitial() {
        return this.initial;
    }

    public void setNim(String code) {
        this.code = code;
    }

    public void setCourse(String matkul) {
        this.matkul = matkul;
    }
    
    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }   

    public void setInitial(String initial) {
        this.initial = initial;
    }

}