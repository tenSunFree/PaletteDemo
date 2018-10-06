package com.home.palettedemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootLayout;
    private TextView textViewTitle, textViewBody;
    private ImageView imageView;
    private Palette.Swatch darkMutedSwatch;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationView();

        /** 生成含有16种颜色种类的Palette */
        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {

                /** 使用Palette对象, 获取解析出的颜色, 並套用至UI控件 */
                darkMutedSwatch = palette.getDarkMutedSwatch();
                rootLayout.setBackgroundColor(darkMutedSwatch.getRgb());
                textViewTitle.setTextColor(darkMutedSwatch.getTitleTextColor());
                textViewBody.setTextColor(darkMutedSwatch.getBodyTextColor());
            }
        });
    }

    private void initializationView() {
        rootLayout = findViewById(R.id.root_layout);
        textViewTitle = findViewById(R.id.text_view_title);
        textViewBody = findViewById(R.id.text_view_body);
        imageView = findViewById(R.id.image_view);
    }
}
