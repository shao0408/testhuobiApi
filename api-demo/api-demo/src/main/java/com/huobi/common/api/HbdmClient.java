package com.huobi.common.api;

import java.io.IOException;

import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbdmClient {
	  private static Logger logger = LoggerFactory.getLogger(HbdmClient.class);
	public static void main(String[] args) throws HttpException, IOException {
			
			/**
			 *  get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
			 */
			String api_key="6330ccf2-3ef30e50-82542b82-81bfa";  //huobi申请的apiKey
			String secret_key = "65c24e2b-5110379b-2fd136f6-80331";  //huobi申请的secretKey
			String url_prex = "http://api.hbdm.com";
			IHbdmRestApi futureGetV1 = new HbdmRestApiV1(url_prex);
			IHbdmRestApi futurePostV1 = new HbdmRestApiV1(url_prex, api_key,secret_key);

			//获取合约信息
			String contractInfo=futureGetV1.future_contract_info("BTC", "this_week", "");
			 logger.info("获取合约信息"+contractInfo);
			
			//获取合约指数信息
			String contractindex=futureGetV1.future_contract_index("BTC");
			logger.info("获取合约指数信息"+contractindex);
			
			//获取合约最高限价和最低限价
			String pricelimit=futureGetV1.future_price_limit("BTC_CW", "this_week", "");
			logger.info("获取合约最高限价和最低限价"+pricelimit);
			//获取当前可用合约总持仓量
			String openInterest=futureGetV1.future_open_interest("BTC_CW", "this_week", "");
			logger.info("获取当前可用合约总持仓量"+openInterest);
			
			//获取行情深度数据
			String marketDepth=futureGetV1.future_market_depth("BTC_CW","step0");
			logger.info("获取行情深度数据"+marketDepth);
			
			//获取K线数据
			String historyKline=futureGetV1.future_market_history_kline("BTC_CW","15min");
			logger.info("获取K线数据"+historyKline);
			
			//获取聚合行情
			String merged=futureGetV1.future_market_detail_merged("BTC_CW");
			logger.info("获取聚合行情"+merged);
			
			//获取市场最近成交记录
			String trade=futurePostV1.future_market_detail_trade("BTC_CW","10");
			logger.info("获取市场最近成交记录"+trade);
			
			//批量获取最近的交易记录
			String historTrade=futurePostV1.future_market_history_trade("BTC_CW","10");
			logger.info("批量获取最近的交易记录"+historTrade);
			//获取用户账户信息
			String accountInfo=futurePostV1.future_contract_account_info("BTC");
			logger.info("获取用户账户信息"+accountInfo);
			
			//获取用户持仓信息
			String positionInfo=futurePostV1.future_contract_position_info("BTC");
			logger.info("获取用户持仓信息"+positionInfo);
			
			//合约下单
			String contractOrder=futurePostV1.future_contract_order("BTC","this_week","BTC180907","","6759","12","buy","open","10","limit");
			
			logger.info("合约下单返回"+contractOrder);
			
			//合约取消订单
			String contractcancel=futurePostV1.future_contract_cancel("123556","");
			logger.info("合约取消订单"+contractcancel);
			
			//合约全部撤单
			String contractCancelall=futurePostV1.future_contract_cancelall("BTC");
			logger.info("合约取消订单"+contractCancelall);
			
			//获取合约订单信息
			String contractOrderInfo=futurePostV1.future_contract_order_info("123556","");
			logger.info("合约取消订单"+contractOrderInfo);
			
			//获取订单明细信息
			String detail=futurePostV1.future_contract_order_detail("BTC","123556","1","100");
			logger.info("获取订单明细信息"+detail);
			
			//获取合约当前未成交委托
			String openorders=futurePostV1.future_contract_openorders("BTC","1","100");
			logger.info("获取订单明细信息"+openorders);
			
			// 获取合约历史委托
			String orderDetail=futurePostV1.future_contract_hisorders("BTC","0","1","0","90","1","20");
			logger.info("获取订单明细信息"+orderDetail);
	}

}
