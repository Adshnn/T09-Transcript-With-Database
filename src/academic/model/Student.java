package academic.model;

/**
 * @author NIM Nama
 * @author NIM Nama
 */
public class Student extends Individu {
    private String nim;
    private int year;
    private String prodi;

    public Student(String nim, String name, int year, String prodi) {
        super(name);
        this.nim = nim;
        this.year = year;
        this.prodi = prodi;
    }

    public String getNim() {
        return this.nim;
    }


    public int getYear() {
        return this.year;
    }

    public String getProdi() {
        return this.prodi;
    }

    public void setNim(String _nim) {
        this.nim = _nim;
    }

public void setYear(int year) {
        this.year = year;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
}