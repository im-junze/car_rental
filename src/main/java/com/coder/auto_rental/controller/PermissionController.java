package com.coder.auto_rental.controller;

import com.coder.auto_rental.entity.Permission;
import com.coder.auto_rental.service.IPermissionService;
import com.coder.auto_rental.utils.Result;
import jakarta.annotation.Resource;
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
@RequestMapping("/auto_rental/permission")
public class PermissionController {
    @Resource
    private IPermissionService permissionService;

    @GetMapping
    public Result list() {
        return Result.success().setData(permissionService.selectList());
    }

    @GetMapping("tree")
    public Result tree() {
        return Result.success().setData(permissionService.selectTree());
    }

    @PostMapping
    public Result save(@RequestBody Permission permission) {
        return permissionService.saveOrUpdate(permission) ? Result.success() : Result.fail();
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        return permissionService.removeById(id) ? Result.success() : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody Permission permission) {
        return permissionService.updateById(permission) ? Result.success() : Result.fail();
    }

    @GetMapping("/hasChildren/{id}")
    public Result hasChildren(@PathVariable Integer id) {
        return permissionService.
                hasChildren(id) ?
                Result.success().setMessage("有") :
                Result.success().setMessage("无");
    }


}
