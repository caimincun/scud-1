package com.team.dream.runlegwork.utils;

import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class PictureUtils {

	public static void cleanImageView(ImageView imageView) {
		if (!(imageView.getDrawable() instanceof BitmapDrawable))
			return;

		// clean up the view's image for the sake of memory
		BitmapDrawable b = (BitmapDrawable) imageView.getDrawable();
		b.getBitmap().recycle();
		imageView.setImageDrawable(null);
	}

}
