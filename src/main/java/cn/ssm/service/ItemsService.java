package cn.ssm.service;

import cn.pojo.Items;
import cn.pojo.ItemsExample;
import cn.ssm.DBTools;
import cn.ssm.mapper.ItemsMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by 28029 on 2017/10/15.
 */
public class ItemsService {
    SqlSession session;
    ItemsMapper itemsMapper;
    public ItemsService(){
        session = DBTools.getSession();
        itemsMapper = (ItemsMapper) session.getMapper(ItemsMapper.class);

    }
    public static void main(String[] args) {
        ItemsService ser = new ItemsService();
        List<Items> list;
        Items selectItem = ser.selectItemById(1);
        ItemsExample itemsExample = new ItemsExample();
        selectItem.setPrice(111.0f);
        selectItem.setId(null);
        selectItem.setName(null);
        selectItem.setCreatetime(null);

        ItemsExample.Criteria cri = itemsExample.createCriteria();
        if(selectItem.getName() !=null && !selectItem.getName().trim().equals(""))
            cri.andNameLike(selectItem.getName());
        if(selectItem.getPrice() >=0 )
            cri.andPriceGreaterThanOrEqualTo(selectItem.getPrice());
        itemsExample.or(cri);


    }

    public Items selectItemById(int id){
        return itemsMapper.selectById(id);
    }

    public List<Items> selectAll(){
        return itemsMapper.selectAll();
    }

    public int  updateItems(Items item){
      int i =  itemsMapper.updateByPrimaryKeySelective(item);
       session.commit();
       return i;
    }

    public List<Items> selectByItem(Items selectItem){
        ItemsExample itemsExample = new ItemsExample();
        ItemsExample.Criteria cri = itemsExample.createCriteria();
        if(selectItem.getName() !=null && !selectItem.getName().trim().equals(""))
            cri.andNameLike(selectItem.getName());
        if(selectItem.getPrice() != null && selectItem.getPrice() >= 0)
            cri.andPriceGreaterThanOrEqualTo(selectItem.getPrice());
        itemsExample.or(cri);

        System.out.println("查询条件：\n"+selectItem.toString());
        List<Items> itemsList = itemsMapper.selectByExample(itemsExample);

        System.out.println("查询成功，数量为："+itemsList.size());
        return itemsList;
    }
}
