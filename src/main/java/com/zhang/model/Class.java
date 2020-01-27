package com.zhang.model;

/**
 * @Author: King
 * @Create: 2019-12-04 18:55
 * @Desc:
 **/
public class Class {

    private Long id ;
    private String className;
    private String classTeacher;
    private String mathTeacher;
    private String englishTeacher;
    private String chineseTeacher;

    public String getChineseTeacher() {
        return chineseTeacher;
    }

    public void setChineseTeacher(String chineseTeacher) {
        this.chineseTeacher = chineseTeacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getMathTeacher() {
        return mathTeacher;
    }

    public void setMathTeacher(String mathTeacher) {
        this.mathTeacher = mathTeacher;
    }

    public String getEnglishTeacher() {
        return englishTeacher;
    }

    public void setEnglishTeacher(String englishTeacher) {
        this.englishTeacher = englishTeacher;
    }


}
