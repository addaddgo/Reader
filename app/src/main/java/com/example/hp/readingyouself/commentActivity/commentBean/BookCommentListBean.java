package com.example.hp.readingyouself.commentActivity.commentBean;

import java.util.List;

public class BookCommentListBean {

    /**
     * reviews : [{"_id":"5b8f60abd6d4260100b723a8","book":{"_id":"50865988d7a545903b000009","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41615%2F41615_3664928fa726466785651ab431ad2c6f.jpg%2F","site":"zhuishuvip","title":"斗破苍穹","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":269,"yes":363,"no":94},"likeCount":11,"haveImage":false,"state":"distillate","updated":"2019-04-14T16:37:50.195Z","created":"2018-09-05T04:50:51.845Z","title":"【书评组】斗气大陆"},{"_id":"5b5e91ecfd9723ba51b0a451","book":{"_id":"523a89f3d87e739d3a005f30","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F79253%2F79253_8fee08f03b1644b6b95f0477071a1ece.jpg%2F","site":"zhuishuvip","title":"斗罗大陆II绝世唐门","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":201,"yes":290,"no":89},"likeCount":8,"haveImage":false,"state":"distillate","updated":"2019-04-14T08:59:10.594Z","created":"2018-07-30T04:19:56.896Z","title":"论【绝世唐门】的疑点"},{"_id":"5b5dc65df7ad10a9799afcc2","book":{"_id":"509375668d834c0f19000116","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41526%2F41526_a5d917afb29942258ab75804606018d2.jpg%2F","site":"zhuishuvip","title":"宠魅","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":1195,"yes":1267,"no":72},"likeCount":17,"haveImage":false,"state":"distillate","updated":"2019-04-15T05:03:35.307Z","created":"2018-07-29T13:51:25.039Z","title":"敬告各位不喜欢、讨厌这本书的读者"},{"_id":"5b5ee61a16fd852b59e96ddc","book":{"_id":"508646479dacd30e3a000001","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F42314%2F42314_e1c68c4267f54a968806264b7538d14a.jpg%2F","site":"zhuishuvip","title":"将夜","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":61,"yes":69,"no":8},"likeCount":5,"haveImage":false,"state":"distillate","updated":"2019-03-28T16:42:55.632Z","created":"2018-07-30T10:19:06.573Z","title":"【书评组】夜以将至，明月渐起"},{"_id":"5b1268ffefc64df73f97092b","book":{"_id":"50864b3b9dacd30e3a00000d","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F42130%2F_42130_731624.jpg%2F","site":"zhuishuvip","title":"亵渎","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":39,"yes":53,"no":14},"likeCount":8,"haveImage":false,"state":"distillate","updated":"2019-04-14T10:08:14.938Z","created":"2018-06-02T09:53:03.180Z","title":"【书评组】烟雨江南《亵渎》"},{"_id":"5b0fb9e8066e931c527fc120","book":{"_id":"508754f2f98e8f7446000036","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F46968%2F_46968_909704.jpg%2F","site":"zhuishuvip","title":"飞升之后","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":227,"yes":238,"no":11},"likeCount":17,"haveImage":false,"state":"distillate","updated":"2019-04-12T04:08:49.733Z","created":"2018-05-31T09:01:28.615Z","title":"【书评组】\u201c生而为男，不求与天地长存，但求死得其所\u201d"},{"_id":"5afa77e4b9f03a76482934f5","book":{"_id":"508751bef98e8f7446000024","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41924%2F41924_a53ddaf38363440dbf903742170dac8f.jpg%2F","site":"zhuishuvip","title":"神墓","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":173,"yes":192,"no":19},"likeCount":30,"haveImage":false,"state":"distillate","updated":"2019-04-12T20:04:00.446Z","created":"2018-05-15T06:02:12.951Z","title":"【书评组】\u201c我命由我不由天!\u201d-成神之作巅峰《神墓》"},{"_id":"5afa1808a8d7387847777da7","book":{"_id":"520b35e54931bdf106000976","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F31023%2F_31023_163971.jpg%2F","site":"zhuishuvip","title":"兽神","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":35,"yes":50,"no":15},"likeCount":4,"haveImage":false,"state":"distillate","updated":"2019-04-10T08:19:17.612Z","created":"2018-05-14T23:13:12.261Z","title":"【书评组|枪城】萨满系列之中式西幻下的兽人传奇｜评兽神"},{"_id":"5a80e20b6d5ef23c41f2ac71","book":{"_id":"50865988d7a545903b000009","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41615%2F41615_3664928fa726466785651ab431ad2c6f.jpg%2F","site":"zhuishuvip","title":"斗破苍穹","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":963,"yes":1157,"no":194},"likeCount":77,"haveImage":false,"state":"distillate","updated":"2019-04-11T15:18:51.432Z","created":"2018-02-12T00:38:35.194Z","title":"《斗破苍穹》由萧炎与云韵而引发的一系列感概"},{"_id":"5a5812abefe0ad8734f789d4","book":{"_id":"53e56ee335f79bb626a496c9","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41584%2F41584_873f1d8b2f7a447a8c59f3573094db1b.jpg%2F","site":"zhuishuvip","title":"帝霸","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":470,"yes":534,"no":64},"likeCount":22,"haveImage":false,"state":"distillate","updated":"2019-04-15T08:17:03.269Z","created":"2018-01-12T01:43:07.193Z","title":"推测帝霸真正境界观和世界观"},{"_id":"59f6561c22d602072aceb8be","book":{"_id":"53a36d86117d2e5e6e9be6f8","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41980%2F41980_692f1c25d6b0457bb8d1dbffe44191c6.jpg%2F","site":"zhuishuvip","title":"天醒之路","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":104,"yes":129,"no":25},"likeCount":13,"haveImage":false,"state":"distillate","updated":"2019-04-15T02:34:37.157Z","created":"2017-10-29T22:28:44.156Z","title":"就算是传说中的天醒，路也不平。"},{"_id":"59eab5cf90786a536ac33ba6","book":{"_id":"524eb66affb16b8d3b011a19","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F39249%2F_39249_741020.jpg%2F","site":"zhuishuvip","title":"如果下辈子我还记得你","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":43,"yes":49,"no":6},"likeCount":14,"haveImage":false,"state":"distillate","updated":"2019-04-02T04:34:44.541Z","created":"2017-10-21T02:49:51.038Z","title":"「书评」偶然看到很久前的一段感慨：爱别离，求不得"},{"_id":"59e7dbb8531ffffa0d275cdb","book":{"_id":"50874373abf1ced53c000028","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F47170%2F_47170_451585.jpg%2F","site":"zhuishuvip","title":"朱雀记","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":1,"yes":33,"no":32},"likeCount":269,"haveImage":false,"state":"distillate","updated":"2019-01-16T06:55:59.694Z","created":"2017-10-18T22:54:48.018Z","title":"【甲-】萨满系列之朱雀记"},{"_id":"59de378c55fd6ffb7f589d4c","book":{"_id":"535504a455e0bb7421003294","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41559%2F41559_79931b318c7946f9a127a5a15fb41fee.jpg%2F","site":"zhuishuvip","title":"大泼猴","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":97,"no":19,"yes":116},"likeCount":4,"haveImage":false,"state":"distillate","updated":"2019-03-12T16:03:18.678Z","created":"2017-10-11T15:23:56.872Z","title":"【丙类】我心里的泼猴应该就是这个样子了"},{"_id":"59d9205987738b79686b7b06","book":{"_id":"50975b961db679b876000029","title":"雪中悍刀行","site":"zhuishuvip","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F22517%2F22517_20f9748b11c84774a4daf6cc71ff0b74.jpg%2F","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":1067,"yes":1095,"no":28},"likeCount":49,"haveImage":false,"state":"distillate","updated":"2019-04-14T16:10:45.641Z","created":"2017-10-07T18:43:37.210Z","title":"【丙类】人人皆想徐凤年，奈何全是温不胜"},{"_id":"59c6729beea160e474c6637f","book":{"_id":"50ecfd81ccee6e4b73000001","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F42104%2F_42104_573763.jpg%2F","site":"zhuishuvip","title":"悟空传","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":30,"yes":36,"no":6},"likeCount":3,"haveImage":false,"state":"distillate","updated":"2019-02-03T15:16:05.556Z","created":"2017-09-23T14:41:31.672Z","title":"【丙类】【读书会】这不是一本普通的小说"},{"_id":"59a7b98e48a3b73d6ea55e25","book":{"_id":"50ac662fde1233e062000001","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F23794%2F23794_519284d68e204d6bac1add4968f2f423.jpg%2F","site":"zhuishuvip","title":"问镜","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":72,"yes":224,"no":152},"likeCount":22,"haveImage":false,"state":"distillate","updated":"2019-03-20T23:50:14.987Z","created":"2017-08-31T07:23:58.843Z","title":"关于仙侠，随便谈谈"},{"_id":"599b7e7f5835fd170e53a81d","book":{"_id":"50874750abf1ced53c000037","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41622%2F41622_a77bc8ec60424a4e90d749e798eab1a5.jpg%2F","site":"zhuishuvip","title":"恶魔法则","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":148,"yes":169,"no":21},"likeCount":14,"haveImage":false,"state":"distillate","updated":"2019-04-14T14:30:05.858Z","created":"2017-08-22T00:44:47.427Z","title":"相信小五，他会回来的"},{"_id":"5999df059067689d5430ad62","book":{"_id":"51ca3c026f5432ef5000016a","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41825%2F_41825_044432.jpg%2F","site":"zhuishuvip","title":"魔王奶爸","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":93,"yes":149,"no":56},"likeCount":4,"haveImage":false,"state":"distillate","updated":"2019-03-13T15:17:59.603Z","created":"2017-08-20T19:12:05.631Z","title":"是本好书，但是不适合我"},{"_id":"5991165ce376b82a5692cac5","book":{"_id":"50874893abf1ced53c00003d","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F80727%2F80727_a65d7b56a1d14ec983f62f1715025c10.jpg%2F","site":"zhuishuvip","title":"历史的尘埃","type":"xhqh","latelyFollower":null,"retentionRatio":null},"helpful":{"total":65,"yes":84,"no":19},"likeCount":3,"haveImage":false,"state":"distillate","updated":"2019-02-02T09:56:23.849Z","created":"2017-08-14T03:17:48.494Z","title":"【历史的尘埃】众人皆苦，唯我孤独"}]
     * ok : true
     */

    private boolean ok;
    private List<ReviewsBean> reviews;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<ReviewsBean> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewsBean> reviews) {
        this.reviews = reviews;
    }

    public static class ReviewsBean {
        /**
         * _id : 5b8f60abd6d4260100b723a8
         * book : {"_id":"50865988d7a545903b000009","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41615%2F41615_3664928fa726466785651ab431ad2c6f.jpg%2F","site":"zhuishuvip","title":"斗破苍穹","type":"xhqh","latelyFollower":null,"retentionRatio":null}
         * helpful : {"total":269,"yes":363,"no":94}
         * likeCount : 11
         * haveImage : false
         * state : distillate
         * updated : 2019-04-14T16:37:50.195Z
         * created : 2018-09-05T04:50:51.845Z
         * title : 【书评组】斗气大陆
         */

        private String _id;
        private BookBean book;
        private HelpfulBean helpful;
        private int likeCount;
        private boolean haveImage;
        private String state;
        private String updated;
        private String created;
        private String title;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public BookBean getBook() {
            return book;
        }

        public void setBook(BookBean book) {
            this.book = book;
        }

        public HelpfulBean getHelpful() {
            return helpful;
        }

        public void setHelpful(HelpfulBean helpful) {
            this.helpful = helpful;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public boolean isHaveImage() {
            return haveImage;
        }

        public void setHaveImage(boolean haveImage) {
            this.haveImage = haveImage;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class BookBean {
            /**
             * _id : 50865988d7a545903b000009
             * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41615%2F41615_3664928fa726466785651ab431ad2c6f.jpg%2F
             * site : zhuishuvip
             * title : 斗破苍穹
             * type : xhqh
             * latelyFollower : null
             * retentionRatio : null
             */

            private String _id;
            private String cover;
            private String site;
            private String title;
            private String type;
            private Object latelyFollower;
            private Object retentionRatio;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getLatelyFollower() {
                return latelyFollower;
            }

            public void setLatelyFollower(Object latelyFollower) {
                this.latelyFollower = latelyFollower;
            }

            public Object getRetentionRatio() {
                return retentionRatio;
            }

            public void setRetentionRatio(Object retentionRatio) {
                this.retentionRatio = retentionRatio;
            }
        }

        public static class HelpfulBean {
            /**
             * total : 269
             * yes : 363
             * no : 94
             */

            private int total;
            private int yes;
            private int no;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getYes() {
                return yes;
            }

            public void setYes(int yes) {
                this.yes = yes;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }
        }
    }
}
