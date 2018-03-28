package edu.csie.ntut.hw1;


import android.content.res.Resources;

public class MarriageSuggestion {

    public String getSuggestion(String strSex, int iAgeRange, int numFamily) {

        String strSug = "建議：";
        String get_married = "趕快結婚！";
        String find_couple = "開始找對象";
        String not_hurry = "還不急";
        
        if (strSex.equals("男")) {
            if (iAgeRange==1) {
                    if (numFamily < 4)  strSug += get_married;
                    else if (numFamily >= 4 && numFamily <= 10) strSug += get_married;
                    else    strSug += not_hurry;
            }
            else if(iAgeRange == 2) {
                if (numFamily < 4)  strSug += get_married;
                else if (numFamily >= 4 && numFamily <= 10) strSug += find_couple;
                else    strSug += not_hurry;
            }
            else if(iAgeRange == 3) {
                if (numFamily < 4)  strSug += find_couple;
                else if (numFamily >= 4 && numFamily <= 10) strSug += get_married;
                else    strSug += find_couple;
            }
        }
        else
        {
            if (iAgeRange==1) {
                if (numFamily < 4)  strSug += get_married;
                else if (numFamily >= 4 && numFamily <= 10) strSug += get_married;
                else    strSug += not_hurry;
            }
            else if(iAgeRange == 2) {
                if (numFamily < 4)  strSug += get_married;
                else if (numFamily >= 4 && numFamily <= 10) strSug += find_couple;
                else    strSug += not_hurry;
            }
            else if(iAgeRange == 3) {
                if (numFamily < 4)  strSug += find_couple;
                else if (numFamily >= 4 && numFamily <= 10) strSug += get_married;
                else    strSug += find_couple;
            }
        }

        return strSug;
    }
}
