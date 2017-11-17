package anulom.executioner5.com3.anulom;

import android.location.Location;

/**
 * Created by Admin on 7/4/2016.
 */
public interface IFetchCurrentLocation {
    public void onSuccess(Location loc);
    public void onFailure();
    public void saveOldLocation(Location loc);
}
