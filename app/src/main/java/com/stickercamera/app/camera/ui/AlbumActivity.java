package com.stickercamera.app.camera.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.common.util.FileUtils;
import com.common.util.ImageUtils;
import com.common.util.StringUtils;
import com.customview.PagerSlidingTabStrip;
import com.github.skykai.stickercamera.R;
import com.stickercamera.AppConstants;
import com.stickercamera.app.camera.CameraBaseActivity;
import com.stickercamera.app.camera.fragment.AlbumFragment;
import com.stickercamera.app.model.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AlbumActivity extends CameraBaseActivity {

    @InjectView(R.id.indicator)
    PagerSlidingTabStrip tab;
    @InjectView(R.id.pager)
    ViewPager pager;
    private Map<String, Album> albums;
    private List<String> paths = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.inject(this);
        albums = ImageUtils.findGalleries(this, paths, 0);
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tab.setViewPager(pager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent result) {
        if (requestCode == AppConstants.REQUEST_CROP && resultCode == RESULT_OK) {
            Intent newIntent = new Intent(this, PhotoProcessActivity.class);
            newIntent.setData(result.getData());
            startActivity(newIntent);
        }
    }


    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return AlbumFragment.newInstance(albums.get(paths.get(position)).getPhotos());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Album album = albums.get(paths.get(position % paths.size()));
            if (StringUtils.equalsIgnoreCase(FileUtils.getInst().getSystemPhotoPath(),
                    album.getAlbumUri())) {
                return "Film Gallery";
            } else if (album.getTitle().length() > 13) {
                return album.getTitle().substring(0, 11) + "...";
            }
            return album.getTitle();
        }

        @Override
        public int getCount() {
            return paths.size();
        }
    }


}
