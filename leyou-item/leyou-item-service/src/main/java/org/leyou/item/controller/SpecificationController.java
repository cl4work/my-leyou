package org.leyou.item.controller;

import org.leyou.item.pojo.SpecGroup;
import org.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther ChenLei
 * @create 2020-10-4:33 PM
 */
@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * @Author: Chen on 10/15/2020 4:42 PM
     * @param cid
     * @return: org.springframework.http.ResponseEntity<java.util.List<org.leyou.item.pojo.SpecGroup>>
     * @Description:根据分类id查询参数组
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid) {

        List<SpecGroup> groups = this.specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);

    }

}
