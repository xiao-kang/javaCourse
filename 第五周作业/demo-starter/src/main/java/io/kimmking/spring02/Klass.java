package io.kimmking.spring02;

import io.kimmking.spring01.Student;

import java.util.List;

public class Klass {

    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
