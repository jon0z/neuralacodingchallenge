package app.joystickcontroller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.joystickcontroller.JoyStickClass;

public class Main extends Activity {
	RelativeLayout layout_joystick;
	TextView textView1, textView2;

	JoyStickClass js;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);

/*Get metrics from device to adjust how stick is displayed*/
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;


	    layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);

/*Setting Stick Size depending on metrics*/
        js = new JoyStickClass(getApplicationContext()
        		, layout_joystick, R.drawable.joystick_thumb);
	    if (width <= 320 || width < 600) {
            js.setStickSize(40, 40);

        }else if (width >= 600){
            js.setStickSize(50, 50);

        }

/*Joystick Movement definition*/
	    layout_joystick.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				js.drawStick(arg1);
				if(arg1.getAction() == MotionEvent.ACTION_DOWN
						|| arg1.getAction() == MotionEvent.ACTION_MOVE) {
					textView1.setText(String.format("@string/x%s", String.valueOf(js.getX())));
					textView2.setText(String.format("@string/y%s", String.valueOf(js.getY())));


					int direction = js.get4Direction();
					if(direction == JoyStickClass.STICK_UP) {
						textView1.setText("X: 0.0");
						textView2.setText("Y: 1.0");

					}
					 else if(direction == JoyStickClass.STICK_RIGHT) {
						textView1.setText("X: 1.0");
						textView2.setText("Y: 0.0");

					}
					 else if(direction == JoyStickClass.STICK_DOWN) {
						textView1.setText("X: 0.0");
						textView2.setText("Y: -1.0");

					}
					 else if(direction == JoyStickClass.STICK_LEFT) {
						textView1.setText("X: -1.0");
						textView2.setText("Y: 0.0");

					}else if(direction == JoyStickClass.STICK_CENTER) {
                        textView1.setText("X: 0.0");
                        textView2.setText("Y: 0.0");
                    }
				} else if(arg1.getAction() == MotionEvent.ACTION_UP) {
					textView1.setText("X : 0.0");
					textView2.setText("Y : 0.0");

				} else {
                    textView1.setText("X : 0.0");
                    textView2.setText("Y : 0.0");

                }
				return true;
			}
        });
    }
}
