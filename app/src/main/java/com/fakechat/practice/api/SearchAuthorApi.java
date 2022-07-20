package com.fakechat.practice.api;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;


public final class SearchAuthorApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return "wxarticle/chapters/json";
    }

    /** 公众号 id */
    private int id;

    public SearchAuthorApi setId(int id) {
        this.id = id;
        return this;
    }

    public final static class Bean {

        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;

        public int getCourseId() {
            return courseId;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getOrder() {
            return order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }
    }
}