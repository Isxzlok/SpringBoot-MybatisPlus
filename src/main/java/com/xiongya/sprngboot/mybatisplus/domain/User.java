package com.xiongya.sprngboot.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Author xiongzhilong
 * @Date 2019-03-1808:37
 */
@Data
public class User extends Model<User> {

    private Integer id;

    private String name;

    private Integer age;

    private String email;

}
