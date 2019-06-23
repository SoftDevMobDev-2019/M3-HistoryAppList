package au.edu.swin.sdmd.historyapplist.landmarks;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is a Singleton which returns a list of landmarks and allows us to get more details about
 * them. Context is passed as we might need it later on.
 *
 * @author nronald
 * @date August 2018
 */

public class Landmarks {

    private static Landmarks sLandmarks;

    private HashMap<String, Landmark> mLandmarks;

    public static Landmarks get(Context context) {
        if (sLandmarks == null) {
            sLandmarks = new Landmarks(context);
        }
        return sLandmarks;
    }

    private Landmarks(Context context) {
        mLandmarks = new HashMap<String, Landmark>();
        mLandmarks.put("Swinburne College", new Landmark(-37.822036, 145.038875));
        mLandmarks.put("Glenferrie Station",new Landmark(-37.821545, 145.036481));
        mLandmarks.put("Theatre", new Landmark( -37.819927, 145.035722));
        mLandmarks.put("Catholic Church", new Landmark( -37.822435,145.035231));
        mLandmarks.put("Corner shops", new Landmark(-37.822520, 145.035483));
        mLandmarks.put("Town Hall", new Landmark(-37.822821, 145.035992));
        mLandmarks.put("Anglican Church", new Landmark(-37.823361, 145.039576));
        mLandmarks.put("Hawthorn Hotel", new Landmark(-37.823027, 145.039909));
        mLandmarks.put("House", new Landmark( -37.823576, 145.042800));
        mLandmarks.put("Glenferrie Hotel ", new Landmark(-37.822604, 145.034480));
        mLandmarks.put("Auburn Rd", new Landmark(-37.823692, 145.044769));
        mLandmarks.put("Hawthorn Station", new Landmark(-37.821946, 145.023194));
        mLandmarks.put("Office building", new Landmark(-37.822170, 145.029600));

    }

    public HashMap<String, Landmark> getLandmarks() {
        return mLandmarks;
    }

    public Landmark getLandmark(String name) {
        if(mLandmarks.containsKey(name)) return mLandmarks.get(name);
        return null;
    }

    public List<String> getLandmarkNames() {
        return new ArrayList<String>(mLandmarks.keySet());
    }
}
