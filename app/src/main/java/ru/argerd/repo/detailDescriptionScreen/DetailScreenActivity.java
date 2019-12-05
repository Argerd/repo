package ru.argerd.repo.detailDescriptionScreen;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class DetailScreenActivity extends AppCompatActivity {
    public static final String TITLE_EXTRA = "title";

    private Toolbar toolbar;
    private TextView detailsToolbarTitle;
    private RecyclerView listOfFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        toolbar = findViewById(R.id.details_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener((back) -> onBackPressed());

        String title = getIntent().getStringExtra(TITLE_EXTRA);

        detailsToolbarTitle = findViewById(R.id.details_toolbar_title);
        detailsToolbarTitle.setText(title);

        int[] photos = {R.drawable.photo_1, R.drawable.photo_2, R.drawable.photo_3,
                R.drawable.photo_4, R.drawable.photo_5};

        listOfFriends = findViewById(R.id.list_of_avatars);
        listOfFriends.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false));
        listOfFriends.setAdapter(new PeopleAdapter(this, photos, 103));

        Resources resources = getResources();
        listOfFriends.addItemDecoration(new Decorator(
                resources.getDimension(R.dimen.margin_first),
                resources.getDimension(R.dimen.margin_top_bottom),
                resources.getDimension(R.dimen.margin_left)));
    }
}
