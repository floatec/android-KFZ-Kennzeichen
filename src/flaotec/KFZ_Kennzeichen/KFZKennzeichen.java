package flaotec.KFZ_Kennzeichen;

import java.util.prefs.Preferences;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Bundle;
import android.preference.PreferenceManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;


public class KFZKennzeichen extends Activity {
	boolean D,AT,CH;
	private EditText eKFZ;
	private ImageView imgD,imgAT,imgCH;
	private TextView tOrte_D,tOrte_AT,tOrte_CH;
	private TableLayout table ;
	private String[]  aSuch  = {
			"D","A","Augsburg",
			"D","AA","Aalen Ostalbkreis",
			"D","AB","Aschaffenburg",
			"D","ABG","Altenburg",
			"D","ABI","Landkreis Anhalt-Bitterfeld",
			"D","AC","Aachen",
			"D","AD","Privatfahrzeuge der amerikanischen Streitkräfte",
			"D","AF","Privatfahrzeuge der amerikanischen Streitkräfte",
			"D","AIC","Aichach-Friedberg",
			"D","AK","Altenkirchen/Westerwald",
			"D","AM","Amberg",
			"D","AN","Ansbach",
			"D","ANA","Annaberg",
			"D","AÖ","Altötting",
			"D","AP","Weimarer-Land",
			"D","AS","Amberg-Sulzbach",
			"D","ASL","Aschersleben-Staßfurter Landkreis",
			"D","ASZ","Aue-Schwarzenberg",
			"D","AUR","Aurich",
			"D","AW","Ahrweiler",
			"D","AZ","Alzey-Worms",
			"D","AZE","Anhalt-Zerbst",
			"D","B","Berlin",
			"D","BA","Bamberg",
			"D","BAD","Baden-Baden",
			"D","BAR","Barnim",
			"D","BB","Böblingen",
			"D","BBG","Bernburg",
			"D","BC","Biberach/Riss",
			"D","BGL","Berchtesgadener Land",
			"D","BI","Bielefeld",
			"D","BIR","Birkenfeld/Nahe und Idar-Oberstein",
			"D","BIT","Bitburg-Prüm",
			"D","BK","Bördekreis",
			"D","BL","Zollernalbkreis in Balingen",
			"D","BLK","Burgenlandkreis",
			"D","BM","Erftkreis in Bergheim",
			"D","BN","Bonn",
			"D","BO","Bochum",
			"D","BÖ","Bördekreis",
			"D","BOR","Borken in Ahaus",
			"D","BOT","Bottrop",
			"D","BRA","Wesermarsch in Brake",
			"D","BRB","Brandenburg",
			"D","BS","Braunschweig",
			"D","BT","Bayreuth",
			"D","BTF","Bitterfeld",
			"D","BÜ","Büsingen am Hochrhein",
			"D","BW","Bundes-Wasser- und Schiffahrtsverwaltung",
			"D","BWL","Baden-Württemberg Landesregierung und Landtag",
			"D","BYL","Bayern Landesregierung und Landtag",
			"D","BZ","Bautzen",
			"D","C","Chemnitz",
			"D","CB","Cottbus",
			"D","CE","Celle",
			"D","CHA","Cham/Oberpfalz",
			"D","CLP","Cloppenburg",
			"D","CO","Coburg",
			"D","COC","Cochem-Zell/Mosel",
			"D","COE","Coesfeld/Westfalen",
			"D","CUX","Cuxhaven",
			"D","CW","Calw",
			"D","D","Düsseldorf",
			"D","DA","Darmstadt",
			"D","DAH","Dachau",
			"D","DAN","Lüchow-Dannenberg",
			"D","DAU","Daun",
			"D","DB","Deutsche Bundesbahn",
			"D","DBR","Bad Doberan",
			"D","DD","Dresden",
			"D","DE","Dessau",
			"D","DEG","Deggendorf",
			"D","DEL","Delmenhorst",
			"D","DGF","Dingolfing-Landau",
			"D","DH","Diepholz",
			"D","DL","Döbeln",
			"D","DLG","Dillingen/Donau",
			"D","DM","Demmin",
			"D","DN","Düren",
			"D","DO","Dortmund",
			"D","DON","Donau-Ries in Donauwörth",
			"D","DU","Duisburg",
			"D","DÜW","Bad Dürkheim",
			"D","DW","Dippoldiswalde",
			"D","DZ","Delitzsch",
			"D","E","Essen",
			"D","EA","Eisenach, Stadt",
			"D","EBE","Ebersberg",
			"D","ED","Erding",
			"D","EE","Elbe-Elster",
			"D","EF","Erfurt",
			"D","EH","Eisenhüttenstadt",
			"D","EI","Eichstätt",
			"D","EIC","Eichsfeld",
			"D","EL","Emsland in Meppen",
			"D","EM","Emmendingen",
			"D","EMD","Emden",
			"D","EMS","Rhein-Lahn-Kreis in Bad Ems",
			"D","EMS","Lahnstein a. Rh.",
			"D","EN","Ennepe-Ruhr-Kreis in Schwelm",
			"D","ER","Erlangen/Stadt",
			"D","ERB","Odenwaldkreis in Erbach",
			"D","ERH","Erlangen-Höchstadt",
			"D","ES","Esslingen/Neckar",
			"D","ESW","Werra-Meissner-Kreis in Eschwege",
			"D","EU","Euskirchen",
			"D","F","Frankfurt/Main",
			"D","FB","Wetteraukreis in Friedberg",
			"D","FD","Fulda",
			"D","FDS","Freudenstadt",
			"D","FF","Frankfurt/Oder",
			"D","FFB","Fürstenfeldbruck",
			"D","FG","Freiberg/Sachsen",
			"D","FL","Flensburg",
			"D","FN","Bodenseekreis in Friedrichshafen",
			"D","FO","Forchheim",
			"D","FR","Freiburg/Breisgau",
			"D","FRG","Freyung-Grafenau",
			"D","FRI","Friesland in Jever",
			"D","FS","Freising",
			"D","FT","Frankenthal/Pfalz",
			"D","FÜ","Fürth",
			"D","G","Gera",
			"D","GAP","Garmisch-Partenkirchen",
			"D","GC","Glauchau",
			"D","GE","Gelsenkirchen",
			"D","GER","Germersheim",
			"D","GF","Gifhorn",
			"D","GG","Gross-Gerau",
			"D","GI","Giessen",
			"D","GL","Rheinisch-Bergischer Kreis in Bergisch Gladbach",
			"D","GM","Oberbergischer Kreis in Gummersbach",
			"D","GÖ","Göttingen",
			"D","GP","Göppingen",
			"D","GR","Görlitz",
			"D","GRZ","Greiz",
			"D","GS","Goslar",
			"D","GT","Gütersloh in Rheda-Wiedenbrück",
			"D","GTH","Gotha",
			"D","GÜ","Güstrow",
			"D","GZ","Günzburg",
			"D","H","Hannover",
			"D","HA","Hagen/Westfalen",
			"D","HAL","Halle/Saale",
			"D","HAM","Hamm/Westfalen",
			"D","HAS","Hassberge in Hassfurt",
			"D","HB","Hansestadt Bremen und Bremerhaven",
			"D","HBN","Hildburghausen",
			"D","HBS","Halberstadt",
			"D","HD","Rhein-Neckar-Kreis und Heidelberg",
			"D","HDH","Heidenheim/Brenz",
			"D","HE","Helmstedt",
			"D","HEF","Bad Hersfeld-Rotenburg",
			"D","HEI","Heide in Dithmarschen/Holstein",
			"D","HER","Herne",
			"D","HF","Herford in Kirchlengern",
			"D","HG","Hochtaunuskreis in Bad Homburg v.d.H.",
			"D","HGW","Hansestadt Greifswald",
			"D","HH","Hansestadt Hamburg",
			"D","HI","Hildesheim",
			"D","HK","Privatfahrzeuge der amerikanischen Streitkräfte",
			"D","HL","Hansestadt Lübeck",
			"D","HM","Hameln-Pyrmont",
			"D","HN","Heilbronn/Neckar",
			"D","HO","Hof",
			"D","HOL","Holzminden",
			"D","HOM","Saar-Pfalz-Kreis in Homburg/Saar",
			"D","HP","Bergstrasse in Heppenheim",
			"D","HR","Schwalm-Eder-Kreis in Homberg",
			"D","HRO","Hansestadt Rostock",
			"D","HS","Heinsberg",
			"D","HSK","Hochsauerlandkreis in Meschede",
			"D","HST","Hansestadt Stralsund",
			"D","HU","Hanau",
			"D","HVL","Havelland",
			"D","HWI","Hansestadt Wismar",
			"D","HX","Höxter",
			"D","HY","Hoyerswerda",
			"D","HZ","Landkreis Harz",
			"D","IGB","St. Ingbert",
			"D","IN","Ingolstadt/Donau",
			"D","IZ","Itzehoe",
			"D","J","Jena",
			"D","JL","Jerichower Land",
			"D","K","Köln",
			"D","KA","Karlsruhe",
			"D","KB","Waldeck-Frankenberg in Korbach",
			"D","KC","Kronach",
			"D","KE","Kempten/Allgäu",
			"D","KEH","Kelheim",
			"D","KF","Kaufbeuren",
			"D","KG","Bad Kissingen",
			"D","KH","Bad Kreuznach",
			"D","KI","Kiel",
			"D","KIB","Donnersberg-Kreis in Kirchheimbolanden",
			"D","KL","Kaiserslautern",
			"D","KLE","Kleve",
			"D","KM","Kamenz",
			"D","KN","Konstanz",
			"D","KO","Koblenz",
			"D","KÖT","Köthen",
			"D","KR","Krefeld",
			"D","KS","Kassel",
			"D","KT","Kitzingen",
			"D","KU","Kulmbach",
			"D","KÜN","Hohenlohe-Kreis in Künzelsau",
			"D","KUS","Kusel",
			"D","KYF","Kyffhäuserkreis",
			"D","L-","Leipzig",
			"D","LA","Landshut",
			"D","LAU","Nürnberger Land in Lauf/Pegnitz",
			"D","LB","Ludwigsburg",
			"D","LD","Landau i. d. Pfalz",
			"D","LDK","Lahn-Dill-Kreis",
			"D","LDS","Dahme-Spreewald",
			"D","LER","Leer/Ostfriesland",
			"D","LEV","Leverkusen",
			"D","LG","Lüneburg",
			"D","LI","Lindau/Bodensee",
			"D","LIF","Lichtenfels",
			"D","LIP","Lippe in Detmold",
			"D","LL","Landsberg/Lech",
			"D","LM","Limburg-Weilburg/Lahn",
			"D","LÖ","Lörrach",
			"D","LOS","Oder-Spree",
			"D","LU","Ludwigshafen/Rhein",
			"D","LWL","Ludwigslust",
			"D","M","München",
			"D","MA","Mannheim",
			"D","MB","Miesbach",
			"D","MD","Magdeburg",
			"D","ME","Mettmann",
			"D","MEI","Meissen",
			"D","MEK","Mittl. Erzgebirgskreis",
			"D","MG","Mönchengladbach",
			"D","MH","Mülheim/Ruhr",
			"D","MI","Minden-Lübbecke/Westfalen",
			"D","MIL","Miltenberg",
			"D","MKK","Main-Kinzig-Kreis",
			"D","MK","Märkischer Kreis in Lüdenscheid",
			"D","MM","Memmingen",
			"D","MN","Unterallgäu in Mindelheim",
			"D","MOL","Märkisch-Oderland",
			"D","MOS","Neckar-Odenwald-Kreis in Mosbach",
			"D","MQ","Merseburg-Querfurt",
			"D","MR","Marburg-Biedenkopf/Lahn",
			"D","MS","Münster/Westfalen",
			"D","MSH","Mansfeld-Südharz-Kreis",
			"D","MSP","Main-Spessart-Kreis in Karlstadt",
			"D","MST","Mecklenburg-Strelitz",
			"D","MTK","Main-Taunus-Kreis in Hofheim",
			"D","MTL","Muldentalkreis",
			"D","MÜ","Mühldorf am Inn",
			"D","MÜR","Müritz",
			"D","MW","Mittweida",
			"D","MYK","Mayen-Koblenz",
			"D","MZ","Mainz-Bingen und Mainz",
			"D","MZG","Merzig-Wadern",
			"D","N","Nürnberg",
			"D","NB","Neubrandenburg",
			"D","ND","Neuburg-Schrobenhausen/Donau",
			"D","NDH","Nordhausen",
			"D","NE","Neuss",
			"D","NEA","Neustadt-Bad Windsheim/Aisch",
			"D","NES","Rhön-Grabfeld in Bad Neustadt/Saale",
			"D","NEW","Neustadt/Waldnaab",
			"D","NF","Nordfriesland in Husum",
			"D","NI","Nienburg/Weser",
			"D","NK","Neunkirchen/Saar",
			"D","NM","Neumarkt/Oberpfalz",
			"D","NMS","Neumünster",
			"D","NOH","Grafschaft Bentheim in Nordhorn",
			"D","NOL","Niederschlesische Oberlausitz",
			"D","NOM","Northeim",
			"D","NR","Neuwied/Rhein",
			"D","NU","Neu-Ulm",
			"D","NVP","Nordvorpommern",
			"D","NW","Neustadt/Weinstrasse",
			"D","NWM","Nordwestmecklenburg",
			"D","OA","Oberallgäu in Sonthofen",
			"D","OAL","Ostallgäu in Marktoberdorf",
			"D","OB","Oberhausen/Rheinland",
			"D","OD","Stormarn in Bad Oldesloe",
			"D","OE","Olpe",
			"D","OF","Offenbach/Main",
			"D","OG","Ortenaukreis in Offenburg",
			"D","OH","Ostholstein in Eutin",
			"D","OHA","Osterode/Harz",
			"D","OHV","Oberhavel",
			"D","OHZ","Osterholz-Scharmbeck",
			"D","OK","Ohrekreis",
			"D","OL","Oldenburg",
			"D","OPR","Ostprignitz-Ruppin",
			"D","OS","Osnabrück",
			"D","OSL","Oberspreewald-Lausitz",
			"D","OVP","Ostvorpommern",
			"D","P","Potsdam",
			"D","PA","Passau",
			"D","PAF","Pfaffenhofen/Ilm",
			"D","PAN","Rottal-Inn in Pfarrkirchen",
			"D","PB","Paderborn",
			"D","PCH","Parchim",
			"D","PE","Peine",
			"D","PF","Enzkreis und Pforzheim",
			"D","PI","Pinneberg",
			"D","PIR","Pirna",
			"D","PL","Plauen",
			"D","PLÖ","Plön/Holstein",
			"D","PM","Potsdam-Mittelmark",
			"D","PR","Prignitz",
			"D","PS","Pirmasens/Südwestpfals",
			"D","QLB","Quedlinburg",
			"D","R","Regensburg",
			"D","RA","Rastatt",
			"D","RD","Rendsburg-Eckernfoerde",
			"D","RE","Recklinghausen in Marl",
			"D","REG","Regen(Bayr. Wald)",
			"D","RG","Riesa-Großenhain",
			"D","RH","Roth/Rednitz",
			"D","RO","Rosenheim",
			"D","ROW","Rotenburg/Wümme",
			"D","RP","Rhein-Pfalz-Kreis",
			"D","RS","Remscheid",
			"D","RT","Reutlingen",
			"D","RÜD","Rheingau-Taunus-Kreis in Rüdesheim",
			"D","RÜG","Rügen",
			"D","RV","Ravensburg",
			"D","RW","Rottweil",
			"D","RZ","Herzogtum Lauenburg in Ratzeburg",
			"D","S","Stuttgart",
			"D","SAD","Schwandorf",
			"D","SAW","Salzwedel",
			"D","SB","Saarbrücken",
			"D","SBK","Schönebeck/Elbe",
			"D","SC","Schwabach",
			"D","SDL","Stendal",
			"D","SE","Bad Segeberg",
			"D","SFA","Soltau-Fallingbostel",
			"D","SG","Solingen",
			"D","SGH","Sangerhausen",
			"D","SHA","Schwäbisch Hall",
			"D","SHG","Schaumburg in Stadthagen",
			"D","SHK","Saale-Holzlandkreis",
			"D","SHL","Suhl",
			"D","SI","Siegen",
			"D","SIG","Sigmaringen",
			"D","SIM","Rhein-Hunsrück-Kreis in Simmern",
			"D","SK","Saalkreis in Halle",
			"D","SL","Schleswig-Flensburg",
			"D","SLF","Saalfeld",
			"D","SLK","Schönebeck/Elbe",
			"D","SLS","Saarlouis",
			"D","SM","Schmalkalden",
			"D","SN","Schwerin",
			"D","SO","Soest",
			"D","SÖM","Sömmerda",
			"D","SOK","Saale-Orla-Kreis",
			"D","SON","Sonneberg",
			"D","SP","Speyer",
			"D","SPN","Spree-Neisse",
			"D","SR","Straubing-Bogen",
			"D","ST","Steinfurt",
			"D","STA","Starnberg",
			"D","STD","Stade",
			"D","STL","Stollberg",
			"D","SU","Rhein-Sieg-Kreis in Siegburg",
			"D","SÜW","Südl. Weinstrasse in Landau",
			"D","SW","Schweinfurt",
			"D","SZ","Salzgitter",
			"D","TBB","Main-Tauber-Kreis in Tauberbischofsheim",
			"D","TDO","Torgau Delitzsch - Oschatz",
			"D","TET","Teterow",
			"D","TF","Teltow-Fläming",
			"D","TIR","Tirschenreuth",
			"D","TO","Torgau-Oschatz",
			"D","TÖL","Bad Tölz-Wolfratshausen",
			"D","TR","Trier-Saarburg",
			"D","TS","Traunstein",
			"D","TÜ","Tübingen",
			"D","TUT","Tuttlingen",
			"D","UE","Uelzen",
			"D","UER","Uecker-Randow",
			"D","UH","Unstrut-Hainich-Kreis",
			"D","UL","Alb-Donau-Kreis und Ulm",
			"D","UM","Uckermark",
			"D","UN","Unna/Westfalen",
			"D","V-","Vogtlandkreis",
			"D","VB","Vogelsbergkreis in Lauterbach",
			"D","VEC","Vechta",
			"D","VER","Verden/Aller",
			"D","VIE","Viersen",
			"D","VK","Völklingen",
			"D","VS","Schwarzwald-Baar-Kreis in Villingen-Schwenningen",
			"D","W","Wuppertal",
			"D","WAF","Warendorf",
			"D","WAK","Wartburgkreis",
			"D","WB","Wittenberg",
			"D","WE","Weimar",
			"D","WEN","Weiden/Oberpfalz",
			"D","WES","Wesel in Moers",
			"D","WF","Wolfenbüttel",
			"D","WHV","Wilhelmshaven",
			"D","WI","Wiesbaden",
			"D","WIL","Bernkastel-Wittlich/Mosel",
			"D","WL","Harburg in Winsen/Luhe",
			"D","WM","Weilheim-Schongau/Oberbayern",
			"D","WN","Rems-Murr-Kreis in Waiblingen",
			"D","WND","St. Wendel",
			"D","WO","Worms",
			"D","WOB","Wolfsburg",
			"D","WR","Wernigerode",
			"D","WSF","Weissenfels",
			"D","WST","Ammerland in Westerstede",
			"D","WT","Waldshut-Tiengen",
			"D","WTM","Wittmund",
			"D","WÜ","Würzburg",
			"D","WUG","Weissenburg-Gunzenhausen",
			"D","WUN","Wunsiedel",
			"D","WW","Westerwald in Montabaur",
			"D","X","Bundeswehr für NATO-Hauptquartiere",
			"D","Y","Bundeswehr",
			"D","Z","Zwickau",
			"D","ZI","Zittau",
			"D","ZW","Zweibrücken",
		"AT","AM","Amstetten",
		"AT","B","Bregenz",
		"AT","BA","Bad Aussee",
		"AT","BL","Bruckl Leitha",
		"AT","BM","Brucki Mur",
		"AT","BN","Baden",
		"AT","BR","Branau",
		"AT","BZ","Bludenz",
		"AT","DL","Deutschlandsberg",
		"AT","DO","Dornbirn",
		"AT","E","Eisenstadt",
		"AT","EF","Eferding",
		"AT","EU","Eisenstadt-Umgebung",
		"AT","FB","Feldbach",
		"AT","FE","Feldkirchen",
		"AT","FF","F?rstenfeld",
		"AT","FK","Feldkirch",
		"AT","FR","Freistadt",
		"AT","G","Graz",
		"AT","GB","Gr?bming",
		"AT","GD","Gm?nd",
		"AT","GF","G?nserndorf",
		"AT","GM","Gmunden",
		"AT","GR","Grieskirchen",
		"AT","GS","G?ssing",
		"AT","GU","Graz-Umgebung",
		"AT","HA","Hallein",
		"AT","HB","Hartberg",
		"AT","HE","Hermagor",
		"AT","HL","Hollabrunn",
		"AT","HO","Horn",
		"AT","I","Innsbruck Stadt",
		"AT","IL","Innsbruck Land",
		"AT","IM","Imst",
		"AT","JE","Jennersdorf",
		"AT","JO","St. Johann",
		"AT","JU","Judenburg",
		"AT","K","Klagenfurt",
		"AT","KB","Kitzb?hel",
		"AT","KF","Knittelfeld",
		"AT","KI","Kirchdorf Krems",
		"AT","KL","Klagenfurt Land",
		"AT","KO","Korneuburg",
		"AT","KR","Krems (Bezirk)",
		"AT","KS","Krems (Stadt)",
		"AT","KU","Kufstein",
		"AT","L","Linz",
		"AT","LA","Landbeck",
		"AT","LB","Leibnitz",
		"AT","LE","Leoben Stadt",
		"AT","LF","Lilienfeld",
		"AT","LI","Liezen",
		"AT","LL","Linz Land",
		"AT","LN","Leoben Bezirk",
		"AT","LZ","Lienz",
		"AT","MA","Mattersburg",
		"AT","MD","M?dling",
		"AT","ME","Melk",
		"AT","MI","Mistelbach",
		"AT","MU","Murau",
		"AT","MZ","M?rzzuschlag",
		"AT","ND","Neusiedl am See",
		"AT","NK","Neunkirchen",
		"AT","OP","Oberpulleorf",
		"AT","OW","Oberwart",
		"AT","P","St. Pölten Stadt",
		"AT","PE","Perg",
		"AT","PL","St. Pölten",
		"AT","RA","Radkersburg",
		"AT","RE","Reutte",
		"AT","RI","Ried am Innkreis",
		"AT","RO","Rohrbach",
		"AT","S","Salzburg Stadt",
		"AT","SB","Scheibbs",
		"AT","SD","Sch?rding",
		"AT","SE","Steyr Land",
		"AT","SL","Salzburg Land",
		"AT","SP","Spittal / Drau",
		"AT","SR","Steyr Stadt",
		"AT","SV","St. Veit / Glan",
		"AT","SW","Schwechat",
		"AT","SZ","Schwaz",
		"AT","TA","Tamsweg",
		"AT","TU","Tulln",
		"AT","UU","Urfahr Umgebung",
		"AT","VB","V?cklabruck",
		"AT","VI","Villach Stadt",
		"AT","VK","Völkermarkt",
		"AT","VL","Villach Land",
		"AT","VO","Voitsberg",
		"AT","W","Wien Stadt",
		"AT","WB","Wiener Neustadt Land",
		"AT","WE","Wels Stadt",
		"AT","WL","Wels Land",
		"AT","WN","Wiener Neustadt Stadt",
		"AT","WO","Wolfsburg",
		"AT","WT","Waidhofen / Thaya",
		"AT","WU","Wien Umgebung",
		"AT","WY","Waidhofen / Ibbs",
		"AT","WZ","Weiz",
		"AT","ZE","Zell am See",
		"AT","ZT","Zwettl",
		"CH","AG","Kanton Aargau",
		"CH","AI","Kanton Appenzell Innerrhoden",
		"CH","AR","Kanton Appenzell Ausserrhoden",
		"CH","BE","Kanton Bern",
		"CH","BL","Kanton Basel-Landschaft",
		"CH","BS","Kanton Basel-Stadt",
		"CH","FR","Kanton Freiburg",
		"CH","GE","Kanton Genf",
		"CH","GL","Kanton Glarus",
		"CH","GR","Kanton Graubünden",
		"CH","JU","Kanton Jura",
		"CH","LU","Kanton Luzern",
		"CH","NE","Kanton Neuenburg",
		"CH","NW","Kanton Nidwalden",
		"CH","OW","Kanton Obwalden",
		"CH","SG","Kanton St. Gallen",
		"CH","SH","Kanton Schaffhausen",
		"CH","SO","Kanton Solothurn",
		"CH","SZ","Kanton Schwyz",
		"CH","TG","Kanton Thurgau",
		"CH","TI","Kanton Tessin",
		"CH","UR","Kanton Uri",
		"CH","VD","Kanton Waadt",
		"CH","VS","Kanton Wallis",
		"CH","ZG","Kanton Zug",
		"CH","ZH","Kanton Zürich"};   
	//private ListView lOrte ;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 table = (TableLayout) findViewById(R.id.TableLayout01);
		 TableLayout tl = (TableLayout)findViewById(R.id.TableLayout01);
         /* Create a new row to be added. */
         TableRow tr = new TableRow(this);
         tr.setLayoutParams(new LayoutParams(
                        LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT));
              /* Create a Button to be the row-content. */
              Button b = new Button(this);
              b.setText("Dynamic Button");
              b.setLayoutParams(new LayoutParams(
                        LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT));
              /* Add Button to row. */
              tr.addView(b);
    /* Add row to TableLayout. */
    tl.addView(tr,new TableLayout.LayoutParams(
              LayoutParams.FILL_PARENT,
              LayoutParams.WRAP_CONTENT));
		 super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 //Get the xml/preferences.xml preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		D = prefs.getBoolean("D",true);
		AT = prefs.getBoolean("AT",true);
		CH = prefs.getBoolean("CH",true);
		/*tOrte_D= (TextView) findViewById(R.id.Orte_D);
		tOrte_AT = (TextView) findViewById(R.id.Orte_AT);
		tOrte_CH = (TextView) findViewById(R.id.Orte_CH);
		imgD = (ImageView) findViewById(R.id.imgD);
		imgAT = (ImageView) findViewById(R.id.imgAT);
		imgCH = (ImageView) findViewById(R.id.imgCH);
		if(!D){
			imgD.setVisibility(imgD.INVISIBLE);
		}
		if(!AT){
			imgAT.setVisibility(imgAT.INVISIBLE);
		}
		if(!CH){
			imgCH.setVisibility(imgCH.INVISIBLE);
		}*/
		
		eKFZ = (EditText) findViewById(R.id.KFZ);
		eKFZ.addTextChangedListener(new TextWatcher() {
			 public void afterTextChanged(Editable s) {
				// table.removeAllViews();
				 /*tOrte_D.setText(findOrte("D"));
				 tOrte_AT.setText(findOrte("AT"));
				 tOrte_CH.setText(findOrte("CH"));
				*/
				 findOrte("D");
				 
	            }

	            // We don't use these methods
	            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
	            public void onTextChanged(CharSequence s, int start, int before, int count) { }

		});		
	}
	private String findOrte(String Land) {
		if(this.eKFZ.getText().toString().length()==0){
			return "Bitte Buchstaben eingeben";
		}
		int difLength=this.eKFZ.getText().toString().length();
		String out="";	
		for (int i = 0; i < aSuch.length/3; i++) {
			
			if(aSuch[i*3]==Land&&aSuch[i*3+1].length()==difLength&& 0==aSuch[i*3+1].substring(0,difLength ).compareToIgnoreCase(this.eKFZ.getText().toString())){
				TableRow tr = new TableRow(this); 
				 tr.setLayoutParams(new LayoutParams(
                         LayoutParams.FILL_PARENT,
                         LayoutParams.WRAP_CONTENT));

				ImageView iv1 = new ImageView(this);
                iv1.setId(500+i);
                iv1.setBackgroundResource(R.drawable.at);
                tr.addView(iv1);
				out+=aSuch[i*3+1]+" "+aSuch[i*3+2]+"\n";
				table.addView(tr,new TableLayout.LayoutParams(
	                    LayoutParams.FILL_PARENT,
	                    LayoutParams.WRAP_CONTENT));

			}else{
				//out=aSuch[i*3]+" "+aSuch[i*3+1]+" "+aSuch[i*3+2]+"\n";
			}
			
		}
		 out+="\n";
		for (int i = 0; i < aSuch.length/3; i++) {
			
			if(aSuch[i*3]==Land&&aSuch[i*3+1].length()>difLength&& 0==aSuch[i*3+1].substring(0,difLength ).compareToIgnoreCase(this.eKFZ.getText().toString())){
				out+=aSuch[i*3+1]+" "+aSuch[i*3+2]+"\n";
				
			}else{
				//out=aSuch[i*3]+" "+aSuch[i*3+1]+" "+aSuch[i*3+2]+"\n";
			}
			
		}
	
		return out.substring(0,out.length()-1);
	}
	 public boolean onCreateOptionsMenu(Menu menu){

    	
    	menu.add(0,2,0,"settings");
    	
    	menu.add(0,-1,0,"Exit");

    	return true;

    	}
    public boolean onOptionsItemSelected (MenuItem item){

    	switch (item.getItemId()){
    	case 2:
    		Intent settingsActivity = new Intent(getBaseContext(), Preferences.class);
    		startActivity(settingsActivity);
    	return true;
    		
    	case -1:
    		this.finish(); 
    	

    	return true;
    	
    	}

    	

    	return false;

    	}

}