package pl.naniewicz.wrocloveplaces.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.model.Place;

/**
 * Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PLACE = "EXTRA_PLACE";

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.backdrop) ImageView mImageViewBackdrop;
    @Bind(R.id.text_view_place_description) TextView mTextViewPlaceDescription;
    @Bind(R.id.text_view_place_review) TextView mTextViewPlaceReview;

    private Place mPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);
        getPlaceFromIntent();
        setupToolbar();
        setContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity_actions, menu);
        return true;
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.fab)
    public void onFabClick() {
        Toast.makeText(this, R.string.msg_details_fab, Toast.LENGTH_SHORT).show();
    }

    private void getPlaceFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            mPlace = intent.getParcelableExtra(EXTRA_PLACE);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbarLayout.setTitle(mPlace.getPlaceName());
        loadBackdrop();
    }

    private void loadBackdrop() {
        Picasso.with(this).load(mPlace.getDrawableRes()).centerCrop().fit().into(mImageViewBackdrop);
    }

    private void setContent() {
        mTextViewPlaceDescription.setText(mPlace.getDescription());
        mTextViewPlaceReview.setText(mPlace.getReview());
    }

}
