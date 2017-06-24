package com.example.matthew.memoryleak_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<ImageView> list = new ArrayList<>();

    static MemoryLeak memoryLeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (memoryLeak == null) { //不断的创建对象;
            memoryLeak = new MemoryLeak();
            /*
            *  应用退出再次进入, 再次点击按钮, 发现: 没有从2M开始 而是 继续增加,
            *  说明内存并没有被回收;
            * */
        }
    }

    public void clickView(View view) {
        for (int i = 0; i < 10000; i++) {
            ImageView imageView = new ImageView(this);
            list.add(imageView);
            /*
            * 可以看到，刚开始系统分配了2M左右的内存，当点击一次Button之后，内存增加到8M，再次点击内存增加到24M左右。
            * 上述情况下，当我们按下返回退出Activity时，然后点击Init GC按钮执行垃圾回收操作，进程中的内存会重新回到2M.
            * */
        }
    }

    class MemoryLeak {  //不使用静态
        void doSomeThing() {
            System.out.println("Wheee!!!");
        }
    }
}
