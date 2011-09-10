package com.alipay.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderUtil {
	
	public String getOrderInfo(ArrayList<Products.ProductDetail> mproductlist, int position) {
		// 所有参数意义请参考文档，这里不做赘述
		String strOrderInfo = "partner=" + "\"" + PartnerConfig.PARTNER + "\"";
		strOrderInfo += "&";
		strOrderInfo += "seller=" + "\"" + PartnerConfig.SELLER + "\"";
		strOrderInfo += "&";
		strOrderInfo += "out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		strOrderInfo += "&";
		strOrderInfo += "subject=" + "\"" + mproductlist.get(position).subject
				+ "\"";
		strOrderInfo += "&";
		strOrderInfo += "body=" + "\"" + mproductlist.get(position).body + "\"";
		strOrderInfo += "&";
		strOrderInfo += "total_fee=" + "\""
				+ mproductlist.get(position).price.replace("一口价:", "") + "\"";
		strOrderInfo += "&";
		strOrderInfo += "notify_url=" + "\""
				+ "http://notify.java.jpxx.org/index.jsp" + "\"";

		return strOrderInfo;
	}

	//
	// get the out_trade_no for an order.
	String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
		String strKey = format.format(date);

		java.util.Random r = new java.util.Random();
		strKey = strKey + r.nextInt();
		strKey = strKey.substring(0, 15);
		return strKey;
	}

	//
	// sign the order info.
	public String sign(String signType, String content) {
		return Rsa.sign(content, PartnerConfig.RSA_PRIVATE);
	}

	//
	// get the sign type we use.
	public String getSignType() {
		String getSignType = "sign_type=" + "\"" + "RSA" + "\"";
		return getSignType;
	}

	//
	// get the char set we use.
	String getCharset() {
		String charset = "charset=" + "\"" + "utf-8" + "\"";
		return charset;
	}

	//
	// check some info.the partner,seller etc.
	public boolean checkInfo() {
		String partner = PartnerConfig.PARTNER;
		String seller = PartnerConfig.SELLER;
		if (partner == null || partner.length() <= 0 || seller == null
				|| seller.length() <= 0)
			return false;

		return true;
	}

}
