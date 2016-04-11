package mx.com.profeluismitre.agendapestanas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LuisMitre on 04/04/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AltasFragment tab1 = new AltasFragment();
                return tab1;
            case 1:
                BajasFragment tab2 = new BajasFragment();
                return tab2;
            case 2:
                CambiosFragment tab3 = new CambiosFragment();
                return tab3;
            case 3:
                ConsultasFragment tab4 = new ConsultasFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
