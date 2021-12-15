package autoTradBot;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.JSONObject;

import coincheck.CoinCheck;

public class GUI_test {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	//
	private static CoinCheck client;
	public static String apiKey;
	public static String apiSecret;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		apiKey = args[0];
		apiSecret = args[1];
		/*
		 * 2021-12-1 新規作成　コインチェックにAPIで接続しよう
		 * CoinCheckサーバーに接続
		 * 2021-12-15 追記　GUIインターフェースの作成
		 */
		client = new CoinCheck(apiKey, apiSecret);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_test window = new GUI_test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("数量（btc）");
		lblNewLabel.setBounds(34, 39, 90, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("金額（円）");
		lblNewLabel_1.setBounds(34, 84, 90, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("サーバ応答");
		lblNewLabel_2.setBounds(34, 126, 90, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(136, 34, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 79, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(34, 154, 382, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("買い");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ①ここに「買い」コードを追加
				double amount = Double.valueOf(textField.getText());	// 数量蘭の数値を読み込む
				double rate = Double.valueOf(textField_1.getText());	// 金額蘭の数値を読み込む
				try {
					giveAnOrder(rate, amount, "buy", "btc_jpy");	// 金額、数量を指定して「買い」注文を入れる
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(293, 34, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("売り");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ②ここに「売り」コードを追加
				double amount = Double.valueOf(textField.getText());	// 数量蘭の数値を読み込む
				double rate = Double.valueOf(textField_1.getText());	// 金額蘭の数値を読み込む
				try {
					giveAnOrder(rate, amount, "sell", "btc_jpy");	// 金額、数量を指定して「売り」注文を入れる
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(293, 79, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 新規注文の発行
	 * order_type オーダータイプ
	 * 		"buy" 指値注文 現物取引 買い
	 * 		"sell" 指値注文 現物取引 売り
	 * pair 取引ペア
	 * 		"btc_jpy"
	 */
	private static JSONObject giveAnOrder(
			double rate, double amount, String order_type, String pair) throws Exception {
		JSONObject orderObj = new JSONObject();
		orderObj.put("rate", rate);
		orderObj.put("amount", amount);
		orderObj.put("order_type", order_type);
		orderObj.put("pair", pair);
		return client.order().create(orderObj);
	}
}
