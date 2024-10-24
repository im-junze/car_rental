package com.coder.auto_rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.auto_rental.entity.AutoMaker;
import com.coder.auto_rental.service.IAutoMakerService;
import com.coder.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.apache.poi.sl.usermodel.AutoShape;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/auto_rental/autoMaker")
public class AutoMakerController {

    @Resource
    private IAutoMakerService autoMakerAutoMakerService;

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable int start,
                         @PathVariable int size,
                         @RequestBody AutoMaker autoMaker) {
        Page<AutoMaker> page = autoMakerAutoMakerService.search(start, size, autoMaker);
        return Result.success(page);

    }


}
