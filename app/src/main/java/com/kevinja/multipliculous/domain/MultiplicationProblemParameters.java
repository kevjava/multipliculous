package com.kevinja.multipliculous.domain;

import android.os.Parcel;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MultiplicationProblemParameters implements ProblemParameters {
    private Map<Integer, Boolean> paramList;

    /**
     * @param paramList A list of possible operands
     */
    public MultiplicationProblemParameters(HashMap<Integer, Boolean> paramList) {
        this.paramList = paramList;
    }

    @Override
    public Map<Integer, Boolean> getParamList() {
        return paramList;
    }

}
