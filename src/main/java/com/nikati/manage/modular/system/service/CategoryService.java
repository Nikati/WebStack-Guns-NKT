package com.nikati.manage.modular.system.service;


import java.util.List;

import com.nikati.manage.modular.system.model.Category;

/**
 * @Author jsnjfz
 * @Date 2019/7/21 15:06
 * 分类业务接口
 */
public interface CategoryService {

    /**
     * 查询所有分类
     * @return
     */
    List<Category> select(int page, int pageSize, String query);

    /**
     * 查询数据总数
     * @return
     */
    int selectCount(String query);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Category selectById(int id);

    /**
     * 添加一个分类
     * @param category
     * @return
     */
    boolean insert(Category category);

    /**
     * 更新一个分类
     * @param category
     * @return
     */
    boolean update(Category category);

    /**
     * 删除指定id的分类
     * @param id
     * @return
     */
    boolean delete(int id);

}
