package edu.beihua.KarryCode.mapper;

import edu.beihua.KarryCode.entity.Customer;
import edu.beihua.KarryCode.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IMessageMapper {
    List<Message> selectAllMessage();
    List<Message> selectAllMessageAll();
    int insertMessage(@Param("name") String name,
                      @Param("date") String date,
                      @Param("con") String con);
    @Update("update message set message_state=#{sta} where message_id=#{id};")
    int updateStaById(@Param("id") int id,
                      @Param("sta") boolean sta);
    @Select("select * from car_rental3.message where message_id = #{id} ;")
    Message selectById(@Param("id") int id);
    @Delete("delete from message where message_id = #{id};")
    int deleteMessage(@Param("id") int id);
    @Delete("delete from message where customer_name = #{name}")
    int deleteMessageByCName(@Param("name") String name);
}
