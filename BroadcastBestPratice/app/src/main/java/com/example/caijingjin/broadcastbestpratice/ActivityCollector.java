package com.example.caijingjin.broadcastbestpratice;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
/**
 * Created by caijingjin on 2017/5/1.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);

    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);

    }

    public static void finishAll(){
        for(Activity activity: activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
