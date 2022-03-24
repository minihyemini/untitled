package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.mes.Order;
import jisungsoft.com.persistence.model.mes.OrderVO;
import jisungsoft.com.repository.mes.ProduceMapper;
import jisungsoft.com.service.ProduceService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("produceService")
public class ProduceServiceImpl extends EgovAbstractServiceImpl implements ProduceService {

    @Resource(name = "produceMapper")
    private ProduceMapper produceMapper;

}
