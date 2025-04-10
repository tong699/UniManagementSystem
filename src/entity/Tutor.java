/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Chan Wei Xin
 */
public class Tutor {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private static int numberOfTutor = 0;
    private String tutorId;
    private String tutorName;
    private int tutorAge;
    private String gender; 
    private String educationLevel;

    public static int getNumberOfTutor() {
        return numberOfTutor;
    }

    public static void setNumberOfTutor(int numberOfTutor) {
        Tutor.numberOfTutor = numberOfTutor;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }


    
    public Tutor(String tutorId, String tutorName, int tutorAge, String gender, String educationLevel) {
        this.tutorId = generateTutorId();
        this.tutorName = tutorName;
        this.tutorAge = tutorAge;
        this.gender = gender;
        this.educationLevel = educationLevel;
    }
    
    private String generateTutorId() {
        int id = idGenerator.incrementAndGet();
        numberOfTutor++;
        return "T" + String.format("%04d",id);
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public int getTutorAge() {
        return tutorAge;
    }

    public void setTutorAge(int tutorAge) {
        this.tutorAge = tutorAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   
}