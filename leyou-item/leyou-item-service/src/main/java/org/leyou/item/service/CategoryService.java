package org.leyou.item.service;

import org.leyou.item.mapper.CategoryMapper;
import org.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChenLei
 * @create 2020-10-3:59 PM
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @Author: Chen on 10/10/2020 4:34 PM
     * @param: [pid]
     * @return: java.util.List<org.leyou.item.pojo.Category>
     * @Description:根据父节点查询子节点
     */
    public List<Category> queryCategoriesByPid(Long pid) {


        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);

    }
}
