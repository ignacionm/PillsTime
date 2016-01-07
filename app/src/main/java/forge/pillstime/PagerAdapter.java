package forge.pillstime;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * Created by Ignacio on 15/10/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter{
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new Fragment_Alarmas();
            case 1:
                return new Fragment_Historial();
            case 2:
                return new Fragment_Consejos();
            case 3:
                return new Fragment_Compras();
            default:
                return null;
        }
    }
    public int getCount() {
        return 4;
    }
}
