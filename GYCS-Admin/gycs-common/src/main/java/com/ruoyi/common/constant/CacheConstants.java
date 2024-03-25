package com.ruoyi.common.constant;

/**
 * 缓存的key 常量
 *
 * @author ruoyi
 */
public class CacheConstants
{
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * 获取用户的公益募资详细信息以及上传证明的信息包括审批信息的redis key
     */
    public static final String RAISE_FUND_AUDIT_KEY = "raise_fund_audit:";


    /**
     * 获取用户的公益募资的上传证明的redis key
     */
    public static final String CERTIFICATE_INFO_KEY =  "certificate_info:";


    /**
     * 获取公益募资活动的投票情况redis key
     */
    public static final String VOTE_INFO = "vote_info:";

    public static final String DONATINA_FUND_KEY = "donatina_fund:";
    public static final String USER_DONATION_BANK_TRANSFER_KEY = "user_donation_bank_transfer:";
    public static final String ACTIVITY_LIST_KEY = "activity_list_key:";
    public static final String RAISE_FUND_WAIT_VOTES = "raise_fund_wait_votes:";
}
