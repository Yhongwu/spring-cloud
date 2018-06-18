package com.howard.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 部门表
 * Created by Howard Yao on 2018/5/27.
 */
//lombok的用法
@AllArgsConstructor //全参构造函数
@NoArgsConstructor //空参构造函数
@Data //ToString,@EqualsAndHashCode,所有字段的 @Getter 所有非final字段的@Setter ，@RequiredArgsConstructor
@Accessors(chain = true) //链式构造
public class Dept implements Serializable{
    /**
     * 主键
     */
    private Long deptNo;
    /**
     * 部门名
     */
    private String deptName;
    /**
     * 来自哪个数据库
     * 微服务下不同服务可能对应不同数据库
     */
    private String dbSource;

    public static void main(String[] args) {
        Dept dept  = new Dept();
        //链式构造
        dept.setDeptNo(1L).setDeptName("dept");
    }
}
