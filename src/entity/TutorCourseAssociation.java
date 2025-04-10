/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Chan Wei Xin
 */
public class TutorCourseAssociation {
    private String tutorId;
    private String courseCode;
    private String tutorType;  

    public TutorCourseAssociation(String tutorId, String courseCode, String tutorType) {
        this.tutorId = tutorId;
        this.courseCode = courseCode;
        this.tutorType = tutorType;
    }

    public String getTutorId() {
        return tutorId;
    }


    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }


    public String getCourseCode() {
        return courseCode;
    }


    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public String getTutorType() {
        return tutorType;
    }

    public void setTutorType(String tutorType) {
        this.tutorType = tutorType;
    }

     public String details() {
        StringBuilder sb = new StringBuilder();
        sb.append("          Add tutor to courses Information\n");
        sb.append("/-------------------------------------------------\\\n");
        sb.append(String.format("|%-25s: %-22s|\n", " Tutor Id", tutorId));
        sb.append(String.format("|%-25s: %-22s|\n", " Course Code", courseCode));
        sb.append(String.format("|%-25s: %-22s|\n", " Tutor Type", tutorType));
        sb.append("\\-------------------------------------------------/\n");
        return sb.toString();
    }
}

