package com.dot;

import com.dot.model.UserAccessLog;
import com.dot.repo.BlockedIPRepository;
import com.dot.repo.UserAccessLogRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DotApplicationTests {
	@Autowired
	private UserAccessLogRepository userAccessLogRepository ;

	@Autowired
	private BlockedIPRepository blockedIPRepository ;

	@BeforeEach
	void setUp() {

	}


	@Test
	void exceedHourlyRequest() {
		String ip_address = "192.168.228.188",start_date = "2022-01-01 13:00:00",end_date = "2022-01-01 14:00:00";
		int count = userAccessLogRepository.hasExceedHourlyRequest(ip_address,start_date,end_date);
		System.out.println(count);
		assertNotEquals(count,200);
	}
	@Test
	@Ignore
	void hourlyRequestExist() {
		String start_date = "2022-01-01 13:00:00",end_date = "2022-01-01 14:00:00";
		int limit = 100;
		//System.out.println("i got here: "+userAccessLogRepository.findAll().stream().count());
		//assertNotNull(userAccessLogRepository.checkHourlyRequest(start_date,end_date));
		//assertEquals(userAccessLogRepository.checkHourlyRequest(start_date,end_date,limit).size(),209);

	}


}
