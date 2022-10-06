package com.user.jitpay.user.management.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class JitPayUser {

    @Id
    private String userId;

    private String email;

    private String firstName;

    private String secondName;

    private Date createdOn = new Date();

    @ElementCollection
    private List<Locations> locations;


}
