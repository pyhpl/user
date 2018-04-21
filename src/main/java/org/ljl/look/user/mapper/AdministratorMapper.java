package org.ljl.look.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.user.entity.Administrator;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdministratorMapper {

    @Select("SELECT * FROM administrator WHERE open_id=#{openId}")
    Administrator selectByOpenId(@Param("openId") String openId);

}
