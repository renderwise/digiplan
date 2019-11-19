package api;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.DocumentException;


public class Htmltopdf {
	String policyNumber = "";
	private String nameOfPolicyHolder;
	private String nicNoOfPolicyHolder;
	private String ageAtNextBirthdayOflifeAssured;
	private String dateOfBirth;
	private String term;
	private String commencementDate;
	private String maturityDate;
	private String frequencyOfPremiumPayment;
	private String lastPremiumDue;
	private String prmForBasicLifeCover;
	private String prmForAddiRiderBenefits;
	private String basicSumAssuredInRs;
	private String digitalSignature;
	private String policyRefNo;
	private String agentCode;
	private String branch;
	private String date;
	private String nameOfLifeAssured;
	private String nicNoOfLifeAssured;
	private JSONArray linBenefitJsonArray;
	private JSONArray spuBenefitJsonArray;
	private JSONObject chlBenefitJson;
	private JSONArray nomineeJsonArray;
	private String planCode;
	

	private JSONObject reducedTermJson;
	private int[] yearArray;
	private Object[] rateArray;
	private int[] stringYearArray;
	
	private JSONArray clauseDetailsInfoArray;

	
	//change 1 sequence 05-09-2018
	private String[] linBenefitSequenceArray;
	private String[] spuBenefitSequenceArray;
	private String[] chlBenefitSequenceArray;
	//change 1 sequence 05-09-2018 ends here
	/**
	 * @throws DocumentException
	 * @throws IOException
	 */
	public String convertHTMLtoPDF() throws DocumentException, IOException {

		String yourXhtmlContentAsString = "<html><head>\r\n";

		yourXhtmlContentAsString += "<style> \r\n" + ".topHeading{font-size: 13pt}\r\n"
				+ ".belowTopHeading{font-size: 7pt}\r\n" + ".tableBorder {border: 0.5pt solid black; page-break-inside: avoid;}\r\n"
				+ ".tableBorder >tbody>tr> td{border: 0.5pt solid black}\r\n"
				+ "div.header {\r\n" + 
				"    display: block; text-align: center; \r\n" + 
				"    position: running(header);\r\n" + 
				"}\r\n" + 
				"div.footer {\r\n" + 
				"    display: block; text-align: center;\r\n" + 
				"    position: running(footer);\r\n" + 
				"}\r\n" + 
				"div.content {page-break-after: always;}\r\n" + 
				"@page {\r\n" + 
				"     @top-center { content: element(header) }\r\n" + 
				"}\r\n" + 
				"@page { \r\n" + 
				"    @bottom-right {content :\"Page \" counter(page) \" of \" counter(pages) }\r\n" + 
				"}" + 
				"@page { \r\n" + 
				"    @bottom-center {content :element(footer) }\r\n" + 
				"}"+
				" </style>";

		
		
		yourXhtmlContentAsString += "\r\n" + "<title></title> \r\n" + " </head> \r\n" + "<body>\r\n"
		
				+"<div class=\"footer\">" + 
				"    <i><b>Janashakthi for Insurance</b></i>" + 
				"</div>"
			
				
				
				+ "<p class=\"\" align=\"CENTER\" style=\"margin-bottom: 0in; line-height: 100%\">\r\n"
				+ "		<font size=\"3\" class=\"topHeading\"><b>JANASHAKTHI\r\n"
				+ "				INSURANCE PLC</b></font>\r\n" + "	</p>\r\n"
				+ "	<div class=\"belowTopHeading\" align=\"CENTER\" style=\" \">\r\n"
				+ "		<font class=\"\">No.675, Dr. Danister De Silva Road, Colombo 09.. Tel :\r\n"
				+ "			0112303300</font>\r\n" + "	</div>\r\n"
				+ "	<div class=\"belowTopHeading\" align=\"CENTER\" style=\" \">\r\n"
				+ "		<font class=\"\">Fax (Gen) : 0112334864, 0117309299 (Life) : 0112309799</font>\r\n"
				+ "	</div>\r\n" + "	\r\n" + "	<p class=\"\" align=\"CENTER\" style=\"margin-bottom: 0in\">\r\n"
				+ "		<font style=\"font-size: 9pt\"><b>SCHEDULE 01</b></font>\r\n" + "	</p>\r\n"
				+ "\r\n <br></br> "
				+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + "\r\n"
				+ "		<tbody><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"155\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Your Life Assurance\r\n"
				+ "							Plan</b></font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "			<td style=\"padding-left:10px;\" width=\"156\" >\r\n"
				+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
				+ planCode + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"118\" >\r\n"
				+ "				<span class=\"\" style=\"margin-left: 0.16in\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Policy Number</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"127\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>" + policyNumber + "</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n" + "	</tbody></table>\r\n"
				+ "\r\n <br></br>"
				+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + "\r\n"
				+ "		<tbody><tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"155\">\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Name of Proposer</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"156\">\r\n"
				+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\">"
				+ nameOfPolicyHolder + "</font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"261\">\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">NIC NUMBER : " + nicNoOfPolicyHolder + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"155\">\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Name of Life Assured</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"156\">\r\n"
				+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\">"
				+ nameOfLifeAssured + "</font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"261\">\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">NIC NUMBER : " + nicNoOfLifeAssured + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "	</tbody></table> <br></br> \r\n"
				+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "		<tbody><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Age at next Birthday of the\r\n"
				+ "						Life Assured</font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "			<td style=\"padding-left:10px;\" width=\"257\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\">" + ageAtNextBirthdayOflifeAssured + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "		<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Date of Birth</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"257\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\">" + dateOfBirth + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "		<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Term </font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "			<td style=\"padding-left:10px;\" width=\"257\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\">" + term + "</font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "		</tr>\r\n" + "		<tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Commencement Date</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"257\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\">" + commencementDate + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "		<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Maturity Date</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"257\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\">" + maturityDate + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "		<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Frequency of Premium Payment/\r\n"
				+ "						Mode</font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"257\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\">" + frequencyOfPremiumPayment + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "		<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Last Premium due Date</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"257\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\">" + lastPremiumDue + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n" + "	</tbody></table>\r\n"
				+ "	\r\n  <br></br>" + "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
				+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>PREMIUM\r\n"
				+ "					DETAILS</b></u></font>\r\n" + "	</span>\r\n"
				+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "		<tbody><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Premium for Basic Life Cover /\r\n"
				+ "						Investment</font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
				+ "					</span></font><font style=\"font-size: 9pt\">"
				+ Htmltopdf.thousandSeparator(Double.parseDouble(prmForBasicLifeCover)) + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "		<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px; \" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\">Premium for Additional Rider\r\n"
				+ "						Benefits</font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
				+ "							" + Htmltopdf.thousandSeparator(Double.parseDouble(prmForAddiRiderBenefits))
				+ "</span></font>\r\n" + "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "		<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Total Premium\r\n"
				+ "							(Including Taxes)</b></font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<a name=\"_GoBack\"></a> <font style=\"font-size: 9pt\"><span lang=\"id-ID\"><b>Rs. </b></span></font><font style=\"font-size: 9pt\"><b> \r\n"
				+ "						"
				+ Htmltopdf.thousandSeparator(
						Double.parseDouble(prmForBasicLifeCover) + Double.parseDouble(prmForAddiRiderBenefits))
				+ "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
				+ "	</tbody></table>\r\n" + "\r\n <br></br>" + "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
				+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>MAIN INSURED\r\n"
				+ "					BENEFIT</b></u></font>\r\n" + "	</span>\r\n"
				+ "	<table width=\"100%\" class=\"\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + "	\r\n"
				+ "		<tbody><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\">Basic Sum Assured: (Rs.)</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; border: 1pt solid black \" width=\"170\" >\r\n"
				+ "				<span class=\"\" align=\"LEFT\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\">" + basicSumAssuredInRs + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n"
				+ "				<span class=\"\">\r\n" + "					<br></br>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "		</tr>\r\n" + "		<tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\"><span lang=\"id-ID\">Maturity\r\n"
				+ "							Benefit : (Rs.)</span></font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; border: 1pt solid black; border-top:none;\" width=\"170\" >\r\n"
				+ "				<span class=\"\" align=\"LEFT\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\">LAST VALUE OF\r\n"
				+ "						ILLUSTRATION</font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n"
				+ "				<span class=\"\">\r\n" + "					<br></br>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "		</tr>\r\n";
				
		
				//Code for JCA01
				if(planCode.equalsIgnoreCase("JCA01")){
					yourXhtmlContentAsString+= "		<tr valign=\"TOP\">\r\n"
							+ "			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n"
							+ "				<span class=\"\">\r\n"
							+ "					<font size=\"1\" style=\"font-size: 9pt\"><span lang=\"id-ID\">Survival benefit : (Rs.)</span></font>\r\n" + "				</span>\r\n"
							+ "			</td>\r\n"
							+ "			<td style=\"padding-left:10px; border: 1pt solid black; border-top:none;\" width=\"170\" >\r\n"
							+ "				<span class=\"\" align=\"LEFT\">\r\n"
							+ "					<font size=\"1\" style=\"font-size: 9pt\">% of SA</font>\r\n" + "				</span>\r\n"
							+ "			</td>\r\n" + "			<td style=\"padding-left:10px; border:none;font-size: 9pt\" width=\"272\" >\r\n"
							+ "				<span class=\"\">\r\n" + "Paid every three Years commencing from the Commencement date" + "				</span>\r\n"
							+ "			</td>\r\n" + "		</tr>\r\n";
				}
				yourXhtmlContentAsString+=
				 "	</tbody></table>\r\n" + "	\r\n ";
				//Code for JCA01 end here
				
				
				
		if (planCode.equalsIgnoreCase("NGT01") || planCode.equalsIgnoreCase("NGT02")) {
			yourXhtmlContentAsString += "<br></br>	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
					+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>ADDITIONAL AMOUNT PAYABLE</b></u></font>\r\n"
					+ "	</span>\r\n";
			if (planCode.equalsIgnoreCase("NGT01")) {
				yourXhtmlContentAsString += "<div style=\"font-size:9pt;\">This Additional Amount shall become payable upon Death or Total Permanent Disability due to Accident or Sickness based\r\n"
						+ "on the Increase of the Sum assured as shown below by 50% in every 05 years before the date of Maturity.</div>";
				yourXhtmlContentAsString += "<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
						+ "		<tbody><tr valign=\"TOP\">\r\n"
						+ "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\">First 5 Years </font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">\r\n"
						+ "					</span></font><font style=\"font-size: 9pt\">Nil</font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
						+ "<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\">6 th to 10th Year</font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
						+ "					</span></font><font style=\"font-size: 9pt\">Rs. 75,000</font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
						+ "<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\">11th to 15th Year</font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
						+ "					</span></font><font style=\"font-size: 9pt\">Rs. 150,000</font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
						+ "<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\">6th to 20th Year</font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
						+ "				<span class=\"\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
						+ "					</span></font><font style=\"font-size: 9pt\">Rs. 225,000</font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
						+ "	</tbody></table>\r\n";
			} else {
				yourXhtmlContentAsString += "<div style=\"font-size:9pt;\">This Additional Amount shall become payable upon Death or Total Permanent Disability due to Accident or Sickness based\r\n"
						+ "on the Increase of the Sum assured by 10% in every year before the date of Maturity.\r\n"
						+ "</div>";
			}

		}
		
		
	
		if(planCode.equalsIgnoreCase("NJU4")){
			yourXhtmlContentAsString+= "<br></br>" + "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
					+ "		<font size=\"1\" style=\"font-size: 8pt\">After the Date of Maturity - Main Insured only</font>\r\n" + "	</span>\r\n"
					+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "		<tbody><tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
					+ "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\">(a) On death other than by an accident /\r\n"
					+ "						Investment</font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
					+ "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
					+ "					</span></font><font style=\"font-size: 9pt\">"
					+ Htmltopdf.thousandSeparator(0.2 * Double.parseDouble(basicSumAssuredInRs)) + "</font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>" + "<tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
					+ "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\">(b) On death caused by an accident /\r\n"
					+ "						Investment</font>\r\n" + "				</span>\r\n"
					+ "			</td>\r\n"
					+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
					+ "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
					+ "					</span></font><font style=\"font-size: 9pt\">"
					+ Htmltopdf.thousandSeparator(1 * Double.parseDouble(basicSumAssuredInRs)) + "</font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n"
					+ "<tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"327\" >\r\n"
					+ "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\">(c) On hospitalization  /\r\n"
					+ "						Investment</font>\r\n" + "				</span>\r\n"
					+ "			</td>\r\n"
					+ "			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n"
					+ "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n"
					+ "					</span></font><font style=\"font-size: 9pt\">"
					+ Htmltopdf.thousandSeparator(0.2 * Double.parseDouble(basicSumAssuredInRs)) + "</font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n" + "		</tr> </tbody></table>\r\n";
			
			
			
			String flag = "F";
			double criticalIllnessContant=0;
			if(flag.equalsIgnoreCase("F")){
				criticalIllnessContant=0.2;
			}
			else if(flag.equalsIgnoreCase("I")){
				criticalIllnessContant=0.005;
			}
			yourXhtmlContentAsString +="<br></br>	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
					+ "		<font size=\"1\" style=\"font-size: 9pt\"><u><b>PACKAGE BENEFITS OF MAIN LIFE INSURED</b></u></font>\r\n" + "	</span>\r\n" + "	\r\n"
					+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\"  style=\"page-break-inside: avoid;\" cellspacing=\"0\">\r\n"
					+ "		<tbody><tr valign=\"TOP\" >\r\n"
					+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px;\" width=\"239\" >\r\n"
					+ "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Benefit for "
					+ ((JSONObject) linBenefitJsonArray.get(0)).get("name") + "</b></font>\r\n" + "				</span>\r\n"
					+ "			</td>\r\n"
					+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"90\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in; \">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Sum Assured (Rs)</b></font>\r\n"
					+ "				</span>\r\n" +

					"			</td>\r\n"
					+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"71\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Premium (Rs)</b></font>\r\n"
					+ "				</span>\r\n" +

					"			</td>\r\n"
					+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"82\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Expiry Date</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"57\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in; \">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Applicable Schedules</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";
			
			yourXhtmlContentAsString += "<tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"239\">\r\n"
					+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
					+ "Accidental Death Benefit, TPD &#38; PPD" + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px; text-align:right;\" width=\"90\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + Htmltopdf.thousandSeparator(2*Double.parseDouble(basicSumAssuredInRs)) + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
					+ "			<td rowspan=\"3\" style=\"text-align:right; vertical-align:middle;\" width=\"71\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + Htmltopdf.thousandSeparator(Double.parseDouble(prmForBasicLifeCover)) + " </b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"82\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + maturityDate + "</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"57\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + "4" + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n" + "		</tr>"
					+"<tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"239\">\r\n"
					+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
					+ "Hospitalization Benefit - Reimbursement" + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px; text-align:right;\" width=\"90\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>"+Htmltopdf.thousandSeparator(criticalIllnessContant*Double.parseDouble(basicSumAssuredInRs)) + "</b></font>\r\n"
					+ "				</span>\r\n"  + "				\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"82\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + maturityDate + "</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"57\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + "6" + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n" + "		</tr>"
					+"<tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"239\">\r\n"
					+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
					+ "Critical Illness Benefit" + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px; text-align:right;\" width=\"90\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + Htmltopdf.thousandSeparator(Double.parseDouble(basicSumAssuredInRs)) + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"82\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + maturityDate + "</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"57\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + "5" + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n" + "		</tr>"
					+"</tbody></table>"
					;
			
		}

		

		yourXhtmlContentAsString += "<br></br> <div style=\"page-break-inside: avoid;\">	<span class=\"\" style=\"margin-bottom: 0in;\">\r\n"
				+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>ADDITIONAL\r\n"
				+ "					RIDER BENEFITS</b></u></font>\r\n" + "	</span>\r\n" + "	\r\n"
				+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\"  style=\"page-break-inside: avoid;\" cellspacing=\"0\">\r\n"
				+ "		<tbody><tr valign=\"TOP\" >\r\n"
				+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px;\" width=\"239\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Benefit for "
				+ ((JSONObject) linBenefitJsonArray.get(0)).get("name") + "</b></font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n"
				+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"90\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in; \">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Sum Assured (Rs)</b></font>\r\n"
				+ "				</span>\r\n" +

				"			</td>\r\n"
				+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"71\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in;\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Premium (Rs)</b></font>\r\n"
				+ "				</span>\r\n" +

				"			</td>\r\n"
				+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"82\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Expiry Date</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"57\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in; \">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Applicable Schedules</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";

		// here addtional code
		for (int i = 0; i < linBenefitJsonArray.size(); i++) {
			JSONObject localLinJson = (JSONObject) linBenefitJsonArray.get(i);
			String benefitDescription = (String) (localLinJson).get("benefitDescription");
			String benefitType = (String) (localLinJson).get("benefitType");
			String sumAssuredRs = Htmltopdf
					.thousandSeparator(Double.parseDouble((String) (localLinJson).get("sumAssured(Rs.)")));
			String premiumRs = Htmltopdf
					.thousandSeparator(Double.parseDouble((String) (localLinJson).get("premium(Rs.)")));
			String expiryDate = (String) (localLinJson).get("expiryDate");
			String applicableSchedules = (String) (localLinJson).get("applicableSchedules");
			yourXhtmlContentAsString += "<tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"239\">\r\n"
					+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
					+ benefitType + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px; text-align:right;\" width=\"90\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + sumAssuredRs + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
					+ "			<td style=\"text-align:right;\" width=\"71\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + premiumRs + " </b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"82\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + expiryDate + "</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"57\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + applicableSchedules + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n" + "		</tr>";

		}

		// here addtional code ends

		yourXhtmlContentAsString += "	</tbody></table>\r\n </div>" + "	<span>\r\n" + "	</span>\r\n <br></br>";
		if(spuBenefitJsonArray.size()!=0){
				yourXhtmlContentAsString 		+= "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" style=\"page-break-inside: avoid;\" cellspacing=\"0\">\r\n"

				
				+ "	<tbody><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"239\" bgcolor=\"#dddddd\">\r\n"
				+ "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Benefit for "
				+ ((JSONObject) spuBenefitJsonArray.get(0)).get("name") + "</b></font>\r\n"
				+ "				</span>\r\n <br></br>" + "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Date of Birth\r\n"
				+ "							" + ((JSONObject) spuBenefitJsonArray.get(0)).get("dateOfBirth")
				+ "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\" text-align:center;\" width=\"90\" bgcolor=\"#dddddd\">\r\n"
				+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Sum Assured (Rs)</b></font>\r\n"
				+ "				</span>\r\n" +

				"			</td>\r\n"
				+ "			<td style=\" text-align:center;\" width=\"71\" bgcolor=\"#dddddd\">\r\n"
				+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Premium (Rs)</b></font>\r\n"
				+ "				</span>\r\n" +

				"			</td>\r\n"
				+ "			<td style=\" text-align:center;\" width=\"82\" bgcolor=\"#dddddd\">\r\n"
				+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Expiry Date</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\" text-align:center;\" width=\"57\" bgcolor=\"#dddddd\">\r\n"
				+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Applicable Schedules</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";

		// here addtional code
		for (int i = 0; i < spuBenefitJsonArray.size(); i++) {
			JSONObject localJson = (JSONObject) spuBenefitJsonArray.get(i);
			String benefitDescription = (String) (localJson).get("benefitDescription");
			String benefitType = (String) (localJson).get("benefitType");
			String sumAssuredRs = Htmltopdf
					.thousandSeparator(Double.parseDouble((String) (localJson).get("sumAssured(Rs.)")));
			String premiumRs = Htmltopdf
					.thousandSeparator(Double.parseDouble((String) (localJson).get("premium(Rs.)")));
			String expiryDate = (String) (localJson).get("expiryDate");
			String applicableSchedules = (String) (localJson).get("applicableSchedules");

			yourXhtmlContentAsString += "<tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"239\">\r\n"
					+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
					+ benefitType + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"text-align:right;\" width=\"90\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + sumAssuredRs + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
					+ "			<td style=\"text-align:right;\" width=\"71\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + premiumRs + " </b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
					+ "			<td style=\"text-align:center;\" width=\"82\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + expiryDate + "</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"text-align:center;\" width=\"57\" >\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + applicableSchedules + "</b></font>\r\n"
					+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n" + "		</tr>";

		}

		// here addtional code ends

		yourXhtmlContentAsString += "	</tbody></table>\r\n" + "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
				+ "		\r\n" + "	</span>\r\n ";
		}
		Object[] childNames = chlBenefitJson.keySet().toArray();
		for (int i = 0; i < childNames.length; i++) {
			JSONArray localJsonArray = (JSONArray) chlBenefitJson.get(childNames[i].toString());
			
			//change 4 sequence 05-09-2018
			localJsonArray=this.getSequencedJsonArray(localJsonArray, chlBenefitSequenceArray);
			//change 4 sequence 05-09-2018 ends here
			
			yourXhtmlContentAsString += "	<br></br><table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\"  style=\"page-break-inside: avoid;\" cellspacing=\"0\">\r\n"
					+ "		<tbody><tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"239\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Benefit for " + childNames[i]
					+ "</b></font>\r\n" + "				</span>\r\n <br></br>" + "				<span class=\"\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Date of Birth "
					+ ((JSONObject) localJsonArray.get(i)).get("dateOfBirth") + "</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"text-align:center;\" width=\"90\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Sum Assured (Rs)</b></font>\r\n"
					+ "				</span>\r\n" +

					"			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"71\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Premium (Rs)</b></font>\r\n"
					+ "				</span>\r\n" +

					"			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"82\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Expiry Date</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\" text-align:center;\" width=\"57\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Applicable Schedules</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";

			for (int j = 0; j < localJsonArray.size(); j++) {
				JSONObject localJson = (JSONObject) localJsonArray.get(j);
				String benefitDescription = (String) (localJson).get("benefitDescription");
				String benefitType = (String) (localJson).get("benefitType");
				String sumAssuredRs = Htmltopdf
						.thousandSeparator(Double.parseDouble((String) (localJson).get("sumAssured(Rs.)")));
				String premiumRs = Htmltopdf
						.thousandSeparator(Double.parseDouble((String) (localJson).get("premium(Rs.)")));
				String expiryDate = (String) (localJson).get("expiryDate");
				String applicableSchedules = (String) (localJson).get("applicableSchedules");

				yourXhtmlContentAsString += "<tr valign=\"TOP\">\r\n"
						+ "			<td style=\"padding-left:10px;\" width=\"239\">\r\n"
						+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
						+ benefitType + "</b></font>\r\n" + "				</span>\r\n"
						+ "			</td>\r\n" + "			<td style=\"text-align:right;\" width=\"90\" >\r\n"
						+ "				<span class=\"\" style=\" margin-right: 0.05in;\" >\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>" + sumAssuredRs + "</b></font>\r\n"
						+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
						+ "			<td style=\"text-align:right;\" width=\"71\" >\r\n"
						+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>" + premiumRs + " </b></font>\r\n"
						+ "				</span>\r\n" + "				\r\n" + "			</td>\r\n"
						+ "			<td style=\"text-align:center;\" width=\"82\" >\r\n"
						+ "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>" + expiryDate + "</b></font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"text-align:center;\" width=\"57\" >\r\n"
						+ "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>" + applicableSchedules
						+ "</b></font>\r\n" + "				</span>\r\n" + "				\r\n"
						+ "			</td>\r\n" + "		</tr>";

			}

			yourXhtmlContentAsString += "	</tbody></table>\r\n";

		}

		yourXhtmlContentAsString += "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + "		\r\n"
				+ "	</span>\r\n <br></br>"
				+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "		<tbody><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"345\" bgcolor=\"#dddddd\" >\r\n"
				+ "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Details of Nominee</b></font>\r\n"
				+ "				</span>\r\n" + "				<span class=\"\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Name </b></font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"148\" bgcolor=\"#dddddd\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>NIC Number / Date of\r\n"
				+ "							Birth</b></font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n"
				+ "			<td style=\" text-align:center;\" width=\"77\" bgcolor=\"#dddddd\" >\r\n"
				+ "				<span class=\"\" align=\"CENTER\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>Share (%)</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";

		for (int i = 0; i < nomineeJsonArray.size(); i++) {
			JSONObject localJson = (JSONObject) nomineeJsonArray.get(i);
			String nomineeName = (String) (localJson).get("nomineeName");
			String nomineeNicNumber = (String) (localJson).get("nomineeNicNumber");
			String nomineeDateOfBirth = (String) (localJson).get("nomineeDateOfBirth");
			String share = (String) (localJson).get("share(%)");

			yourXhtmlContentAsString += "<tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"345\" >\r\n"
					+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>"
					+ nomineeName + " </b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"148\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + nomineeNicNumber + " / "
					+ nomineeDateOfBirth + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"text-align:center;\" width=\"77\" >\r\n"
					+ "				<span class=\"\" align=\"CENTER\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>" + share + "</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";

		}

		yourXhtmlContentAsString += "	</tbody></table>\r\n" + "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
				+ "		<br></br>\r\n" + "	</span>\r\n";
		
		
		
		if(planCode.equalsIgnoreCase("li4")){
			
			
			if(frequencyOfPremiumPayment.equalsIgnoreCase("M")||frequencyOfPremiumPayment.equalsIgnoreCase("Q")||frequencyOfPremiumPayment.equalsIgnoreCase("H")||frequencyOfPremiumPayment.equalsIgnoreCase("A"))
			{
				yourXhtmlContentAsString+="	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
						+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>SPECIAL CONDITIONS</b></u></font>\r\n" + "	</span>\r\n";
			yourXhtmlContentAsString+="<table class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%; font-size:9pt;\">\r\n" + 
					"<tbody>\r\n" ;		
			
					yourXhtmlContentAsString+="<tr>\r\n" + 
						"<td style=\"padding:4pt;\">"+
						"In addition to the Schedules mentioned above, Schedule 11 for of the Policy Document shall also become applicable to this policy."+
						"</td>\r\n" + 
						"</tr>\r\n" ;
		
			yourXhtmlContentAsString+= "</tbody>\r\n" + 
			"</table><br></br>";
			}
			else if(frequencyOfPremiumPayment.equalsIgnoreCase("S")){
				yourXhtmlContentAsString+="	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
						+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>SPECIAL CONDITIONS</b></u></font>\r\n" + "	</span>\r\n";
			yourXhtmlContentAsString+="<table class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%; font-size:9pt;\">\r\n" + 
					"<tbody>\r\n" ;		
			
					yourXhtmlContentAsString+="<tr>\r\n" + 
						"<td style=\"padding:4pt;\">"+
						"In addition to the Schedules mentioned above, Schedule 12 for of the Policy Document shall also become applicable to this policy."+
						"</td>\r\n" + 
						"</tr>\r\n" ;
			
			yourXhtmlContentAsString+= "</tbody>\r\n" + 
			"</table><br></br>";
			}
			}
		
		
		yourXhtmlContentAsString=this.printSpecialTermAndConditions(yourXhtmlContentAsString,clauseDetailsInfoArray);
		
		
		/*
		 * "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" frame=\"BELOW\">\r\n"
		 * + "		<tbody><tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF MAIN LIFE ASSURED&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF SPOUSE ASSURED&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"9\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" +
		 * "	</tbody></table>\r\n" +
		 * "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" +
		 * "		<br></br>\r\n" + "	</span>\r\n" +
		 * "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" frame=\"BELOW\">\r\n"
		 * + "		<tbody><tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF CHILD1&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"9\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" +
		 * "	</tbody></table>\r\n" +
		 * "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" +
		 * "		<br></br>\r\n" + "	</span>\r\n" +
		 * "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" frame=\"BELOW\">\r\n"
		 * + "		<tbody><tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF CHILD2&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"9\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" +
		 * "	</tbody></table>\r\n" +
		 * "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" +
		 * "		<br></br>\r\n" + "	</span>\r\n" +
		 * "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" frame=\"BELOW\">\r\n"
		 * + "		<tbody><tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF CHILD3&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"9\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" +
		 * "	</tbody></table>\r\n" +
		 * "	<span class=\"\" align=\"LEFT\" style=\"margin-bottom: 0in\">\r\n"
		 * + "		<br></br>\r\n" + "	</span>\r\n" +
		 * "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" frame=\"BELOW\">\r\n"
		 * + "		<tbody><tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF CHILD4&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"9\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" +
		 * "	</tbody></table>\r\n" +
		 * "	<span class=\"\" align=\"LEFT\" style=\"margin-bottom: 0in\">\r\n"
		 * + "		<br></br>\r\n" + "	</span>\r\n" +
		 * "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" frame=\"BELOW\">\r\n"
		 * + "		<tbody><tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF CHILD5&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"9\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" +
		 * "	</tbody></table>\r\n" +
		 * "	<span class=\"\" align=\"LEFT\" style=\"margin-bottom: 0in\">\r\n"
		 * + "		<br></br>\r\n" + "	</span>\r\n" +
		 * "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" frame=\"BELOW\">\r\n"
		 * + "		<tbody><tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"10\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\">\r\n" +
		 * "					<font style=\"font-size: 9pt\"><u><b>SPECIAL TERMS\r\n"
		 * +
		 * "								AND EXCLUSIONS FOR &lt;NAME OF CHILD6&gt;</b></u></font>\r\n"
		 * + "				</span>\r\n" + "			</td>\r\n" +
		 * "		</tr>\r\n" + "		<tr>\r\n" +
		 * "			<td style=\"padding-left:10px;\" width=\"597\" height=\"9\" valign=\"TOP\" >\r\n"
		 * + "				<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n"
		 * + "					<br></br>\r\n" + "				</span>\r\n" +
		 * "			</td>\r\n" + "		</tr>\r\n" +
		 * "	</tbody></table>\r\n" +
		 * "	<span class=\"\" align=\"LEFT\" style=\"margin-bottom: 0in\">\r\n"
		 * + "		\r\n" + "	</span>\r\n" +
		 */

		
		
		
		if (planCode.equalsIgnoreCase("vsp") || planCode.equalsIgnoreCase("li4")) {
			
			
			yourXhtmlContentAsString += "	<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "		<tbody><tr>\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"602\" valign=\"TOP\">\r\n"
					+ "				<span class=\"\" align=\"LEFT\" style=\"margin-bottom: 0in\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><u><b>Illustrated\r\n"
					+ "								Maturity Benefit </b></u></font>\r\n"
					+ "				</span>\r\n" + "				<div class=\"\">\r\n"
					+ "					<font color=\"#000000\"><font style=\"font-size: 9pt\">We\r\n"
					+ "							give below the Investment Account balances at different dividend\r\n"
					+ "							rates, assuming all premiums under the policy are paid Annually\r\n"
					+ "							on the due dates.</font></font>\r\n" + "				</div>\r\n"
					+ "			</td>\r\n" + "		</tr>\r\n" + "	</tbody></table>\r\n"
					+ "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + "		\r\n" + "	</span>\r\n <br></br>"
					+ "	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "		<tbody><tr valign=\"TOP\">\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"28\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>Term</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"63\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>"+rateArray[0]+"%</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"92\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>"+rateArray[1]+"%</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"82\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>"+rateArray[2]+"%</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n"
					+ "			<td style=\"padding-left:10px;\" width=\"83\" bgcolor=\"#dddddd\">\r\n"
					+ "				<span class=\"\" align=\"CENTER\">\r\n"
					+ "					<font style=\"font-size: 9pt\"><b>"+rateArray[3]+"%</b></font>\r\n"
					+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";
				
			
			
			
			for(int termCount=0;termCount<stringYearArray.length;termCount++){
				
				yourXhtmlContentAsString += "<tr valign=\"TOP\">\r\n"
						+ "			<td style=\"padding-left:10px;\" width=\"28\">\r\n"
						+ "				<span class=\"\" align=\"CENTER\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>"+stringYearArray[termCount]+"</b></font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px;\" width=\"63\" >\r\n"
						+ "				<span class=\"\" align=\"CENTER\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>"+getTerm(stringYearArray[termCount],rateArray[0].toString())+"</b></font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px;\" width=\"92\" >\r\n"
						+ "				<span class=\"\" align=\"CENTER\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>"+getTerm(stringYearArray[termCount],rateArray[1].toString())+"</b></font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px;\" width=\"82\">\r\n"
						+ "				<span class=\"\" align=\"CENTER\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>"+getTerm(stringYearArray[termCount],rateArray[2].toString())+"</b></font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n"
						+ "			<td style=\"padding-left:10px;\" width=\"83\">\r\n"
						+ "				<span class=\"\" align=\"CENTER\">\r\n"
						+ "					<font style=\"font-size: 9pt\"><b>"+getTerm(stringYearArray[termCount],rateArray[3].toString())+"</b></font>\r\n"
						+ "				</span>\r\n" + "			</td>\r\n" + "		</tr>\r\n";
				
				
			}
			
			
			
			yourXhtmlContentAsString += "	</tbody></table>\r\n"
			+ "	<span class=\"\" align=\"LEFT\" style=\"margin-bottom: 0in; line-height: 100%\">\r\n"
			+ "		\r\n" + "	</span>\r\n" + "<br></br>	\r\n"
			+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>ACTUAL DECLARED DIVIDEND RATES</b></u></font>\r\n"
			+"<table width=\"50%\" class=\"tableBorder\" style=\"font-size: 8pt; text-align:center\" cellpadding=\"0\" cellspacing=\"0\">"
			+ "<tbody>"
			+ "<tr style=\"background-color:#dddddd\"> <td style=\"padding-left:10px;\" width=\"172\" ><b> Year</b></td>"
			+ "<td style=\"padding-left:10px;\" width=\"172\" ><b>Annual Dividend Rate </b></td>"
		
			+ "</tr>"
			+ "<tr> <td style=\"padding-left:10px;\" width=\"172\" >2017</td>"
			+ "<td style=\"padding-left:10px;\" width=\"172\" >8.75%</td>"
		
			+ "</tr>"			
			+ "<tr> <td style=\"padding-left:10px;\" width=\"172\" >2018</td>"
			+ "<td style=\"padding-left:10px;\" width=\"172\" >*8.75%</td>"
			
			+ "</tr>"
			+ "</tbody>"
			+ "</table>"
			+ "		<font size=\"1\" style=\"font-size: 8pt\">* Guaranteed minimum dividend rate declared for "+Calendar.getInstance().get(Calendar.YEAR)+"<br></br>"
			+ "This above is only an illustration of probable maturity values at different rates of dividends and does not form a part of the policy. The maturity value will depend on the actual dividend rate declared for each calendar year.<br></br>\r\n" + 
		
			"At the beginning for each calendar year Company will declare the guaranteed minimum rate of dividend applicable for that particular calendar year.<br></br>\r\n" + 
		
			"The actual rate of dividend will be declared at the beginning of the following year, after the valuation carried out by the Actuary, but in no case will it be less than the minimum guaranteed rate. </font>\r\n"
						
				;

			
			
			
		
		}
		
		
		
		yourXhtmlContentAsString += "	<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "	<tbody><tr valign=\"TOP\">\r\n" + "			<td style=\"padding-left:10px;\" width=\"294\">\r\n"
				+ "<span class=\"\" align=\"RIGHT\" style=\"margin-bottom: 0in; \">\r\n"
				+ "					<br></br>\r\n" + "				</span>\r\n"
				+ "				<span class=\"\">\r\n" + "					<font style=\"font-size: 9pt\"><b>Date "
				+ date + "</b></font>\r\n" + "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;text-align:right;\" width=\"294\">\r\n"
				+ "				<span class=\"\" align=\"RIGHT\" style=\"margin-bottom: 0in\">\r\n"
				+ "					<br></br>\r\n" + "				</span>\r\n"
				+ "				<span class=\"\" align=\"RIGHT\">\r\n"
				+ "					<font style=\"font-size: 9pt\"><b>General Manager –\r\n"
				+ "							Life Operations</b></font>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "		</tr>\r\n" + "	</tbody></table>\r\n"
				+ "	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + "		<br></br>\r\n" + "	</span>\r\n";

		yourXhtmlContentAsString += "	<table width=\"100%\" class=\"\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "	\r\n" + "		<tbody><tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\"><b>Policy Reference Number</b></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"170\" >\r\n"
				+ "				<span class=\"\" align=\"LEFT\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\">" + policyRefNo + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n"
				+ "				<span class=\"\">\r\n" + "					<br></br>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "		</tr>\r\n" + "		<tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\"><span lang=\"id-ID\"><b>Agent Code</b></span></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"170\" >\r\n"
				+ "				<span class=\"\" align=\"LEFT\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\">" + agentCode + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n"
				+ "				<span class=\"\">\r\n" + "					<br></br>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "		</tr>\r\n" + "		<tr valign=\"TOP\">\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n"
				+ "				<span class=\"\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\"><span lang=\"id-ID\"><b>Branch</b></span></font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px;\" width=\"170\" >\r\n"
				+ "				<span class=\"\" align=\"LEFT\">\r\n"
				+ "					<font size=\"1\" style=\"font-size: 9pt\">" + branch + "</font>\r\n"
				+ "				</span>\r\n" + "			</td>\r\n"
				+ "			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n"
				+ "				<span class=\"\">\r\n" + "					<br></br>\r\n" + "				</span>\r\n"
				+ "			</td>\r\n" + "		</tr>\r\n" + "	</tbody></table>\r\n" + "	\r\n ";

		yourXhtmlContentAsString +=  "</body></html>";

		return yourXhtmlContentAsString;

	}
	
	
		public String printSpecialTermAndConditions(String yourXhtmlContentAsString, JSONArray localClauseDetailsInfoArray) {
			// TODO Auto-generated method stub
			
			JSONArray mainClausesArray=new JSONArray();
			JSONArray spouseClausesArray=new JSONArray();
			JSONArray childClausesArray=new JSONArray();
			for (int i = 0; i <localClauseDetailsInfoArray.size() ; i++) {
				String specialClauseCategory= (String)(((JSONObject) localClauseDetailsInfoArray.get(i) ).get("specialClauseCategory"));
				if( specialClauseCategory.equalsIgnoreCase("LIN") ){mainClausesArray.add(localClauseDetailsInfoArray.get(i));}
				else if( specialClauseCategory.equalsIgnoreCase("SPU") ){spouseClausesArray.add(localClauseDetailsInfoArray.get(i));}
				else if( specialClauseCategory.equalsIgnoreCase("CHL") ){childClausesArray.add(localClauseDetailsInfoArray.get(i));}
			}
			
			if(mainClausesArray.size()>0){
				yourXhtmlContentAsString+="	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
						+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>SPECIAL TERMS AND EXCLUSIONS FOR *MAINNAME*</b></u></font>\r\n" + "	</span>\r\n";
			yourXhtmlContentAsString+="<table class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%; font-size:9pt;\">\r\n" + 
					"<tbody>\r\n" ;		
			for (int i = 0; i < mainClausesArray.size(); i++) {
				String specialClauseName= ((String)(((JSONObject) mainClausesArray.get(i) ).get("specialClauseName"))).replace("<", "&lt;").replace(">", "&gt;");
					yourXhtmlContentAsString+="<tr>\r\n" + 
						"<td style=\"padding:4pt;\">"+specialClauseName+"</td>\r\n" + 
						"</tr>\r\n" ;
			}
			yourXhtmlContentAsString+= "</tbody>\r\n" + 
			"</table><br></br>";
			}
			
			if(spouseClausesArray.size()>0){
				yourXhtmlContentAsString+="	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
						+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>SPECIAL TERMS AND EXCLUSIONS FOR *SPOUSENAME*</b></u></font>\r\n" + "	</span>\r\n";
			yourXhtmlContentAsString+="<table class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%; font-size:9pt;\">\r\n" + 
					"<tbody>\r\n" ;		
			for (int i = 0; i < spouseClausesArray.size(); i++) {
				String specialClauseName= ((String)(((JSONObject) spouseClausesArray.get(i) ).get("specialClauseName"))).replace("<", "&lt;").replace(">", "&gt;");
					yourXhtmlContentAsString+="<tr>\r\n" + 
						"<td style=\"padding:4pt;\">"+specialClauseName+"</td>\r\n" + 
						"</tr>\r\n" ;
			}
			yourXhtmlContentAsString+= "</tbody>\r\n" + 
			"</table><br></br>";
			}
			
			
			if(childClausesArray.size()>0){
				LinkedHashSet<String> childSet=new LinkedHashSet<String>();
				for (int i = 0; i < childClausesArray.size(); i++) {
					String specialClauseTempInsuredCode= ((String)(((JSONObject) childClausesArray.get(i) ).get("specialClauseTempInsuredCode")));
					childSet.add(specialClauseTempInsuredCode);
				}
				Iterator<String> iterator = childSet.iterator();
				while(iterator.hasNext()) {
					String specialClauseTempInsuredCodeInSet=iterator.next();
					
					yourXhtmlContentAsString+="	<span class=\"\" style=\"margin-bottom: 0in\">\r\n"
							+ "		<font size=\"1\" style=\"font-size: 8pt\"><u><b>SPECIAL TERMS AND EXCLUSIONS FOR *CHILDNAME*</b></u></font>\r\n" + "	</span>\r\n";
				yourXhtmlContentAsString+="<table class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%; font-size:9pt;\">\r\n" + 
						"<tbody>\r\n" ;		
				for (int i = 0; i < childClausesArray.size(); i++) {
					String specialClauseTempInsuredCode= ((String)(((JSONObject) childClausesArray.get(i) ).get("specialClauseTempInsuredCode")));
					if(specialClauseTempInsuredCode.equalsIgnoreCase(specialClauseTempInsuredCodeInSet)){
					String specialClauseName= ((String)(((JSONObject) childClausesArray.get(i) ).get("specialClauseName"))).replace("<", "&lt;").replace(">", "&gt;");
						yourXhtmlContentAsString+="<tr>\r\n" + 
							"<td style=\"padding:4pt;\">"+specialClauseName+"</td>\r\n" + 
							"</tr>\r\n" ;
					}
				}
				yourXhtmlContentAsString+= "</tbody>\r\n" + 
				"</table><br></br>";
				}
			}
			return yourXhtmlContentAsString;
		}


	public void getAllFields(JSONObject jobj,JSONObject jobjTerm) {
		JSONArray welcomeDetailInfoArray = (JSONArray) jobj.get("welcomeDetailInfo");
		JSONObject welcomeDetailInfo = (JSONObject) welcomeDetailInfoArray.get(0);

		planCode = (String) welcomeDetailInfo.get("planCode");
		System.out.println("planCode is : " + planCode);

		JSONArray policyDetailInfoArray = (JSONArray) jobj.get("policyDetailInfo");
		JSONObject policyDetailInfo = (JSONObject) policyDetailInfoArray.get(0);

		policyNumber = (String) policyDetailInfo.get("policyNumber");
		System.out.println("policyNumber is : " + policyNumber);

		nameOfPolicyHolder = (String) policyDetailInfo.get("nameOfPolicyHolder");
		System.out.println("nameOfPolicyHolder is : " + nameOfPolicyHolder);

		nameOfLifeAssured = (String) policyDetailInfo.get("nameOfLifeAssured");
		System.out.println("nameOfLifeAssured is : " + nameOfLifeAssured);

		nicNoOfLifeAssured = (String) policyDetailInfo.get("nicNoOfLifeAssured");
		System.out.println("nicNoOfLifeAssured is : " + nicNoOfLifeAssured);

		nicNoOfPolicyHolder = (String) policyDetailInfo.get("nicNoOfPolicyHolder");
		System.out.println("nicNoOfPolicyHolder is : " + nicNoOfPolicyHolder);

		ageAtNextBirthdayOflifeAssured = (String) policyDetailInfo.get("ageAtNextBirthdayOflifeAssured");
		System.out.println("ageAtNextBirthdayOflifeAssured is : " + ageAtNextBirthdayOflifeAssured);

		dateOfBirth = (String) policyDetailInfo.get("dateOfBirth");
		System.out.println("dateOfBirth is : " + dateOfBirth);

		term = (String) policyDetailInfo.get("term");
		System.out.println("term is : " + term);

		commencementDate = (String) policyDetailInfo.get("commencementDate");
		System.out.println("commencementDate is : " + commencementDate);

		maturityDate = (String) policyDetailInfo.get("maturityDate");
		System.out.println("maturityDate is : " + maturityDate);

		frequencyOfPremiumPayment = (String) policyDetailInfo.get("frequencyOfPremiumPayment");
		System.out.println("frequencyOfPremiumPayment is : " + frequencyOfPremiumPayment);

		lastPremiumDue = (String) policyDetailInfo.get("lastPremiumDue");
		System.out.println("lastPremiumDue is : " + lastPremiumDue);
		
		
		prmForBasicLifeCover =  policyDetailInfo.get("prmForBasicLifeCover") + "";
		System.out.println("prmForBasicLifeCover is : " + prmForBasicLifeCover);

		prmForAddiRiderBenefits = (Double) policyDetailInfo.get("prmForAddiRiderBenefits") + "";
		System.out.println("prmForAddiRiderBenefits is : " + prmForAddiRiderBenefits);

		basicSumAssuredInRs = (String) policyDetailInfo.get("basicSumAssured(Rs.)");
		System.out.println("basicSumAssuredInRs is : " + basicSumAssuredInRs);

		digitalSignature = (String) policyDetailInfo.get("digitalSignature");
		System.out.println("digitalSignature is : " + digitalSignature);

		policyRefNo = (String) policyDetailInfo.get("policyRefNo");
		System.out.println("policyRefNo is : " + policyRefNo);

		agentCode = (String) policyDetailInfo.get("agentCode");
		System.out.println("agentCode is : " + agentCode);

		branch = (String) policyDetailInfo.get("branch");
		System.out.println("branch is : " + branch);

		date = (String) policyDetailInfo.get("date");
		System.out.println("branch is : " + date);

		linBenefitJsonArray = new JSONArray();
		spuBenefitJsonArray = new JSONArray();
		chlBenefitJson = new JSONObject();

		JSONArray benefitWiseInfo = (JSONArray) jobj.get("benefitWiseInfo");
		// linBenefitArray;
		for (int i = 0; i < benefitWiseInfo.size(); i++) {
			JSONObject benefitJson = (JSONObject) benefitWiseInfo.get(i);
			String typeOfUser = (String) benefitJson.get("relationship");
			String name = (String) benefitJson.get("name");
			if (typeOfUser.equals("LIN")) {
				linBenefitJsonArray.add(benefitJson);
			} else if (typeOfUser.equals("SPU")) {
				spuBenefitJsonArray.add(benefitJson);
			} else if (typeOfUser.equals("CHL")) {
				if (chlBenefitJson.get(name) == null) {
					chlBenefitJson.put(name, new JSONArray());
					((JSONArray) chlBenefitJson.get(name)).add(benefitJson);
				} else {
					((JSONArray) chlBenefitJson.get(name)).add(benefitJson);
				}
			}
		}
		//change 2 sequence 05-09-2018
		String[] linBenefitSequenceArrayLocal={"BASIC","FIB","FUNEX","LACCID_R1","LCRITL_R1","LHOSPT_R1","HOSPT_R1","LADDI_R1"};
		String[] spuBenefitSequenceArrayLocal={"BASIC","FIB","FUNEX","LACCID_R1","LCRITL_R1","LHOSPT_R1","HOSPT_R1","LADDI_R1"};
		String[] chlBenefitSequenceArrayLocal={"BASIC","FIB","FUNEX","LACCID_R1","LCRITL_R1","LHOSPT_R1","HOSPT_R1","LADDI_R1"};
		
		linBenefitSequenceArray=linBenefitSequenceArrayLocal;
		spuBenefitSequenceArray=spuBenefitSequenceArrayLocal;
		chlBenefitSequenceArray=chlBenefitSequenceArrayLocal;
		
		linBenefitJsonArray=this.getSequencedJsonArray(linBenefitJsonArray, linBenefitSequenceArray);
		spuBenefitJsonArray=this.getSequencedJsonArray(spuBenefitJsonArray, spuBenefitSequenceArray);
		
		//change 2 sequence 05-09-2018 ends here
		
		System.out.println("linBenefitJsonArray is : " + linBenefitJsonArray.toJSONString());
		System.out.println("spuBenefitJsonArray is : " + spuBenefitJsonArray.toJSONString());
		System.out.println("chlBenefitJson is : " + chlBenefitJson.toJSONString());
		nomineeJsonArray = (JSONArray) jobj.get("nomineeDetailsInfo");
		System.out.println("nomineeJsonArray is : " + nomineeJsonArray.toJSONString());
		
		
		
		clauseDetailsInfoArray=(JSONArray)jobj.get("clauseDetailsInfo");
		
		
		this.getTermJson(jobjTerm);
	}
	
	//change 3 sequence 05-09-2018
	public JSONArray getSequencedJsonArray(JSONArray jarr,String[] sequenceArray){
		JSONArray jReturnArray=new JSONArray();
		if(sequenceArray.length>=jarr.size()){
		for(int i=0;i<sequenceArray.length;i++){
			for(int j=0;j<jarr.size();j++){
				if(sequenceArray[i].equals( ((JSONObject) jarr.get(j)).get("benefitType"))){
					jReturnArray.add((JSONObject) jarr.get(j));
				}
			}
		}
		}
		else{
			jReturnArray=jarr;
		}
		return jReturnArray;
	}
	//change 3 sequence 05-09-2018 ends here
	
	public void getTermJson(JSONObject termJson){
		
		
		reducedTermJson= (JSONObject) termJson.get("term");
		
		JSONArray jArray=(JSONArray)(reducedTermJson.get("year1"));
		
		rateArray=new Object[jArray.size()];
		for(int i=0; i<jArray.size();i++){
			
			rateArray[i]= (Object)((JSONObject)jArray.get(i)).get("rate");
			
		}
		
		Object[] yearArrayString= reducedTermJson.keySet().toArray();
		yearArray=new int[yearArrayString.length];
		int yearInt;
		for(int year=0;year<yearArrayString.length;year++){
			yearInt= Integer.parseInt(((String)yearArrayString[year]).substring(4));
			reducedTermJson.put(yearInt, reducedTermJson.get(yearArrayString[year]));
			reducedTermJson.remove(yearArrayString[year]);
			yearArray[year]=yearInt;
		}
		
		
		stringYearArray=yearArray;
		System.out.println(yearArray.length);
		System.out.println(rateArray.length);
		//stringYearArray = Arrays.copyOf(yearArray, yearArray.length, String[].class);
		Arrays.sort(stringYearArray);
		
	}
	

	public static boolean generatePDF(String jsonString,String termJsonString) {
		
		JSONObject jobj;
		JSONObject jobjTerm;
		try {
			jobj = (JSONObject) (new JSONParser()).parse(jsonString);
			jobjTerm = (JSONObject) (new JSONParser()).parse(termJsonString);
			Htmltopdf htmlToPdf = new Htmltopdf();
			htmlToPdf.getAllFields(jobj,jobjTerm);

			
			String htmlString = htmlToPdf.convertHTMLtoPDF();
			FileUtils.writeStringToFile(new File("C:\\pdf\\a.html"),htmlString);
			// htmlString);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlString);
			renderer.layout();
			String fileNameWithPath = "C:\\pdf\\" + "PDF-XhtmlRendered.pdf";
			FileOutputStream fos = new FileOutputStream(fileNameWithPath);
			renderer.createPDF(fos);
			fos.close();
			System.out.println("File : '" + fileNameWithPath + "' created.");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}


	public String getTerm(int year,String rate) {
		JSONArray jArray=(JSONArray)(reducedTermJson.get(year));
		JSONObject jobj;
		for(int rateCount=0;rateCount<jArray.size();rateCount++){
			jobj= (JSONObject)jArray.get(rateCount);
			if(jobj.get("rate").toString().equals(rate)){
				return jobj.get("value").toString();
			}
		}
		return null;
	}
	
	
	public static String thousandSeparator(double no) {
		String numberAsString = String.format("%,.2f", no);
		return numberAsString;

	}

	/**
	 * @param args
	 * @throws ParseException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ParseException, DocumentException, IOException {
		String jsonObjectSring = "{\r\n" + 
				"  \"welcomeDetailInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"companyCode\": \"00003\",\r\n" + 
				"      \"date\": \"29-08-2018\",\r\n" + 
				"      \"policyHolderName\": \"MRS K Perera \",\r\n" + 
				"      \"addressLine1\": \"No 3\",\r\n" + 
				"      \"addressLine2\": \"Galle Road\",\r\n" + 
				"      \"policyNumber\": \"LI41867037\",\r\n" + 
				"      \"digitalSignature\": \"GM\",\r\n" + 
				"      \"planCode\": \"LI4\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"companyCode\": \"00003\",\r\n" + 
				"      \"date\": \"29-08-2018\",\r\n" + 
				"      \"policyHolderName\": \"MRS K Perera \",\r\n" + 
				"      \"addressLine1\": \"No 3\",\r\n" + 
				"      \"addressLine2\": \"Galle Road\",\r\n" + 
				"      \"policyNumber\": \"LI41867037\",\r\n" + 
				"      \"digitalSignature\": \"GM\",\r\n" + 
				"      \"planCode\": \"LI4\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"policyDetailInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"date\": \"29-08-2018\",\r\n" + 
				"      \"policyNumber\": \"LI41867037\",\r\n" + 
				"      \"nameOfPolicyHolder\": \"MRS K Perera \",\r\n" + 
				"      \"nicNoOfPolicyHolder\": \"865050317V\",\r\n" + 
				"      \"nameOfLifeAssured\": \"MRS K Perera \",\r\n" + 
				"      \"nicNoOfLifeAssured\": \"865050317V\",\r\n" + 
				"      \"ageAtNextBirthdayOflifeAssured\": \"33\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"term\": \"12\",\r\n" + 
				"      \"commencementDate\": \"29-08-2018\",\r\n" + 
				"      \"maturityDate\": \"29-08-2030\",\r\n" + 
				"      \"frequencyOfPremiumPayment\": \"H\",\r\n" + 
				"      \"lastPremiumDue\": \"28-02-2030\",\r\n" + 
				"      \"prmForBasicLifeCover\": 0,\r\n" + 
				"      \"prmForAddiRiderBenefits\": 68381.2,\r\n" + 
				"      \"basicSumAssured(Rs.)\": \"25160\",\r\n" + 
				"      \"digitalSignature\": \"GM\",\r\n" + 
				"      \"agentCode\": \"AGE000000043726\",\r\n" + 
				"      \"branch\": \"BSL\",\r\n" + 
				"      \"policyRefNo\": \"JLP-10S-01-SEPT-2017\",\r\n" + 
				"      \"ruleId\": \"INDIVIDUAL\",\r\n" + 
				"      \"reference\": \"OPTIONAL RIDERS\",\r\n" + 
				"      \"cededSi\": \"25000\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"benefitWiseInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"MRS K Perera \",\r\n" + 
				"      \"benefitType\": \"BASIC\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"8000000\",\r\n" + 
				"      \"premium(Rs.)\": \"14008\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"MRS K Perera \",\r\n" + 
				"      \"benefitType\": \"FIB\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"100000\",\r\n" + 
				"      \"premium(Rs.)\": \"5055.76\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"MRS K Perera \",\r\n" + 
				"      \"benefitType\": \"FUNEX\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"100000\",\r\n" + 
				"      \"premium(Rs.)\": \"2575\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"MRS K Perera \",\r\n" + 
				"      \"benefitType\": \"HOSPT_R1\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"100000\",\r\n" + 
				"      \"premium(Rs.)\": \"8319.83\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"MRS K Perera \",\r\n" + 
				"      \"benefitType\": \"LACCID_R1\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"500000\",\r\n" + 
				"      \"premium(Rs.)\": \"515\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"MRS K Perera \",\r\n" + 
				"      \"benefitType\": \"LCRITL_R1\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"100000\",\r\n" + 
				"      \"premium(Rs.)\": \"151.41\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"MRS K Perera \",\r\n" + 
				"      \"benefitType\": \"LHOSPT_R1\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1986\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"1800000\",\r\n" + 
				"      \"premium(Rs.)\": \"9012.5\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"SPU\",\r\n" + 
				"      \"name\": \"MRS S Perera \",\r\n" + 
				"      \"benefitType\": \"FUNEX\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1984\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"100000\",\r\n" + 
				"      \"premium(Rs.)\": \"2575\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"SPU\",\r\n" + 
				"      \"name\": \"MRS S Perera \",\r\n" + 
				"      \"benefitType\": \"LACCID_R1\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1984\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"400000\",\r\n" + 
				"      \"premium(Rs.)\": \"412\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"SPU\",\r\n" + 
				"      \"name\": \"MRS S Perera \",\r\n" + 
				"      \"benefitType\": \"LADDI_R1\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1984\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"8000000\",\r\n" + 
				"      \"premium(Rs.)\": \"16686\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"SPU\",\r\n" + 
				"      \"name\": \"MRS S Perera \",\r\n" + 
				"      \"benefitType\": \"LHOSPT_R1\",\r\n" + 
				"      \"dateOfBirth\": \"05-01-1984\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"1800000\",\r\n" + 
				"      \"premium(Rs.)\": \"9012.5\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"CHL\",\r\n" + 
				"      \"name\": \"MRS L Perera \",\r\n" + 
				"      \"benefitType\": \"LCRITL_R1\",\r\n" + 
				"      \"dateOfBirth\": \"18-12-2014\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"100000\",\r\n" + 
				"      \"premium(Rs.)\": \"58.2\",\r\n" + 
				"      \"expiryDate\": \"29-08-2030\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"nomineeDetailsInfo\": [],\r\n" + 
				"  \"clauseDetailsInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"specialClauseCode\": \"Clause1\",\r\n" + 
				"      \"specialClauseName\": \"Notwithstanding anything herein contained to the contrary, it is hereby declared and agreed that the benefits under Endorsement No.< > stands cancelled.\",\r\n" + 
				"      \"specialClauseCategory\": \"LIN\",\r\n" + 
				"      \"specialClauseInsuredCode\": \"IP0000004019928\",\r\n" + 
				"      \"specialClauseTempInsuredCode\": \"N/A\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"specialClauseCode\": \"Clause2\",\r\n" + 
				"      \"specialClauseName\": \"Notwithstanding anything herein contained to the contrary, it is hereby declared and agreed that the benefits under Endorsement No.< > stands cancelled65.\",\r\n" + 
				"      \"specialClauseCategory\": \"LIN\",\r\n" + 
				"      \"specialClauseInsuredCode\": \"IP0000004019928\",\r\n" + 
				"      \"specialClauseTempInsuredCode\": \"N/A\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"specialClauseCode\": \"Clause3\",\r\n" + 
				"      \"specialClauseName\": \"Notwithstanding anything herein contained to the contrary, it is hereby declared and agreed that the Company shall not be liable to make any payment under Hospitalization Benefit and Total Permanent Disability benefit in the event of bodily injury to the life assured arising directly or indirectly due to occupation.\",\r\n" + 
				"      \"specialClauseCategory\": \"SPU\",\r\n" + 
				"      \"specialClauseInsuredCode\": \"IP0000004019929\",\r\n" + 
				"      \"specialClauseTempInsuredCode\": \"INS_CD_2\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"specialClauseCode\": \"Clause2\",\r\n" + 
				"      \"specialClauseName\": \"Notwithstanding anything herein contained to the contrary, it is hereby declared and agreed that the Company shall not be liable to make any payment under Hospitalization Benefit and Total Permanent Disability benefit in the event of bodily injury to the life assured arising directly or indirectly due to occupation.\",\r\n" + 
				"      \"specialClauseCategory\": \"CHL\",\r\n" + 
				"      \"specialClauseInsuredCode\": \"IP0000004019929\",\r\n" + 
				"      \"specialClauseTempInsuredCode\": \"INS_CD_2\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"dTAQuoteRecordsInfo\": [],\r\n" + 
				"  \"receiptFees\": [\r\n" + 
				"    {\r\n" + 
				"      \"transType\": \"RENEWALFEE\",\r\n" + 
				"      \"transCode\": \"21\",\r\n" + 
				"      \"foreignAmountPaid\": \"110\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"transType\": \"CESS\",\r\n" + 
				"      \"transCode\": \"27\",\r\n" + 
				"      \"foreignAmountPaid\": \"187\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"transType\": \"PEXPENSES\",\r\n" + 
				"      \"transCode\": \"13\",\r\n" + 
				"      \"foreignAmountPaid\": \"450\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"receiptPrintDetail\": [\r\n" + 
				"    {\r\n" + 
				"      \"receiptNo\": \"LPR2018-104733\",\r\n" + 
				"      \"payeeName\": \"Kaveeshaa\",\r\n" + 
				"      \"address\": \"No 3\\nGalle Road\",\r\n" + 
				"      \"policyNo\": \"LI41867037\",\r\n" + 
				"      \"cashierName\": \"VAIBHAV\",\r\n" + 
				"      \"receiptDate\": \"29-08-2018 51:25:06 PM\",\r\n" + 
				"      \"printDate\": \"29-08-2018 14:30:06 PM\",\r\n" + 
				"      \"paymentType\": \"\",\r\n" + 
				"      \"chequeNo\": \"\",\r\n" + 
				"      \"chequeDate\": \"\",\r\n" + 
				"      \"premium\": 93381,\r\n" + 
				"      \"premiumMode\": \"H\",\r\n" + 
				"      \"agentCode\": \"AGE000000043726\",\r\n" + 
				"      \"agentName\": \"JALATHGE SHANTHA\",\r\n" + 
				"      \"amountPaid\": 94128,\r\n" + 
				"      \"amountPaidInWord\": \"Ninety Four Thousand One Hundred Twenty Eight Only\",\r\n" + 
				"      \"advisorDebit\": 0,\r\n" + 
				"      \"amountInDeposit\": 94128,\r\n" + 
				"      \"totalDeposit\": 94128,\r\n" + 
				"      \"noOfInstallments\": 1,\r\n" + 
				"      \"lastDuePaid\": \"29-08-2018\",\r\n" + 
				"      \"nextDueDate\": \"28-02-2019\",\r\n" + 
				"      \"totalPremiumPaid\": 94128,\r\n" + 
				"      \"lateFee\": 0,\r\n" + 
				"      \"interestFee\": 0,\r\n" + 
				"      \"balanceInDeposit\": 0,\r\n" + 
				"      \"totalAllocatedAmount\": 94128,\r\n" + 
				"      \"linkid\": \"20180819876776\",\r\n" + 
				"      \"dbName\": \"ibmdb5\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"extraPremiumDetails\": [],\r\n" + 
				"  \"cashPlanDetails\": [\r\n" + 
				"    {\r\n" + 
				"      \"category\": \"LIN\",\r\n" + 
				"      \"cashPlanDays\": \"180\",\r\n" + 
				"      \"cashPlanPerDay\": \"10000\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"category\": \"SPU\",\r\n" + 
				"      \"cashPlanDays\": \"180\",\r\n" + 
				"      \"cashPlanPerDay\": \"10000\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"message\": \"\",\r\n" + 
				"  \"status\": \"Success\"\r\n" + 
				"}";
		
		String termJsonString="{\r\n" + 
				"  \"message\": \"Data fetch Successfully\",\r\n" + 
				"  \"status\": \"success\",\r\n" + 
				"  \"term\": {\r\n" + 
				"    \"year10\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"3198596.32\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"3517352.93\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"3869192.87\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"4058564.1\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year6\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"1403521.68\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"1477914.24\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"1555895.44\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"1596285.69\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year5\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"1049670.85\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"1094443.27\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"1140827.07\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"1164641.84\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year8\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"2217985.15\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"2385070.95\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"2564688.81\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"2659483.97\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year7\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"1791860.3\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"1906379.6\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"2027920.42\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"2091456.58\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year9\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"2685553.88\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"2919869.92\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"3175080.25\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"3311087.51\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year2\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"184497.05\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"188018.31\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"191558.39\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"193335.86\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year1\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"51264.44\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"51954.72\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"52642.65\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"52985.86\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year4\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"727253.58\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"751286.77\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"775906.12\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"788441.01\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"year3\": [\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 10,\r\n" + 
				"        \"value\": \"433469\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 12,\r\n" + 
				"        \"value\": \"444197.55\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 14,\r\n" + 
				"        \"value\": \"455076.69\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"rate\": 15,\r\n" + 
				"        \"value\": \"460574.14\"\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  \"noOfTerms\": 10\r\n" + 
				"}";
		
		Htmltopdf.generatePDF(jsonObjectSring,termJsonString);
		
	}

}
