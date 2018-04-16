package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.user.entity.TopicFocus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicFocusMapper {

    @Select("SELECT * FROM topic_focus ORDER BY #{orderBy} #{order} LIMIT #{number}")
    List<TopicFocus> selectByCondition(@Param("orderBy") String orderBy, @Param("order") String order, @Param("number") int number);

}
