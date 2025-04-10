/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Chan Wei Xin
 */
public class TutorialGrpCourseAssociation {
    private String groupId;
    private String CourseCode;
    private String tutorid;


    public TutorialGrpCourseAssociation(String groupId, String CourseCode, String tutorid) {
        this.groupId = groupId;
        this.CourseCode = CourseCode;
        this.tutorid = tutorid;
    }

    public String getTutorid() {
        return tutorid;
    }

    public void setTutorid(String tutorid) {
        this.tutorid = tutorid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }
    
    public String details() {
    StringBuilder sb = new StringBuilder();
    sb.append("       Add tutor to tutorial Group Information\n");
    sb.append("/-------------------------------------------------\\\n");
    sb.append(String.format("|%-25s: %-22s|\n", " Tutorial Group No:", groupId));
    sb.append(String.format("|%-25s: %-22s|\n", " Course Code:", CourseCode));
    sb.append(String.format("|%-25s: %-22s|\n", " Tutor Id", tutorid));
    sb.append("\\-------------------------------------------------/\n");
    return sb.toString();
    }

    @Override
    public String toString() {
        return "TutorialGrpCourseAssociation{" + "groupId=" + groupId + ", CourseCode=" + CourseCode + ", tutorid=" + tutorid + '}';
    }
}
