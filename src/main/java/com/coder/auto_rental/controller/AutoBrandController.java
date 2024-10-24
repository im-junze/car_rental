package com.coder.auto_rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.auto_rental.entity.AutoBrand;
import com.coder.auto_rental.service.IAutoBrandService;
import com.coder.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/auto_rental/autoBrand")
public class AutoBrandController {
    @Resource
    private IAutoBrandService autoBrandService;

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable int start,
                         @PathVariable int size
            , @RequestBody AutoBrand autoBrand) {
        Page<AutoBrand> page = new Page<>(start, size);
        return Result.success(autoBrandService.searchByPage(page, autoBrand));
    }

    @PostMapping
    public Result save(@RequestBody AutoBrand autoBrand) {
        return autoBrandService.save(autoBrand) ? Result.success() : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody AutoBrand autoBrand) {
        return autoBrandService.updateById(autoBrand) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        return autoBrandService.removeByIds(list) ? Result.success() : Result.fail();
    }

}
