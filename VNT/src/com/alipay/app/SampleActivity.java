package com.alipay.app;

import java.net.URLEncoder;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.android.vnt.R;
import com.alipay.util.AlixId;
import com.alipay.util.BaseHelper;
import com.alipay.util.MobileSecurePayHelper;
import com.alipay.util.MobileSecurePayer;
import com.alipay.util.OrderUtil;
import com.alipay.util.Products;
import com.alipay.util.ResultChecker;

public class SampleActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {
	static String TAG = "AppDemo4";

	// 商品列表
	ListView mproductListView = null;
	// 商品列表adapter
	ProductListAdapter m_listViewAdapter = null;
	// 数据源
	ArrayList<Products.ProductDetail> mproductlist;
	// 安全支付启动时进度对话框
	private ProgressDialog mProgress = null;
	// 订单信息工具类
	private OrderUtil util = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote_service_binding);

		//
		// 检查支付宝安全服务是否已经安装
		MobileSecurePayHelper mspHelper = new MobileSecurePayHelper(this);
		mspHelper.detectMobile_sp();

		//
		// retrieve and show the product list.
		initProductList();
	}

	/**
	 * 数据源初始化以及显示到列表
	 */
	void initProductList() {
		Products products = new Products();
		this.mproductlist = products.retrieveProductInfo();

		mproductListView = (ListView) findViewById(R.id.ProductListView);
		m_listViewAdapter = new ProductListAdapter(this, this.mproductlist);
		mproductListView.setAdapter(m_listViewAdapter);
		mproductListView.setOnItemClickListener(this);
		mproductListView.setOnItemLongClickListener(this);
	}

	/**
	 * 商品列表点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//
		// 检查支付宝安全服务是否已经安装
		MobileSecurePayHelper mspHelper = new MobileSecurePayHelper(this);
		boolean isMobile_spExist = mspHelper.detectMobile_sp();
		// 初始化订单信息工具类
		util = new OrderUtil();

		if (!isMobile_spExist)
			return;

		// 检查partnerID seller 等信息
		if (!util.checkInfo()) {
			BaseHelper
					.showDialog(
							SampleActivity.this,
							"提示",
							"缺少partner或者seller，请在src/com/alipay/android/appDemo4/PartnerConfig.java中增加。",
							R.drawable.infoicon);
			return;
		}

		// 对订单进行支付
		try {
			// 生成订单
			String orderInfo = util.getOrderInfo(mproductlist, position);
			String signType = util.getSignType();
			// 订单签名
			String strsign = util.sign(signType, orderInfo);
			strsign = URLEncoder.encode(strsign);
			String info = orderInfo + "&sign=" + "\"" + strsign + "\"" + "&"
					+ util.getSignType();

			// 开始支付
			MobileSecurePayer msp = new MobileSecurePayer();
			// 返回是否正在支付
			boolean bRet = msp.pay(info, mHandler, AlixId.RQF_PAY, this);

			if (bRet) {
				// 显示进度对话框 表示正在进行支付
				closeProgress();
				mProgress = BaseHelper.showProgress(this, null, "正在支付", false,
						true);
			} else
				;
		} catch (Exception ex) {
			ex.printStackTrace();
			Toast.makeText(SampleActivity.this, R.string.remote_call_failed,
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		return false;
	}

	//
	// 接收支付结果
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				String strRet = (String) msg.obj;

				switch (msg.what) {
				case AlixId.RQF_PAY: {

					Log.e("in the handler ", "AlixId.RQF_PAY");

					//
					closeProgress();

					BaseHelper.log(TAG, strRet);

					try {
						String memo = "memo=";
						int imemoStart = strRet.indexOf("memo=");
						imemoStart += memo.length();
						int imemoEnd = strRet.indexOf(";result=");
						memo = strRet.substring(imemoStart, imemoEnd);
						// 验签
						ResultChecker resultChecker = new ResultChecker(strRet);

						int retVal = resultChecker.checkSign();

						// *****************
						// Log.e("resultCHecker", resultChecker.)
//						Log.e("strTET------------------------", strRet);
//						Log.e("retVal", retVal
//								+ "******************************");
						// *****************
						// 验签失败
						if (retVal == ResultChecker.RESULT_CHECK_SIGN_FAILED) {
							BaseHelper.showDialog(SampleActivity.this, "提示",
									getResources().getString(
											R.string.check_sign_failed),
									android.R.drawable.ic_dialog_alert);
						} else {
							BaseHelper.showDialog(SampleActivity.this, "提示",
									memo, R.drawable.infoicon);
						}

					} catch (Exception e) {
						e.printStackTrace();

						BaseHelper.showDialog(SampleActivity.this, "提示",
								strRet, R.drawable.infoicon);
					}
				}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	//
	// the OnCancelListener for lephone platform.
	public static class AlixOnCancelListener implements
			DialogInterface.OnCancelListener {
		Activity mcontext;

		public AlixOnCancelListener(Activity context) {
			mcontext = context;
		}

		public void onCancel(DialogInterface dialog) {
			mcontext.onKeyDown(KeyEvent.KEYCODE_BACK, null);
		}
	}

	//
	// close the progress bar
	void closeProgress() {
		try {
			if (mProgress != null) {
				mProgress.dismiss();
				mProgress = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			BaseHelper.log(TAG, "onKeyDown back");

			this.finish();
			return true;
		}

		return false;
	}

	//
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy");

		try {
			mProgress.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
