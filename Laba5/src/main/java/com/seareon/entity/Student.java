package com.seareon.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс представляет собой модель студента.
 *
 * @author Misha Ro
 * @version 1.0
 */
public class Student {
    /**
     * Student id
     */
    private int id;
    /**
     * Student name
     */
    private String firstName;
    /**
     * Фамилия студента
     */
    private String lastName;
    /**
     * Номер группы студента
     */
    private int groupName;
    /**
     * Кафедра, где студент учится
     */
    private String department;
    /**
     * Средняя оценка студента
     */
    private double averageMark;
    /**
     * Город, где родился студент
     */
    private String city;
    /**
     * Адрес проживания студента
     */
    private String address;
    /**
     * Телефон студента
     */
    private String phone;

    /**
     * Конструктор - создание нового объекта
     */
    public Student() {  }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param resultSet
     * @throws SQLException
     */
    public Student(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        firstName = resultSet.getString("first_name");
        lastName = resultSet.getString("last_name");
        groupName = resultSet.getInt("group_name");
        department = resultSet.getString("department");
        averageMark = resultSet.getDouble("average_mark");
        city = resultSet.getString("city");
        address = resultSet.getString("address");
        phone = resultSet.getString("phone");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGroup() {
        return groupName;
    }

    public void setGroup(int group) {
        this.groupName = group;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Переопределение метода из класса {@link Object}
     * @return
     */
    @Override
    public int hashCode() {
        int result = 31;

        result += id;
        result += firstName.hashCode();
        result += lastName.hashCode();
        result += groupName;
        result += department.hashCode();
        result += averageMark;
        result += city.hashCode();
        result += address.hashCode();
        result += phone.hashCode();

        return result;
    }

    /**
     * Переопределение метода из класса {@link Object}
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if(!(obj instanceof Student)) {
            return false;
        }

        Student student = (Student) obj;

        if(id != student.getId() || !firstName.equals(student.getFirstName()) ||
                !lastName.equals(student.getLastName()) || groupName != student.getGroup() ||
                averageMark != student.getAverageMark() || !city.equals(student.getCity()) ||
                !address.equals(student.getAddress()) || !phone.equals(student.getPhone())) {
            return false;
        }

        return true;
    }
}
