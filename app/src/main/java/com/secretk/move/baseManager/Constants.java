package com.secretk.move.baseManager;

import android.os.Environment;

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
     * 登录的用户类型
     */
    String USER_TYPE = "UserType";
    /**
     * 登录的用户ID
     */
    String USER_ID = "userId";
    /**
     * 登录的用户FIND数量
     */
    String KFF_COIN_NUM = "kffCoinNum";
    /**
     * 登录的用户手机号
     */
    String MOBILE = "mobile";
    /**
     * 分页单次请求数量
     */
    int PAGE_SIZE = 10;
    /**
     * 是否开始推送服务
     */
    boolean IS_PUSH_SERVICE = true;

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
     * 常用外母目录
     */
    String IMG_PATH = Environment.getRootDirectory().getPath()+File.separator+SHAREDPREFS_NAME;
    /**
     * 是否开启调试模式
     */
    boolean DEBUG = true;
    /**
     * 评测的默认分数
     */
    float DEFAULT_SCORE = 6.0f;
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

    String BASE_IMG_URL = "";
    String BASE_IMG_HTML5 = "https://m.qufen.top/";
    String QUNIU_IMG_RUL = "https://pic.qufen.top/";


    String BASE_URL = "http://app.qufen.top/rest/";//线上
//
//     String BASE_URL = "http://192.168.10.153:809/";//栋栋
//
//      String BASE_URL = "http://repayy.free.ngrok.cc/rest/";//林俊
//      String BASE_URL = "http://192.168.10.128:8044/rest/";//林俊
//      :15537894586   abc12310  志远手机密码：0405
//    String BASE_URL = "http://47.98.197.101/rest/";//宋


//     String BASE_URL = "http://192.168.10.205/";//測試
    /**
     * 关于我们
      */
    String ABOUT = BASE_IMG_HTML5+"mine/about";
    /**
     * 帮助中心
      */
    String HELP = BASE_IMG_HTML5+"mine/help";
    /**
     * 注册协议
      */
    String AGREEMENT = BASE_IMG_HTML5+"user/agreement";
    /**
     * 评测模型说明
     */
    String MODEL = BASE_IMG_HTML5+"user/model";
    /**
     * 悬赏规则
     */
    String REGULATION = BASE_IMG_HTML5+"mine/regulation";
    /**
     * 评测分享
      */
    String EVALUATION_SHARE= BASE_IMG_HTML5+"project/articleInfo?id=";
    /**
     * 评测部分分享
      */
    String EVALUATION_PART_SHARE = BASE_IMG_HTML5+"project/evaluationLitt?id=";
    /**
     * 文章分享
      */
    String ARTICLE_SHARE = BASE_IMG_HTML5+"project/article?id=";
    /**
     * 讨论分享
      */
    String DISCUSS_SHARE= BASE_IMG_HTML5+"project/discuss?id=";
    /**
     * 懸賞讨论分享
      */
    String ANSWER= BASE_IMG_HTML5+"reward/answer?id=1";
    /**
     * 悬赏分享
      */
    String REWARD = BASE_IMG_HTML5+"reward/?id=";
    /**
     * 新闻
      */
    String CURRENCY= BASE_IMG_HTML5+"project/currency?id=";
    /**
     * 赚币中心 邀请好友
      */
    String INVITE_FRIENDS = BASE_IMG_HTML5+"redenvelopes/envelopes?invaUIH=";
    /**
     * 币智慧
     */
    String BI_ZHI_HUI= "http://bizhihui.baiduux.com/h5/bizhihui.html";
    /**
     * 分享默认图标
     */
    String SHARE_MR_IOCN= "https://app.qufen.top/upload/file/logo.png";
    /**
     * 分享默认红包图标
     */
    String SHARE_HONGBAO_IOCN= "https://app.qufen.top/upload/file/hongbao.png";

    //发送验证码通用接口
//    String DYNAMIC_VALIDATE_CODE_SEND = BASE_URL + "kff/dynamicValidateCode/send";
    ////发送验证码通用接口 阿里云 module ：login   register    forgetPassword
    String DYNAMIC_VALIDATE_CODE_SEND = BASE_URL + "kff/dynamicValidateCode/sendAliyun";

    String TOKEN_POP = BASE_URL + "kff/user/tokenPOP";
    //获取七牛token
    String SEND_TOKEN = BASE_URL + "kff/qiNiuUpload/sendToken";
    //校验验证码接口。
    String DYNAMIC_VALIDATE_CODE_VERIFY = BASE_URL + "kff/dynamicValidateCode/verify";
    //验证手机号是否已经存在
    String PHONE_AVAILABLE = BASE_URL + "kff/user/register/phoneAvailable";
    //用户注册
    String USER_REGISTER = BASE_URL + "kff/user/register";
    //老用户用户登录
    String USER_LOGIN = BASE_URL + "kff/user/login";
    //获取登录用户信息。
    String GET_USER_INFO = BASE_URL + "kff/user/getUserInfo";
    //重置密码
    String FORGET_PASSWORD = BASE_URL + "kff/user/forgetPassword";
    //总产明细列表的接口
    String MY_TOKEN_RECORDS = BASE_URL + "kff/user/myTokenRecords";
    //总产明细列表的接口
    String MY_TOKEN_BILL = BASE_URL + "kff/user/myTokenBill";
    //发放中的接口
    String TOKEN_IN_DISTRIBUTED = BASE_URL + "kff/user/TokenInDistributed";
    //绑定钱包的接口
    String MY_PINLESS_WALLET = BASE_URL + "kff/user/myPinlessWallet";

    //首页推荐
    String MAIN_RECOMMEND = BASE_URL + "kff/home/newRecommendList";
    //首页关注
    String MAIN_FOLLOW = BASE_URL + "kff/home/followList";
    //首页评测
    String RECOMMEND_LIST = BASE_URL + "kff/home/recommendList";
    //悬赏主页列表
    String REWARD_LIST = BASE_URL+"kff/rewardActivity/rewardList";
    //首页最新
    String NEWEST_LIST = BASE_URL + "kff/home/newestList";
    //首页打假
    String MAIN_DISCUSS = BASE_URL + "kff/home/getBurstList";
    //青天榜
    String MAIN_BLUE_SKY = BASE_URL + "kff/home/projectRankList";
    //项目Tab
    String GET_PROJECT_TAB = BASE_URL + "kff/project/getProjectTab";
    //获取tab栏下相关的项目列
    String GET_PROJECT_BY_TAB_ID = BASE_URL + "kff/project/getProjectByTabId";

    //上传用户头像 1用户头像；2帖子中内容图片；3项目图标
    String UPLOAD_USER_ICON_FILE = BASE_URL + "kff/home/uploadImgFile";

    //上传认证照片
    String ID_CARD = BASE_URL + "kff/upload/idcard";
    //上传认证信息
    String UPLOAD_USER_CARD = BASE_URL + "kff/userCard/uploadUserCard";
    //重新上传认证信息
    String CARD_TI_FORM_AGAIN = BASE_URL + "kff/userCard/submitUserCardTiFormAgain";
    //提交项目。
    String SUBMIT_PROJECT = BASE_URL + "kff/project/submitProject";
    //获取项目类型列表
    String PROJECT_TYPE_LIST = BASE_URL + "kff/project/projectTypeList";
    //	更新用户信息（头像，昵称）
    String UPDATE_USER_INFO = BASE_URL + "kff/user/updateUserInfo";
    //	我的收藏列表
    String MYC_OLLECT_LIST = BASE_URL + "kff/user/myCollectList";
    //	币圈必读相关接口
    String GET_BG_MUST_READ = BASE_URL + "/kff/post/getBgMustRead";
    //	我的粉丝列表
    String GET_MY_FAN_LIST = BASE_URL + "/kff/user/getMyFanList";
    //	我的收藏列表
    String MY_FOLLOW_LIST = BASE_URL + "kff/user/myFollowList";
    //点击生成邀请奖励
    String CREATE_URLIN_REGISTER = BASE_URL + "kff/user/createUrlInRegister";
    //点击生成poster 海报
    String CREATE_POSTE = BASE_URL + "kff/user/createPoster";
    //提交意见反馈。
    String SUBMIT_SUGGEST = BASE_URL + "kff/system/submitSuggest";
    String GET_H5_URLS = BASE_URL + "kff/user/getH5URLs";

    //用户主页
    String USERHOME_INDEX = BASE_URL + "kff/userhome/index";
    //判断用户当前版本确定是否提示用户升级。
    String UPGRADE = BASE_URL + "kff/system/upgrade";
    //用户 测评列表
    String USERHOME_EVALUATION_LIST = BASE_URL + "kff/userhome/evaluationList";
    //用户讨论列表
    String USERHOME_DISCUSS_LIST = BASE_URL + "kff/userhome/discussList";
    //  文章列表
    String USERHOME_ARTICLE_LIST = BASE_URL + "kff/userhome/articleList";
    //  简单评测  项目统计
    String SIMPLE_EVALUATION_LIST = BASE_URL + "kff/project/simpleEvaluationList";

    //获得投诉详情选项列表
    String GET_REPORT_LIST = BASE_URL + "kff/report/getReportModelList";
    //进行投诉操作
    String SAVE_REPORT = BASE_URL + "kff/report/saveReport";

    //项目主页 ，显示项目基本信息
    String PROJECT_INDEX = BASE_URL + "kff/project/index";
    //首页审核展示状态
    String USERCARD_STATUS = BASE_URL + "kff/userCard/usercardStatus ";
    //我的消息列表。
    String MESSAGE_LIST = BASE_URL + "kff/message/messageList";
    //消息详情
    String MESSAGE_DETAIL = BASE_URL + "kff/message/messageDetail";
    //消息详情
    String DELETE_MESSAGE = BASE_URL + "kff/message/deleteMessage";
    //项目主页 评测列表
    String PROJECT_EVALUATION_LIST = BASE_URL + "kff/project/evaluationList";
    //项目讨论列表
    String PROJECT_DISCUSS_LIST = BASE_URL + "kff/project/discussList";
    //项目  文章列表
    String PROJECT_ARTICLE_LIST = BASE_URL + "kff/project/articleList";
    //行情数据(获得交易所和交易对)
    String GET_EXCHANGE_AND_TRAN_PAIR = BASE_URL + "kff/project/getExchangeAndTranPair";
    //讨论详情 页
    String DISCUSS_DETAIL = BASE_URL + "kff/home/discussDetail";
    //项目评分统计
    String EVA_STAT_SCORE = BASE_URL + "kff/project/evaStatScore";
    //对(评测/文章/讨论)类型的帖子进行评论或者对评论内容进行评论
    String SAVE_COMMENT = BASE_URL + "kff/comments/saveComment";
    //讨论详情 最新评测列表和单个评论详情页childCommentList
    String HOME_DISCUSS_COMMENT_LIST = BASE_URL + "kff/home/discussCommentList";
    //文章详情 页面
    String ARTICLE_DETAIL = BASE_URL + "kff/home/articleDetail";
    //文章评论列表
    String ARTICLE_COMMENT_LIST = BASE_URL + "kff/home/articleCommentList";

    //对 评论进行点赞
    String SAVE_COMMENTS_PRAISE = BASE_URL + "kff/praise/saveCommentsPraise";
    //取消对某个评论的点赞
    String CANCEL_COMMENTS_PRAISE = BASE_URL + "kff/praise/cancelCommentsPraise";

    //点赞帖子 包括评测 ，文章
    String SAVE_POST_PRAISE = BASE_URL + "kff/praise/savePostPraise";
    //取消对帖子的点赞  包括 文章 评测
    String CANCEL_POST_PRAISE = BASE_URL + "kff/praise/cancelPostPraise";
    //取消关注 项目 / 帖子 /人
    String CANCEL_FOLLOW = BASE_URL + "kff/follow/cancelFollow";
    //关注 项目 / 帖子 /人
    String SAVE_FOLLOW = BASE_URL + "kff/follow/saveFollow";

    //收藏帖子 包括 讨论，评测 ，文章
    String SAVE_COLLECT = "kff/collect/saveCollect";
    //取消对帖子的收藏  包括 讨论 文章 评测
    String CANCEL_COLLECT = "kff/collect/cancelCollect";

    //获取首页资讯的列表
    String GET_NEWS_FLASH_PAGE_LIST = BASE_URL + "kff/newsFlash/getNewsFlashPageList";
    //获取币种详情页的快讯列表接口
    String GET_APP_NEWS_FLASH_PAGE_LIST = BASE_URL + "kff/newsFlash/getAppNewsFlashPageList";

    //点评挖矿列表接口
    String GET_MINING_ACTIVITY_PAGE_LIST = BASE_URL + "kff/miningActivity/getMiningActivityPageList";
    //尽调挖矿接口
    String GET_REWARD_ACTIVITY_LIST = BASE_URL + "kff/rewardActivity/getRewardActivityList";

    //获取首页资讯的头部轮播图
    String GET_NEWS_FLASH_IMG_LIST = BASE_URL + "kff/newsFlashImg/getNewsFlashImgList";
    //点评挖矿活动说明接口
    String GET_EXPLAIN_ACTIVITY = BASE_URL + "kff/miningActivity/getExplainActivity";

    //获取点评挖矿的详细信息接口，前端需要自己控制好活动是否开始，活动不是进行中的就不能领取活动
    String GET_MINING_ACTIVITY_DETAIL = BASE_URL + "kff/miningActivity/getMiningActivityDetail";
    //领取活动奖励接口
    String ADD_REWARD_DETAIL = BASE_URL + "kff/rewardDetail/addRewardDetail";

    //获取发现顶部的轮播图
    String GET_NEWS_FLASH_IMG_LIST_FOR_FOUND = BASE_URL + "kff/newsFlashImg/getNewsFlashImgListForFound";

    //发现页面取热门项目，活跃用户
    String GET_HOT_PROJECT_AND_HOT_USER = BASE_URL + "kff/miningActivity/getHotProjectAndHotUser";
    //快讯看涨和看跌
    String UPDATE_NEWS_FLASH_RISE_AND_FALL = BASE_URL + "kff/newsFlash/updateNewsFlashRiseAndFall";


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

    //讨论详情 页
    String HOME_DISCUSS_DETAIL = BASE_URL + "kff/home/discussDetail";

    //活动分享成功回调页面
    String ADD_POST_SHARE = BASE_URL + "kff/postShare/addPostShare";
    //根据用户查询挖矿的奖励收益列表接口
    String GET_USER_REWARD_LIST = BASE_URL + "kff/rewardDetail/getUserRewardList";
    //讨论，评测，文章 中 某个评论的评论列表
    String COMMENT_COMMENTS_LIST = BASE_URL + "kff/home/commentCommentsList";

    //.KOL榜单
    String GET_KOL_PROJECT_PAGE = BASE_URL + "kff/rankingList/getKOLProjectPage";
    //获得最热项目榜
    String GET_HOT_PROJECT_PAGE = BASE_URL + "kff/rankingList/getHotProjectPage";
    //项目评分榜(
    String GET_EVA_PROJECT_PAGE = BASE_URL + "kff/rankingList/getEvaProjectPage";

    //发表讨论时 添加标签 获取标签列表
    String RELEASE_DISCUSS_LIST = BASE_URL + "kff/discuss/tagList";
    String GET_TAGS_AND_TAG_TYPE = BASE_URL + "kff/tags/getTagsAndTagType";
    String RELEASE_ARTICLE = BASE_URL + "kff/article/saveArticle";
    String RELEASE_DISCUSS = BASE_URL + "kff/discuss/saveDiscuss";
    String SEARCH_PROJECTS_1 = BASE_URL + "kff/project/searchProjects";
    String SEARCH_PROJECTS = BASE_URL + "kff/project/getProjectPage";

    //1-项目，2-内容，
    //3-用户，4-话题
    String INDEX_SEARCH = BASE_URL+"kff/search/indexSearch";
    //话题 详情
    String GET_DTAG_DETAIL = BASE_URL+"kff/tags/getDTagDetail";
    //获取话题下的评测，文章，讨论
    //type	int	帖子类型：1-评测；2-讨论；3-文章
    String GET_POST_INFO_WITH_TAGS = BASE_URL+"kff/post/getPostInfoWithTags";
    //讨论页面获取话题接口
    String GET_DTAGS_INFO = BASE_URL+"kff/tags/getDTagsInfo";

    //悬赏详情
    String REWARD_DETAIL = BASE_URL+"kff/rewardActivity/rewardDetail";

    //发布悬赏接口
    String SAVE_REWARD_ACTIVITY = BASE_URL+"kff/rewardActivity/saveRewardActivity";
    //悬赏精彩，全部回答接口
    String GET_REWARD_ANSWER_LIST = BASE_URL+"kff/rewardActivity/getRewardAnswerList";
//    悬赏广场接口?
    String REWARD_SQUARE_LIST = BASE_URL+"kff/rewardActivity/rewardSquareList";
    //终止悬赏接口
    String REWARD_LIST_AZ = BASE_URL+"kff/rewardActivity/stopReward";
    String GET_MEMBER = BASE_URL+"kff/userhome/getMember";
    //

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
    /**
     *上传用户头像
     * 1用户头像；
     * 2帖子中内容图片；
     * 3项目图标
     */
    interface UPLOADIMG_TYPE {
        String IMG_TYPE_KEY = "imgtype";
        String USER_ICON = "1";
        String POST_ICON = "2";
        String PROJECT_ICON = "3";
    }

    /**
     * 发布成功 跳转对应的key
     *
     */
    interface PublishSucceed {
        String PUBLISH_TYPE = "publish_type";
        String SUBMIT_TEXT = "submit_text";
        String SUBMIT_TITLE = "submit_title";
        String PUBLISH_BTN_TEXT = "publish_btn_text";
        String PUBLISH_POST_ID = "publish_post_id";
        //评测
        String EVALUATION = "evaluation";
        //文章
        String ARTICLE = "article";
        //讨论
        String DISCUSS = "discuss";
        //悬赏
        String REWARD = "reward";
        //发布项目
        String PUBLISH_PROJECT = "publish_project";
    }
    int TOPIC_SORT_BY_NUM = 1;
    int TOPIC_SORT_BY_NAME = 2;

}
