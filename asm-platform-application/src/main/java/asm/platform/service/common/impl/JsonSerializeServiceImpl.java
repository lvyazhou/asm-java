package asm.platform.service.common.impl;

import asm.platform.service.common.JsonSerializeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 吕亚洲 <lvyazhou@qq.cn>
 * @date 2022/11/11
 */
@Service
@Slf4j
public class JsonSerializeServiceImpl implements JsonSerializeService {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String serialize(Object object) {
        String jsonString = null;
        if (object != null) {
            try {
                jsonString = this.objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.error("Serialize object to json string occurred error. {}", ExceptionUtils.getStackTrace(e));
            }
        }
        return jsonString;
    }
}
