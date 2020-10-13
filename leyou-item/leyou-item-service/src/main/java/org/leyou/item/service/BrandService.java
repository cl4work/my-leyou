package org.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.leyou.common.pojo.PageResult;
import org.leyou.item.mapper.BrandMapper;
import org.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @auther ChenLei
 * @create 2020-10-4:20 PM
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, boolean desc) {

        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询，或根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        //包装成PageInfo对象
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        //包装成分页结果集返回
        return new PageResult<>(brandPageInfo.getTotal(), brandPageInfo.getList());
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {

        // 先新增brand
        this.brandMapper.insertSelective(brand);

        //再新增中间表
        cids.forEach(cid -> {
            brandMapper.insertCategoryAndBrand(cid,brand.getId());
        });
    }
}
