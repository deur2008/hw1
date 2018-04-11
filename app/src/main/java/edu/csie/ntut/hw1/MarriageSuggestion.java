package edu.csie.ntut.hw1;


import android.content.res.Resources;

public class MarriageSuggestion {

    public String getSuggestion(String strSex, int iAgeRange) {

        String strSug = "建議：";
        String get_married = "趕快結婚！";
        String find_couple = "開始找對象";
        String not_hurry = "還不急";
        String error = "請選擇性別";
        
        if (strSex.equals("男")) {
            if (iAgeRange==1)   strSug += not_hurry;
            else if(iAgeRange == 2) strSug += get_married;
            else if(iAgeRange == 3) strSug += find_couple;
        }
        else if (strSex.equals("女"))
        {
            if (iAgeRange==1)   strSug += not_hurry;
            else if(iAgeRange == 2) strSug += get_married;
            else if(iAgeRange == 3) strSug += find_couple;
        }
        else{
            strSug += error;
        }

        return strSug;
    }
}
