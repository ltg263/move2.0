package com.secretk.move.bean;

/**
 * 作者： litongge
 * 时间：  2018/5/21 17:22
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class EvaluationNewBean {
    String modelName;
    float score;
    int modelWeight;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getModelWeight() {
        return modelWeight;
    }

    public void setModelWeight(int modelWeight) {
        this.modelWeight = modelWeight;
    }

    @Override
    public String toString() {
        return "{" +
                "modelName:'" + modelName + '\'' +
                ", score:" + score +
                ", modelWeight:" + modelWeight +
                '}';
    }
}
