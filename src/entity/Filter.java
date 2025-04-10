/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.DoublyLinkedList;

/**
 *
 * @author Kong Jia Le
 */
public class Filter {
    private final DoublyLinkedList<String> programmeFilter; // enter programme, if user not enter 0 then continue to receive programme
    private final double minFeeFilter; // negative value if no min fee filter
    private final double maxFeeFilter; // negative value if no max fee filter
    private final String paymentStatusFilter; // [1]paid, [2]unpaid, [Else]no filter
    private final DoublyLinkedList<String> courseFilter; // must filter this

    public Filter(DoublyLinkedList<String> programmeFilter, double minFeeFilter, double maxFeeFilter, String paymentStatusFilter, DoublyLinkedList<String> courseFilter) {
        this.programmeFilter = programmeFilter;
        this.minFeeFilter = minFeeFilter;
        this.maxFeeFilter = maxFeeFilter;
        this.paymentStatusFilter = paymentStatusFilter;
        this.courseFilter = courseFilter;
    }

    public DoublyLinkedList<String> getProgrammeFilter() {
        return programmeFilter;
    }

    public double getMinFeeFilter() {
        return minFeeFilter;
    }

    public double getMaxFeeFilter() {
        return maxFeeFilter;
    }

    public String getPaymentStatusFilter() {
        return paymentStatusFilter;
    }

    public DoublyLinkedList<String> getCourseFilter() {
        return courseFilter;
    }

}
