package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.user.entity.TopicFocus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicFocusMapper {

    @Insert("INSERT INTO topic_focus VALUES(#{uuid}::uuid, #{fromUser}, #{topicUuid}::uuid, #{focusDate}, #{valid})")
    void insert(TopicFocus topicFocus);

    @Update("UPDATE topic_focus SET valid=${@org.ljl.look.user.configuration.ConstConfig@UNVALID} WHERE uuid=#{uuid}::uuid")
    void deleteByUuid(@Param("uuid") String uuid);

    @Select("SELECT * FROM topic_focus WHERE from_user=#{fromUser} AND topic_uuid=#{topicUuid}::uuid")
    TopicFocus selectByFromUserAndTopicUuid(@Param("fromUser") String fromUser, @Param("topicUuid") String topicUuid);

    @Select("SELECT * FROM topic_focus WHERE from_user=#{fromUser} AND topic_uuid=#{topicUuid}::uuid AND valid=${@org.ljl.look.user.configuration.ConstConfig@VALID}")
    TopicFocus selectByFromUserAndTopicUuidAndValid(@Param("fromUser") String fromUser, @Param("topicUuid") String topicUuid);

    @Update("UPDATE topic_focus SET valid=#{valid} WHERE uuid=#{uuid}::uuid")
    void updateValidByUuid(@Param("valid") short valid, @Param("uuid") String uuid);

    @Select("SELECT topic_uuid FROM topic_focus WHERE valid=${@org.ljl.look.user.configuration.ConstConfig@VALID} GROUP BY topic_uuid ORDER BY count(*)")
    List<String> selectTopicUuidGroupByTopicUuidOrderByCount();

    @Select("SELECT * FROM topic_focus WHERE from_user=#{fromUser} AND valid=${@org.ljl.look.user.configuration.ConstConfig@VALID}")
    List<TopicFocus> selectByFromUser(@Param("fromUser") String fromUser);

}
