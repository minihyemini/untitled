package jisungsoft.com.repository.mes;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.mes.Order;
import jisungsoft.com.persistence.model.mes.OrderVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("orderMapper")
public interface OrderMapper {

    public List<Order> selectClientOrderListAll(Order order);

    /**
     * 엑셀출력용
     */
    public List<Object> selectClientOrderListMap(Order order);

    public List<Order> selectRequestOrderListAll(Order order);

    public List<Order> selectRequestOrderItemListAll(Order order);

    public Order selectRequestOrderDetail(Order order);

    public Order selectRequestOrderItemDetail(Order order);

    public int selectRequestOrderItemListCnt(Order order);

    public int selectRequestOrderListCnt(Order order);

    public int selectOrderNextId(Order order);

    public int insertRequestOrder(OrderVO orderVO) throws DataAccessException;

    public void updateRequestOrder(OrderVO orderVO) throws DataAccessException;

    public void deleteRequestOrder(OrderVO orderVO) throws DataAccessException;

    public void insertRequestOrderItem(OrderVO orderVO) throws DataAccessException;

    public void updateRequestOrderItem(OrderVO orderVO) throws DataAccessException;

    public void deleteRequestOrderItem(OrderVO orderVO) throws DataAccessException;

    public void deleteRequestOrderByCltId(OrderVO orderVO) throws DataAccessException;
}
