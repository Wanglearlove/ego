package com.ego.item.service;

import com.ego.item.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 根据父id查询分类列表
     * @param pid 父id
     * @return 分类列表
     */
    List<Category> findListByPid(Long pid);

    /**
     * 保存新增类别
     * @param category
     */
    void save(Category category);

    void update(Long id, String name);

    void delete(Long id);
}
