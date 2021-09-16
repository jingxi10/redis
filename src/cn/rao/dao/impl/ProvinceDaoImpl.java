package cn.rao.dao.impl;

import cn.rao.dao.ProvinceDao;
import cn.rao.domain.Province;
import cn.rao.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAll() {
        //定义sql
        String sql="select * from province";
        //执行sql
        List<Province> list= template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;

    }
}
