package flaotec.KFZ_Kennzeichen;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class KFZ extends Activity {

	int offset = 0;
	String updatecheck;
	boolean F,PL,D, AT, CH, IT, autochange = false;
	private EditText eKFZ;
	private Button bLand;
	private ImageView imgD, imgAT, imgCH;
	private TextView tTitle;
	private TableLayout tl;
	boolean BL = true, old = true;
	private KFZdatabase DB;
	SharedPreferences prefs;

	protected void Searchsettings(int offset) {
		this.offset=offset;
	}

	private boolean exect, gmaps, land;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		D = prefs.getBoolean("D", true);
		AT = prefs.getBoolean("AT", true);
		CH = prefs.getBoolean("CH", true);
		IT = prefs.getBoolean("IT", true);
		PL = prefs.getBoolean("PL", true);
		F = prefs.getBoolean("FR", true);
		exect = prefs.getBoolean("exact", true);
		gmaps = prefs.getBoolean("gmaps", true);
		land = prefs.getBoolean("land", true);
		BL = prefs.getBoolean("BL", true);
		search();
	}
	
	public void showdonate() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.donate));
 
		builder.setMessage(

				getString(R.string.donate_text))
				.setCancelable(false)
				.setPositiveButton(getString(R.string.donate), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						File fp = new File(
								updatecheck);
						try {
							fp.createNewFile();
						} catch (Exception e) {
							// TODO: handle exception
						}

						dialog.cancel();
						 Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://floatec.de/donate.html"));
				   		 startActivity(browser);
					}
				})
				.setNegativeButton(getString(R.string.no),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								File fp = new File(
										updatecheck);
								try {
									fp.createNewFile();
								} catch (Exception e) {
									Log.w("floatec",e.getMessage());
									// TODO: handle exception
								}
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* First Tab Content */
		DB = new KFZdatabase();
		setContentView(R.layout.main);
		updatecheck="/data/data/flaotec.KFZ_Kennzeichen/"+getString(R.string.app_version);
		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		D = prefs.getBoolean("D", true);
		AT = prefs.getBoolean("AT", true);
		CH = prefs.getBoolean("CH", true);
		IT = prefs.getBoolean("IT", true);
		PL = prefs.getBoolean("PL", true);
		F = prefs.getBoolean("FR", true);
		exect = prefs.getBoolean("exact", true);
		gmaps = prefs.getBoolean("gmaps", true);
		land = prefs.getBoolean("land", true);
		BL = prefs.getBoolean("BL", true);
		tl = (TableLayout) findViewById(R.id.TableLayout01);

		/* Create a new row to be added. */
		tl.removeAllViews();
		TableRow tr = new TableRow(this);
		tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		ImageView iv1 = new ImageView(this);

		iv1.setBackgroundResource(android.R.drawable.ic_menu_search);
		tr.addView(iv1);
		TextView tw = new TextView(this);
		tw.setText(getString(R.string.leer));
		tw.setTextSize(20);
		tr.addView(tw);
		tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		eKFZ = (EditText) findViewById(R.id.KFZ);
		eKFZ.setText(prefs.getString("edit", ""));
		eKFZ.setInputType(InputType.TYPE_TEXT_VARIATION_FILTER);
		eKFZ.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				// table.removeAllViews();
				
				search();
			}

			// We don't use these methods
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String tmp = eKFZ.getText().toString().toUpperCase();
				if (eKFZ.getText().toString().compareTo(tmp) != 0) {
					//eKFZ.setText(tmp);
					//eKFZ.setSelection(eKFZ.getText().toString().length());
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("edit", tmp);
					editor.commit();
				}

			}

		});
		search();
		File fp = new File(updatecheck);
		if (!fp.isFile()&&prefs.getString("donate", "").compareTo("NHZFtHxM3k!s%KxJ")!=0) {
			showdonate();
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void search() {

		tl.removeAllViews();
		/*
		 * if( spinner.getSelectedItemPosition()==1){ findOLand(true);
		 * findOLand(false); return; } if(
		 * spinner.getSelectedItemPosition()==2){ offset=1; }
		 */
		
		if (exect) {
			if (D) {
				findOrte("D", true, offset);
			}
			if (AT) {
				findOrte("AT", true, offset);
			}
			if (CH) {
				findOrte("CH", true, offset);
			}
			if (IT) {
				findOrte("IT", true, offset);
			}
			if (PL) {
				findOrte("PL", true, offset);
			}
			if (F) {
				findOrte("F", true, offset);
			}
			if (D) {
				findOrte("D", false, offset);
			}
			if (AT) {
				findOrte("AT", false, offset);
			}
			if (CH) {
				findOrte("CH", false, offset);
			}
			if (IT) {
				findOrte("IT", false, offset);
			}
			if (PL) {
				findOrte("PL", false, offset);
			}
			if (F) {
				findOrte("F", false, offset);
			}
		} else {
			if (D){
				drawLandtrenner(getString(R.string.D));
				findOrte("D", true, offset);
				findOrte("D", false, offset);
			}
			if (AT) {
				drawLandtrenner(getString(R.string.AT));
				findOrte("AT", true, offset);
				findOrte("AT", false, offset);
			}
			if (CH) {
				drawLandtrenner(getString(R.string.CH));
				findOrte("CH", true, offset);
				findOrte("CH", false, offset);
			}
			if (IT) {
				drawLandtrenner(getString(R.string.IT));
				findOrte("IT", true, offset);
				findOrte("IT", false, offset);
			}
			if (PL) {
				drawLandtrenner(getString(R.string.PL));
				findOrte("PL", true, offset);
				findOrte("PL", false, offset);
			}
			if (F) {
				drawLandtrenner(getString(R.string.FR));
				findOrte("F", true, offset);
				findOrte("F", false, offset);
			}
		}

	}
	private void drawLandtrenner(String Land){
	
				TableRow trtrenner = new TableRow(this);
				trtrenner.setGravity(Gravity.CENTER_VERTICAL);
				
				trtrenner.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				View line2 = new View(this);
				line2.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				line2.setBackgroundColor(Color.LTGRAY);
				line2.setMinimumHeight(20);
				trtrenner.addView(line2);
				View line3 = new View(this);
				line3.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				line3.setBackgroundColor(Color.LTGRAY);
				line3.setMinimumHeight(20);
				trtrenner.addView(line3);
				TextView line = new TextView(this);
				line.setBackgroundColor(Color.LTGRAY);
				line.setMinimumHeight(20);
				line.setText(Land);
				line.setTextColor(Color.BLACK);
				line.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				trtrenner.addView(line);
				

				tl.addView(trtrenner, new TableLayout.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}
	private int getBLresByKZ(String kz, String land) {

		if (kz == "Baden-Württemberg") {
			return R.drawable.bw;
		}
		if (kz == "Bayern") {
			return R.drawable.by;
		}
		if (kz == "Thüringen") {
			return R.drawable.th;
		}
		if (kz == "Berlin") {
			return R.drawable.be;
		}
		if (kz == "Brandenburg") {
			return R.drawable.bb;
		}
		if (kz == "Bremen") {
			return R.drawable.hb;
		}
		if (kz == "Hamburg") {
			return R.drawable.hh;
		}
		if (kz == "Hessen") {
			return R.drawable.he;
		}
		if (kz == "Mecklenburg-Vorpommern") {
			return R.drawable.mv;
		}
		if (kz == "Niedersachsen") {
			return R.drawable.ni;
		}
		if (kz == "Nordrhein-Westfalen") {
			return R.drawable.nw;
		}
		if (kz == "Rheinland-Pfalz") {
			return R.drawable.rp;
		}
		if (kz == "Saarland") {
			return R.drawable.sl;
		}
		if (kz == "Sachsen") {
			return R.drawable.sn;
		}
		if (kz == "Sachsen-Anhalt") {
			return R.drawable.st;
		}
		if (kz == "Schleswig-Holstein") {
			return R.drawable.sh;
		}

		if (kz == "Burgenland") {
			return R.drawable.burgenland;
		}
		if (kz == "Kärnten") {
			return R.drawable.kaernten;
		}
		if (kz == "" + "Niederösterreich") {
			return R.drawable.niederoesterreich;
		}
		if (kz == "Östetreich") {
			return R.drawable.oesterreich;
		}
		if (kz == "Salzburg") {
			return R.drawable.salzburg;
		}
		if (kz == "Steiermark") {
			return R.drawable.steiermark;
		}
		if (kz == "Tirol") {
			return R.drawable.tirol;
		}
		if (kz == "Vorarlberg") {
			return R.drawable.vorarlberg;
		}
		if (kz == "Wien") {
			return R.drawable.wien;
		}
		if (land == "AT") {
			return R.drawable.at;
		}
		return R.drawable.bd;
	}
	
	

	private String findOrte(String Land, boolean exect, int offset) {
		// wenn lehrer suchbegriff
		if (this.eKFZ.getText().toString().length() == 0) {
			tl.removeAllViews();
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			ImageView iv1 = new ImageView(this);
			iv1.setBackgroundResource(android.R.drawable.ic_menu_search);
			tr.addView(iv1);
			TextView tw = new TextView(this);
			tw.setText(getString(R.string.leer));
			tw.setTextSize(20);
			tr.addView(tw);
			tl.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			return "please enter";
		}
		// haptfunktion
		int difLength = this.eKFZ.getText().toString().length();
		String out = "";
		ArrayList<String> res;
		if(offset==0){
			res = DB.findKFZ(this.eKFZ.getText().toString(),
				Land, exect, old);
		}else{
			 res = DB.findRueck(this.eKFZ.getText().toString(),
					Land, exect, old);
			}
		if(res.size()>0&&this.exect){
			if (Land=="D"){
				drawLandtrenner(getString(R.string.D));
				
			}
			if (Land=="AT") {
				drawLandtrenner(getString(R.string.AT));
				
			}
			if (Land=="CH") {
				drawLandtrenner(getString(R.string.CH));
				
			}
			if (Land=="IT") {
				drawLandtrenner(getString(R.string.IT));
				
			}
			if (Land=="PL") {
				drawLandtrenner(getString(R.string.PL));
			
			}
			if (Land=="F") {
				drawLandtrenner(getString(R.string.FR));
			
			}
		}	
				
		for (int i = 0; i < res.size() / 5; i++) {

			// Log.i("KFZ",aSuch[i*5]+" "+aSuch[i*5+1]+" "+aSuch[i*5+2]);
			TableRow tr1 = new TableRow(this);
			tr1.setGravity(Gravity.CENTER_VERTICAL);
			tr1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			// create views
			ImageView iv1 = new ImageView(this);
			TextView tw = new TextView(this);
			TextView tw2 = new TextView(this);
			TextView tw3 = new TextView(this);
			LinearLayout LL = new LinearLayout(this);
			LL.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			/* image */
			iv1.setId(500 + i);

			if (Land == "CH")
				iv1.setBackgroundResource(R.drawable.ch);
			else if (Land == "IT")
				iv1.setBackgroundResource(R.drawable.it);
			else if (Land == "PL")
				iv1.setBackgroundResource(R.drawable.pl);
			else if (Land == "F")
				iv1.setBackgroundResource(R.drawable.fr);
			else {
				if (BL)
					iv1.setBackgroundResource(getBLresByKZ(res.get(i * 5 + 3),
							Land));
				else {

					if (Land == "AT")
						iv1.setBackgroundResource(R.drawable.at);
					else
						iv1.setBackgroundResource(R.drawable.de);
				}
			}
			if (exect) {
				tw.setTextSize(22);
				tw2.setTextSize(22);
				SpannableString str = SpannableString.valueOf(" "
						+ res.get(i * 5 + 2)+"\n"+ res.get(i * 5 + 3));
				str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
						 res.get(i * 5 + 2).length()+1, 0);
				tw2.setText(str);
				str = SpannableString.valueOf(" " + res.get(i * 5 + 1));
				str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
						str.length(), 0);
				
				tw.setText(str);
				tw3.setTextSize(12);
				tw3.setText(res.get(i * 5 + 3));
			} else {
				tw.setTextSize(18);
				tw2.setTextSize(18);
				SpannableString str = SpannableString.valueOf(" "
						+ res.get(i * 5 + 2)+"\n"+ res.get(i * 5 + 3));
				str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
						 res.get(i * 5 + 2).length()+1, 0);
				//str.setSpan(new Paint().setColor(0xDDDDDD), res.get(i * 5 + 2).length()+1, str.length(), 0);
				tw2.setText(str);
				tw.setText(res.get(i * 5 + 1));
				String t = res.get(i * 5 + 2);
				//tw2.setText(t);
				tw3.setTextSize(12);
				tw3.setText(res.get(i * 5 + 3));
			}
			tw.setTextColor(Color.BLACK);
			tw2.setTextColor(Color.BLACK);
			tw3.setTextColor(Color.BLACK);
			tw2.setTag(res.get(i * 5 + 2));
			tw.setTag(res.get(i * 5 + 2));

			View.OnClickListener eventHandler = new View.OnClickListener() {

				
				public void onClick(View v) {
					// TODO Auto-generated method stu

					Intent intent = new Intent(
							android.content.Intent.ACTION_VIEW,
							Uri.parse("geo:0,0?q=" + (String) v.getTag()));
					startActivity(intent);
					// openContextMenu(v);
				}
			};
			if (gmaps) {
				tw2.setOnClickListener(eventHandler);
				tw.setOnClickListener(eventHandler);
			}
			//registerForContextMenu(tw);
			//registerForContextMenu(tw2);
			tr1.addView(iv1);
			tr1.addView(tw);
			LL.setOrientation(LL.VERTICAL);
			//LL.addView(tw2);
			LL.addView(tw3);
			tr1.addView(tw2);
			out += res.get(i * 5 + 1) + " " + res.get(i * 5 + 2) + "\n";
			tl.addView(tr1, new TableLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			tl.setColumnShrinkable(2, true);
			TableRow trtrenner = new TableRow(this);
			trtrenner.setGravity(Gravity.CENTER_VERTICAL);
			trtrenner.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			View line = new View(this);
			line.setBackgroundColor(Color.LTGRAY);
			line.setMinimumHeight(2);
			trtrenner.addView(line);
			View line2 = new View(this);
			line2.setBackgroundColor(Color.LTGRAY);
			line2.setMinimumHeight(2);
			trtrenner.addView(line2);
			View line3 = new View(this);

			line3.setBackgroundColor(Color.LTGRAY);
			line3.setMinimumHeight(2);
			trtrenner.addView(line3);

			tl.addView(trtrenner, new TableLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		}
		out += "\n";

		return out.substring(0, out.length() - 1);
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Wähle");
		menu.add(0, 1, 0, "Maps öffnen");
		menu.add(0, 2, 0, "Landkreis googlen");
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 2, 0, getString(R.string.settings));
		menu.add(0, 1, 0, getString(R.string.about));
		menu.add(0, 3, 0, getString(R.string.fehlerreport));
		menu.add(0, 4, 0, getString(R.string.donate));
		menu.add(0, -1, 0, getString(R.string.exit));

		return true;

	}

	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 2:

			return true;

		case 1:

			return true;

		}
		return false;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 3:
			Intent browser = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("mailto:android@floatec.de?subject=Android app:"
							+ getString(R.string.app_name)
							+ " V."
							+ getString(R.string.app_version)
							+ "&body=Fehlendes Kennzeichen(missing item):\nAusgeschrieben(name):\nBundesland(ragion):\nWenn veraltet neues kennzeichen:\nLand(country):\n\nSonstige Fehler oder Verbesserungsvorschläge(other errors):"));
			startActivity(browser);
			return true;
		case 2:
			Intent settingsActivity = new Intent(getBaseContext(),
					preferences.class);
			startActivity(settingsActivity);
			return true;
		case 1:
			Intent intent_menu_ueber = new Intent(this,
					UeberSeiteAnzeigen.class);
			startActivity(intent_menu_ueber);
			return true;
		case 4: 
			showdonate();
			return true;
		case -1:
			this.finish();

			return true;

		}

		return false;

	}

	public void openContextMenu(View v) {

	}

}