package cn.rao.jedis.test;

import cn.rao.jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    @Test
    public void test1(){
        //1获取连接
        Jedis jedis=new Jedis("localhost",6379);
        jedis.set("username","zhangsan");
        jedis.close();
    }
    @Test

    //hash存储

    public void test2(){
        //1获取连接
        Jedis jedis=new Jedis("localhost",6379);
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","14");
        jedis.hset("user","gender","male");
        //获取
        String name = jedis.hget("user", "name");

        System.out.println(name);
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            //user
            String value = user.get(key);
            System.out.println(key+":"+value);
        }

        jedis.close();
    }
    @Test
    public void test3(){
        //1获取连接
        Jedis jedis=new Jedis("localhost",6379);
        jedis.lpush("mylist","a","b","c");
        jedis.rpush("mylist","a","b","c");
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);
        String element1 = jedis.lpop("mylist");
        System.out.println(element1);
        String element2 = jedis.rpop("mylist");
        System.out.println(element2);
        List<String> mylist2 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist2);


    }
    @Test
    public void test4(){
        //1获取连接
        Jedis jedis=new Jedis("localhost",6379);
        jedis.sadd("myset","java","php","c++");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        jedis.close();
    }

    /*
    jedis连接池使用
    * */
    @Test
    public void test7(){
        //1创建连接池对象
        JedisPool jedisPool=new JedisPool();
        //2获取连接
        Jedis jedis = jedisPool.getResource();
        jedis.set("hehe","haha");
        jedis.close();
    }
    @Test
    public void test8(){
        Jedis jedis = JedisPoolUtils.getJedis();
       jedis.set("hello", "world");
       jedis.close();
    }
}

