package org.leyou.item.controller;

import org.leyou.item.pojo.Category;
import org.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther ChenLei
 * @create 2020-10-4:01 PM
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * @Author: Chen on 10/10/2020 4:38 PM
     * @param: [pid]
     * @return: org.springframework.http.ResponseEntity<java.util.List < org.leyou.item.pojo.Category>>
     * @Description:
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

        //400:参数不合法
        if (pid == null || pid < 0) {
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.categoryService.queryCategoriesByPid(pid);
        if (CollectionUtils.isEmpty(categories)) {
            //404:资源未找到
            return ResponseEntity.notFound().build();
        }
        //200:正常响应
        return ResponseEntity.ok(categories);

    }
}
