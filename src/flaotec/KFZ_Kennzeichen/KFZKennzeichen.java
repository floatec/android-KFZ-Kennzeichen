package flaotec.KFZ_Kennzeichen;





import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;


import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.TableRow.LayoutParams;


public class KFZKennzeichen extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        Resources res = getResources();
        /* TabHost will have Tabs */
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
        /* TabSpec used to create a new tab. 
         * By using TabSpec only we can able to setContent to the tab. 
         * By using TabSpec setIndicator() we can set name to tab. */
        
        /* tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
        TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        
        /* TabSpec setIndicator() is used to set name for the tab. */
        /* TabSpec setContent() is used to set content for a particular tab. */
        firstTabSpec.setIndicator(getString(R.string.kennzeichen),res.getDrawable(R.drawable.ic_tab_car)).setContent(new Intent(this,KFZ.class));
        secondTabSpec.setIndicator(getString(R.string.laender),res.getDrawable(R.drawable.ic_tab_land)).setContent(new Intent(this,LandTab.class));
        thirdTabSpec.setIndicator(getString(R.string.rueckwaerts),res.getDrawable(R.drawable.ic_tab_car_back)).setContent(new Intent(this,RueckwertsTab.class));
         
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec);
       tabHost.addTab(secondTabSpec);
       tabHost.addTab(thirdTabSpec);
        
    }
}