package edu.beihua.KarryCode.mapper;

import edu.beihua.KarryCode.entity.Customer;
import edu.beihua.KarryCode.entity.Order;
import edu.beihua.KarryCode.entity.Vehicle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderMapper {
    int insertOrder(Order order);
    List<Order> selectOrder(@Param("customer") Customer customer,
                            @Param("cha") Boolean cha);
    Order selectOrder_ByLNR(@Param("license") String license,
                            @Param("CName") String CustomerName,
                            @Param("OrderReturn") boolean OrderReturn);
    int updateOrderReturnDateAndSta_ByOrderId(@Param("date") String date,
                                              @Param("id") int OrderId);
    Order selectOrder_Customer(@Param("license") String license);
}
