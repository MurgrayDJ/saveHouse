package com.murgray.savehouse;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

//@Animate(Animation.CARD_LEFT_IN_DESC)
@NonReusable
@Layout(R.layout.rooms_recycler_layout)
public class GalleryItem {

    @View(R.id.imageView)
    public ImageView imageView;
    public Drawable mDrawable;

    public GalleryItem(Drawable drawable) {
        mDrawable = drawable;
    }

    @Resolve
    public void onResolved() {
        imageView.setImageDrawable(mDrawable);
    }
}
