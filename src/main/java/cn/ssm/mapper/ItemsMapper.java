package cn.ssm.mapper;

import cn.pojo.Items;
import cn.pojo.ItemsExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by 28029 on 2017/10/15.
 */
public interface ItemsMapper {
    public List<Items> selectAll();


    public Items selectById(@Param("id")int id);
    public List<Items> selectByExample(ItemsExample example);
    public int updateByPrimaryKeySelective(Items record);
    public int deleteByPrimaryKey(@Param("id")int id);
   // public int insert(Items record);
}
