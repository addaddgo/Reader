package com.example.hp.readingyouself.readingDataSupport.dataForm;

import org.litepal.crud.DataSupport;

import java.util.List;

public class BookInformationBean extends DataSupport {

    /**
     * _id : 59ba0dbb017336e411085a4e
     * longIntro : 彼时的归途，已是一条命运倒悬的路。
     昔日的荣华，如白云苍狗，恐大梦一场。
     少年执笔，龙蛇飞动。
     是为一抹光芒劈开暮气沉沉之乱世，问鼎玉宇苍穹。

     复仇之路，与吾同行。
     一口玄黄真气定可吞天地日月星辰，雄视草木苍生。

     铁画夕照，雾霭银钩，笔走游龙冲九州。
     横姿天下，墨洒青山，鲸吞湖海纳百川。
     * title : 元尊
     * majorCate : 玄幻
     * minorCateV2 : 东方玄幻
     * creater : OnePlus ONEPLUS A5000
     * minorCate : 东方玄幻
     * author : 天蚕土豆
     * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2107590%2F2107590_718bb20f6bba4ee2910cf30d51882112.jpg%2F
     * majorCateV2 : 玄幻
     * isMakeMoneyLimit : false
     * isFineBook : true
     * safelevel : 0
     * allowFree : false
     * originalAuthor :
     * anchors : []
     * authorDesc :
     * rating : {"count":41830,"score":8.646,"isEffect":true}
     * hasCopyright : true
     * buytype : 0
     * sizetype : -1
     * superscript :
     * currency : 0
     * contentType : txt
     * _le : false
     * allowMonthly : false
     * allowVoucher : true
     * allowBeanVoucher : true
     * hasCp : true
     * banned : 0
     * postCount : 21934
     * latelyFollower : 123056
     * followerCount : 0
     * wordCount : 2095663
     * serializeWordCount : 4269
     * retentionRatio : 69.78
     * updated : 2019-04-19T12:58:56.648Z
     * isSerial : true
     * chaptersCount : 830
     * lastChapter : 正文 第八百二十五章 全新的待遇
     * gender : ["male"]
     * tags : ["东方玄幻","争霸流","坚毅","天蚕土豆","热血","爽文","玄幻"]
     * advertRead : true
     * cat : 东方玄幻
     * donate : false
     * _gg : false
     * isForbidForFreeApp : false
     * isAllowNetSearch : false
     * limit : false
     * copyrightDesc : 本书由上海元聚进行电子本制作与发行
     * discount : null
     */

    private String _id;
    private String longIntro;
    private String title;
    private String majorCate;
    private String minorCateV2;
    private String creater;
    private String minorCate;
    private String author;
    private String cover;
    private String majorCateV2;
    private boolean isMakeMoneyLimit;
    private boolean isFineBook;
    private int safelevel;
    private boolean allowFree;
    private String originalAuthor;
    private String authorDesc;
    private RatingBean rating;
    private boolean hasCopyright;
    private int buytype;
    private int sizetype;
    private String superscript;
    private int currency;
    private String contentType;
    private boolean _le;
    private boolean allowMonthly;
    private boolean allowVoucher;
    private boolean allowBeanVoucher;
    private boolean hasCp;
    private int banned;
    private int postCount;
    private int latelyFollower;
    private int followerCount;
    private int wordCount;
    private int serializeWordCount;
    private String retentionRatio;
    private String updated;
    private boolean isSerial;
    private int chaptersCount;
    private String lastChapter;
    private boolean advertRead;
    private String cat;
    private boolean donate;
    private boolean _gg;
    private boolean isForbidForFreeApp;
    private boolean isAllowNetSearch;
    private boolean limit;
    private String copyrightDesc;
    private Object discount;
    private List<?> anchors;
    private List<String> gender;
    private List<String> tags;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCateV2() {
        return minorCateV2;
    }

    public void setMinorCateV2(String minorCateV2) {
        this.minorCateV2 = minorCateV2;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMajorCateV2() {
        return majorCateV2;
    }

    public void setMajorCateV2(String majorCateV2) {
        this.majorCateV2 = majorCateV2;
    }

    public boolean isIsMakeMoneyLimit() {
        return isMakeMoneyLimit;
    }

    public void setIsMakeMoneyLimit(boolean isMakeMoneyLimit) {
        this.isMakeMoneyLimit = isMakeMoneyLimit;
    }

    public boolean isIsFineBook() {
        return isFineBook;
    }

    public void setIsFineBook(boolean isFineBook) {
        this.isFineBook = isFineBook;
    }

    public int getSafelevel() {
        return safelevel;
    }

    public void setSafelevel(int safelevel) {
        this.safelevel = safelevel;
    }

    public boolean isAllowFree() {
        return allowFree;
    }

    public void setAllowFree(boolean allowFree) {
        this.allowFree = allowFree;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getAuthorDesc() {
        return authorDesc;
    }

    public void setAuthorDesc(String authorDesc) {
        this.authorDesc = authorDesc;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public boolean isHasCopyright() {
        return hasCopyright;
    }

    public void setHasCopyright(boolean hasCopyright) {
        this.hasCopyright = hasCopyright;
    }

    public int getBuytype() {
        return buytype;
    }

    public void setBuytype(int buytype) {
        this.buytype = buytype;
    }

    public int getSizetype() {
        return sizetype;
    }

    public void setSizetype(int sizetype) {
        this.sizetype = sizetype;
    }

    public String getSuperscript() {
        return superscript;
    }

    public void setSuperscript(String superscript) {
        this.superscript = superscript;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean is_le() {
        return _le;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean isAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean isAllowBeanVoucher() {
        return allowBeanVoucher;
    }

    public void setAllowBeanVoucher(boolean allowBeanVoucher) {
        this.allowBeanVoucher = allowBeanVoucher;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isIsSerial() {
        return isSerial;
    }

    public void setIsSerial(boolean isSerial) {
        this.isSerial = isSerial;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public boolean isAdvertRead() {
        return advertRead;
    }

    public void setAdvertRead(boolean advertRead) {
        this.advertRead = advertRead;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public boolean isDonate() {
        return donate;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

    public boolean is_gg() {
        return _gg;
    }

    public void set_gg(boolean _gg) {
        this._gg = _gg;
    }

    public boolean isIsForbidForFreeApp() {
        return isForbidForFreeApp;
    }

    public void setIsForbidForFreeApp(boolean isForbidForFreeApp) {
        this.isForbidForFreeApp = isForbidForFreeApp;
    }

    public boolean isIsAllowNetSearch() {
        return isAllowNetSearch;
    }

    public void setIsAllowNetSearch(boolean isAllowNetSearch) {
        this.isAllowNetSearch = isAllowNetSearch;
    }

    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public String getCopyrightDesc() {
        return copyrightDesc;
    }

    public void setCopyrightDesc(String copyrightDesc) {
        this.copyrightDesc = copyrightDesc;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public List<?> getAnchors() {
        return anchors;
    }

    public void setAnchors(List<?> anchors) {
        this.anchors = anchors;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class RatingBean extends DataSupport{
        /**
         * count : 41830
         * score : 8.646
         * isEffect : true
         */

        private int count;
        private double score;
        private boolean isEffect;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public boolean isIsEffect() {
            return isEffect;
        }

        public void setIsEffect(boolean isEffect) {
            this.isEffect = isEffect;
        }
    }
}
