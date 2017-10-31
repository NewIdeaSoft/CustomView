package com.newideasoft.scrollpage;

import java.util.ArrayList;

/**
 * Created by NewIdeaSoft on 2017/10/31.
 */

public class DataPrepareTools {
    public static ArrayList<PageData> prepareData(int[] srcs, String[] titles) {
        ArrayList<PageData> preparedData = new ArrayList<>();
        if (srcs != null) {
            if (titles != null) {
                if (srcs.length == titles.length) {
                    for (int i = 0; i < srcs.length; i++) {
                        preparedData.add(new PageData(srcs[i], titles[i]));
                    }
                }
            } else {

                for (int i = 0; i < srcs.length; i++) {
                    preparedData.add(new PageData(srcs[i], null));
                }

            }
        }
        return preparedData;
    }
}
