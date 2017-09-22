package ListenerIm;

import ListenerIO.SetOnListenerIO;

/**
 * Created by ios12 on 17/9/22.
 */

public class SetOnListenerIm implements SetOnListenerIO {
    @Override
    public void setOnListener(String string) {
        System.out.println(string);
    }
}
