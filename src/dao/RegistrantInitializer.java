/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.LinkedQueue;
import entity.Registrant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kong Jia Le
 */
public class RegistrantInitializer {

    public LinkedQueue<Registrant> initializeRegistrant() {
        LinkedQueue<Registrant> registrantQueue = new LinkedQueue<>();
        registrantQueue.enqueue(
                new Registrant("Emily Wong", "emilywong123@gmail.com", "RDS", LocalDate.parse("03-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 4, false));
        registrantQueue.enqueue(
                new Registrant("Chan Wei Xin", "chanwx123@gmail.com", "RDS", LocalDate.parse("03-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.7, true));
        registrantQueue.enqueue(
                new Registrant("Lim Jia Cheng", "limjc123@gmail.com", "REE", LocalDate.parse("03-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.7, true));
        registrantQueue.enqueue(
                new Registrant("Michael Tan", "michaeltan123@gmail.com", "RSW", LocalDate.parse("04-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.5, true));
        registrantQueue.enqueue(
                new Registrant("Lewis Milton", "michaeltan123@gmail.com", "RME", LocalDate.parse("04-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.5, true));
        registrantQueue.enqueue(
                new Registrant("Jerry Tay Kien Hui", "jerrytay123@gmail.com", "RSW", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 4, true));
        registrantQueue.enqueue(
                new Registrant("Kong Jia Le", "kongjl123@gmail.com", "RDS", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.5, true));
        registrantQueue.enqueue(
                new Registrant("Wong Shi Lu", "johnlim123@gmail.com", "REE", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 3.9, true));
        registrantQueue.enqueue(
                new Registrant("Tong Chun Mun", "tongcm123@gmail.com", "RSW", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 4, true));
        registrantQueue.enqueue(
                new Registrant("Johnny Pang", "johnnyp123@gmail.com", "REE", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.5, true));
        registrantQueue.enqueue(
                new Registrant("Sarah Lee", "sarahlee123@gmail.com", "RDS", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 3.9, false));
        registrantQueue.enqueue(
                new Registrant("David Lim", "davidlim123@gmail.com", "RSW", LocalDate.parse("07-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.27, false));
        registrantQueue.enqueue(
                new Registrant("Liew Jia Jia", "lindatan123@gmail.com", "RME", LocalDate.parse("08-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 3.6, true));
        registrantQueue.enqueue(
                new Registrant("Linda Tan", "lindatan123@gmail.com", "RDS", LocalDate.parse("08-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 3.6, true));
        registrantQueue.enqueue(
                new Registrant("Max Low", "maryng123@gmail.com", "RME", LocalDate.parse("09-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 4.0, true));
        registrantQueue.enqueue(
                new Registrant("Mary Ng", "maryng123@gmail.com", "RDS", LocalDate.parse("09-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 4.0, true));
        registrantQueue.enqueue(
                new Registrant("John Tay", "stevenchew123@gmail.com", "REE", LocalDate.parse("10-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.8, true));
        registrantQueue.enqueue(
                new Registrant("Steven Chew", "stevenchew123@gmail.com", "RSW", LocalDate.parse("10-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.8, true));
        registrantQueue.enqueue(
                new Registrant("Felicia Chin", "sarahlee123@gmail.com", "REE", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 3.9, false));
        registrantQueue.enqueue(
                new Registrant("Alex Chong", "jerrytay123@gmail.com", "REE", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 4, true));
        registrantQueue.enqueue(
                new Registrant("John Lim", "johnlim123@gmail.com", "RSW", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.9, true));
        registrantQueue.enqueue(
                new Registrant("Johnny Pang", "johnnyp123@gmail.com", "REE", LocalDate.parse("06-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", 3.5, true));
        registrantQueue.enqueue(
                new Registrant("Wong Kah Hui", "emilywong123@gmail.com", "RME", LocalDate.parse("03-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "F", 4, false));

        return registrantQueue;
    }
}
