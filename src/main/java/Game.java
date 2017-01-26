/**
 * Created by k2works on 2017/01/18.
 */
public class Game {
    public int score()
    {
        return scoreForFrame(itsCurrentFrame);
    }

    public int getCurrentFrame()
    {
        return itsCurrentFrame;
    }

    public void add(int pins)
    {
        itsScorer.addThrow(pins);
        adjustCurrentFrame(pins);
    }

    private void adjustCurrentFrame(int pins)
    {
        if (strike(pins) || !firstThrowInFrame)
            advanceFrame();
        else
            firstThrowInFrame = false;
    }

    private boolean strike(int pins) {
        return (firstThrowInFrame && pins == 10);
    }

    private void advanceFrame() {
        itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);
    }

    public int scoreForFrame(int theFrame)
    {
        return itsScorer.scoreForFrame(theFrame);
    }

    private int itsCurrentFrame = 1;
    private  boolean firstThrowInFrame = true;
    private Scorer itsScorer = new Scorer();
}
