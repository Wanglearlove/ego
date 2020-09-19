package com.ego.item.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ego.common.PageResult;
import com.ego.item.mapper.BrandMapper;
import com.ego.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BrandService {
    @Resource
    private BrandMapper brandMapper;
    /**
     * 分页
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param descending
     * @param key
     * @return
     */
    public PageResult<Brand> page(Integer pageNo, Integer pageSize, String sortBy, Boolean descending, String key) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        //过滤
        if (StringUtils.isNotBlank(key)){
            //模糊查询name，精确查询letter
            queryWrapper.like("name",key).or().eq("letter",key.toUpperCase());
        }
        //排序条件
        if (StringUtils.isNotBlank(sortBy) && descending != null){
            queryWrapper.orderBy(true,!descending,sortBy);
        }
        Page<Brand> brandPage = brandMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
        return new PageResult<>(brandPage.getTotal(),brandPage.getRecords());
    }
}
