package flaotec.KFZ_Kennzeichen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LandTab extends Activity {

	boolean D, AT, CH, IT, autochange = false;
	private EditText eKFZ;
	private Button bLand;
	private ImageView imgD, imgAT, imgCH;
	private TextView tTitle;
	private TableLayout tl;
	boolean BL = true, old = true;
	private static final String[] aLand = { "A", "Österreich", "AFG",
			"Afghanistan", "AL", "Albanien", "AM", "Armenien", "AND",
			"Andorra", "ANG", "Angola", "AUS", "Australien", "AXA", "Anguilla",
			"AZ", "Aserbaidschan", "B", "Belgien", "BD", "Bangladesch", "BDS",
			"Barbados", "BF",
			"Burkina Faso (früher Obervolta HV (Haute Volta))", "BG",
			"Bulgarien", "BIH", "Republik Bosnien-Herzegowina", "BOL",
			"Bolivien", "BR", "Brasilien", "BRN", "Bahrain", "BRU",
			"Brunei Darussalam", "BS", "Bahamas", "BY",
			"Belarus (Republik Weißrußland)", "BZ",
			"Belize (früher BH British Honduras)", "C", "Kuba", "CC",
			"Corps Consulaire (Konsularischer Korps)", "CD",
			"Demokratische Republik Kongo (früher: ZRE Zaire)", "CDN",
			"Kanada", "CH", "Schweiz", "CI", "Côte d'Ivore (Elfenbeinküste)",
			"CL", "Sri Lanka (früher auch: Ceylon)", "CO", "Columbien", "COM",
			"Komoren", "CR", "Costa Rica", "CY", "Zypern", "CZ",
			"Tschechische Republik", "D", "Deutschland", "DK", "Dänemark",
			"DOM", "Dominikanische Republik", "DZ", "Algerien", "E", "Spanien",
			"EAK", "Kenia", "EAT", "Tansania", "EAU", "Uganda", "EC",
			"Ekuador", "ER", "Eritrea", "ES", "El Salvador", "EST", "Estland",
			"ET", "Ägypten / Vereinigte Arabische Republiken", "ETH",
			"Äthiopien", "EW", "Republik Estland", "F", "Frankreich", "FIN",
			"Finnland (früher: SF)", "FJI", "Fidschi", "FL", "Liechtenstein",
			"FR", "Färöer", "FSM",
			"Mikronesien (Federated States of Micronesia)", "G", "Gabun", "GB",
			"Großbritannien", "GBA", "Alderney", "GBG", "Guernsey", "GBJ",
			"Jersey", "GBM", "Insel Man", "GBZ", "Gibraltar", "GCA",
			"Guatemala", "GE", "Georgien", "GH", "Ghana", "GNB",
			"Guinea-Bissau", "GR", "Griechenland", "GRO", "Grönland", "GUF",
			"Französisch Guyana (French Guyana)", "GUY",
			"Guyana (früher: BRG British Guayana)", "H", "Ungarn", "HK",
			"Hongkong", "HN", "Honduras", "HR", "Kroatien", "I", "Italien",
			"IL", "Israel", "IND", "Indien", "IR", "Iran", "IRL", "Irland",
			"IRQ", "Irak", "IS", "Island", "J", "Japan", "JA",
			"Jamaika (früher auch: Britische Antillen)", "JOR", "Jordanien",
			"K", "Kambodscha / Kamputschea", "KS", "Kirgisistan (auch: KGZ)",
			"KSA", "Kingdom of Saudi-Arabia (früher: SA)", "KWT", "Kuwait",
			"KZ", "Kasachstan (auch: KAZ)", "L", "Luxemburg", "LAO", "Laos",
			"LAR", "Libysch-Arabische Dschamahirija", "LS",
			"Lesotho (früher: BL Basutoland)", "LT", "Litauen", "LV",
			"Lettland", "M", "Malta (früher: GBY)", "MA", "Marokko", "MAL",
			"Malaysia (auch PTM)", "MC", "Monaco", "MD", "Moldawien", "MEX",
			"Mexiko", "MGL", "Mongolei (auch: MNG)", "MH", "Marshall-Inseln",
			"MK", "Mazedonien", "MOC", "Mosambikl (Mozambique)", "MS",
			"Mauritius", "MV", "Malediven", "MW",
			"Malawi (früher: NP Nyassaland)", "MYA",
			"Myanmar (früher: BUR Burma / Birma)", "N", "Norwegen", "NA",
			"Niederländische Antillen", "NAM",
			"Namibia (früher: SWA Südwestafrika)", "NAU", "Nauru", "NCL",
			"Neukaledonien (New Caledonia)", "NEP", "Nepal", "NF",
			"Neufundland", "NGN", "Neuguinea", "NIC", "Nicaragua", "NIG",
			"Niger", "NL", "Niederlande", "NZ", "Neuseeland", "OM", "Oman",
			"P", "Portugal", "PA", "Panama", "PE", "Peru", "PK",
			"Pakistan (auch PAK)", "PL", "Polen", "PNG", "Papua-Neuguinea",
			"PY", "Paraguay", "Q", "Katar (Qatar)", "R", "Rumänien", "RA",
			"Argentinien", "RB", "Botswana (früher: BP Betschuanaland)", "RC",
			"China / Taiwan", "RCA", "Zentralafrikanische Republik", "RCB",
			"Kongo (Brazzaville)", "RCH", "Chile", "RG", "Republik Guinea",
			"RH", "Haiti", "RI", "Indonesien", "RIM", "Mauretanien", "RL",
			"Libanon", "RM", "Madagaskar", "RMM", "Mali", "RN", "Niger", "RO",
			"Rumänien", "ROK", "Korea", "ROU", "Uruguay (früher: U)", "RP",
			"Philippinen (früher: PI)", "RPB", "Benin (früher: DY Dahome)",
			"RT", "Togo (auch: TG)", "RSM", "San Marino", "RU", "Urundi",
			"RUS", "Russische Föderation (auch: SU)", "RWA", "Ruanda", "S",
			"Schweden", "SD", "Swaziland", "SGP", "Singapur", "SK",
			"Slowakische Republik", "SLO", "Slowenien", "SME", "Surinam",
			"SMOM", "Malteserorden, Rom (Souvereign Military Order of Malta)",
			"SN", "Senegal", "SP", "Somalia", "SY", "Seychellen", "SYR",
			"Syrien", "TC", "Kamerun", "TCD", "Tschad", "THA",
			"Thailand (auch: T)", "TJ", "Tadschikistan (auch: TAD)", "TM",
			"Turkmenistan (auch: TK)", "TN", "Tunesien", "TR", "Türkei", "TT",
			"Trinidad und Tobago", "UA", "Ukraine", "UAE",
			"Vereinigte Arabische Emirate", "USA",
			"Vereinigte Staaten von Amerika", "UZB", "Usbekistan", "V",
			"Vatikan (auch: SCV Stato della Citta del Vaticano)", "VN",
			"Vietnam", "VU", "Vanuatu", "WAG", "Gambia", "WAL", "Sierra Leone",
			"WAN", "Nigeria", "WD", "Dominica (Windward-Insel)", "WG",
			"Grenada", "WL", "Santa Lucia (Windward-Insel)", "WS", "Samoa",
			"WV", "St. Vincent (Windward-Insel)", "YU",
			"Föderative Republik Jugoslawien", "YV", "Venezuela", "Z",
			"Sambia", "ZA", "Südafrikanische Union", "ZW",
			"Simbabwe (Zimbabwe) (früher: RSR/SR Süd-Rhodesien)" };
		private boolean exect, gmaps, land;
		SharedPreferences prefs;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* First Tab Content */

		setContentView(R.layout.main);
		prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		D = prefs.getBoolean("D", true);
		AT = prefs.getBoolean("AT", true);
		CH = prefs.getBoolean("CH", true);
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
		eKFZ.setInputType(InputType.TYPE_TEXT_VARIATION_FILTER);
		eKFZ.setText(prefs.getString("Ledit", ""));
		eKFZ.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				// table.removeAllViews();
				/*
				 * tOrte_D.setText(findOrte("D"));
				 * tOrte_AT.setText(findOrte("AT"));
				 * tOrte_CH.setText(findOrte("CH"));
				 */

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
					eKFZ.setText(tmp);
					eKFZ.setSelection(eKFZ.getText().toString().length());
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("Ledit", tmp);
					editor.commit();
				}

			}

		});
		search();
	}
	private void search(){
		
		 tl.removeAllViews();
		 
		 findOLand(true);
		 findOLand(false);
		 return;
		
		
	}
	
	
	private String findOLand(boolean exect) {
		//wenn lehrer suchbegriff
		if(this.eKFZ.getText().toString().length()==0){
			 tl.removeAllViews();
			TableRow tr = new TableRow(this); 
			 tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
			 ImageView iv1 = new ImageView(this);
             iv1.setBackgroundResource(android.R.drawable.ic_menu_search);
             tr.addView(iv1);
				TextView tw=new TextView(this);
				tw.setText( getString(R.string.leer));
				tw.setTextSize(20);
				 tr.addView(tw);
				 tl.addView(tr,new TableLayout.LayoutParams(
		                    LayoutParams.FILL_PARENT,
		                    LayoutParams.WRAP_CONTENT));
			return "please enter";
		}
		//haptfunktion
		int difLength=this.eKFZ.getText().toString().length();
		String out="";	
		for (int i = 0; i < aLand.length/2; i++) {
			
			if((aLand[i*2].length()==difLength&& 0==aLand[i*2].substring(0,difLength ).compareToIgnoreCase(this.eKFZ.getText().toString())&&exect)||
					aLand[i*2].length()>difLength&& 0==aLand[i*2].substring(0,difLength ).compareToIgnoreCase(this.eKFZ.getText().toString())&&exect==false){
				TableRow tr1 = new TableRow(this); 
				 tr1.setLayoutParams(new LayoutParams(
                         LayoutParams.FILL_PARENT,
                         LayoutParams.WRAP_CONTENT));
				 //create views
			
				TextView tw=new TextView(this);
				 TextView tw2=new TextView(this);
				 
				 /*image*/
               
				
			
				if(exect){
					tw.setTextSize(28);
					tw2.setTextSize(28);
					 SpannableString str = SpannableString.valueOf(" "+aLand[i*2+1]); 
					 str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 
					 str.length(), 0); 
						tw2.setText(str);
						str = SpannableString.valueOf(" "+aLand[i*2]); 
						 str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 
						 str.length(), 0); 
						tw.setText(str);
				}else{
					tw.setTextSize(20);
					tw2.setTextSize(20);
					tw.setText(aLand[i*2]);
					tw2.setText(aLand[i*2+1]);
				}
				tw.setTextColor(Color.BLACK);
				tw2.setTextColor(Color.BLACK);
				tw2.setTag(aLand[i*2+1]);
				tw.setTag(aLand[i*2+1]);
				
				 View.OnClickListener eventHandler = new View.OnClickListener() { 
					
					 public int i;
					 public void seti(int i){
						 this.i=i;
					 }
					public void onClick(View v) {
						// TODO Auto-generated method stu 
						
					 Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
							 Uri.parse("geo:0,0?q="+(String) v.getTag()));
							 startActivity(intent);
							//openContextMenu(v);
				 }};
				
				 if(gmaps){
					 tw2.setOnClickListener(eventHandler);
					 tw.setOnClickListener(eventHandler);
					}
				 registerForContextMenu(tw);
				 registerForContextMenu(tw2); 
				//tr1.addView(iv1);
				tr1.addView(tw);
				tr1.addView(tw2);
                out+=aLand[i*2]+" "+aLand[i*2+1]+"\n";
				tl.addView(tr1,new TableLayout.LayoutParams(
	                    LayoutParams.FILL_PARENT,
	                    LayoutParams.WRAP_CONTENT));
				tl.setColumnShrinkable(1, true);
				TableRow trtrenner = new TableRow(this); 
				trtrenner.setGravity(Gravity.CENTER_VERTICAL);
				 trtrenner.setLayoutParams(new LayoutParams(
                         LayoutParams.FILL_PARENT,
                         LayoutParams.WRAP_CONTENT));
				 View line =new View(this);
				 line.setBackgroundColor(Color.LTGRAY);
				 line.setMinimumHeight(2);
				 trtrenner.addView(line);
				 View line2 =new View(this);
				 line2.setBackgroundColor(Color.LTGRAY);
				 line2.setMinimumHeight(2);
				 trtrenner.addView(line2);

				 tl.addView(trtrenner,new TableLayout.LayoutParams(
		                    LayoutParams.FILL_PARENT,
		                    LayoutParams.WRAP_CONTENT));
			}else{
				//out=aLand[i*3]+" "+aLand[i*2]+" "+aLand[i*3+2]+"\n";
			}
			
		}
		 out+="\n";
		 

		return out.substring(0,out.length()-1);
	}

	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		    super.onCreateContextMenu(menu, v, menuInfo);  
		        menu.setHeaderTitle("Wähle");  
		        menu.add(0, 1, 0, "Maps öffnen");  
		        menu.add(0, 2, 0, "Landkreis googlen");  
		    }  
	 public boolean onCreateOptionsMenu(Menu menu){

    	
    	menu.add(0,2,0,"Einstellungen");
    	menu.add(0,1,0,"Über");
    	menu.add(0,3,0,"Fehler melden");
    	menu.add(0,-1,0,"Exit");

    	return true;

    	}
	 public boolean onContextItemSelected(MenuItem item) {  
		 switch (item.getItemId()){
	    	case 2:
	    		
	    	return true;
	    		
	    	case 1:
	    		 
	    	

	    	return true;
	    	
	    	}  
	return false;  
	}  
    public boolean onOptionsItemSelected (MenuItem item){

    	switch (item.getItemId()){
    	case 3:
    		 Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:android@floatec.de?subject=Android app:"+getString(R.string.app_name)+" V."+getString(R.string.app_version)+"&body=Fehlendes Kennzeichen:\nKurzzeichen:\nAusgeschrieben:\nBundesland:\nWenn veraltet neues kennzeichen:\nDeutschland[ ]Österreich[ ]Schweitz[ ]Italien[ ]\n\nSonstige Fehler oder Verbesserungsvorschläge:"));
      		 startActivity(browser);
    	return true;
    	case 2:
    		Intent settingsActivity = new Intent(getBaseContext(), preferences.class);
    		startActivity(settingsActivity);
    	return true;
    	case 1:
    		Intent intent_menu_ueber = new Intent(this,
					UeberSeiteAnzeigen.class);
			startActivity(intent_menu_ueber);
			return true;
    	
    	case -1:
    		this.finish(); 
    	

    	return true;
    	
    	}

    	

    	return false;

    	}
    public void openContextMenu(View v){
    	
    	 
    }
	
}