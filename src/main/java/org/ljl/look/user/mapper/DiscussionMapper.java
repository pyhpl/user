package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.user.entity.Discussion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiscussionMapper {
    @Select("SELECT * FROM discussion WHERE belong_to_activity=#{belongToActivity}::uuid")
    List<Discussion> selectByBelongToActivity(@Param("belongToActivity") String belongToActivity);

    @Insert("INSERT INTO discussion VALUES(#{uuid}::uuid, #{belongToActivity}::uuid, #{belongToDiscussion}::uuid, #{fromUser}, #{toUser}, #{contents}, #{likeCount}, #{dislikeCount}, #{discussDate}, #{valid})")
    void insert(Discussion discussion);

    @Select("SELECT count(*) FROM discussion WHERE belong_to_activity=#{belongToActivity}::uuid")
    int countByBelongToActivity(@Param("belongToActivity") String belongToActivity);
}
