package asm.platform.service.common;

/**
 * Service for json serialize.
 *
 * @author 吕亚洲 <lvyazhou@qq.cn>
 * @date 2022/11/11
 */
public interface JsonSerializeService {

    /**
     * Serialize object to json string.
     *
     * @param object The object to serialize.
     * @return json string. if param: object is null, return json string is null.
     */
    String serialize(Object object);
}
