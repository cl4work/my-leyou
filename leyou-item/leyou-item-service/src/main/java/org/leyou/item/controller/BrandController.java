package org.leyou.item.controller;

import org.leyou.common.pojo.PageResult;
import org.leyou.item.pojo.Brand;
import org.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther ChenLei
 * @create 2020-10-4:21 PM
 */
@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @Author: Chen on 10/11/2020 5:45 PM
     * @return: org.springframework.http.ResponseEntity<org.leyou.common.pojo.PageResult < org.leyou.item.pojo.Brand>>
     * @Description:通过分页条件进行分页查询
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) boolean desc
    ) {

        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if (result == null || CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);

    }

    @PostMapping
    public ResponseEntity<Void> saveBand(Brand brand, @RequestParam("cids") List<Long> cids) {

        this.brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
