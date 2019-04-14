package com.example.hp.readingyouself.ReadingDataSupport.LocData;

import com.example.hp.readingyouself.ReadingDataSupport.DataForm.Rank;

public class LocDataGiver {

    private Rank maleWeekRank;
    private Rank maleMonthRank;
    private Rank maleTotalRank;
    private Rank femaleWeekRank;
    private Rank femaleMonthRank;
    private Rank femaleTotalRank;


    public void setMaleWeekRank(Rank maleWeekRank) {
        this.maleWeekRank = maleWeekRank;
    }

    public void setMaleMonthRank(Rank maleMonthRank) {
        this.maleMonthRank = maleMonthRank;
    }

    public void setMaleTotalRank(Rank maleTotalRank) {
        this.maleTotalRank = maleTotalRank;
    }

    public void setFemaleWeekRank(Rank femaleWeekRank) {
        this.femaleWeekRank = femaleWeekRank;
    }

    public void setFemaleMonthRank(Rank femaleMonthRank) {
        this.femaleMonthRank = femaleMonthRank;
    }

    public void setFemaleTotalRank(Rank femaleTotalRank) {
        this.femaleTotalRank = femaleTotalRank;
    }

    public Rank getMaleWeekRank() {
        return maleWeekRank;
    }

    public Rank getMaleMonthRank() {
        return maleMonthRank;
    }

    public Rank getMaleTotalRank() {
        return maleTotalRank;
    }

    public Rank getFemaleWeekRank() {
        return femaleWeekRank;
    }

    public Rank getFemaleMonthRank() {
        return femaleMonthRank;
    }

    public Rank getFemaleTotalRank() {
        return femaleTotalRank;
    }
}
