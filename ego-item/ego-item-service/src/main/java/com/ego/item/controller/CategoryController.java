package com.ego.item.controller;


import com.ego.item.pojo.Category;
import com.ego.item.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;
//    /category/list?pid=0
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryListByPid(@RequestParam("pid") Long pid, HttpServletResponse response){
        //查询数据库
        List<Category> result = categoryService.findListByPid(pid);
        //如果有数据返回数据
        if (null!=result && result.size() > 0){
            return  ResponseEntity.ok(result);
        }
        //没有数据，返回204
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Category category){
        try {
            categoryService.save(category);
//            log.info("新增类别成功,信息如下:{}",category);
        }catch (Exception e){
//            log.error("新增类别异常:{}",e);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam("id") Long id,@RequestParam("name") String name){
        try {
            categoryService.update(id,name);
        }catch (Exception e){
//            log.error("更新异常:{}",e);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}

