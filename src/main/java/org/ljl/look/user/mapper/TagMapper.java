package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.user.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    @Select("SELECT uuid, name FROM tag WHERE user_open_id=#{userOpenId} AND valid=1")
    List<Tag> selectByUserOpenId(@Param("userOpenId") String userOpenId);

    @Insert("INSERT INTO tag VALUES(#{uuid}::uuid, #{userOpenId}, #{name}, #{createDate}, #{valid})")
    void insert(Tag tag);

    @Delete("DELETE FROM tag WHERE uuid=#{uuid}::uuid")
    void delete(@Param("uuid") String uuid);
}


