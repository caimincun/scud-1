package com.team.dream.runlegwork;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.team.dream.imageloader.cache.disc.impl.UnlimitedDiskCache;
import com.team.dream.imageloader.cache.disc.naming.Md5FileNameGenerator;
import com.team.dream.imageloader.cache.memory.impl.LruMemoryCache;
import com.team.dream.imageloader.core.ImageLoader;
import com.team.dream.imageloader.core.ImageLoaderConfiguration;
import com.team.dream.imageloader.core.assist.QueueProcessingType;
import com.team.dream.imageloader.core.decode.BaseImageDecoder;
import com.team.dream.imageloader.core.download.BaseImageDownloader;
import com.team.dream.imageloader.core.listener.ImageLoadingListener;
import com.team.dream.imageloader.utils.StorageUtils;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.singleservice.LocationCache;
import com.team.dream.runlegwork.tool.DisplayImageOptionsUnits;

public class SingletonServiceManager {

	public static final String LITEPAL_MANAGER = "litepal_manager";
	public static final String ACCOUNT_MANAGER = "account_manager";
	public static final String Location_Cache_Util = "location_cache";
	public ImageLoader imageLoader = null;
	private static Context context;
	private static SingletonServiceManager mInstance;

	private static String userToken;
	private SharedPreferences sp;

	public static SingletonServiceManager getInstance() {
		if (mInstance == null) {
			throw new AssertionError("ServiceUtils not instance.");
		}
		return mInstance;
	}

	public static void newInstance(Context context) {
		synchronized (SingletonServiceManager.class) {
			if (mInstance == null) {
				mInstance = new SingletonServiceManager(context);
			}
		}
	}

	private SingletonServiceManager(Context cxt) {
		context = cxt;
		imageLoader = ImageLoader.getInstance();
		initImageLoader(context);
	}

	static class ServiceFether {
		int mServiceCacheIndex = -1;

		public Object getService(SingletonServiceManager serve) {
			ArrayList<Object> cache = serve.mServiceCache;
			Object service;
			synchronized (cache) {
				if (cache.size() == 0) {
					for (int i = 0; i < sNextPerServiceCacheIndex; i++) {
						cache.add(null);
					}
				} else {
					service = cache.get(mServiceCacheIndex);
					if (service != null) {
						return service;
					}
				}
			}
			service = createService(serve);
			cache.set(mServiceCacheIndex, service);
			return service;
		}

		public Object createService(SingletonServiceManager ctx) {
			throw new RuntimeException("Not implemented");
		}
	}

	final ArrayList<Object> mServiceCache = new ArrayList<Object>();

	private static final HashMap<String, ServiceFether> APP_SERVICE_MAP = new HashMap<String, ServiceFether>();
	private static int sNextPerServiceCacheIndex = 0;

	static {

		registerService(LITEPAL_MANAGER, new StaticServiceFetcher() {
			@Override
			public Object createStaticService() {
				return new LitePalManager();
			}
		});
		registerService(ACCOUNT_MANAGER, new StaticServiceFetcher() {
			@Override
			public Object createStaticService() {
				return new AccountManager(context);
			}
		});
		registerService(Location_Cache_Util, new StaticServiceFetcher() {
			@Override
			public Object createStaticService() {
				return new LocationCache(context);
			}
		});
	}

	private static void registerService(String serviceName, ServiceFether fetcher) {
		if (!(fetcher instanceof ServiceFether)) {
			fetcher.mServiceCacheIndex = sNextPerServiceCacheIndex++;
		}
		APP_SERVICE_MAP.put(serviceName, fetcher);
	}

	abstract static class StaticServiceFetcher extends ServiceFether {
		private Object mCachedInstance;

		@Override
		public final Object getService(SingletonServiceManager unused) {
			synchronized (StaticServiceFetcher.this) {
				Object service = mCachedInstance;
				if (service != null) {
					return service;
				}
				return mCachedInstance = createStaticService();
			}
		}

		public abstract Object createStaticService();
	}

	public void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getCacheDirectory(context);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 1).memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				// .memoryCacheSize(2 * 1024 * 1024)
				// .memoryCacheSizePercentage(13)
				.diskCache(new UnlimitedDiskCache(cacheDir))
				// .diskCacheSize(50 * 1024 * 1024)
				// .diskCacheFileCount(100)
				.imageDownloader(new BaseImageDownloader(context)) // default
				.imageDecoder(new BaseImageDecoder(true)) // default
				.writeDebugLogs().build();
		imageLoader.init(config);
	}

	public Object getAppService(String name) {
		ServiceFether fetcher = APP_SERVICE_MAP.get(name);
		return fetcher == null ? null : fetcher.getService(this);
	}

	/**
	 * 加载图片
	 * 
	 * @param imgurl
	 * @param imageView
	 * @param defaultPicId
	 */
	public void display(String imgurl, ImageView imageView, int defaultPicId, ImageLoadingListener listener) {
		imageLoader.displayImage(imgurl, imageView, DisplayImageOptionsUnits.getIns().displayImageOptions(defaultPicId), listener);
	}

	private void setToken(String token) {
		sp = context.getSharedPreferences("account", Context.MODE_PRIVATE);
		sp.edit().putString("", "");
	}

}