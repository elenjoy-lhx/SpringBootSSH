package com.lhx.util;

public class PayUtil {
	public static String translateCode(String code) {
		if (code.equals("00")) {
			return "支付成功";
		} else if (code.equals("01")) {
			return "交易异常，支付失败。详情请咨询95516";
		} else if (code.equals("02")) {
			return "您输入的卡号无效，请确认后输入";
		} else if (code.equals("03")) {
			return "发卡银行不支持，支付失败";
		} else if (code.equals("05")) {
			return "发卡行不予承兑";
		} else if (code.equals("06")) {
			return "您的卡已经过期，请使用其他卡支付";
		} else if (code.equals("11")) {
			return "您卡上的余额不足";
		} else if (code.equals("14")) {
			return "您的卡已过期或者是您输入的有效期不正确，支付失败";
		} else if (code.equals("15")) {
			return "您输入的银行卡密码有误，支付失败";
		} else if (code.equals("20")) {
			return "您输入的转入卡卡号有误，支付失败";
		} else if (code.equals("21")) {
			return "您输入的手机号或CVN2有误，支付失败";
		} else if (code.equals("22")) {
			return "操作有误";
		} else if (code.equals("25")) {
			return "原始交易查找失败";
		} else if (code.equals("30")) {
			return "报文格式错误";
		} else if (code.equals("36")) {
			return "交易金额超过网上银行交易金额限制，支付失败";
		} else if (code.equals("39")) {
			return "您已连续多次输入错误密码";
		} else if (code.equals("40")) {
			return "请与您的银行联系";
		} else if (code.equals("41")) {
			return "您的银行不支持认证支付，请选择快捷支付";
		} else if (code.equals("42")) {
			return "您的银行不支持普通支付，请选择快捷支付";
		} else if (code.equals("51")) {
			return "余额不足";
		} else if (code.equals("54")) {
			return "卡片过期";
		} else if (code.equals("55")) {
			return "密码错";
		} else if (code.equals("56")) {
			return "交易受限";
		} else if (code.equals("57")) {
			return "不允许持卡人进行的交易";
		} else if (code.equals("59")) {
			return "有作弊嫌疑";
		} else if (code.equals("71")) {
			return "交易无效，无法完成，支付失败";
		} else if (code.equals("75")) {
			return "连续多次输入密码错";
		} else if (code.equals("80")) {
			return "内部错误";
		} else if (code.equals("81")) {
			return "可疑报文";
		} else if (code.equals("82")) {
			return "验签失败";
		} else if (code.equals("83")) {
			return "超时";
		} else if (code.equals("84")) {
			return "订单不存在";
		} else if (code.equals("94")) {
			return "重复交易";
		}

		return "状态未知";
	}
}
