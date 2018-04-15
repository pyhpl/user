package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
