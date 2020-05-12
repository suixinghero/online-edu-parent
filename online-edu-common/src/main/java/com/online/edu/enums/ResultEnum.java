package com.online.edu.enums;

/**
 * @author xujin
 * @package-name com.online.edu.enums
 * @createtime 2020-04-04 11:00
 * @description: 返回对象枚举
 */

public class ResultEnum{
    /**
     * 通用
     */
    public enum COMMON  {
        /**
         * 成功
         */
        SUCCESS(20000, "成功"),

        /**
         * 系统异常
         */
        ERROR(20001, "系统异常"),
        /**
         * 没有权限
         */
        NO_AUTH(30001,"没有权限"),
        /**
         * 唯一索引冲突
         */
        INDEX_DUPLICATE(0002, "唯一索引冲突"),

        /**
         * 参数错误
         */
        PARAM_WRONGFUL(0003, "参数错误"),

        /**
         * 没有任何变动
         */
        NO_CHANGE(0101, "没有任何变动");

        /**
         * 返回码
         */
        private Integer code;
        /**
         * 返回描述
         */
        private String info;

        COMMON(Integer code, String info) {
            this.code = code;
            this.info = info;
        }
        public Integer getCode() {
            return this.code;
        }
        public String getInfo() {
            return this.info;
        }

    }
}
