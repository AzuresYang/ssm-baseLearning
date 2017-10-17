package test;

import org.apache.ibatis.session.SqlSession;
import test.mapper.CustomerMapper;
import cn.ssm.DBTools;

import java.util.List;

/**
 * Created by 28029 on 2017/10/13.
 */
public class CustomerService {
    public static void main(String[] args) {
        for(Customer item:selectAllCustomer()){
            System.out.println(item.toString());
        }
    }
    private static List<Customer> selectAllCustomer(){
        SqlSession session = DBTools.getSession();
        CustomerMapper mapper = session.getMapper(CustomerMapper.class);
        try {
            List<Customer> user=mapper.selectAllCustomer();
            System.out.println("选择所有用户成功");
            session.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally{
            session.close();
        }
        return null;
    }
}
