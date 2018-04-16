package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.user.entity.Join;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JoinMapper {
    @Select("SELECT * FROM joins WHERE activity_uuid=#{activityUuid}::uuid")
    List<Join> selectByActivityUuid(@Param("activityUuid") String activityUuid);

    @Select("SELECT count(*) FROM joins WHERE activity_uuid=#{activityUuid}::uuid")
    int countByActivityUuid(@Param("activityUuid") String activityUuid);

    @Insert("INSERT INTO joins VALUES(#{uuid}::uuid, #{fromUser}, #{activityUuid}::uuid, #{joinDate}, #{valid})")
    void insert(Join join);

    @Select("SELECT * FROM joins WHERE from_user=#{fromUser} AND activity_uuid=#{activityUuid}::uuid")
    Join selectByFromUserAndActivityUuid(@Param("fromUser") String fromUser, @Param("activityUuid") String activityUuid);

    @Update("UPDATE joins SET valid=#{valid} WHERE uuid=#{uuid}::uuid")
    void updateValidByUuid(@Param("valid") short valid, @Param("uuid") String uuid);

    @Update("UPDATE joins SET valid=${@org.ljl.look.user.configuration.ConstConfig@UNVALID} WHERE uuid=#{uuid}::uuid")
    void deleteByUuid(@Param("uuid") String uuid);

    @Select("SELECT * FROM joins WHERE activity_uuid=#{activityUuid}::uuid AND from_user=#{fromUser} AND valid=#{valid}")
    Join selectByActivityUuidAndFromUserAndValid(Join join);

}
