package org.ljl.look.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ljl.look.user.entity.Tag;
import org.ljl.look.user.mapper.TagMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public List<Tag> gets(String userOpenId) {
        return tagMapper.selectByUserOpenId(userOpenId);
    }

    public void add(Tag tag) {
        tagMapper.insert(tag);
    }

    public void adds(List<Tag> tags) {
        tags.forEach(this::add);
    }

    public void delete(String uuid) {
        tagMapper.delete(uuid);
    }

    public void deletes(List<String> uuids) {
        uuids.forEach(this::delete);
    }
}
