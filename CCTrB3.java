package autoTradBot;

import org.json.JSONObject;

import coincheck.CoinCheck;

public class CCTrB3 {
	private static CoinCheck client;
	public static String apiKey;
	public static String apiSecret;

	public static void main(String[] args)  throws Exception {
		apiKey = args[0];
		apiSecret = args[1];
		/*
		 * 2021-12-1 新規作成　コインチェックにAPIで接続しよう
		 * CoinCheckサーバーに接続
		 */
		client = new CoinCheck(apiKey, apiSecret);
		
		// 取り引き板情報を取得
		JSONObject cp = client.ticker().all();
		
		// 残高を取得
		JSONObject balance = client.account().balance(); 
		
	}

}
