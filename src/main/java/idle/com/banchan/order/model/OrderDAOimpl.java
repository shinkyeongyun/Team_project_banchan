package idle.com.banchan.order.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class OrderDAOimpl implements OrderDAO {

	@Autowired
	SqlSession sqlSession;

}
