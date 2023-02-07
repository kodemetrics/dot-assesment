package com.dot.repo;

import com.dot.model.BlockedIP;
import com.dot.model.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedIPRepository extends JpaRepository<BlockedIP, Long>  {
}
