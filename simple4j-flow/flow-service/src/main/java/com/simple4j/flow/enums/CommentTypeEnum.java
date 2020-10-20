package com.simple4j.flow.enums;

/**
 * @author 庄金明
 * @date 2020年3月23日
 */
public enum CommentTypeEnum {
    /**
     * 过程意见类型
     */
    TJ("提交"), CXTJ("重新提交"), RL("认领"), QXRL("取消认领"), SP("审批"), WC("完成"), TH("退回"), CH("撤回"), ZC("暂存"), ZB("转办"), WP(
            "委派"), ZZ("终止"), CC("抄送"), YY("已阅");

    /**
     * 名称
     */
    private String name;

    public static String getEnumMsgByType(String type) {
        for (CommentTypeEnum e : CommentTypeEnum.values()) {
            if (e.toString().equals(type)) {
                return e.name;
            }
        }
        return "";
    }
    public static CommentTypeEnum getEnumByType(String type) {
        for (CommentTypeEnum e : CommentTypeEnum.values()) {
            if (e.toString().equals(type)) {
                return e;
            }
        }
        return null;
    }

    private CommentTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
