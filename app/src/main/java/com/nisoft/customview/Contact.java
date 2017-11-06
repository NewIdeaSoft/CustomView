package com.nisoft.customview;

import com.nisoft.androidlib.utils.PinYinUtils;

/**
 * Created by NewIdeaSoft on 2017/11/6.
 */

public class Contact {
    private String mNameCHI;
    private String mNameENG;

    public Contact(String nameCHI) {
        mNameCHI = nameCHI;
        mNameENG = PinYinUtils.getPinYin(mNameCHI);
    }

    public String getNameCHI() {
        return mNameCHI;
    }

    public void setNameCHI(String nameCHI) {
        mNameCHI = nameCHI;
    }

    public String getNameENG() {
        return mNameENG;
    }

    public void setNameENG(String nameENG) {
        mNameENG = nameENG;
    }
}
