package com.charmyin.cmstudio.tzyc._7s.vo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestVORepository extends MongoRepository<TestVo, String> {

}
