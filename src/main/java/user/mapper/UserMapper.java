package user.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import user.entity.User;

@Mapper
@Repository
public interface UserMapper {
    @Insert("INSERT INTO users VALUES(#{openId}, #{name}, #{avatar})")
    void insert(User user);

    @Select("SELECT * FROM users WHERE open_id=#{openId}")
    User select(@Param("openId") String openId);

    @Update("UPDATE users SET name=#{name}, avatar=#{avatar} WHERE open_id=#{openId}")
    void update(User user);
}
