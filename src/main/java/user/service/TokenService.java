package user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import user.util.JsonTool;
import user.util.UuidTool;

import java.util.Map;

@Service
@Transactional
@Slf4j
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${wx-code-api.base-url}?appid=${wx-code-api.app-id}&secret=${wx-code-api.secret}&js_code={code}&grant_type=authorization_code")
    private String wxCodeApi;
    private final RestTemplate restTemplate = new RestTemplate();

    public String get(String code) {
        // 获取secret_key和openid
        String userIdentityStr = restTemplate.getForObject(wxCodeApi, String.class, code);
        // 字符串转map
        Map userIdentityMap = JsonTool.fromJson(userIdentityStr, new TypeReference<Map>() {});
        // 获取openid
        String openId = (String) userIdentityMap.get("openid");
        // 将openid使用md5加密生成token
        String token = DigestUtils.md5DigestAsHex((openId + UuidTool.getValue()).getBytes());
        log.info("token(" + openId + "): " + token);
        // 将token存入redis中
        stringRedisTemplate.opsForValue().set(token, openId);
        return token;
    }

    public void delete(String token) {
        stringRedisTemplate.delete(token);
    }
}
