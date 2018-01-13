package com.gmail.aray.chou.mybatis.enumeration.type.bug.demo;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhouhongyang@zbj.com on 1/11/2018.
 */
public interface BlogMapper {
    @Insert("insert into blog (id,title,status,type) values (#{id},#{title}," +
            " #{status}," +
            " #{type})")
    int insertBlog(Blog blog);

    @Update(" update blog set status=#{status} where type=#{type}")
    int updateBlog(@Param("status") BlogStatus status, @Param("type") BlogType type);

    @Select("select * from  blog  where type=#{type}")
    List<Blog> select(@Param("type") BlogType type);

    @Delete("delete blog where  type=#{type}")
    int delete(@Param("type") BlogType type);
}
