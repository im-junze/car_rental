package com.coder.auto_rental.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coder.auto_rental.entity.Dept;
import com.coder.auto_rental.service.IDeptService;
import com.coder.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auto_rental/dept")
public class DeptController {
    @Resource
    private IDeptService servicedDept;

    @PostMapping
    public Result list(@RequestBody Dept dept) {
        return Result.success(servicedDept.selectList(dept));
    }

    @GetMapping
    public Result tree() {
        return Result.success(servicedDept.selectTree());
    }

    @PostMapping("save")
    public Result save(@RequestBody Dept dept) {
        return servicedDept.save(dept) ? Result.success() : Result.fail();
    }

    @PutMapping()
    public Result update(@RequestBody Dept dept) {
        return   servicedDept.update(dept, new QueryWrapper<Dept>().eq("id", dept.getId())) ? Result.success() : Result.fail();
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        return servicedDept.removeById(id) ? Result.success() : Result.fail();
    }
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        return  servicedDept.haschildren(id) ?  Result.success().setMessage("有") : Result.success().setMessage("无");
    }
}
