package edu.beihua.KarryCode.mapper;

import edu.beihua.KarryCode.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface ICustomerMapper {
    Customer selectBy_CName_CPass(@Param("name") String name,
                                  @Param("pass") String pass);
    boolean insertNewCustomer(@Param("name")  String name,
                              @Param("pass")  String pass,
                              @Param("email") String email,
                              @Param("phone") String phone,
                              @Param("money") double money);
    double selectMoneyBY_CName(String name);
    boolean updateMoneyBy_CName(@Param("name") String name,
                                @Param("money") double money);
    List<Customer> queryByCustomerName_fuzzy(@Param("name") String name);

    List<Customer> selectAllCustomer();
    @Select("select count(*) from customers;")
    int queryCustomerCount();

    Customer selectCustomerAcc(@Param("name") String name);
    @Update("update customers set customer_name = #{name} ,customer_password = #{pass} ,customer_Email = #{Email} ,customer_Phone = #{phone} where customer_name = #{bakName};")
    int updateCustomerAll_ByName(@Param("name") String name,
                                 @Param("pass") String pass,
                                 @Param("Email") String Email,
                                 @Param("phone") String phone,
                                 @Param("bakName") String bakName);
    String forgotUsername_SelectUsername(@Param("phone") String phone, @Param("email") String email);

}
