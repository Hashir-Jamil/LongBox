package org.longbox.businesslogic.utils;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountryToContinentUtils {
	
	private static HashMap<String, String> regionsHashMap = new HashMap<String, String>();
	
	private static final String NA = "North_America";
	private static final String SA = "South_America";
	private static final String EU = "Europe";
	private static final String AS = "Asia";
	private static final String AF = "Africa";
	private static final String OC = "Oceania";
	private static final String AT = "Antarctica";
	
	static {
		regionsHashMap.put("Canada", NA);
		regionsHashMap.put("United States", NA);
		
		regionsHashMap.put("Andorra", AF);
		regionsHashMap.put("United Arab Emirates", AS);
		regionsHashMap.put("Afghanistan", AS);
		regionsHashMap.put("Antigua and Barbuda", SA);
		regionsHashMap.put("Anguilla", NA);
		regionsHashMap.put("Albania", EU);
		regionsHashMap.put("Armenia", AS);
		regionsHashMap.put("Angola", AF);
		regionsHashMap.put("Antarctica", AT);
		regionsHashMap.put("Argentina", SA);
		regionsHashMap.put("American Samoa", OC);
		regionsHashMap.put("Austria", EU);
		regionsHashMap.put("Australia", OC);
		regionsHashMap.put("Aruba", SA);
		regionsHashMap.put("Azerbaijan", AS);
		regionsHashMap.put("Bosnia and Herzegovina", EU);
		regionsHashMap.put("Barbados", NA);
		regionsHashMap.put("Bangladesh", AS);
		regionsHashMap.put("Belgium", EU);
		regionsHashMap.put("Burkina Faso", AF);
		regionsHashMap.put("Bulgaria", EU);
		regionsHashMap.put("Bahrain", AS);
		regionsHashMap.put("Burundi", AF);
		regionsHashMap.put("Benin", AF);
		regionsHashMap.put("Saint Barth", NA);
		regionsHashMap.put("Bermuda", NA);
		regionsHashMap.put("Brunei Darussalam", AS);
		regionsHashMap.put("Bolivia", SA);
		regionsHashMap.put("Bonaire", SA);
		regionsHashMap.put("Brazil", SA);
		regionsHashMap.put("Bahamas", NA);
		regionsHashMap.put("Bhutan", AS);
		regionsHashMap.put("Bouvet Island", AT);
		regionsHashMap.put("Botswana", AF);
		regionsHashMap.put("Belarus", EU);
		regionsHashMap.put("Belize", NA);
		regionsHashMap.put("Cocos", AS);
		regionsHashMap.put("Congo", AF);
		regionsHashMap.put("Central African Republic", AF);
		regionsHashMap.put("Congo", AF);
		regionsHashMap.put("Switzerland", EU);
		regionsHashMap.put("Cote D'Ivoire", AF);
		regionsHashMap.put("Ivory Coast", AF);
		regionsHashMap.put("Cook Islands", OC);
		regionsHashMap.put("Chile", SA);
		regionsHashMap.put("Cameroon", AF);
		regionsHashMap.put("China", AS);
		regionsHashMap.put("Colombia", SA);
		regionsHashMap.put("Costa Rica", NA);
		regionsHashMap.put("Serbia and Montenegro", EU);
		regionsHashMap.put("Cuba", NA);
		regionsHashMap.put("Cape Verde", AF);
		regionsHashMap.put("Cura", SA);
		regionsHashMap.put("Christmas Island", AS);
		regionsHashMap.put("Cyprus", EU);
		regionsHashMap.put("Czech Republic", EU);
		regionsHashMap.put("Germany", EU);
		regionsHashMap.put("Djibouti", AF);
		regionsHashMap.put("Denmark", EU);
		regionsHashMap.put("Dominica", NA);
		regionsHashMap.put("Dominican Republic", NA);
		regionsHashMap.put("Algeria", AF);
		regionsHashMap.put("Ecuador", EU);
		regionsHashMap.put("Estonia", EU);
		regionsHashMap.put("Egypt", AF);
		regionsHashMap.put("Western Sahara", AF);
		regionsHashMap.put("Eritrea", AF);
		regionsHashMap.put("Spain", EU);
		regionsHashMap.put("Ethiopia", AF);
		regionsHashMap.put("Finland", EU);
		regionsHashMap.put("Fiji", OC);
		regionsHashMap.put("Falkland Islands", SA);
		regionsHashMap.put("Micronesia", OC);
		regionsHashMap.put("Faeroe Islands", EU);
		regionsHashMap.put("France", EU);
		regionsHashMap.put("Gabon", AF);
		regionsHashMap.put("United Kingdom of Great Britain & N. Ireland", EU);
		regionsHashMap.put("United Kingdom", EU);
		regionsHashMap.put("Grenada", NA);
		regionsHashMap.put("Georgia", AS);
		regionsHashMap.put("French Guiana", SA);
		regionsHashMap.put("Guernsey", EU);
		regionsHashMap.put("Ghana", AF);
		regionsHashMap.put("Gibraltar", EU);
		regionsHashMap.put("Greenland", EU);
		regionsHashMap.put("Gambia", AF);
		regionsHashMap.put("Guinea", AF);
		regionsHashMap.put("Guadaloupe", NA);
		regionsHashMap.put("Equatorial Guinea", AF);
		regionsHashMap.put("Greece", EU);
		regionsHashMap.put("South Georgia and the South Sandwich Islands", SA);
		regionsHashMap.put("South Georgia", SA);
		regionsHashMap.put("South Sandwich Islands", SA);
		regionsHashMap.put("Sandwich Islands", SA);
		regionsHashMap.put("Guatemala", SA);
		regionsHashMap.put("Guam", OC);
		regionsHashMap.put("Guinea-Bissau", AF);
		regionsHashMap.put("Guinea Bissau", AF);
		regionsHashMap.put("Guyana", SA);
		regionsHashMap.put("Hong Kong", AS);
		regionsHashMap.put("Heard and McDonald Islands", OC);
		regionsHashMap.put("Honduras", SA);
		regionsHashMap.put("Hrvatska (Croatia)", SA);
		regionsHashMap.put("Hrvatska", SA);
		regionsHashMap.put("Croatia", SA);
		regionsHashMap.put("Haiti", NA);
		regionsHashMap.put("Hungary", EU);
		regionsHashMap.put("Indonesia", AS);
		regionsHashMap.put("Ireland", EU);
		regionsHashMap.put("Israel", AS);
		regionsHashMap.put("Isle of Man", EU);
		regionsHashMap.put("India", AS);
		regionsHashMap.put("British Indian Ocean Territory (Chagos Archipelago)", AS);
		regionsHashMap.put("British Indian Ocean Territory", AS);
		regionsHashMap.put("Chagos Archipelago", AS);
		regionsHashMap.put("Iraq", AS);
		regionsHashMap.put("Iran", AS);
		regionsHashMap.put("Iceland", EU);
		regionsHashMap.put("Italy", EU);
		regionsHashMap.put("Jersey", EU);
		regionsHashMap.put("Jamaica", NA);
		regionsHashMap.put("Jordan", AS);
		regionsHashMap.put("Japan", AS);
		regionsHashMap.put("Kenya", AF);
		regionsHashMap.put("Kyrgyz", AS);
		regionsHashMap.put("Kyrgyzstan", AS);
		regionsHashMap.put("Cambodia", AS);
		regionsHashMap.put("Kiribati", OC);
		regionsHashMap.put("Comoros", AF);
		regionsHashMap.put("St. Kitts and Nevis", NA);
		regionsHashMap.put("Korea", AS);
		regionsHashMap.put("North Korea", AS);
		regionsHashMap.put("South Korea", AS);
		regionsHashMap.put("Kuwait", AS);
		regionsHashMap.put("Cayman Islands", NA);
		regionsHashMap.put("Kazakhstan", AS);
		regionsHashMap.put("Laos", AS);
		regionsHashMap.put("Lao People's Democratic Republic", AS);
		regionsHashMap.put("Lebanon", AS);
		regionsHashMap.put("St. Lucia", NA);
		regionsHashMap.put("Liechtenstein", EU);
		regionsHashMap.put("Sri Lanka", AS);
		regionsHashMap.put("Liberia", AF);
		regionsHashMap.put("Lesotho", AS);
		regionsHashMap.put("Lithuania", EU);
		regionsHashMap.put("Luxembourg", EU);
		regionsHashMap.put("Latvia", EU);
		regionsHashMap.put("Libyan Arab Jamahiriya", AF);
		regionsHashMap.put("Libyan", AF);
		regionsHashMap.put("Morocco", AF);
		regionsHashMap.put("Monaco", EU);
		regionsHashMap.put("Moldova", EU);
		regionsHashMap.put("Montenegro", EU);
		regionsHashMap.put("Saint Martin", NA);
		regionsHashMap.put("Madagascar", AF);
		regionsHashMap.put("Marshall Islands", OC);
		regionsHashMap.put("Macedonia", EU);
		regionsHashMap.put("Mali", AF);
		regionsHashMap.put("Myanmar", AS);
		regionsHashMap.put("Mongolia", AS);
		regionsHashMap.put("Macao", AS);
		regionsHashMap.put("Macau", AS);
		regionsHashMap.put("Northern Mariana Islands", OC);
		regionsHashMap.put("Martinique", NA);
		regionsHashMap.put("Mauritania", AF);
		regionsHashMap.put("Montserrat", NA);
		regionsHashMap.put("Malta", EU);
		regionsHashMap.put("Mauritius", AF);
		regionsHashMap.put("Maldives", AS);
		regionsHashMap.put("Malawi", AF);
		regionsHashMap.put("Mexico", NA);
		regionsHashMap.put("Malaysia", AS);
		regionsHashMap.put("Mozambique", AF);
		regionsHashMap.put("Namibia", AF);
		regionsHashMap.put("New Caledonia", OC);
		regionsHashMap.put("Niger", AF);
		regionsHashMap.put("Norfolk Island", OC);
		regionsHashMap.put("Nigeria", AF);
		regionsHashMap.put("Nicaragua", SA);
		regionsHashMap.put("Netherlands", EU);
		regionsHashMap.put("Norway", EU);
		regionsHashMap.put("Nepal", AS);
		regionsHashMap.put("Nauru", OC);
		regionsHashMap.put("Niue", OC);
		regionsHashMap.put("New Zealand", OC);
		regionsHashMap.put("Oman", AS);
		regionsHashMap.put("Panama", NA);
		regionsHashMap.put("Peru", SA);
		regionsHashMap.put("French Polynesia", OC);
		regionsHashMap.put("Papua New Guinea", OC);
		regionsHashMap.put("Philippines", AS);
		regionsHashMap.put("Pakistan", AS);
		regionsHashMap.put("Poland", EU);
		regionsHashMap.put("St. Pierre and Miquelon", NA);
		regionsHashMap.put("Pitcairn Island", OC);
		regionsHashMap.put("Puerto Rico", NA);
		regionsHashMap.put("Palestinian Territory", AS);
		regionsHashMap.put("Palestine", AS);
		regionsHashMap.put("Portugal", EU);
		regionsHashMap.put("Palau", OC);
		regionsHashMap.put("Qatar", AS);
		regionsHashMap.put("Reunion", AF);
		regionsHashMap.put("Reunion Islands", AF);
		regionsHashMap.put("Romania", EU);
		regionsHashMap.put("Serbia", EU);
		regionsHashMap.put("Russian Federation", EU);
		regionsHashMap.put("Russia", EU);
		regionsHashMap.put("Rwanda", AF);
		regionsHashMap.put("Saudi Arabia", AS);
		regionsHashMap.put("Solomon Islands", OC);
		regionsHashMap.put("Seychelles", AF);
		regionsHashMap.put("Sudan", AF);
		regionsHashMap.put("Sweden", EU);
		regionsHashMap.put("Singapore", AS);
		regionsHashMap.put("St. Helena", AF);
		regionsHashMap.put("Slovenia", EU);
		regionsHashMap.put("Svalbard & Jan Mayen Islands", EU);
		regionsHashMap.put("Svalbard", EU);
		regionsHashMap.put("Jan Mayen Islands", EU);
		regionsHashMap.put("Slovakia", EU);
		regionsHashMap.put("Slovakia (Slovak Republic)", EU);
		regionsHashMap.put("Sierra Leone", AF);
		regionsHashMap.put("San Marino", EU);
		regionsHashMap.put("Senegal", AF);
		regionsHashMap.put("Somalia", AF);
		regionsHashMap.put("Suriname", SA);
		regionsHashMap.put("South Sudan", AF);
		regionsHashMap.put("Sao Tome and Principe", AF);
		regionsHashMap.put("Sao Tome", AF);
		regionsHashMap.put("Principe", AF);
		regionsHashMap.put("El Salvador", SA);
		regionsHashMap.put("Sint Maarten", AF);
		regionsHashMap.put("Sint Maarten (Dutch part)", AF);
		regionsHashMap.put("Syrian Arab Republic", AS);
		regionsHashMap.put("Syria", AS);
		regionsHashMap.put("Swaziland", AF);
		regionsHashMap.put("Turks and Caicos Islands", NA);
		regionsHashMap.put("Chad", AF);
		regionsHashMap.put("French Southern Territories", AT);
		regionsHashMap.put("Togo", AF);
		regionsHashMap.put("Thailand", AS);
		regionsHashMap.put("Tajikistan", AS);
		regionsHashMap.put("Tokelau", OC);
		regionsHashMap.put("Tokelau (Tokelau Islands)", OC);
		regionsHashMap.put("Timor-Leste", AS);
		regionsHashMap.put("Turkmenistan", AS);
		regionsHashMap.put("Tunisia", AF);
		regionsHashMap.put("Tonga", OC);
		regionsHashMap.put("Turkey", AS);
		regionsHashMap.put("Trinidad and Tobago", SA);
		regionsHashMap.put("Tuvalu", OC);
		regionsHashMap.put("Taiwan", AS);
		regionsHashMap.put("Tanzania", AF);
		regionsHashMap.put("Ukraine", EU);
		regionsHashMap.put("Uganda", AF);
		regionsHashMap.put("United States Minor Outlying Islands", NA);
		regionsHashMap.put("United States of America", NA);
		regionsHashMap.put("United States Of America", NA);
		regionsHashMap.put("Uruguay", SA);
		regionsHashMap.put("Uzbekistan", AS);
		regionsHashMap.put("Holy See (Vatican City State)", EU);
		regionsHashMap.put("Holy See", EU);
		regionsHashMap.put("Vatican", EU);
		regionsHashMap.put("Vatican City State", EU);
		regionsHashMap.put("St. Vincent and the Grenadines", NA);
		regionsHashMap.put("Venezuela", SA);
		regionsHashMap.put("British Virgin Islands", NA);
		regionsHashMap.put("US Virgin Islands", NA);
		regionsHashMap.put("Viet Nam", AS);
		regionsHashMap.put("Vietnam", AS);
		regionsHashMap.put("Vanuatu", OC);
		regionsHashMap.put("Wallis and Futuna Islands", OC);
		regionsHashMap.put("Samoa", OC);
		regionsHashMap.put("Yemen", AS);
		regionsHashMap.put("Mayotte", AF);
		regionsHashMap.put("South Africa", AF);
		regionsHashMap.put("Zambia", AF);
		regionsHashMap.put("Zimbabwe", AF);		
	}
	
	public static String getContinent(String country) {
		if (regionsHashMap.containsKey(country)) {
			return regionsHashMap.get(country);
		} else {
			return NA;
		}
	}
	
	public static String getNA() {
		return NA;
	}
	
	public static String getSA() {
		return SA;
	}
	
	public static String getEU() {
		return EU;
	}
	
	public static String getAS() {
		return AS;
	}
	
	public static String getAF() {
		return AF;
	}
	
	public static String getOC() {
		return OC;
	}
	
	public static String getAT() {
		return AT;
	}

}
