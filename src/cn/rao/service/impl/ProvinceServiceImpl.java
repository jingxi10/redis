package cn.rao.service.impl;

import cn.rao.dao.ProvinceDao;
import cn.rao.dao.impl.ProvinceDaoImpl;
import cn.rao.domain.Province;
import cn.rao.jedis.util.JedisPoolUtils;
import cn.rao.service.ProvinceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
   private ProvinceDao dao =new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
       return  dao.findAll();
    }

    @Override
    public String findAllJson() {
        Jedis jedis= JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        //2判断provice_json数据是否为null
        if(province_json==null||province_json.length()==0){
            //redis没有数据
            System.out.println("redis中没有数据");
            List<Province> ps = dao.findAll();
            //序列化json
            ObjectMapper mapper=new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province",province_json);
            jedis.close();
        }else {
            System.out.println("redis有数据,查询缓存");
        }
        return province_json;
    }
}
