package com.kevinja.multipliculous.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by kev on 2015-09-20.
 */
public interface ProblemParameters {
    Map<Integer, Boolean> getParamList();
}
