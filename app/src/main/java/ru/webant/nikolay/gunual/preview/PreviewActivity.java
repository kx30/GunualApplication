package ru.webant.nikolay.gunual.preview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ru.webant.nikolay.gunual.category.CategoryActivity;

import ru.webant.gunual.R;

public class PreviewActivity extends AppCompatActivity {

    private int mDotsCount;
    private ImageView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        Button continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("value", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isSkipped", true);
            editor.apply();
            Intent intent = new Intent(PreviewActivity.this, CategoryActivity.class);
            startActivity(intent);
        });

        initDots(adapter, viewPager);
    }

    private void initDots(ViewPagerAdapter adapter, ViewPager viewPager) {
        LinearLayout sliderDotsPanel = findViewById(R.id.dot_layout);
        mDotsCount = adapter.getCount();
        mDots = new ImageView[mDotsCount];
        for (int i = 0; i < mDotsCount; i++) {
            mDots[i] = new ImageView(this);
            mDots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 0, 12, 0);
            sliderDotsPanel.addView(mDots[i], params);
        }

        mDots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < mDotsCount; j++) {
                    mDots[j].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.inactive_dot));
                }
                mDots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
