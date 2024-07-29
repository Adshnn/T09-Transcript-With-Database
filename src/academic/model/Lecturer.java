package academic.model;

public class Lecturer extends Individu {
    private String nidn;
    private String initial;
    private String email;
    private String prodi;

    public Lecturer(String nidn, String name, String initial, String email, String prodi) {
        super(name);
        this.nidn = nidn;
        this.initial = initial;
        this.email = email;
        this.prodi = prodi;
    }

    public String getNidn() {
        return this.nidn;
    }

    public String getInitial() {
        return this.initial;
    }

    public String getEmail() {
        return this.email;
    }

    public String getProdi() {
        return this.prodi;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

}
