package com.team.dream.runlegwork_data.Interface;

import org.litepal.crud.DataSupport;

public interface LitePalCache<T extends DataSupport> {

	 boolean save(T t);

	int update(T t);

	void query(T t,String where);

	int delete();

}
