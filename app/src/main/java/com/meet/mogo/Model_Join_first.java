package com.meet.mogo;

public class Model_Join_first {


    private String classcode,collegecode;

    public Model_Join_first() {

    }

    public Model_Join_first(String classcode, String collegecode) {
        this.classcode = classcode;
        this.collegecode = collegecode;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getCollegecode() {
        return collegecode;
    }

    public void setCollegecode(String collegecode) {
        this.collegecode = collegecode;
    }
}
