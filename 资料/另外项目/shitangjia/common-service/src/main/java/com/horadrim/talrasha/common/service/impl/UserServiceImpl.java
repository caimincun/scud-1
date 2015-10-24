package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.UserDao;
import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.service.UserService;
import org.hibernate.sql.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource(name="userDao")
    protected UserDao userDao;

    @Override
    protected GenericDao<User, Integer> getGenericDao() {
        return userDao;
    }

    @Override
    public List<User> queryUsers(int canteenId,  int currentPage,int size, int sort) {
        List<Object> params= new ArrayList<>();
        params.add(canteenId);
        StringBuffer hql=new StringBuffer("FROM User u where u.canteenId=?");
        if (sort==1){
            hql.append(" ORDER BY u.id DESC");
        }
        return userDao.list(hql.toString(),currentPage*size,size,params.toArray());
    }

    @Override
    public boolean countUserByPhone(String phone) {
        String sql= " select count(id) from User where phone=?";
         return userDao.count(sql,new Object[]{phone})>0;
    }

//
//    @Override
//	public User getByUsername(String username) {
//        final String hql = "from User as user where user.username = ? and user.islock=0";
//        return userDao.query(hql, new Object[]{username});
//	}
//
//	@Override
//	public User getByEmail(String email) {
//        final String hql = "from User as user where user.email = ? ";
//        return userDao.query(hql, new Object[]{email});
//	}
//
//    @Override
//    public int login(String username, String password) {
//        User user = getByUsername(username);
//        if(user==null || user.getType()==User.usertype.NORMAL)
//            return -2;
//        if(user.getPassword().equals(DigestUtils.md5Hex(password)))
//            return user.getUserId();
//        return -1;
//    }
//
//    @Override
//    public List<Map> listUserByGroup(int groupId) {
//        final String sql = "SELECT u.userid , u.username FROM ol_user_group ug LEFT JOIN ol_user u " +
//                            "ON ug.userid = u.userid WHERE ug.groupid = ? and u.islock=0";
//        return listFieldBySQL(sql, new Object[]{groupId});
//    }
//
//    @Override
//    public ComPagerWithTitleRes listUnderCompany(User currentUser,String userName,int pageindex, int size) {
//        String sql = "FROM User WHERE parentid = ? and islock=0";
//        if(!StringUtils.isBlank(userName))
//            sql = sql+" AND username like '%"+userName+"%'";
//        int parentUserId = currentUser.getParentid()==0?currentUser.getUserId():currentUser.getParentid();
//        List<User> users = list(sql,(pageindex-1)*size,size,new Object[]{parentUserId});
//        long count= count("SELECT count(userId) "+sql , new Object[]{parentUserId});
//        if (currentUser.getParentid()==0)
//            users.add(currentUser);
//        List<UserInfoPojo> sucResList = new ArrayList<>();
//        for (User user : users){
//            UserInfoPojo res = new UserInfoPojo();
//            res.setRealName(user.getRealName());
//            res.setUserId(user.getUserId());
//            res.setUsername(user.getUsername());
//            res.setEmail(user.getEmail());
//            sucResList.add(res);
//        }
//
//        ComPagerWithTitleRes comPagerWithTitleRes = new ComPagerWithTitleRes();
//        comPagerWithTitleRes.setTitle(TitleUtil.UNDERCOMPANY_USERINFO);
//        comPagerWithTitleRes.setData(sucResList);
//        comPagerWithTitleRes.setCount(count);
//        return comPagerWithTitleRes;
//    }
//
//    @Override
//    public void updatePwd(String newPwd, int userid,String realName) {
//        final String sql = "UPDATE User u SET u.password = ?,realName=? WHERE u.userId = ?";
//        executeUpdate(sql , new Object[]{newPwd,userid,realName});
//    }



}
