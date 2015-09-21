package com.kevinja.multipliculous.domain;

import android.os.Parcelable;

/**
 * Created by kev on 2015-09-20.
 */
public interface Problem {
    void generate();
    Integer getAnswer();
    ProblemParameters getProblemParameters();
}
