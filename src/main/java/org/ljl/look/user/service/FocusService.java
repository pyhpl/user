package org.ljl.look.user.service;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.Focus;
import org.ljl.look.user.mapper.FocusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FocusService {

    @Autowired
    private FocusMapper focusMapper;

    public void add(Focus focus) {
        Focus mayBeExistedFocus = focusMapper.selectByFromUserAndActivityUuid(focus.getFromUser(), focus.getActivityUuid());
        if (mayBeExistedFocus == null) { // 不存在则新建记录
            focusMapper.insert(focus);
        } else { // 已存在则更新
            focusMapper.updateValidByUuid(ConstConfig.VALID, mayBeExistedFocus.getUuid());
        }
    }

    public List<Focus> getsByFromUser(String fromUser) {
        return focusMapper.selectByFromUser(fromUser);
    }

    public void deleteByUuid(String uuid) {
        focusMapper.deleteByUuid(uuid);
    }
}
