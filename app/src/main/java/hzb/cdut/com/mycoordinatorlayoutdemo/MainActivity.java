package hzb.cdut.com.mycoordinatorlayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListener();
    }

    private void setListener() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出Snackbar右滑可以隐藏
                final Snackbar snackbar = Snackbar.make(v, "点击进入第二个Activity", Snackbar.LENGTH_LONG);
                snackbar.setAction("sure", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        MainActivity.this.startActivity(intent);
                        // 这样设置FloatingActionButton才会在Snackbar弹出后下移
                        snackbar.getView().setVisibility(View.GONE);
                        //                        snackbar.dismiss(); // 没有效果
                    }
                }).show();
            }
        });

//        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "Snackbar可以左滑取消哦~", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
