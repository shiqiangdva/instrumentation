package sq.test_instrumentation;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    private Handler mHandler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testFindAll();
//        btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sayHello();
//            }
//        });

        /***********************************socket*******************************************/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("192.168.1.154",23333);
                    // 接收
                    InputStream is = socket.getInputStream();
                    byte[] buffer = new byte[102400];
                    int len;
                    while ((len = is.read(buffer)) != -1){
                        String string = new String(buffer,0,len);
                        Message message = Message.obtain();
                        message.what = 233;
                        message.obj = string;
                        mHandler.sendMessage(message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
    /***************************socket*************************************/
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 233){
                String obj = (String) msg.obj;
                try {
                    Log.d("cc", "收到的数据为：" + obj);
//                    cmdText.setText(obj);
                    Runtime.getRuntime().exec(new String[]{"/system/bin/su","-c", obj});
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    /*****************************socket*************************************/

//    private String testFindAll() {
//        String s = "sss";
//        return s;
//    }
//
//    public void sayHello(){
//        TextView textView = (TextView) findViewById(R.id.textView);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        textView.setText("Hello," + editText.getText().toString() + "!");
//    }

}
