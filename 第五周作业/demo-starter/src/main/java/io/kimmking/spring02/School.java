package io.kimmking.spring02;

import io.kimmking.aop.ISchool;

public class School implements ISchool {
            Klass class1;

//    @Resource(name = "student100")
//    Student student100;

    @Override
    public void ding() {

        System.out.println("Class1 have " + this.class1.getStudents().size() );

    }

    public Klass getClass1() {
        return class1;
    }

    public void setClass1(Klass class1) {
        this.class1 = class1;
    }

}
