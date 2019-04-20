package com.example.hp.readingyouself.commentActivity.commentBean;

import java.util.List;

public class ComprehensiveCommentBean{

    /**
     * post : {"_id":"59ce0b0c7ec629f31c37d7a5","author":{"_id":"52f840b982cfcc3a74031693","avatar":"/avatar/56/a9/56a96462a50ca99f9cf83440899e46f3","nickname":"追书首席打杂","activityAvatar":"/activities/20180215/4.jpg","type":"official","lv":11,"gender":"male","rank":null,"created":"2014-02-10T03:00:09.000Z","id":"52f840b982cfcc3a74031693"},"type":"normal","game":null,"book":null,"isStopPriority":true,"deleted":false,"likeCount":9662,"isStick":false,"block":"ramble","state":"normal","updated":"2019-04-14T11:16:45.116Z","created":"2017-09-29T08:57:48.108Z","commentCount":117416,"voteCount":0,"votes":[],"content":"各位小伙伴，国庆快乐。\r\n\r\n该帖帖子为2017年国庆活动，活动早已经结束。请各位误入的小伙伴，留意我们2018年最新的活动帖：[[post:5baf14726f660bbe4fe5dc36 【传送门】国庆大作战！每日奉上5000000追书券！]]\r\n\r\n再次祝福大家国庆节快乐，感恩与你携手又过了一年，希望无论白天或黑夜、追书一直能够陪伴你身边。\r\n\r\n国庆快乐～φ(≧ω≦*)♪\r\n国庆也要好好休息，不要熬夜看书、熬夜伤身体，听话。o(*≧▽≦)ツ","title":"【该活动已过期】此为2017年国庆节活动，已过期！请留意！","shareLink":"http://share.zhuishushenqi.com/post/59ce0b0c7ec629f31c37d7a5","id":"59ce0b0c7ec629f31c37d7a5"}
     * ok : true
     */

    private PostBean post;
    private boolean ok;

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class PostBean {
        /**
         * _id : 59ce0b0c7ec629f31c37d7a5
         * author : {"_id":"52f840b982cfcc3a74031693","avatar":"/avatar/56/a9/56a96462a50ca99f9cf83440899e46f3","nickname":"追书首席打杂","activityAvatar":"/activities/20180215/4.jpg","type":"official","lv":11,"gender":"male","rank":null,"created":"2014-02-10T03:00:09.000Z","id":"52f840b982cfcc3a74031693"}
         * type : normal
         * game : null
         * book : null
         * isStopPriority : true
         * deleted : false
         * likeCount : 9662
         * isStick : false
         * block : ramble
         * state : normal
         * updated : 2019-04-14T11:16:45.116Z
         * created : 2017-09-29T08:57:48.108Z
         * commentCount : 117416
         * voteCount : 0
         * votes : []
         * content : 各位小伙伴，国庆快乐。

         该帖帖子为2017年国庆活动，活动早已经结束。请各位误入的小伙伴，留意我们2018年最新的活动帖：[[post:5baf14726f660bbe4fe5dc36 【传送门】国庆大作战！每日奉上5000000追书券！]]

         再次祝福大家国庆节快乐，感恩与你携手又过了一年，希望无论白天或黑夜、追书一直能够陪伴你身边。

         国庆快乐～φ(≧ω≦*)♪
         国庆也要好好休息，不要熬夜看书、熬夜伤身体，听话。o(*≧▽≦)ツ
         * title : 【该活动已过期】此为2017年国庆节活动，已过期！请留意！
         * shareLink : http://share.zhuishushenqi.com/post/59ce0b0c7ec629f31c37d7a5
         * id : 59ce0b0c7ec629f31c37d7a5
         */

        private String _id;
        private AuthorBean author;
        private String type;
        private Object game;
        private Object book;
        private boolean isStopPriority;
        private boolean deleted;
        private int likeCount;
        private boolean isStick;
        private String block;
        private String state;
        private String updated;
        private String created;
        private int commentCount;
        private int voteCount;
        private String content;
        private String title;
        private String shareLink;
        private String id;
        private List<?> votes;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getGame() {
            return game;
        }

        public void setGame(Object game) {
            this.game = game;
        }

        public Object getBook() {
            return book;
        }

        public void setBook(Object book) {
            this.book = book;
        }

        public boolean isIsStopPriority() {
            return isStopPriority;
        }

        public void setIsStopPriority(boolean isStopPriority) {
            this.isStopPriority = isStopPriority;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public boolean isIsStick() {
            return isStick;
        }

        public void setIsStick(boolean isStick) {
            this.isStick = isStick;
        }

        public String getBlock() {
            return block;
        }

        public void setBlock(String block) {
            this.block = block;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShareLink() {
            return shareLink;
        }

        public void setShareLink(String shareLink) {
            this.shareLink = shareLink;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<?> getVotes() {
            return votes;
        }

        public void setVotes(List<?> votes) {
            this.votes = votes;
        }

        public static class AuthorBean {
            /**
             * _id : 52f840b982cfcc3a74031693
             * avatar : /avatar/56/a9/56a96462a50ca99f9cf83440899e46f3
             * nickname : 追书首席打杂
             * activityAvatar : /activities/20180215/4.jpg
             * type : official
             * lv : 11
             * gender : male
             * rank : null
             * created : 2014-02-10T03:00:09.000Z
             * id : 52f840b982cfcc3a74031693
             */

            private String _id;
            private String avatar;
            private String nickname;
            private String activityAvatar;
            private String type;
            private int lv;
            private String gender;
            private Object rank;
            private String created;
            private String id;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getActivityAvatar() {
                return activityAvatar;
            }

            public void setActivityAvatar(String activityAvatar) {
                this.activityAvatar = activityAvatar;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getLv() {
                return lv;
            }

            public void setLv(int lv) {
                this.lv = lv;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public Object getRank() {
                return rank;
            }

            public void setRank(Object rank) {
                this.rank = rank;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
