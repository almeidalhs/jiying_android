package com.corelib.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.corelib.util.YLBBitmapUtil;

public class MemoryAndDiskBitmapCache extends DiskBasedCache implements ImageCache {

	public MemoryAndDiskBitmapCache(File rootDirectory, int maxCacheSizeInBytes,LruCache<String, Bitmap> memoryCache) {
		super(rootDirectory, maxCacheSizeInBytes);
		mMemoryCache = memoryCache;
	}

	public MemoryAndDiskBitmapCache(File cacheDir,LruCache<String, Bitmap> memoryCache) {
		super(cacheDir);
		mMemoryCache = memoryCache;
	}

	public Bitmap getBitmap(String url) {
		Bitmap bitmap = getBitmapFromMemory(url);
		if(bitmap != null) {
			return bitmap;
		}
		final Entry requestedItem = get(url);

		if (requestedItem == null)
			return null;

		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			Bitmap b = BitmapFactory.decodeFile(url, options);
			// Bitmap bitmap = BitmapFactory.decodeByteArray(
			// requestedItem.data, 0, requestedItem.data.length);
			return compressBmpFromBmp(b);
		} catch (Exception e) {
			return null;
		}
	}

	private Bitmap compressBmpFromBmp(Bitmap image) {
		if (image == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		while (baos.toByteArray().length / 1024 > 100) {
			baos.reset();
			options -= 10;
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	public void putBitmap(String url, Bitmap bitmap) {
		putBitmapToMemory(url,bitmap);
		final Entry entry = new Entry();
		entry.data = YLBBitmapUtil.convertBitmapToBytes(bitmap);
		put(url, entry);
	}

	//-------------------------memory cache --------
	private LruCache<String, Bitmap> mMemoryCache;



	public Bitmap getBitmapFromMemory(String url) {
		return mMemoryCache.get(url);
	}

	public void putBitmapToMemory(String url, Bitmap arg1) {
		if (getBitmapFromMemory(url) == null) {
			mMemoryCache.put(url, arg1);
		}
	}

}
