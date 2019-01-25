package little.princess.sys.manager.clientsys.web.message;

import com.alibaba.fastjson.JSON;
import little.princess.sys.manager.util.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 请求响应统一返回
 *
 * @author: murasakiseifu
 * @date: 2018/12/21 11:41 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:41 AM}
 */
public class ResponseMessage {

    private static final long serialVersionUID = 8992436576262574064L;

    private Logger logger = LoggerFactory.getLogger(ResponseMessage.class);

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 反馈数据
     */
    private Object data;

    /**
     * 反馈信息
     */
    private String message;

    /**
     * 响应码
     */
    private int code;

    /**
     * 过滤字段：指定需要序列化的字段
     */
    private transient Map<Class<?>, Set<String>> includes;

    /**
     * 过滤字段：指定不需要序列化的字段
     */
    private transient Map<Class<?>, Set<String>> excludes;

    private transient boolean onlyData;

    private transient String callback;

    /**
     * toMap
     *
     * @date 2018/8/9 下午3:30
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", this.success);
        if (data != null) {
            map.put("data", this.getData());
        }
        if (message != null) {
            map.put("message", this.getMessage());
        }
        map.put("code", this.getCode());
        return map;
    }

    protected ResponseMessage(String message) {
        this.code = 500;
        this.message = message;
        this.success = false;
    }

    protected ResponseMessage(boolean success, Object data) {
        this.code = success ? 200 : 500;
        this.data = data;
        this.success = success;
    }

    protected ResponseMessage(boolean success, Object data, int code) {
        this(success, data);
        this.code = code;
    }

    /**
     * include
     *
     * @date 2018/8/9 下午3:30
     * @param: [type, fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage include(Class<?> type, String... fields) {
        return include(type, Arrays.asList(fields));
    }
//
//    public static void main(String[] args) {
//        String o = "aaaaa.bbbbb.cccc.ddddd";
//        String[] op = o.split("[.]", 2);
//        for (String str : op) {
//            System.out.println(str);
//        }
//    }

    /**
     * include
     *
     * @date 2018/8/9 下午3:31
     * @param: [type, fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage include(Class<?> type, Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String[] tmp = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        include(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                    logger.info("%s", e);
                }
            } else {
                getStringListFormMap(includes, type).add(field);
            }
        });
        return this;
    }

    /**
     * exclude
     *
     * @date 2018/8/9 下午3:31
     * @param: [type, fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage exclude(Class<?> type, Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String[] tmp = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        exclude(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                    logger.info("%s", e);
                }
            } else {
                getStringListFormMap(excludes, type).add(field);
            }
        });
        return this;
    }

    /**
     * exclude
     *
     * @date 2018/8/9 下午3:31
     * @param: [fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage exclude(Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class<?> type;
        if (data != null) {
            type = data.getClass();
        } else {
            return this;
        }
        exclude(type, fields);
        return this;
    }

    /**
     * include
     *
     * @date 2018/8/9 下午3:31
     * @param: [fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage include(Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class<?> type;
        if (data != null) {
            type = data.getClass();
        } else {
            return this;
        }
        include(type, fields);
        return this;
    }

    /**
     * exclude
     *
     * @date 2018/8/9 下午3:32
     * @param: [type, fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage exclude(Class<?> type, String... fields) {
        return exclude(type, Arrays.asList(fields));
    }

    /**
     * exclude
     *
     * @date 2018/8/9 下午3:32
     * @param: [fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage exclude(String... fields) {
        return exclude(Arrays.asList(fields));
    }

    /**
     * include
     *
     * @date 2018/8/9 下午3:32
     * @param: [fields]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage include(String... fields) {
        return include(Arrays.asList(fields));
    }

    /**
     * getStringListFormMap
     *
     * @date 2018/8/9 下午3:33
     * @param: [map, type]
     * @return: java.util.Set<java.lang.String>
     */
    protected Set<String> getStringListFormMap(Map<Class<?>, Set<String>> map, Class<?> type) {
        Set<String> list = map.get(type);
        if (list == null) {
            list = new HashSet<>();
            map.put(type, list);
        }
        return list;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    /**
     * setData
     *
     * @date 2018/8/9 下午3:33
     * @param: [data]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * toString
     *
     * @date 2018/8/9 下午3:33
     * @param: []
     * @return: java.lang.String
     */
    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, DateFormatUtils.PATTERN_DEFAULT_ON_SECOND);
    }

    public int getCode() {
        return code;
    }

    /**
     * setCode
     *
     * @date 2018/8/9 下午3:33
     * @param: [code]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * fromJson
     *
     * @date 2018/8/9 下午3:34
     * @param: [json]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public static ResponseMessage fromJson(String json) {
        return JSON.parseObject(json, ResponseMessage.class);
    }

    public Map<Class<?>, Set<String>> getExcludes() {
        return excludes;
    }

    public Map<Class<?>, Set<String>> getIncludes() {
        return includes;
    }

    /**
     * onlyData
     *
     * @date 2018/8/9 下午3:34
     * @param: []
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage onlyData() {
        setOnlyData(true);
        return this;
    }

    public void setOnlyData(boolean onlyData) {
        this.onlyData = onlyData;
    }

    public boolean isOnlyData() {
        return onlyData;
    }

    /**
     * callback
     *
     * @date 2018/8/9 下午3:35
     * @param: [callback]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public ResponseMessage callback(String callback) {
        this.callback = callback;
        return this;
    }

    public String getCallback() {
        return callback;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * ok
     *
     * @date 2018/8/9 下午3:35
     * @param: []
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public static ResponseMessage ok() {
        return ok(null);
    }

    /**
     * ok
     *
     * @date 2018/8/9 下午3:36
     * @param: [data]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public static ResponseMessage ok(Object data) {
        return new ResponseMessage(true, data);
    }

    /**
     * created
     *
     * @date 2018/8/9 下午3:36
     * @param: [data]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public static ResponseMessage created(Object data) {
        return new ResponseMessage(true, data, 201);
    }

    /**
     * error
     *
     * @date 2018/8/9 下午3:36
     * @param: [message]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public static ResponseMessage error(String message) {
        return new ResponseMessage(message);
    }

    /**
     * error
     *
     * @date 2018/8/9 下午3:36
     * @param: [message, code]
     * @return: cn.vbill.middleware.porter.manager.web.message.ResponseMessage
     */
    public static ResponseMessage error(String message, int code) {
        return new ResponseMessage(message).setCode(code);
    }
}
