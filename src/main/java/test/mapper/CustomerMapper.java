package test.mapper;

import test.Customer;

import java.util.List;

/**
 * Created by 28029 on 2017/10/13.
 */
public interface CustomerMapper {
     public List<Customer> selectAllCustomer() throws  Exception;
}
