package edu.beihua.KarryCode.test;

import edu.beihua.KarryCode.DBCon.DBCon;
import edu.beihua.KarryCode.entity.Customer;
import edu.beihua.KarryCode.mapper.ICustomerMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class test_for_Mybatis {
    public static void main(String[] args) {
        DBCon dbCon = new DBCon();
        SqlSessionFactory sqlSessionFactory = dbCon.sqlSessionFactory();
        Customer customer;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICustomerMapper iCustomerMapper = session.getMapper(ICustomerMapper.class);
            customer = iCustomerMapper.selectBy_CName_CPass("lkr","123456");
        }
        //System.out.println(customer.getCustomer_password());
        System.out.println(customer.toString());
    }
}
