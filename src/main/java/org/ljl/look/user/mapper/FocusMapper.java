package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.user.entity.Focus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FocusMapper {

    @Insert("INSERT INTO focus VALUES(#{fromUser}, #{activityUuid}::uuid, #{focusDate}, #{valid}, #{uuid}::uuid)")
    void insert(Focus focus);

    @Select("SELECT * FROM focus WHERE from_user=#{fromUser}")
    List<Focus> selectByFromUser(@Param("fromUser") String fromUser);

    @Select("SELECT * FROM focus WHERE from_user=#{fromUser} AND activity_uuid=#{activityUuid}::uuid")
    Focus selectByFromUserAndActivityUuid(@Param("fromUser") String fromUser, @Param("activityUuid") String activityUuid);

    @Update("UPDATE focus SET valid=#{valid} WHERE uuid=#{uuid}::uuid")
    void updateValidByUuid(@Param("valid") short valid, @Param("uuid") String uuid);

    @Update("UPDATE focus SET valid=${@org.ljl.look.user.configuration.ConstConfig@UNVALID} WHERE uuid=#{uuid}::uuid")
    void deleteByUuid(@Param("uuid") String uuid);
}
