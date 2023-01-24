package com.oldigitalsolutions.management.utils;

public class StageUtils {

    public static final String getNextStage(String currentStage) {
        String nextStage = "";

        switch (currentStage) {
            case Constant.STAGE_NEW -> nextStage = Constant.STAGE_FIRST_OUTREACH;
            case Constant.STAGE_FIRST_OUTREACH -> nextStage = Constant.STAGE_SECOND_OUTREACH;
        }

        return nextStage;
    }
}
