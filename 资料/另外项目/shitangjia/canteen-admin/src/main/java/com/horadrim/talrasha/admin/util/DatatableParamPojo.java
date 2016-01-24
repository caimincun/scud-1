package com.horadrim.talrasha.admin.util;

/**
 * Created by Administrator on 2015/7/29.
 * jQuery datatable传入的参数，后台解析参数得到气势索引和位置
 */
public class DatatableParamPojo {

    private String sEcho;
    private int iDisplayStart;
    private int iDisplayLength;

//    private int iColumns;
//    private String sColumns;
//    private String mDataProp_0;
//    private String mDataProp_1;
//    private int iSortCol_0;
//    private String sSortDir_0;
//    private int iSortingCols;
//    private boolean bSortable_0;
//    private boolean bSortable_1;

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }
    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

}
