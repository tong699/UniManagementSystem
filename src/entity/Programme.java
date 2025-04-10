/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.DoublyLinkedList;
import adt.LinkedQueue;

/**
 *
 * @author User
 */
public class Programme {

    private String programmeId;
    private String programmeName;
    private DoublyLinkedList<TutorialGroup> tutorialGroupList;
    private LinkedQueue<Student> waitingListQueue;

    public Programme(String programmeId, String programmeName) {
        this.programmeId = programmeId;
        this.programmeName = programmeName;
        tutorialGroupList = new DoublyLinkedList<>();
        waitingListQueue = new LinkedQueue<>();
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public DoublyLinkedList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(DoublyLinkedList<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }

    public LinkedQueue<Student> getWaitingListQueue() {
        return waitingListQueue;
    }

    public void setWaitingListQueue(LinkedQueue<Student> waitingListQueue) {
        this.waitingListQueue = waitingListQueue;
    }


    @Override
    public String toString() {
        return "Programme{" + "programmeId=" + programmeId + ", tutorialGroupList=" + tutorialGroupList + '}';
    }

}
