package com.example.zhy.mvvmdemo.bean;

public class UserBean extends BaseBean{

    public User data;

    public class User{
        /**
         * 用户id
         */
        public String memberId;
        /**
         * 用户名
         */
        public String memberName;
        /**
         * 是否设置了手势密码
         */
        public String isGesturePassword;
        /**
         * 邮箱是否验证
         */
        public String isTrueMail;
        /**
         * 是否实名认证
         */
        public String isTrueMan;
        /**
         * 手机是否验证
         */
        public String isTruePhone;
        /**
         * 是否绑定银行卡
         */
        public String isTrueBankCard;
        /**
         * 绑定邮箱
         */
        public String email;
        /**
         * 真实姓名
         */
        public String trueName;
        /**
         * 身份证号
         */
        public String certCode;
        /**
         * 手机号
         */
        public String phone;
        /**
         * 银行卡开户行
         */
        public String bankName;
        /**
         * 银行卡号
         */
        public String bankCard;
        /**
         * 开户地（省）
         */
        public String bankProvince;
        /**
         * 开户地（市）
         */
        public String bankCity;
        /**
         * 海淀区上地支行
         */
        public String bankSubBankName;
        public String unusedCouponNum;
        public String taxCode;
        /**
         * 头像连接
         */
        public String headUrl;
        public String token;

        public String inviteCashTotal;
        public String inviteCashBalance;
        public String memberInviteCode;
        public String memberPoints;
        public String safePassword;
        public String isHfAccount;
        public String isBindCard;

        public String emailReal;
        public String trueNameReal;
        public String certCodeReal;
        public String mobile;
        public String mobileRel;
        public String usrCustId;
        public String usrMp;
        public String puid;//是否绑定哔咯账户
        public String principalConsumeState;

        @Override
        public String toString() {
            return "UserModel{" +
                    "memberId='" + memberId + '\'' +
                    ", memberName='" + memberName + '\'' +
                    ", isGesturePassword='" + isGesturePassword + '\'' +
                    ", isTrueMail='" + isTrueMail + '\'' +
                    ", isTrueMan='" + isTrueMan + '\'' +
                    ", isTruePhone='" + isTruePhone + '\'' +
                    ", isTrueBankCard='" + isTrueBankCard + '\'' +
                    ", email='" + email + '\'' +
                    ", trueName='" + trueName + '\'' +
                    ", certCode='" + certCode + '\'' +
                    ", phone='" + phone + '\'' +
                    ", bankName='" + bankName + '\'' +
                    ", bankCard='" + bankCard + '\'' +
                    ", bankProvince='" + bankProvince + '\'' +
                    ", bankCity='" + bankCity + '\'' +
                    ", bankSubBankName='" + bankSubBankName + '\'' +
                    ", headUrl='" + headUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "data=" + data +
                '}';
    }
}
