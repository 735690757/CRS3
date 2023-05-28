package edu.beihua.KarryCode.mapper;

import edu.beihua.KarryCode.entity.Vehicle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IVehicleMapper {
    List<Vehicle> selectLimit10(@Param("start") int start,
                                @Param("end") int end);
    int selectVehiclesCount();
    Vehicle selectVehicle(@Param("license") String license);
    int updateVehicleState(@Param("license")String license,
                           @Param("sta") boolean sta);
    @Select("select Vehicle_state from vehicle where Vehicle_license = #{license}")
    boolean selectVehicleState(@Param("license") String license);
    @Select("select * from vehicle where Vehicle_name like CONCAT('%', #{VName}, '%');")
    List<Vehicle> queryByVehicleName_fuzzy(@Param("VName") String VName);
    @Update("update vehicle set vehicle.Vehicle_license = #{license} ,vehicle.Vehicle_name = #{name} ,vehicle.Vehicle_rent = #{rent} ,vehicle.Vehicle_state = #{state} where vehicle.Vehicle_license=#{bak}")
    int vehicleInformationModifiedAll(@Param("license") String license, @Param("name") String name, @Param("rent") double rent, @Param("state") boolean state, @Param("bak") String bak);
    int addVehicle(@Param("license") String license, @Param("name") String name, @Param("rent") double rent);
}
