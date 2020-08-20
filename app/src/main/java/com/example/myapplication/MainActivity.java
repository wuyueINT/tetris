package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.example.myapplication.R.id;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;


public final class MainActivity extends AppCompatActivity {

    private static final int MSG_START_GAME = 0;
    FakuaiProduct fakuai;
    protected FakuaiProduct nextFakuai;

    protected BoardAdapter adapter;
    protected BoardAdapter2 adapter2;
    private MyHandler handler;
    protected Integer[][] data;
    protected Integer[][] data2;

    private Button leftBtn;
    private Button rightBtn;
    private Button dropBtn;
    private Button resetBtn;
    private Button reshapeBtn;
    private RecyclerView rvGameArea;
    private RecyclerView rvNextFakuai;

    private Boolean isRunning = false;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        initView();
        initData();

        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fakuai!=null){
                    put02Position();
                    fakuai.leftMove();
                    if (putFakuai2List()){
                        adapter.notifyDataSetChanged();
                    } else {
                        put12Position();
                        fakuai.rightMove();
                    }
                }
            }
        });

        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fakuai!=null){
                    put02Position();
                    fakuai.rightMove();
                    if (putFakuai2List()){
                        adapter.notifyDataSetChanged();
                    } else {
                        fakuai.leftMove();
                        put12Position();
                    }
                }
            }
        });

        dropBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fakuai!=null){
                    put02Position();
                    fakuai.dropMove();
                    if (putFakuai2List()){
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "哇，慢一点", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        reshapeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fakuai!=null){
                    put02Position();
                    fakuai.reshape();
                    if (putFakuai2List()){
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        resetBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning){
                    handler.sendEmptyMessageDelayed(MSG_START_GAME, 1000);
                }
                isRunning = true;
            }
        });
    }

    private void initView() {
        leftBtn = findViewById(id.btn_left);
        rightBtn = findViewById(id.btn_right);
        dropBtn = findViewById(id.btn_drop);
        resetBtn = findViewById(id.btn_reset);
        reshapeBtn = findViewById(id.btn_reshape);
        rvGameArea = findViewById(id.rv_fakuai);
        rvNextFakuai = findViewById(id.rv_next);
    }

    private void initData() {
        //避免内存泄露的handler写法
        handler = new MyHandler(new MyHandler.HandlerCallback() {
            @Override
            public void handleMessage(Message msg) {
                handMes(msg);
            }
        });

        //初始化游戏页面的显示
        data = new Integer[30][10];
        for (int i=0; i<30; i++){
            for (int j=0; j<10; j++){
                data[i][j] = 0;
            }
        }
        adapter = new BoardAdapter(this, data);
        rvGameArea.setLayoutManager(new GridLayoutManager(this, 10));
        rvGameArea.setAdapter(adapter);

        //初始化下一个方块的页面显示
        data2 = new Integer[4][4];
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                data2[i][j] = 0;
            }
        }
        adapter2 = new BoardAdapter2(this, data2);
        rvNextFakuai.setLayoutManager(new GridLayoutManager(this, 4));
        rvNextFakuai.setAdapter(adapter2);
    }

    private void handMes(Message msg) {
        switch (msg.what){
            case MSG_START_GAME:
                initFakuai();
                put02Position();
                fakuai.downMove();
                if (putFakuai2List()){
                    adapter.notifyDataSetChanged();
                } else {
                    fakuai = null;
                }
                handler.sendEmptyMessageDelayed(MSG_START_GAME, 1000);
            default:
                break;
        }
    }

    //将方块的位置状态更新到游戏页面中
    private boolean putFakuai2List() {
        Integer[] a = fakuai.getPosition(); //二维数组，记录方块左下角的位置
        Integer[][] b = fakuai.getShape(); //二维数组，描述方块的形状

        if (b!=null){
            //将下一个方块的形状更新到显示页面
            for (int i=0; i<4; i++){
                System.arraycopy(b[i], 0, data2[i], 0, 4);
            }
            adapter2.notifyDataSetChanged();
            //接下来处理游戏页面
            //如果检测到有碰撞，则不移动方块
            for (int i=0; i<4; i++){
                for (int j=0; j<4; j++){
                    int x = a[0]-3+i;
                    int y = a[1]+j;
                    if (x<0 || y<0 || x>=30 || y>=10){
                        break;
                    }else if (x>=29){ //接触底部
                        return true;
                    } else if (data[x][y]==1 && b[i][j]==1){ //与其他方块碰撞
                        return false;
                    }
                }
            }
            //如果检测到没有碰撞，移动方块
            for (int i=0; i<4; i++){
                for (int j=0; j<4; j++){
                    int x = a[0]-3+i;
                    int y = a[1]+j;
                    if (x<0 || y<0 || x>=30 || y>=10){
                        break;
                    }else if (b[i][j]==1){
                        data[x][y] = b[i][j];
                    }
                }
            }
        }
        Integer[][] c = nextFakuai.getShape();
        if (c!=null){
            for (int i=0; i<4; i++){
                System.arraycopy(c[i], 0, data2[i], 0, 4);
            }
        }
        return true;
    }

    //将方块的位置置零
    private void put02Position() {
        Integer[] a = fakuai.getPosition(); //二维数组，记录方块左下角的位置
        Integer[][] b = fakuai.getShape(); //二维数组，描述方块的形状
        if (b!=null){
            for (int i=0; i<4; i++){
                for (int j=0; j<4; j++){
                    int x = a[0]-3+i;
                    int y = a[1]+j;
                    if (x>=0 && x<30 && y>=0 && y<10){
                        if (b[i][j]==1){
                            data[x][y] = 0;
                        }
                    }
                }
            }
        }
    }

    //将方块的位置置一
    private void put12Position() {
        Integer[] a = fakuai.getPosition(); //二维数组，记录方块左下角的位置
        Integer[][] b = fakuai.getShape(); //二维数组，描述方块的形状
        if (b!=null){
            for (int i=0; i<4; i++){
                for (int j=0; j<4; j++){
                    int x = a[0]-3+i;
                    int y = a[1]+j;
                    if (x>=0 && x<30 && y>=0 && y<10){
                        if (b[i][j]==1){
                            data[x][y] = 1;
                        }
                    }
                }
            }
        }
    }

    private void initFakuai() {
        if (fakuai==null && nextFakuai==null){
            fakuai = FakuaiFactoryKt.getProduct(UtilKt.getRandomNum(1, 7));
            nextFakuai = FakuaiFactoryKt.getProduct(UtilKt.getRandomNum(1, 7));
        } else if (fakuai==null && nextFakuai!=null){
            fakuai = nextFakuai;
            nextFakuai = FakuaiFactoryKt.getProduct(UtilKt.getRandomNum(1, 7));
        }
    }

}
