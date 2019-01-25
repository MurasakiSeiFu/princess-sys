package little.princess.sys.manager.web.token;

/**
 * @author: murasakiseifu
 * @date: 2018/12/21 11:58 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:58 AM}
 */
public class Token {

    private String uuid;

    private long createTime = System.currentTimeMillis();
    // 失效时间单位秒
    private Long expireTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

}
