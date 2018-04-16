package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.user.entity.ActivityFocus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityFocusMapper {

    @Insert("INSERT INTO activity_focus VALUES(#{uuid}::uuid, #{fromUser}, #{activityUuid}::uuid, #{focusDate}, #{valid})")
    void insert(ActivityFocus activityFocus);

    @Select("SELECT * FROM activity_focus WHERE from_user=#{fromUser}")
    List<ActivityFocus> selectByFromUser(@Param("fromUser") String fromUser);

    @Select("SELECT * FROM activity_focus WHERE from_user=#{fromUser} AND activity_uuid=#{activityUuid}::uuid")
    ActivityFocus selectByFromUserAndActivityUuid(@Param("fromUser") String fromUser, @Param("activityUuid") String activityUuid);

    @Update("UPDATE activity_focus SET valid=#{valid} WHERE uuid=#{uuid}::uuid")
    void updateValidByUuid(@Param("valid") short valid, @Param("uuid") String uuid);

    @Update("UPDATE activity_focus SET valid=${@org.ljl.look.user.configuration.ConstConfig@UNVALID} WHERE uuid=#{uuid}::uuid")
    void deleteByUuid(@Param("uuid") String uuid);

    @Select("SELECT * FROM activity_focus WHERE activity_uuid=#{activityUuid}::uuid AND from_user=#{fromUser} AND valid=#{valid}")
    ActivityFocus selectByActivityUuidAndFromUserAndValid(ActivityFocus activityFocus);

}
