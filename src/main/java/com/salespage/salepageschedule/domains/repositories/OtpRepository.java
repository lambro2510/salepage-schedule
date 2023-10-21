package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.Otp;
import com.salespage.salepageschedule.domains.entities.types.OtpStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends MongoRepository<Otp, ObjectId> {
  List<Otp> findByStatus(OtpStatus otpStatus);
}
