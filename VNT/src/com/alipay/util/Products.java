/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 */

package com.alipay.util;

import java.util.ArrayList;

/**
 * 
 *
 */
public final class Products {
	public class ProductDetail {
		public String subject;
		public String body;
		public String price;
		int resId;
	}

	ArrayList<Products.ProductDetail> mproductlist = new ArrayList<Products.ProductDetail>();

	public ArrayList<Products.ProductDetail> retrieveProductInfo() {
		ProductDetail productDetail = null;

		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "报警服务";
		productDetail.body 		= "可以发出报警功能";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "导航服务";
		productDetail.body 		= "可以进行线路导航";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "语音服务";
		productDetail.body 		= "可以与服务总台进行语音通信";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "娱乐休闲";
		productDetail.body 		= "可以浏览图片、听音乐、看视频、玩游戏";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "紧急信息";
		productDetail.body 		= "可以查阅服务台发过来的紧急信息";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "便捷信息";
		productDetail.body 		= "可以查阅服务台发来的便捷信息 ";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "联网服务";
		productDetail.body 		= "可以进行网上冲浪";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "E-mail服务";
		productDetail.body 		= "可以收发邮件";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		
		//
		// x < 50
		productDetail = new ProductDetail();
		productDetail.subject 	= "保养服务";
		productDetail.body 		= "可以将车辆的一切信息发送到服务后台";
		productDetail.price 	= "一口价:0.01";
		productDetail.resId		= 30;
		mproductlist.add(productDetail);
		
		//
		return mproductlist;
	}
}
