package com.user.jitpay.user.management.dao;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
@NoArgsConstructor
public class Locations {

    private Date createdOn = new Date();

    private Location location;

}
