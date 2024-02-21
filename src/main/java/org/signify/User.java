package org.signify;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "is_active")
    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    private Boolean isActive;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private Instant modifiedAt;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Gender")
    private Gender gender;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Embedded
    private Address address;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "dob")
    private Date dateOfBirth;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Formula("YEAR(CURRENT_TIMESTAMP) - YEAR(dob) - (RIGHT(CURRENT_TIMESTAMP, 5) < RIGHT(dob, 5))")
    private int age;

    public User(String name, String emailId, String phoneNumber, Boolean isActive, Date dob) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.dateOfBirth = dob;

    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
//    public User(String name, String emailId, String phoneNumber, Gender gender) {
//        super();
//        this.name = name;
//        this.emailId = emailId;
//        this.phoneNumber = phoneNumber;
//        this.gender = gender;
//    }
    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", gender=" + gender +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
