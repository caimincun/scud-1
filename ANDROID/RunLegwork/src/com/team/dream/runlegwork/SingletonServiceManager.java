package com.team.dream.runlegwork;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

public class SingletonServiceManager {

	public static final String LITEPAL_MANAGER = "litepal_manager";

	private static Context context;
	private static SingletonServiceManager mInstance;

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

	public Object getAppService(String name) {
		ServiceFether fetcher = APP_SERVICE_MAP.get(name);
		return fetcher == null ? null : fetcher.getService(this);
	}
}