package cn.zhouyafeng.itchat4j.utils.enums;

public enum RetCodeEnum {

	NORMAL("0", "普通"),
	LOGIN_OUT("1102", "退出"),//cookie值无效
	LOGIN_WRITEFORLOGIN("1100", "未登录提示"),
	LOGIN_OTHERWHERE("1101", "其它地方登陆"),
	MOBILE_LOGIN_OUT("1102", "移动端退出"),
	MOBILE_ENVIR_ERROR("1103", "环境异常"),
	MOBILE_REQUEST_FREQUEMTLY("1105", "操作频繁"),
	UNKOWN("9999", "未知")
	;
	
	private String code;
	private String type;

	RetCodeEnum(String code, String type) {
		this.code = code;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

}
