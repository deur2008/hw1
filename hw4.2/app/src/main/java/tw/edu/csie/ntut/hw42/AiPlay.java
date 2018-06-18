package tw.edu.csie.ntut.hw42;

public class AiPlay {
    public String testPlayA(int iComPlay) {
        String Str = "";

        // 1 – 剪刀, 2 – 石頭, 3 – 布.
        if (iComPlay == 1) {
            Str += "剪刀";
        }
        if (iComPlay == 2) {
            Str += "石頭";
        }
        if (iComPlay == 3) {
            Str += "布";
        }
        return Str;
    }
    public String testPlayB(int iComPlay, int mylar) {
        String Str = "判定輸贏：";
        if (iComPlay == mylar) {
            Str+="雙方平手！";
        } else if (((mylar - iComPlay) == 1) || ((iComPlay - mylar) > 1)) {
            Str+="恭喜，你贏了！";
        } else {
            Str+="很可惜，你輸了！";
        }
        return Str;
    }
}
