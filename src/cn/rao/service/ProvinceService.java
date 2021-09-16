package cn.rao.service;

import cn.rao.domain.Province;

import java.util.List;

public interface ProvinceService {

    public List<Province> findAll();
    public String findAllJson();
}
