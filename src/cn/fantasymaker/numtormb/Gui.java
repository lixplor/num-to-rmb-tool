package cn.fantasymaker.numtormb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

public class Gui {

	private static Gui gui = null;
	private String inputString;
	private BigDecimal bDecimal;
	private double num;
	private NumToRmb ntb = new NumToRmb();
	private String resultTextString;

	private Gui() {

	}

	public static Gui getInstance() {
		if (gui == null) {
			gui = new Gui();
		}
		return gui;
	}

	public void init() {
		// frame设置
		Frame frame = new Frame("大写人民币金额转换器");
		frame.setBounds(0, 0, 350, 103);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});

		// 提示标签设置
		Label tipLabel = new Label("请在文本框中输入阿拉伯数字金额（最高到亿，最低2位小数）", Label.LEFT);

		// 结果标签设置
		Label resultlabel = new Label();
		resultlabel.setName("showLabel");
		resultlabel.setBackground(Color.LIGHT_GRAY);

		// 文本框设置
		TextField textField = new TextField(100);
		textField.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				inputString = textField.getText();
				if (!inputString.isEmpty()) {
					try {
						bDecimal = new BigDecimal(inputString);
						num = bDecimal.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();

						resultTextString = ntb.numToRMB(num);
						resultlabel.setText(resultTextString);
					} catch (ArrayIndexOutOfBoundsException aioobe) {
						resultlabel.setText("数字过大！请重新输入。");
					} catch (NumberFormatException nfe) {
						resultlabel.setText("您输入的数字有误！请重新输入。");
					}
				} else {
					resultlabel.setText("");
				}
			}
		});

		// 添加组件，显示
		frame.add(tipLabel, BorderLayout.NORTH);
		frame.add(textField, BorderLayout.CENTER);
		frame.add(resultlabel, BorderLayout.SOUTH);
		frame.setVisible(true);

	}
}
