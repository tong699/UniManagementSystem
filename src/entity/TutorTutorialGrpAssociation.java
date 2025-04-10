/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Chan Wei Xin
 */
public class TutorTutorialGrpAssociation {
    private String groupId;
    private String tutorId;

    
    public TutorTutorialGrpAssociation(String groupId, String tutorId){
        this.groupId = groupId;
        this.tutorId = tutorId ;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupNo(String groupId) {
        this.groupId = groupId;
    }
    
    
    public String details() {
        StringBuilder sb = new StringBuilder();
        sb.append("       Add tutor to tutorial Group Information\n");
        sb.append("/-------------------------------------------------\\\n");
        sb.append(String.format("|%-25s: %-22s|\n", " Tutor Id", tutorId));
        sb.append(String.format("|%-25s: %-22s|\n", " Tutorial Group No:", groupId));
        sb.append("\\-------------------------------------------------/\n");
        return sb.toString();
    }   
}
