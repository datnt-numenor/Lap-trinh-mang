package TCP;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 20151107;
    private int id;
    private String code, gpaLetter;
    private float gpa;
    public Student(int id, String code, float gpa) {
        this.id = id; this.code = code; this.gpa = gpa;
    }
    public float getGpa() { return gpa; }
    public void setGpaLetter(String gpaLetter) { this.gpaLetter = gpaLetter; }
}
