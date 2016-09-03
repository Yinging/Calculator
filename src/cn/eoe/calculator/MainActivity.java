package cn.eoe.calculator;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private TextView tvScreen;
	private List<Item> items = new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvScreen = (TextView) findViewById(R.id.tvScreen);
		findViewById(R.id.btn0).setOnClickListener(this);
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);
		findViewById(R.id.btn3).setOnClickListener(this);
		findViewById(R.id.btn4).setOnClickListener(this);
		findViewById(R.id.btn5).setOnClickListener(this);
		findViewById(R.id.btn6).setOnClickListener(this);
		findViewById(R.id.btn7).setOnClickListener(this);
		findViewById(R.id.btn8).setOnClickListener(this);
		findViewById(R.id.btn9).setOnClickListener(this);
		findViewById(R.id.btnX).setOnClickListener(this);
		findViewById(R.id.btnAdd).setOnClickListener(this);
		findViewById(R.id.btnSub).setOnClickListener(this);
		findViewById(R.id.btnResult).setOnClickListener(this);
		findViewById(R.id.btnClear).setOnClickListener(this);
		findViewById(R.id.btnDiv).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn0:
			tvScreen.append("0");
			break;

		case R.id.btn1:
			tvScreen.append("1");
			break;
		case R.id.btn2:
			tvScreen.append("2");
			break;
		case R.id.btn3:
			tvScreen.append("3");
			break;
		case R.id.btn4:
			tvScreen.append("4");
			break;
		case R.id.btn5:
			tvScreen.append("5");
			break;
		case R.id.btn6:
			tvScreen.append("6");
			break;
		case R.id.btn7:
			tvScreen.append("7");
			break;
		case R.id.btn8:
			tvScreen.append("8");
			break;
		case R.id.btn9:
			tvScreen.append("9");
			break;
		case R.id.btnAdd:
			items.add(new Item(Double
					.parseDouble(tvScreen.getText().toString()), Types.NUM));// 表示items[0]是数字a
			checkAndCompute();// 没有大于3，不执行
			items.add(new Item(0, Types.ADD));// 表示items[1]是+
			tvScreen.setText("");

			break;
		case R.id.btnSub:
			items.add(new Item(Double
					.parseDouble(tvScreen.getText().toString()), Types.NUM));
			checkAndCompute();
			items.add(new Item(0, Types.SUB));
			tvScreen.setText("");
			break;
		case R.id.btnX:
			items.add(new Item(Double
					.parseDouble(tvScreen.getText().toString()), Types.NUM));
			checkAndCompute();
			items.add(new Item(0, Types.X));
			tvScreen.setText("");
			break;
		case R.id.btnDiv:
			items.add(new Item(Double
					.parseDouble(tvScreen.getText().toString()), Types.NUM));
			checkAndCompute();
			items.add(new Item(0, Types.DIV));
			tvScreen.setText("");
			break;
		case R.id.btnClear:
			tvScreen.setText("");
			items.clear();
			break;
		case R.id.btnResult:
			items.add(new Item(Double
					.parseDouble(tvScreen.getText().toString()), Types.NUM));// 表示items[2]是数字b
			checkAndCompute();
			tvScreen.setText(items.get(0).value + "");
			items.clear();
			break;
		default:
			break;
		}
	}

	public void checkAndCompute() {
		if (items.size() >= 3) {
			double a = items.get(0).value;
			double b = items.get(2).value;
			int opt = items.get(1).type;
			items.clear();
			switch (opt) {
			case Types.ADD:
				items.add(new Item(a + b, Types.NUM));// 便于连续运算，a+b+c,
														// items.clear();使得a+b是items[0]
				break;
			case Types.SUB:
				items.add(new Item(a - b, Types.NUM));
				break;
			case Types.X:
				items.add(new Item(a * b, Types.NUM));
				break;
			case Types.DIV:
				items.add(new Item(a / b, Types.NUM));
				break;
			}
		}
	}
}
