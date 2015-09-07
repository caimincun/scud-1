package cn.scud.main.store.service.impl;

import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.main.skill.model.Skill;
import cn.scud.main.store.dao.StoreDao;
import cn.scud.main.store.model.Store;
import cn.scud.main.store.service.StoreService;
import cn.scud.main.user.model.UserInfo;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/6.
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreDao storeDao;


    @Override
    public void saveStore(Store store) {
        store.setStoreToken(WebUtil.getStoreToken());
        storeDao.saveStore(store);
    }

    @Override
    public Store loadStore(String storeToken) {
        return storeDao.loadStore(storeToken);
    }

    @Override
    public void updateStore(Store store) {
        storeDao.updateStore(store);
    }

    /**
     * 分类查询附近的商铺
     * @param session
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param storeType
     * @return
     */
    @Override
    public List<Store> storeNearby(HttpSession session, String lng, String lat, int radius, int page_index, int page_size, String storeType) {

        Boolean ifLoop = true;

        if(page_index == 0){
            session.setAttribute("user_differ_num",-1);
        }

        int loopTime = 0;                                                                            // 为了避免数据库数据不够为空的死循环，对循环次数进行限定
        int numTemp = 0;
        while(ifLoop) {
            loopTime++;
            int searchNum =  Integer.parseInt(session.getAttribute("user_differ_num").toString());
            numTemp = searchNum+1;
            JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius, numTemp, page_size);
            List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
            List storeLbsids = new ArrayList();
            for (JsonPioContent jsonPioContent : jsonPioContents) {
                storeLbsids.add(jsonPioContent.getUid());
            }
            List<Store> storeList = new ArrayList<Store>();
            if(storeLbsids.size()>0){
                storeList = storeDao.searchNearbyPoi(storeLbsids); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
            }
            for (JsonPioContent jsonPioContent : jsonPioContents) {
                for (Store store : storeList) {
                    if (jsonPioContent.getUid() == store.getStoreLbsid()) {
                            if (("全部").equals(storeType) || store.getStoreType().equals(storeType)) {
                                store.setDistance(jsonPioContent.getDistance());
                                storeList.add(store);
                                break;
                            }
                    }
                }
            }
            if(storeList.size() == 0){                   // 判断这次分页查询是否有值
                ifLoop = true;
                session.setAttribute("user_differ_num",searchNum+1);
            }else{
                ifLoop = false;
            }
            if(loopTime>5){
                ifLoop = false;             // 如果超过如 5 次 分页查询都没有数据，则判定数据库为空数据跳出循环
            }
        }
        return null;
    }


}
