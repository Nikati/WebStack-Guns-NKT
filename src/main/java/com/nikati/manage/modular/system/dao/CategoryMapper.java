package com.nikati.manage.modular.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nikati.manage.core.common.node.MenuNode;
import com.nikati.manage.core.common.node.ZTreeNode;
import com.nikati.manage.modular.system.model.Category;

public interface CategoryMapper extends BaseDao<Category> {

    List<Category> getCatogry(Map map);

    List<MenuNode> getCatogryNode(Map map);

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();
}