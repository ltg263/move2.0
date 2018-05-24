package com.secretk.move.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/22 16:38
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class SysEvaluationModelBean extends BaseRes{


    /**
     * data : {"modeDetailList":[{"id":13,"modelId":3,"detailName":"项目进度","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":20,"createTime":1525610023000,"createTimeStr":"2018-05-06 20:33:43","updateTime":1525610023000,"updateTimeStr":"2018-05-06 20:33:43","createUserId":1,"status":1,"totalScore":0,"raterNum":0.1},{"id":12,"modelId":3,"detailName":"团队实力","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":30,"createTime":1525610001000,"createTimeStr":"2018-05-06 20:33:21","updateTime":1525610001000,"updateTimeStr":"2018-05-06 20:33:21","createUserId":1,"status":1,"totalScore":0,"raterNum":0},{"id":11,"modelId":3,"detailName":"技术框架","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":20,"createTime":1525609979000,"createTimeStr":"2018-05-06 20:32:59","updateTime":1525609979000,"updateTimeStr":"2018-05-06 20:32:59","createUserId":1,"status":1,"totalScore":0,"raterNum":0},{"id":10,"modelId":3,"detailName":"项目定位","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":30,"createTime":1525609954000,"createTimeStr":"2018-05-06 20:32:34","updateTime":1525609954000,"updateTimeStr":"2018-05-06 20:32:34","createUserId":1,"status":1,"totalScore":0,"raterNum":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        private List<ModeDetailListBean> modeDetailList;

        public List<ModeDetailListBean> getModeDetailList() {
            return modeDetailList;
        }

        public void setModeDetailList(List<ModeDetailListBean> modeDetailList) {
            this.modeDetailList = modeDetailList;
        }

        public static class ModeDetailListBean implements Parcelable {
            /**
             * id : 13
             * modelId : 3
             * detailName : 项目进度
             * detailDesc : 从项目前景、市场空间、竞争对手等各方面来评估。
             * detailWeight : 20
             * createTime : 1525610023000
             * createTimeStr : 2018-05-06 20:33:43
             * updateTime : 1525610023000
             * updateTimeStr : 2018-05-06 20:33:43
             * createUserId : 1
             * status : 1
             * totalScore : 0
             * raterNum : 0.1
             */

            private int id;
            private int modelId;
            private String detailName;
            private String detailDesc;
            private int detailWeight;
            private long createTime;
            private String createTimeStr;
            private long updateTime;
            private String updateTimeStr;
            private int createUserId;
            private int status;
            private float totalScore;
            private double raterNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getModelId() {
                return modelId;
            }

            public void setModelId(int modelId) {
                this.modelId = modelId;
            }

            public String getDetailName() {
                return detailName;
            }

            public void setDetailName(String detailName) {
                this.detailName = detailName;
            }

            public String getDetailDesc() {
                return detailDesc;
            }

            public void setDetailDesc(String detailDesc) {
                this.detailDesc = detailDesc;
            }

            public int getDetailWeight() {
                return detailWeight;
            }

            public void setDetailWeight(int detailWeight) {
                this.detailWeight = detailWeight;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCreateTimeStr() {
                return createTimeStr;
            }

            public void setCreateTimeStr(String createTimeStr) {
                this.createTimeStr = createTimeStr;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateTimeStr() {
                return updateTimeStr;
            }

            public void setUpdateTimeStr(String updateTimeStr) {
                this.updateTimeStr = updateTimeStr;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public float getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(float totalScore) {
                this.totalScore = totalScore;
            }

            public double getRaterNum() {
                return raterNum;
            }

            public void setRaterNum(double raterNum) {
                this.raterNum = raterNum;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.modelId);
                dest.writeString(this.detailName);
                dest.writeString(this.detailDesc);
                dest.writeInt(this.detailWeight);
                dest.writeLong(this.createTime);
                dest.writeString(this.createTimeStr);
                dest.writeLong(this.updateTime);
                dest.writeString(this.updateTimeStr);
                dest.writeInt(this.createUserId);
                dest.writeInt(this.status);
                dest.writeFloat(this.totalScore);
                dest.writeDouble(this.raterNum);
            }

            public ModeDetailListBean() {
            }

            protected ModeDetailListBean(Parcel in) {
                this.id = in.readInt();
                this.modelId = in.readInt();
                this.detailName = in.readString();
                this.detailDesc = in.readString();
                this.detailWeight = in.readInt();
                this.createTime = in.readLong();
                this.createTimeStr = in.readString();
                this.updateTime = in.readLong();
                this.updateTimeStr = in.readString();
                this.createUserId = in.readInt();
                this.status = in.readInt();
                this.totalScore = in.readFloat();
                this.raterNum = in.readDouble();
            }

            public static final Parcelable.Creator<ModeDetailListBean> CREATOR = new Parcelable.Creator<ModeDetailListBean>() {
                @Override
                public ModeDetailListBean createFromParcel(Parcel source) {
                    return new ModeDetailListBean(source);
                }

                @Override
                public ModeDetailListBean[] newArray(int size) {
                    return new ModeDetailListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.modeDetailList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.modeDetailList = in.createTypedArrayList(ModeDetailListBean.CREATOR);
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
