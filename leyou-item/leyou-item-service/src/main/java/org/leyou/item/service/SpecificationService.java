package org.leyou.item.service;

import org.leyou.item.mapper.SpecGroupMapper;
import org.leyou.item.mapper.SpecParamMapper;
import org.leyou.item.pojo.SpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChenLei
 * @create 2020-10-4:31 PM
 */
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * @Author: Chen on 10/15/2020 4:42 PM
     * @param cid
     * @return: org.springframework.http.ResponseEntity<java.util.List<org.leyou.item.pojo.SpecGroup>>
     * @Description:根据分类id查询参数组
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {

        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return this.groupMapper.select(record);

    }
}
