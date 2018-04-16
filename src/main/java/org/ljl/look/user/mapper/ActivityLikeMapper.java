package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.user.entity.ActivityLike;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ActivityLikeMapper {

    @Insert("INSERT INTO activity_like VALUES(#{uuid}::uuid, #{fromUser}, #{activityUuid}::uuid, #{valid})")
    void insert(ActivityLike activityLike);

    @Select("SELECT * FROM activity_like WHERE activity_uuid=#{activityUuid}::uuid AND from_user=#{fromUser} AND valid=#{valid}")
    ActivityLike selectByActivityUuidAndFromUserAndValid(ActivityLike activityLike);

    @Update("UPDATE activity_like SET valid=${@org.ljl.look.user.configuration.ConstConfig@UNVALID} WHERE uuid=#{uuid}::uuid")
    void delete(@Param("uuid") String uuid);

    @Update("UPDATE activity_like SET valid=${@org.ljl.look.user.configuration.ConstConfig@VALID} WHERE uuid=#{uuid}::uuid")
    void updateValidByUuid(@Param("uuid") String uuid);

    @Select("SELECT count(*) FROM activity_like WHERE activity_uuid=#{activityUuid}::uuid " +
            "AND valid=${@org.ljl.look.user.configuration.ConstConfig@VALID}")
    int countByActivityUuid(@Param("activityUuid") String activityUuid);
}
