package edu.beihua.KarryCode.mapper;

import edu.beihua.KarryCode.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface IAdminMapper {
    @Select("select * from admin where admin.Admin_name = #{name} and admin.Admin_password = #{pass}")
    Admin Login(@Param("name") String name,
                @Param("pass") String pass);
}
