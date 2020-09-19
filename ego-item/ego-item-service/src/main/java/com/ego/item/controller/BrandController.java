package com.ego.item.controller;

import com.ego.common.PageResult;
import com.ego.item.pojo.Brand;
import com.ego.item.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController {
    @Resource
    private BrandService brandService;
//    page?pageNo=1&pageSize=5&sortBy=name&descending=false&key=11

    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> page(
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "descending",required = false) Boolean descending,
            @RequestParam("key") String key
    ){
        //查询数据库
        PageResult<Brand> result = brandService.page(pageNo,pageSize,sortBy,descending,key);
        //如果有数据返回数据
        if (result!=null && CollectionUtils.isNotEmpty(result.getItems())){
            return ResponseEntity.ok(result);
        }
        //没有数据，返回204
        return  ResponseEntity.noContent().build();
    }

}
