package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.mes.Order;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 수주 / 발주 관리
 */
public interface OrderService {

    /**
     * 거래처,주문 목록
     */
    public List<Order> getClientOrderListAll(Order order);

    public List<Object> getClientOrderListMap(Order order);
    /**
     * 생산품,주문 목록
     */
    public List<Order> getProductOrderListAll(Order order);
    /**
     * 수주 품목 목록
     */
    public List<Order> getRequestOrderItemListAll(Order order);
    /**
     * 수주 상세
     */
    public Order getRequestOrderDetail(Order order);
    /**
     * 수주 카운트
     */
    public int getRequestOrderListCnt(Order order);
    /**
     * 수주 수정
     */
    public void addRequestOrder(Order order) throws DataAccessException;
    /**
     * 수주 수정
     */
    public void editRequestOrder(Order order) throws DataAccessException;
    /**
     * 수주 삭제
     */
    public void removeRequestOrderItem(Order order) throws DataAccessException;
}
