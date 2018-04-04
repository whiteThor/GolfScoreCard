package org.vilchezruben.golfscorecard;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {

    private static final String PREFS_FILE = "org.vilchezruben.golfscorecard.preference";
    private static final String KEY_STROKECOUNT = "KEY_STROKECOUNT";
    Hole[] mHoles = new Hole[18];
    private ListAdapter mListAdaptar;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        //inicializando mHoles
        int strokes = 0;
        for (int i = 0; i < mHoles.length; i++) {
            strokes = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0);
            mHoles[i] = new Hole("Hole " + (i+1) + " : ", strokes );
        }

        mListAdaptar = new ListAdapter(this, mHoles);

        setListAdapter(mListAdaptar);
    }

    /**
     * Called as part of the activity lifecycle when an activity is going into
     * the background, but has not (yet) been killed.  The counterpart to
     * {@link #onResume}.
     * <p>
     * <p>When activity B is launched in front of activity A, this callback will
     * be invoked on A.  B will not be created until A's {@link #onPause} returns,
     * so be sure to not do anything lengthy here.
     * <p>
     * <p>This callback is mostly used for saving any persistent state the
     * activity is editing, to present a "edit in place" model to the user and
     * making sure nothing is lost if there are not enough resources to start
     * the new activity without first killing this one.  This is also a good
     * place to do things like stop animations and other things that consume a
     * noticeable amount of CPU in order to make the switch to the next activity
     * as fast as possible, or to close resources that are exclusive access
     * such as the camera.
     * <p>
     * <p>In situations where the system needs more memory it may kill paused
     * processes to reclaim resources.  Because of this, you should be sure
     * that all of your state is saved by the time you return from
     * this function.  In general {@link #onSaveInstanceState} is used to save
     * per-instance state in the activity and this method is used to store
     * global persistent data (in content providers, files, etc.)
     * <p>
     * <p>After receiving this call you will usually receive a following call
     * to {@link #onStop} (after the next activity has been resumed and
     * displayed), however in some cases there will be a direct call back to
     * {@link #onResume} without going through the stopped state.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onStop
     */
    @Override
    protected void onPause() {
        super.onPause();

        for (int i = 0; i < mHoles.length; i++) {
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getStrokeCount());
        }
        mEditor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clean_stroke) {
            mEditor.clear();
            mEditor.apply();

            for (Hole hole: mHoles) {
                hole.setStrokeCount(0);
            }
            mListAdaptar.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
