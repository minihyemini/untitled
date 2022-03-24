package jisungsoft.com.service.impl;

import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.OrderType;
import jisungsoft.com.persistence.dto.mes.Order;
import jisungsoft.com.persistence.model.mes.OrderVO;
import jisungsoft.com.repository.mes.OrderMapper;
import jisungsoft.com.service.OrderService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Transactional(readOnly = true)
@Service("orderService")
public class OrderServiceImpl extends EgovAbstractServiceImpl implements OrderService {

    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;

    public final String ORDER_LOT_NUM_PREFIX = Globals.ORDER_LOT_NUM_PREFIX;
    public final String RECEIVE_LOT_NUM_PREFIX = Globals.RECEIVE_LOT_NUM_PREFIX;
    public final String ORDER_NUMBER_PREFIX = Globals.ORDER_NUMBER_PREFIX;
    public final String RECEIVE_NUMBER_PREFIX = Globals.RECEIVE_NUMBER_PREFIX;

    @Override
    public List<Order> getClientOrderListAll(Order order) {
        List<Order> resultData = orderMapper.selectClientOrderListAll(order);
        List<Order> itemList = orderMapper.selectRequestOrderItemListAll(order);

        for (Order ord : resultData) {
            int totPrice = 0;
            int totTax = 0;
            int cnt = 0;

            for (Order item : itemList) {
                if (ord.getOrdId() == item.getOrdId()) {
                    if (item.getUnitPrice() != null && item.getQunty() != null) {
                        //품목 수량 * 단가
                        int unitTotPrice = item.getQunty() * item.getUnitPrice();
                        item.setUnitTotPrice(unitTotPrice);
                        //판매금액계
                        totPrice += unitTotPrice;
                        if (item.getSurtaxAt().equals("Y")) {
                            int surtaxRate = (int) Math.round(item.getSurtaxRate());
                            int tax = unitTotPrice * surtaxRate / 100;
                            item.setSurtax(tax);
                            totTax += tax;
                        }
                        cnt += item.getQunty();
                    }
                }
            }
            ord.setQunty(cnt);
            ord.setTotPrice(totPrice);
            ord.setTotSurtax(totTax);
        }

        return  resultData;
    }

    @Override
    public List<Object> getClientOrderListMap(Order order) {
        return orderMapper.selectClientOrderListMap(order);
    }

    @Override
    public List<Order> getProductOrderListAll(Order order) {
        return orderMapper.selectRequestOrderListAll(order);
    }

    @Override
    public List<Order> getRequestOrderItemListAll(Order order) {
        List<Order> itemList = orderMapper.selectRequestOrderItemListAll(order);
        for (Order item : itemList) {
            int totPrice = item.getQunty() * item.getUnitPrice();
            item.setUnitTotPrice(totPrice);

            if (item.getSurtaxAt().equals("Y")) {
                int surtaxRate = (int) Math.round(item.getSurtaxRate());
                int tax = totPrice * surtaxRate / 100;
                item.setSurtax(tax);
            }
        }

        return itemList;
    }

    @Override
    public Order getRequestOrderDetail(Order order) {
        return orderMapper.selectRequestOrderDetail(order);
    }

    @Override
    public int getRequestOrderListCnt(Order order) {
        return orderMapper.selectRequestOrderItemListCnt(order);
    }

    @Override
    public void addRequestOrder(Order order) throws DataAccessException {
        //수주,판매번호 생성
        //수주,판매 LotNu 생성
        Order orderNum = createOrderNumberData(order);

        //add order
        OrderVO dataOrder = OrderVO.createDataOrder(
                null,
                orderNum.getOrdLotNo(),
                orderNum.getOrdNum(),
                order.getOrdType(),
                order.getOrdStatus(),
                order.getOrdCategory(),
                order.getOrderDate(),
                order.getOrdDuedate(),
                order.getOrdDlvrschdt(),
                order.getEsntlId(),
                order.getFrstRegisterId(),
                null,
                order.getMatId(),
                order.getPdId(),
                order.getCltId(),
                order.getMatodrqstId(),
                order.getOrdDesc()
        );
        int id = orderMapper.insertRequestOrder(dataOrder);
        order.setOrdId(id);

        //add item
        List<Order> orderFormList = order.getItemFormList();
        for (Order ord : orderFormList) {
            OrderVO item = OrderVO.createDataOrderItem(
                    null,
                    order.getOrdId(),
                    ord.getPdnumId(),
                    ord.getUnitPrice(),
                    ord.getQunty(),
                    ord.getSurtaxAt(),
                    ord.getSurtaxRate());

            orderMapper.insertRequestOrderItem(item);
        }
    }

    @Override
    public void editRequestOrder(Order order) throws DataAccessException {
        //edit order
        OrderVO dataOrder = OrderVO.createDataOrder(
                order.getOrdId(),
                order.getOrdLotNo(),
                order.getOrdNum(),
                order.getOrdType(),
                order.getOrdStatus(),
                order.getOrdCategory(),
                order.getOrderDate(),
                order.getOrdDuedate(),
                order.getOrdDlvrschdt(),
                order.getEsntlId(),
                null,
                order.getLastUpdusrId(),
                order.getMatId(),
                order.getPdId(),
                order.getCltId(),
                order.getMatodrqstId(),
                order.getOrdDesc()
        );

        orderMapper.updateRequestOrder(dataOrder);

        //edit item
        List<Order> orderFormList = order.getItemFormList();
        for (Order ord : orderFormList) {
            OrderVO item = OrderVO.createDataOrderItem(
                    ord.getOiId(),
                    order.getOrdId(),
                    ord.getPdnumId(),
                    ord.getUnitPrice(),
                    ord.getQunty(),
                    ord.getSurtaxAt(),
                    ord.getSurtaxRate());
            if (ord.getOiId() != null) {
                orderMapper.updateRequestOrderItem(item);
            } else {
                orderMapper.insertRequestOrderItem(item);
            }
        }
    }

    @Override
    public void removeRequestOrderItem(Order order) throws DataAccessException {
        OrderVO dataOrderItemId = OrderVO.createDataOrderItemId(order.getOiId());

        orderMapper.deleteRequestOrderItem(dataOrderItemId);
    }

    /**
     * 수주, 판매번호 생성
     * 수주, 판매 LotNum 생성
     */
    public Order createOrderNumberData(Order order) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        String searchDate = now.format(format);
        Order searchCnt = new Order();
        searchCnt.setSearchDate(searchDate);
        int nextId = orderMapper.selectOrderNextId(order);

        if (order.getOrdType().equals(OrderType.RECEIVE.name())) {
            order.setOrdNum(RECEIVE_NUMBER_PREFIX+searchDate+String.format("%03d", nextId));
            order.setOrdLotNo(RECEIVE_LOT_NUM_PREFIX+searchDate+String.format("%03d", nextId));
        } else if (order.getOrdType().equals(OrderType.PLACE.name())) {
            order.setOrdNum(ORDER_NUMBER_PREFIX+searchDate+String.format("%03d", nextId));
            order.setOrdLotNo(ORDER_LOT_NUM_PREFIX+searchDate+String.format("%03d", nextId));
        }

        return order;
    }
}
