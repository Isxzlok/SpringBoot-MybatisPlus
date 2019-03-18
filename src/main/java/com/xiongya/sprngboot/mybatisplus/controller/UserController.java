package com.xiongya.sprngboot.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiongya.sprngboot.mybatisplus.domain.User;
import com.xiongya.sprngboot.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author xiongzhilong
 * @Date 2019-03-1809:51
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //注入分页插件bean
    @Autowired
    private PaginationInterceptor page;

    /**
     * 插入
     * User实体类需继承Model
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertUser1")
    public User insert1(@RequestBody User user){
        user.insert();
        return user;
    }


    /**
     * 插入
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertUser")
    public User insert(@RequestBody User user){

        userMapper.insert(user);
        return user;
    }

    /**
     * http://127.0.0.1:8080/user/delete/7
     * 根据id删除用户，并返回删除的记录数
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public int deleteById(@PathVariable Integer id){

        return userMapper.deleteById(id);

    }

    /**
     * 通过id修改用户信息
     * @param user
     */
    @RequestMapping(value = "/update")
    public void updateById(@RequestBody User user){

        userMapper.updateById(user);
    }

    @RequestMapping(value = "/update1")
    public int updateByName(@RequestBody User user, @RequestParam String name){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getName,name);
        return userMapper.update(user, updateWrapper);
    }

    /**
     * http://127.0.0.1:8080/user/selete?name=xzl
     * 通过用户名找到该用户
     * @param name
     * @return
     */
    @RequestMapping(value = "select")
    public User selectByName(@RequestParam String name){
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .lambda()
                .eq(User::getName, name));
        return user;
    }

    /**
     * http://localhost:8080/user/pageSelect/1/3
     * 不带条件的分页查询
     * @param currentPage 当前页
     * @param pageSize  一页的记录数
     * @return
     */
    @RequestMapping(value="pageSelect/{currentPage}/{pageSize}")
    public IPage<User> pageSelect(@PathVariable Integer currentPage,
                           @PathVariable Integer pageSize){

        IPage<User> page = userMapper.selectPage(new Page<User>((currentPage - 1) * pageSize, pageSize), null);

        return page;
    }

    /**
     * http://localhost:8080/user/queryPageWithCondition/1/3
     * 带模糊查询条件的分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "queryPageWithCondition/{currentPage}/{pageSize}")
    public IPage<User> queryPageWithCondition(@PathVariable Integer currentPage,
                                              @PathVariable Integer pageSize){
        IPage<User> page = userMapper.selectPage(new Page<User>((currentPage - 1) * pageSize, pageSize),
                              new QueryWrapper<User>().lambda()
                              .like(User::getName, "o"));
        return page;

    }

}




