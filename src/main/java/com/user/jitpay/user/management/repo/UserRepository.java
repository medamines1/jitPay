package com.user.jitpay.user.management.repo;

import com.user.jitpay.user.management.dao.JitPayUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<JitPayUser, String> {

    @Query("select ul from JitPayUser ul join fetch ul.locations lc where ul.userId = :userId and lc.createdOn between :startDate and :endDate")
    JitPayUser findByLocationsInRange(String userId, Date startDate, Date endDate);
}
