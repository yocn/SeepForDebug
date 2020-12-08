package com.yocn.seep.config;

import android.view.View;

public class SeepConfig {
    private static SeepConfig instance;
    private SeepNetConfig seepNetConfig = new SeepNetConfig();
    private SeepUIConfig seepUIConfig = new SeepUIConfig();

    private SeepConfig() {
    }

    public static SeepConfig getInstance() {
        if (instance == null) {
            instance = new SeepConfig();
        }
        return instance;
    }

    public SeepNetConfig getSeepNetConfig() {
        return seepNetConfig;
    }

    public SeepUIConfig getSeepUIConfig() {
        return seepUIConfig;
    }

    public static class SeepConfigBuilder {
        private static SeepConfigBuilder instance;
        private SeepNetConfig seepNetConfig = new SeepNetConfig();
        private SeepUIConfig seepUIConfig = new SeepUIConfig();

        public static SeepConfigBuilder getInstance() {
            if (instance == null) {
                instance = new SeepConfigBuilder();
            }
            return instance;
        }


        public SeepConfigBuilder SetFilterFragment(Class<? extends androidx.fragment.app.Fragment>... filters) {
            seepUIConfig.filterFragment(filters);
            return this;
        }

        public SeepConfigBuilder SetFilterView(Class<? extends View>... filters) {
            seepUIConfig.filterView(filters);
            return this;
        }
    }
}
