import java.util.ArrayList;

public class Line {

    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;

    public Line(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public Boolean isLineHOrV() {
        if (this.xStart == this.xEnd || this.yStart == this.yEnd) {
            return true;
        }
        return false;
    }

    public Boolean isLineDia() {
        if (java.lang.Math.abs(xStart - xEnd) == java.lang.Math.abs(yStart - yEnd)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> allPointsHorOrVer() {
        ArrayList<String> tempList = new ArrayList<>();

        // all points for vertical line
        if (this.xStart == this.xEnd) {
            if (yStart < yEnd) {
                for (int i = yStart; i <= yEnd; i++) {
                    tempList.add(xStart + "," + i);
                }
            } else {
                for (int i = yEnd; i <= yStart; i++) {
                    tempList.add(xStart + "," + i);
                }
            }

        }
        // all points for horizontal line
        else {
            if (xStart < xEnd) {
                for (int i = xStart; i <= xEnd; i++) {
                    tempList.add(i + "," + yStart);
                }
            } else {
                for (int i = xEnd; i <= xStart; i++) {
                    tempList.add(i + "," + yStart);
                }
            }

        }

        return tempList;
    }

    public ArrayList<String> allPointsDia() {
        ArrayList<String> tempList = new ArrayList<>();

        if (this.xStart < this.xEnd && this.yStart < this.yEnd) {
            int x = this.xStart;
            int y = this.yStart;

            while (x <= this.xEnd) {
                tempList.add(x + "," + y);
                x++;
                y++;
            }
        } else if (this.xStart > this.xEnd && this.yStart > this.yEnd) {
            int x = this.xEnd;
            int y = this.yEnd;

            while (x <= this.xStart) {
                tempList.add(x + "," + y);
                x++;
                y++;
            }
        } else if(this.xStart < this.xEnd && this.yStart > this.yEnd) {
            int x = this.xStart;
            int y = this.yStart;

            while (x <= this.xEnd) {
                tempList.add(x + "," + y);
                x++;
                y -= 1;
            }
        } else if (this.xStart > this.xEnd && this.yStart < this.yEnd) {
            int x = this.xStart;
            int y = this.yStart;

            while (x >= xEnd) {
                tempList.add(x + "," + y);
                x--;
                y++;
            }
        }

        return tempList;
    }

    @Override
    public String toString() {
        return this.xStart + "," + this.yStart + " -> " + this.xEnd + "," + this.yEnd;
    }

}
