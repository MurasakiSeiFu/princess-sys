package little.princess.sys.manager.web.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Token 工具类
 *
 * @author: murasakiseifu
 * @date: 2018/12/21 11:59 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:59 AM}
 */
public class TokenUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * EXPIRATION_TIME
     */
    public static Long EXPIRATION_TIME = 3600 * 1000L * 24 * 7; // 1h

    /**
     * SECRET
     */
    public static final String SECRET = "KeyTokenServer1";

    /**
     * 生成token
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static <T extends Token> String sign(T object) throws Exception {
        if (object.getExpireTime() != null) {
            EXPIRATION_TIME = object.getExpireTime() * 1000;
        }
        Map<String, Object> map = objectToMap(object);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String jwtString = Jwts.builder().setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString()).signWith(signatureAlgorithm, SECRET).compact();
        return jwtString;
    }

    /**
     * 解析token
     *
     * @param jwsToken
     * @param classT
     * @return
     * @throws Exception
     */
    public static <T extends Token> T unsign(String jwsToken, Class<T> classT) throws Exception {
        if (isValid(jwsToken)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwsToken);
            if (claimsJws.getBody().getExpiration().before(new Date(System.currentTimeMillis()))) {
                throw new Exception("token已失效");
            }
            return mapToObject(claimsJws.getBody(), classT);
        }
        return null;
    }

    /**
     * 验证token数据是否正确
     *
     * @param token
     * @return
     */
    public static boolean isValid(String token) {
        if (token == null) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            LOGGER.info("%s", e);
            return false;
        }
    }

    /**
     * 实体转map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    private static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] superDeclaredFields = obj.getClass().getSuperclass().getDeclaredFields();
        for (Field field : superDeclaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    /**
     * map转实体
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    private static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        T obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        setFieldsValue(obj, fields, map);
        Field[] fields2 = obj.getClass().getSuperclass().getDeclaredFields();
        setFieldsValue(obj, fields2, map);
        return obj;
    }

    /**
     * 实体字段赋值
     *
     * @param obj
     * @param declaredFields
     * @param map
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static void setFieldsValue(Object obj, Field[] declaredFields, Map<String, Object> map)
            throws IllegalArgumentException, IllegalAccessException {
        for (Field field : declaredFields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            Object value = map.get(field.getName());
            if (field.getType().equals(Long.class) && value != null && value.getClass().equals(Integer.class)) {
                value = Long.valueOf((Integer) value);
            }
            field.set(obj, value);
        }
    }
}
