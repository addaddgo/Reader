package com.example.hp.readingyouself.readingDataSupport.locData;

import com.example.hp.readingyouself.readingDataSupport.dataForm.Rank;
import com.example.hp.readingyouself.readingDataSupport.dataForm.SearchLog;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class LocDataGiver {

    public LocDataGiver(){
        Connector.getDatabase();
    }
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

    public List<SearchLog> getSearchLog(){
        List<SearchLog> logs =  DataSupport.findAll(SearchLog.class);
        if(logs == null){
           logs = new ArrayList<>();
           return logs;
        }else{
            return logs;
        }
    }
}
