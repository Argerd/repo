package ru.argerd.repo.detailDescriptionScreen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.argerd.repo.R;

public class DetailScreenActivity extends AppCompatActivity {
    public static final String TITLE_EXTRA = "title";

    private Toolbar toolbar;
    private TextView detailsToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        toolbar = findViewById(R.id.details_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener((back) -> {
            onBackPressed();
        });

        String title = getIntent().getStringExtra(TITLE_EXTRA);

        detailsToolbarTitle = findViewById(R.id.details_toolbar_title);
        detailsToolbarTitle.setText(title);
    }
}
