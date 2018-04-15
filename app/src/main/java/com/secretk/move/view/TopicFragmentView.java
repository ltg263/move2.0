package com.secretk.move.view;

import com.secretk.move.base.BaseResponseView;
import com.secretk.move.bean.TopicBean;


import java.util.List;

public interface TopicFragmentView extends BaseResponseView{
    void loadInfoSuccess(List<TopicBean> list);
}
