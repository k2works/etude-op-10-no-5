/**
 * Created by k2works on 2017/01/18.
 */
public class Game {
    public int score()
    {
        return scoreForFrame(getCurrentFrame()-1);
    }

    public int getCurrentFrame()
    {
        return itsCurrentFrame;
    }

    public void add(int pins)
    {
        itsThrows[itsCurrentThrow++]=pins;
        itsScore += pins;
        adjustCurrentFrame(pins);
    }

    private void adjustCurrentFrame(int pins)
    {
        if (firstThrowInFrame == true )
        {
            if( pins == 10 ) // ストライク
                itsCurrentFrame++;
            else
                firstThrowInFrame = false;
        }
        else
        {
            firstThrowInFrame =true;
            itsCurrentFrame++;
        }
        itsCurrentFrame = Math.min(11, itsCurrentFrame);
    }

    public int scoreForFrame(int theFrame)
    {
        ball = 0;
        int score = 0;
        for (int currentFrame = 0;
             currentFrame < theFrame;
             currentFrame++)
        {
            firstThrow = itsThrows[ball];
            if (strike())
            {
                ball++;
                score += 10 + nextTwoBalls();
            }
            else
            {
                score += handleSecondThrow();
            }
        }
        return score;
    }

    private boolean strike() {
        return firstThrow == 10;
    }

    private int nextTwoBalls() {
        return itsThrows[ball] + itsThrows[ball + 1];
    }

    private int handleSecondThrow() {
        int score = 0;
        secondThrow = itsThrows[ball+1];
        int frameScore = firstThrow + secondThrow;
        // スペアの得点計算には次のフレームの第１投が必要
        if ( frameScore == 10 )
        {
            ball += 2;
            score += frameScore + itsThrows[ball];
        }
        else
        {
            ball += 2;
            score += frameScore;
        }
        return score;
    }

    private int ball;
    private int firstThrow;
    private int secondThrow;

    private int itsScore = 0;
    private int[] itsThrows = new int[21];
    private int itsCurrentThrow = 0;
    private int itsCurrentFrame = 1;
    private  boolean firstThrowInFrame = true;
}
