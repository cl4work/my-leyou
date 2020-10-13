package org.leyou.item.mapper;

import org.apache.ibatis.annotations.Insert;
import org.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

/**
 * @auther ChenLei
 * @create 2020-10-4:18 PM
 */
public interface BrandMapper extends Mapper<Brand> {

    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) values (#{cid}, #{bid})")
    void insertCategoryAndBrand(@RequestParam("cid") Long cid, @RequestParam("bid") Long bid);
}
