package com.hongyan.hyutil.model.wanandroid.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author: hongyan
 * created on: 2019/3/28 15:36
 * description:
 */
public class WanAndroidBean implements Serializable {


    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10018,"link":"https://mp.weixin.qq.com/s?__biz=MzU2NTgwMzA5Mg==&amp;mid=2247483724&amp;idx=1&amp;sn=5d5668a5c96b38160bc49f17d4f4dfc3&amp;chksm=fcb76077cbc0e961e8759968617779e7e9b27fff9e00768a080dbbff6591f074eef840e955da&amp;token=341489502&amp;lang=zh_CN#rd","niceDate":"3小时前","niceShareDate":"3小时前","origin":"","prefix":"","projectLink":"","publishTime":1572492788000,"selfVisible":0,"shareDate":1572492788000,"shareUser":"winlee28","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android性能优化之布局优化实战","type":0,"userId":25211,"visible":1,"zan":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10018,"link":"https://mp.weixin.qq.com/s?__biz=MzU2NTgwMzA5Mg==&amp;mid=2247483724&amp;idx=1&amp;sn=5d5668a5c96b38160bc49f17d4f4dfc3&amp;chksm=fcb76077cbc0e961e8759968617779e7e9b27fff9e00768a080dbbff6591f074eef840e955da&amp;token=341489502&amp;lang=zh_CN#rd","niceDate":"3小时前","niceShareDate":"3小时前","origin":"","prefix":"","projectLink":"","publishTime":1572492788000,"selfVisible":0,"shareDate":1572492788000,"shareUser":"winlee28","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android性能优化之布局优化实战","type":0,"userId":25211,"visible":1,"zan":0}]
         */

        private int curPage;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author :
             * chapterId : 502
             * chapterName : 自助
             * collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : true
             * id : 10018
             * link : https://mp.weixin.qq.com/s?__biz=MzU2NTgwMzA5Mg==&amp;mid=2247483724&amp;idx=1&amp;sn=5d5668a5c96b38160bc49f17d4f4dfc3&amp;chksm=fcb76077cbc0e961e8759968617779e7e9b27fff9e00768a080dbbff6591f074eef840e955da&amp;token=341489502&amp;lang=zh_CN#rd
             * niceDate : 3小时前
             * niceShareDate : 3小时前
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1572492788000
             * selfVisible : 0
             * shareDate : 1572492788000
             * shareUser : winlee28
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : Android性能优化之布局优化实战
             * type : 0
             * userId : 25211
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
