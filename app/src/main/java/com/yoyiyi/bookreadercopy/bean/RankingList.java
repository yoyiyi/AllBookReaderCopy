package com.yoyiyi.bookreadercopy.bean;


import com.yoyiyi.bookreadercopy.bean.base.BaseEntity;

import java.util.List;

/**
 * Created by zzq on 2016/12/5.
 */

public class RankingList extends BaseEntity {
    public List<FemaleBean> female;
    public List<MaleBean> male;

    public static class FemaleBean {
        /**
         * _id : 54d43437d47d13ff21cad58b
         * title : 追书最热榜 Top100
         * cover : /ranking-cover/142319314350435
         * collapse : false
         * monthRank : 564d853484665f97662d0810
         * totalRank : 564d85b6dd2bd1ec660ea8e2
         */

        public String _id;
        public String title;
        public String cover;
        public boolean collapse;
        public String monthRank;
        public String totalRank;
    }

    public static class MaleBean {
        /**
         * _id : 54d42d92321052167dfb75e3
         * title : 追书最热榜 Top100
         * cover : /ranking-cover/142319144267827
         * collapse : false
         * monthRank : 564d820bc319238a644fb408
         * totalRank : 564d8494fe996c25652644d2
         */

        public String _id;
        public String title;
        public String cover;
        public boolean collapse;
        public String monthRank;
        public String totalRank;
    }




  /*  *//**
     * female : [{"_id":"54d43437d47d13ff21cad58b","title":"追书最热榜 Top100",
     * "cover":"/ranking-cover/142319314350435","collapse":false,
     * "monthRank":"564d853484665f97662d0810","totalRank":"564d85b6dd2bd1ec660ea8e2"}]
     * ok : true
     *//*

    public List<MaleBean> female;
    *//**
     * _id : 54d42d92321052167dfb75e3
     * title : 追书最热榜 Top100
     * cover : /ranking-cover/142319144267827
     * collapse : false
     * monthRank : 564d820bc319238a644fb408
     * totalRank : 564d8494fe996c25652644d2
     *//*

    public List<MaleBean> male;

    public static class MaleBean {
        public String _id;
        public String title;
        public String cover;
        public boolean collapse;
        public String monthRank;
        public String totalRank;

        public MaleBean() {
        }

        public MaleBean(String title) {
            this.title = title;
        }
    }*/
}
