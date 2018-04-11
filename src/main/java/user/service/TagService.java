package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.entity.Tag;
import user.mapper.TagMapper;
import user.util.UuidTool;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public List<String> gets(String openId) {
        return tagMapper.selectByOpenId(openId).stream()
            .map(Tag::getName).collect(Collectors.toList());
    }

    public void add(Tag tag) {
        tagMapper.insert(tag);
    }

    public void adds(List<Tag> tags) {
        tags.forEach(tagMapper::insert);
    }

    public void delete(String uuid) {
        tagMapper.delete(uuid);
    }
}
