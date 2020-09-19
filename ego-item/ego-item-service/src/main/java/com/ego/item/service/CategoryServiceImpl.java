package com.ego.item.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.ego.item.mapper.CategoryMapper;
import com.ego.item.pojo.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findListByPid(Long pid) {
        Map<String,Object> map = new HashMap<>();
        map.put("parent_id",pid);
        List<Category> result = categoryMapper.selectByMap(map);
        return result;
    }

    @Override
    public void save(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void update(Long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryMapper.updateById(category);
    }

    @Override
    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }
}
