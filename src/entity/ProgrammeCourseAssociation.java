/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author jerry
 */
public class ProgrammeCourseAssociation {

    private String programmeId;
    private String courseCode;
    private String courseType;

    public ProgrammeCourseAssociation(String programmeId, String courseCode, String courseType) {
        this.programmeId = programmeId;
        this.courseCode = courseCode;
        this.courseType = courseType;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }


}
