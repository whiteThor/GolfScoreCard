package org.vilchezruben.golfscorecard;

/**
 * Created by Vilchez Ruben on 4/04/2018.
 */

public class Hole {

    private String mLabel;
    private int mStrokeCount;

    public Hole() {
    }

    public Hole(String label, int strokeCount) {
        mLabel = label;
        mStrokeCount = strokeCount;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getStrokeCount() {
        return mStrokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        mStrokeCount = strokeCount;
    }
}
