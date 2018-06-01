package com.secretk.move.baseManager;

import com.secretk.move.MoveApplication;

import java.io.File;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 * 描述：常用的常量
 */
public interface Constants {
    /**
     * AES 加密的KEY
     */
    String AES_KEY = "0987654321qazxcv";
    /**
     * 应用名称
     */
    String APPNAME_ENGLISH = "move";
    /**
     * token_key
     */
    String TOKEN_KEY = "token";
    /**
     * 登录的状态
     */
    String IS_LOGIN_KEY = "isLogin";
    /**
     * 登录的用户信息
     */
    String USER_INFOS = "userInfos";
    /**
     * 分页单次请求数量
     */
    int PAGE_SIZE = 10;

    /**
     * 文件存储的名称
     */
    String SHAREDPREFS_NAME = "move";
    /**
     * apk 在本地的存放地址
     */
    String APKPATH = MoveApplication.getContext().getExternalFilesDir(null).getAbsolutePath() + "/move.apk";
    /**
     * 文件存储目录
     */
    String LOCAL_PATH = MoveApplication.getContext().getExternalFilesDir(null).getAbsolutePath()
            + File.separator + SHAREDPREFS_NAME;
    /**
     * 是否开启调试模式
     */
    boolean DEBUG = true;
    /**
     * 点赞失败
     */
    String PRAISE_ERROR = "praiseError";
    /**
     * 关注失败
     */
    String FOLLOW_ERROR = "followError";
    /**
     * 收藏失败
     */
    String COLLECT_ERROR = "collectStatus";
    /**
     * startActivityForResult
     */
    String REQUEST_CODE = "requestCode";

    /**
     * 图片
     */
    String BASE_IMG_URL = "http://47.98.197.101/";
    /**
     * 外网
     */
    String BASE_URL = "http://47.98.197.101/rest/";

    /**
     * 王晗
     */
//    String BASE_URL = "http://192.168.10.122:8082/";
    /**
     * 张栋栋
     */
//    String BASE_URL = "http://192.168.10.151:809/";
    /**
     * 张栋栋 --证件的URL
     */
    String BASE_URL_ZJ = "http://192.168.10.151:8080/";
    /**
     * 罗艳清
     */
//    String BASE_URL = "http://192.168.10.199:8080/rest/";

    //发送验证码通用接口
    String DYNAMIC_VALIDATE_CODE_SEND = BASE_URL + "kff/dynamicValidateCode/send";
    //校验验证码接口。
    String DYNAMIC_VALIDATE_CODE_VERIFY = BASE_URL + "kff/dynamicValidateCode/verify";
    //验证手机号是否已经存在
    String PHONE_AVAILABLE = BASE_URL + "kff/user/register/phoneAvailable";
    //用户注册
    String USER_REGISTER = BASE_URL + "kff/user/register";
    //老用户用户登录
    String USER_LOGIN = BASE_URL + "kff/user/login";
    //重置密码
    String FORGET_PASSWORD = BASE_URL + "kff/user/forgetPassword";
    //总产明细列表的接口
    String MY_TOKEN_RECORDS = BASE_URL + "kff/user/myTokenRecords";
    //发放中的接口
    String TOKEN_IN_DISTRIBUTED = BASE_URL + "kff/user/TokenInDistributed";

    //首页推荐
    String MAIN_RECOMMEND = BASE_URL + "/kff/home/recommendList";
    //首页关注
    String MAIN_FOLLOW = BASE_URL + " /kff/home/followList";
    //青天榜
    String MAIN_BLUE_SKY = BASE_URL + "/kff/home/projectRankList";

    //上传用户头像
    String UPLOAD_USER_ICON_FILE = BASE_URL + "kff/home/uploadImgFile";
    //上传认证照片
    String ID_CARD = BASE_URL + "/kff/upload/idcard";
    //上传认证信息
    String UPLOAD_USER_CARD = BASE_URL + "kff/userCard/uploadUserCard ";
    //	更新用户信息（头像，昵称）
    String UPDATE_USER_INFO = BASE_URL + "/kff/user/updateUserInfo";
    //	我的收藏列表
    String MYC_OLLECT_LIST = BASE_URL + "kff/user/myCollectList";
    //	我的收藏列表
    String MY_FOLLOW_LIST = BASE_URL + "kff/user/myFollowList";
    //点击生成邀请奖励
    String CREATE_URLIN_REGISTER = BASE_URL + "kff/user/createUrlInRegister";
    //点击生成poster 海报
    String CREATE_POSTE = BASE_URL + "kff/user/createPoster";
    //提交意见反馈。
    String SUBMIT_SUGGEST = BASE_URL + "kff/system/submitSuggest";
    String GET_H5_URLS = BASE_URL + "/kff/user/getH5URLs";

    //用户主页
    String USERHOME_INDEX = BASE_URL + "kff/userhome/index";
    //用户 测评列表
    String USERHOME_EVALUATION_LIST = BASE_URL + "kff/userhome/evaluationList";
    //用户讨论列表
    String USERHOME_DISCUSS_LIST = BASE_URL + "kff/userhome/discussList";
    //  文章列表
    String USERHOME_ARTICLE_LIST = BASE_URL + "kff/userhome/articleList";

    //项目主页 ，显示项目基本信息
    String PROJECT_INDEX = BASE_URL + "/kff/project/index";
    //首页审核展示状态
    String USERCARD_STATUS = BASE_URL + "kff/userCard/usercardStatus ";
    //我的消息列表。
    String MESSAGE_LIST = BASE_URL + "kff/message/messageList";
    //消息详情
    String MESSAGE_DETAIL = BASE_URL + "kff/message/messageDetail";
    //消息详情
    String DELETE_MESSAGE = BASE_URL + "kff/message/deleteMessage";
    //项目主页 评测列表
    String PROJECT_EVALUATION_LIST = BASE_URL + "/kff/project/evaluationList";
    //项目讨论列表
    String PROJECT_DISCUSS_LIST = BASE_URL + "kff/project/discussList";
    //项目  文章列表
    String PROJECT_ARTICLE_LIST = BASE_URL + "kff/project/articleList";
    //讨论详情 页
    String DISCUSS_DETAIL = BASE_URL + "/kff/home/discussDetail";
    //对(评测/文章/讨论)类型的帖子进行评论或者对评论内容进行评论
    String SAVE_COMMENT = BASE_URL + "kff/comments/saveComment";
    //讨论详情 最新评测列表和单个评论详情页childCommentList
    String HOME_DISCUSS_COMMENT_LIST = BASE_URL + "kff/home/discussCommentList";
    //文章详情 页面
    String ARTICLE_DETAIL = BASE_URL + "kff/home/articleDetail";
    //文章评论列表
    String ARTICLE_COMMENT_LIST = BASE_URL + "/kff/home/articleCommentList";

    //对 评论进行点赞
    String SAVE_COMMENTS_PRAISE = BASE_URL + "kff/praise/saveCommentsPraise";
    //取消对某个评论的点赞
    String CANCEL_COMMENTS_PRAISE = BASE_URL + "kff/praise/cancelCommentsPraise";

    //点赞帖子 包括评测 ，文章
    String SAVE_POST_PRAISE = BASE_URL + "kff/praise/savePostPraise";
    //取消对帖子的点赞  包括 文章 评测
    String CANCEL_POST_PRAISE = BASE_URL + "kff/praise/cancelPostPraise";
    //取消关注 项目 / 帖子 /人
    String CANCEL_FOLLOW = BASE_URL + "/kff/follow/cancelFollow";
    //关注 项目 / 帖子 /人
    String SAVE_FOLLOW = BASE_URL + "kff/follow/saveFollow";

    //收藏帖子 包括 讨论，评测 ，文章
    String SAVE_COLLECT = "kff/collect/saveCollect";
    //取消对帖子的收藏  包括 讨论 文章 评测
    String CANCEL_COLLECT = "kff/collect/cancelCollect";


    //评测详情 页
    String EVALUATION_DETAIL = BASE_URL + "kff/home/evaluationDetail";
    //测评评论
    String EVLUATION_COMMENT_LIST = BASE_URL + "kff/home/evaluationCommentList";
    //捐赠(赞赏)
    String COMMENDATION = BASE_URL + "kff/token/commendation";
    //发表评测类型的帖子
    String SAVE_EVALUATION = BASE_URL + "kff/evaluation/saveEvaluation";
    //发表全面或部分 专业评测时需要用到此接口来获取 生效中的系统专业评测详情
    String GET_SYSEVALUATION_MODEL = BASE_URL + "kff/evaluation/getSysEvaluationModel";
    //发表评测 用户 自建评测模型
    String SAVE_EVALUATION_MODEL = BASE_URL + "kff/evaluation/saveEvaluationModel";

    //=======
    //讨论详情 页
    String HOME_DISCUSS_DETAIL = BASE_URL + "kff/home/discussDetail";
    //针对文章单个评论的评论列表页
    String ARTICLE_COMMENT_REPLY_LIST = BASE_URL + "kff/home/articleCommentReplyList";

    //发表讨论时 添加标签 获取标签列表
    String RELEASE_DISCUSS_LIST = BASE_URL + "/kff/discuss/tagList";
    String RELEASE_ARTICLE = BASE_URL + "/kff/article/saveArticle";
    String RELEASE_DISCUSS = BASE_URL + "/kff/discuss/saveDiscuss";
    String SEARCH_PROJECTS = BASE_URL + "/kff/project/searchProjects";

    String UPLOAD_IMG_FILE = BASE_URL + "kff/home/uploadImgFile";

    /**
     * 1-关注项目;2-关注帖子；3-关注用户
     */
    interface SaveFollow {
        int PROJECT = 1;
        int INVITATION = 2;
        int USER = 3;
    }

    /**
     * 1对应为值为“简单评测",
     * 2 为 "ALL-专业评测"
     * 3 为 "PART—项目立项、核心团队"
     * 4 为 "ALL-专业评测"
     */
    interface ModelType {
        String MODEL_TYPE = "model_type_key";
        int MODEL_TYPE_SIMPLENESS = 1;
        int MODEL_TYPE_ALL = 2;
        int MODEL_TYPE_PART = 3;
        int MODEL_TYPE_ALL_NEW = 4;
    }

    int TOPIC_SORT_BY_NUM = 1;
    int TOPIC_SORT_BY_NAME = 2;

}
