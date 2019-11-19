package api;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.process.internal.RequestScope.Instance;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.DocumentException;

public class HtmltopdfNJEV {
	private String policyNumber = "";
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
	private double sumAssuredNumeric;
	private String vestingDateCommencementDateOfLifeCover;
	private String dueDateOfFirstAnnuityInstallment;
	private String applicableSchedules;

	/**
	 * @throws DocumentException
	 * @throws IOException
	 */
	public String convertHTMLtoPDF() throws DocumentException, IOException {

		String yourXhtmlContentAsString = "<html><head>\r\n";

		yourXhtmlContentAsString += "<style> \r\n" + ".topHeading{font-size: 13pt}\r\n"
				+ ".belowTopHeading{font-size: 7pt}\r\n" + ".tableBorder {border: 0.5pt solid black}\r\n"
				+ ".tableBorder >tbody>tr> td{border: 0.5pt solid black}\r\n" + " </style>";

		yourXhtmlContentAsString += "\r\n" + "<title></title> \r\n" + " </head> \r\n" + 
		"<body>\r\n" + 
		"	<p class=\"\" align=\"CENTER\" style=\"margin-bottom: 0in; line-height: 100%\">\r\n" + 
		"		<font size=\"3\" class=\"topHeading\"><b>JANASHAKTHI\r\n" + 
		"				INSURANCE PLC</b></font>\r\n" + 
		"	</p>\r\n" + 
		"	<div class=\"belowTopHeading\" align=\"CENTER\" style=\" \">\r\n" + 
		"		<font class=\"\">No.675, Dr. Danister De Silva Road, Colombo 09.. Tel :\r\n" + 
		"			0112303300</font>\r\n" + 
		"	</div>\r\n" + 
		"	<div class=\"belowTopHeading\" align=\"CENTER\" style=\" \">\r\n" + 
		"		<font class=\"\">Fax (Gen) : 0112334864, 0117309299 (Life) : 0112309799</font>\r\n" + 
		"	</div>\r\n" + 
		"	\r\n" + 
		"	<p class=\"\" align=\"CENTER\" style=\"margin-bottom: 0in\">\r\n" + 
		"		<font style=\"font-size: 9pt\"><b>SCHEDULE 01</b></font>\r\n" + 
		"	</p>\r\n" + 
		"\r\n" + 
		" <br></br> 	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"\r\n" + 
		"		<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"155\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Your Life Assurance\r\n" + 
		"							Plan</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"156\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>"+planCode+"</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"118\" >\r\n" + 
		"				<span class=\"\" style=\"margin-left: 0.16in\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Policy Number</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"127\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>"+policyNumber+"</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"	</tbody></table>\r\n" + 
		"\r\n" + 
		" <br></br>	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"\r\n" + 
		"		<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"155\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Name of Proposer</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"156\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+nameOfPolicyHolder+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"261\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">NIC NUMBER : "+nicNoOfPolicyHolder+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"155\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Name of Life Assured / Child</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; border-right:none;\" width=\"156\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+nameOfLifeAssured+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; border-left:none;\" width=\"261\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"	</tbody></table> <br></br> \r\n" + 
		"	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"		<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Age at next Birthday of the\r\n" + 
		"						Life Assured</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+ageAtNextBirthdayOflifeAssured+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Date of Birth</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+dateOfBirth+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Commencement Date</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+commencementDate+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"			<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Frequency of premium Payment /Mode</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+frequencyOfPremiumPayment+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Due date of last Premium</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+lastPremiumDue+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Vesting date/Commencement date of life cover</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+vestingDateCommencementDateOfLifeCover+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"			<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Due date of first annuity Installment</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+dueDateOfFirstAnnuityInstallment+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"			<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Date of maturity / Expiry</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"257\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+maturityDate+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"			\r\n" + 
		"	</tbody></table>\r\n" + 
		"	\r\n" + 
		" <br></br>	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		<font size=\"1\" style=\"font-size: 8pt\"><u><b>PREMIUM\r\n" + 
		"					DETAILS</b></u></font>\r\n" + 
		"	</span>\r\n" + 
		"	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"		<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Premium for Basic Life Cover /\r\n" + 
		"						Investment</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n" + 
		"					</span></font><font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(Double.parseDouble(prmForBasicLifeCover))+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; \" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Premium for Additional Rider\r\n" + 
		"						Benefits</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n" + 
		"							"+HtmltopdfNJEV.thousandSeparator(Double.parseDouble(prmForAddiRiderBenefits))+"</span></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Total Premium\r\n" + 
		"							(Including Taxes)</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<a name=\"_GoBack\"></a> <font style=\"font-size: 9pt\"><span lang=\"id-ID\"><b>Rs. </b></span></font><font style=\"font-size: 9pt\"><b> \r\n" + 
		"						"+HtmltopdfNJEV.thousandSeparator(Double.parseDouble(prmForBasicLifeCover)+ Double.parseDouble(prmForAddiRiderBenefits) )+"</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"	</tbody></table>\r\n" + 
		"\r\n" + 
		" <br></br>	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		<font size=\"1\" style=\"font-size: 8pt\"><u><b>MAIN INSURED\r\n" + 
		"					BENEFIT</b></u></font>\r\n" + 
		"	</span>\r\n" + 
		"	<table width=\"100%\" class=\"\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"	\r\n" + 
		"		<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\">Basic Sum Assured: (Rs.)</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-right:10px; text-align:right; border: 1pt solid black \" width=\"170\" >\r\n" + 
		"				<span class=\"\" align=\"LEFT\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<br></br>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\"><span lang=\"id-ID\">Maturity\r\n" + 
		"							Benefit : (Rs.)</span></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-right:10px; text-align:right; border: 1pt solid black; border-top:none;\" width=\"170\" >\r\n" + 
		"				<span class=\"\" align=\"LEFT\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*1.5)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<br></br>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"	</tbody></table>\r\n" + 
		"	\r\n" + 
		" <br></br>	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		<font size=\"1\" style=\"font-size: 8pt\"><u><b>BIRTH DAY GIFT for Name of Life Assured</b></u></font>\r\n" + 
		"	</span>\r\n" + 
		"	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"		<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">From 18th Birthday to 34 th Birth Day</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n" + 
		"					</span></font><font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*0.05)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; \" width=\"327\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">From 35th Birthday to 49th Birth Day</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; padding-right:10px; text-align:right\" width=\"257\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><span lang=\"id-ID\">Rs.\r\n" + 
		"							"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*0.075)+"</span></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"	</tbody></table>\r\n" + 
		"	\r\n" + 
		"<br></br>	\r\n" + 
		"	\r\n" + 
		"	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\"  style=\"page-break-inside: avoid;\" cellspacing=\"0\">\r\n" + 
		"		<tbody><tr valign=\"TOP\" >\r\n" + 
		"			<td bgcolor=\"#dddddd\" style=\"padding-left:10px;\" width=\"239\" colspan=\"2\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Benefit for Name of Life Assured </b><div>After 18th Birth day &#38; vesting of the policy on the Life Assured but before Maturity Date </div></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in; \">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Sum Assured (Rs)</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Applicable Schedules</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"239\" rowspan=\"4\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">(1) (a)Death Or Total Permanent Disability due to sickness Benefit</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">18th to one day less than 25th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+sumAssuredNumeric+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">25th to one day less than 35th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*2)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">35th to one day less than 45th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*3)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">45th to 50th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*4)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"239\" rowspan=\"4\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">(b) Death Or Total Permanent Disability due to Accident Benefit</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">18th to one day less than 25th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*2)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">25th to one day less than 35th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*4)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">35th to one day less than 45th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*6)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">45th to 50th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*8)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;     width: 20%;\" width=\"239\" rowspan=\"4\">\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">(c)Partial Permanent Disability of the Life Assured resulting loss of One limb or one Eye due to an Accident</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">18th to one day less than 25th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*0.5)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">25th to one day less than 35th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">35th to one day less than 45th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*1.5)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">45th to 50th birthday</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*2)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">3</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"			</tbody></table>\r\n" + 
		"	\r\n" + 
		" <br></br>	<table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" style=\"page-break-inside: avoid;\" cellspacing=\"0\">\r\n" + 
		"	<tbody>\r\n" + 
		"	<tr valign=\"TOP\" >\r\n" + 
		"			<td bgcolor=\"#dddddd\" style=\"padding-left:10px;\" width=\"239\"  >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">(2) Amount payable before 18th birth of the Life Assured</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in; \">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Sum Assured (Rs)</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td bgcolor=\"#dddddd\" style=\"padding-left:10px; text-align:center;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Applicable Schedules</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			\r\n" + 
		"		</tr>\r\n" + 
		"	\r\n" + 
		"	<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">In case of Death</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Refund of Premium</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">4</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Total Permanent Disability due to Accident</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">4</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"		\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; text-align:left;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Partial Permanent Disability due to Accident</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"text-align:right;\" width=\"71\" >\r\n" + 
		"				<span class=\"\" style=\" margin-right: 0.05in;\">\r\n" + 
		"					<font style=\"font-size: 9pt\">"+HtmltopdfNJEV.thousandSeparator(sumAssuredNumeric*0.5)+"</font>\r\n" + 
		"				</span>\r\n" + 
		"				\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\" text-align:center;\" width=\"82\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in\">\r\n" + 
		"					<font style=\"font-size: 9pt\">4</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		\r\n" + 
		"	</tbody></table>\r\n" + 
		"	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		\r\n" + 
		"	</span>\r\n" + 
		" 	<br></br><table width=\"100%\" class=\"tableBorder\" cellpadding=\"0\" style=\"page-break-inside: avoid;\" cellspacing=\"0\">\r\n" + 
		"	<tbody>\r\n" + 
		"	<tr valign=\"TOP\" >\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"239\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\">Special Conditions Applicable to this Policy</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td  style=\"padding-left:10px; text-align:center;\" width=\"90\" >\r\n" + 
		"				<span class=\"\" align=\"CENTER\" style=\"margin-left: -0.08in; margin-right: -0.08in; \">\r\n" + 
		"					<font style=\"font-size: 9pt\">Schedule Number 15</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			\r\n" + 
		"			\r\n" + 
		"		</tr></tbody></table>\r\n" + 
		"	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		\r\n" + 
		"	</span>\r\n" + 
		" <br></br>	\r\n" + 
		"	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		<br></br>\r\n" + 
		"	</span>\r\n" + 
		"	<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"	<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"294\">\r\n" + 
		"<span class=\"\" align=\"RIGHT\" style=\"margin-bottom: 0in; \">\r\n" + 
		"					<br></br>\r\n" + 
		"				</span>\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>Date 16-07-2018</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;text-align:right;\" width=\"294\">\r\n" + 
		"				<span class=\"\" align=\"RIGHT\" style=\"margin-bottom: 0in\">\r\n" + 
		"					<br></br>\r\n" + 
		"				</span>\r\n" + 
		"				<span class=\"\" align=\"RIGHT\">\r\n" + 
		"					<font style=\"font-size: 9pt\"><b>General Manager –\r\n" + 
		"							Life Operations</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"	</tbody></table>\r\n" + 
		"	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		<br></br>\r\n" + 
		"	</span>\r\n" + 
		"	<table width=\"100%\" class=\"\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
		"	\r\n" + 
		"		<tbody><tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\"><b>Policy Reference Number</b></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"170\" >\r\n" + 
		"				<span class=\"\" align=\"LEFT\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\">"+policyRefNo+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<br></br>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\"><span lang=\"id-ID\"><b>Agent Code</b></span></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"170\" >\r\n" + 
		"				<span class=\"\" align=\"LEFT\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\">"+agentCode+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<br></br>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"		<tr valign=\"TOP\">\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"172\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\"><span lang=\"id-ID\"><b>Branch</b></span></font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px;\" width=\"170\" >\r\n" + 
		"				<span class=\"\" align=\"LEFT\">\r\n" + 
		"					<font size=\"1\" style=\"font-size: 9pt\">"+branch+"</font>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"			<td style=\"padding-left:10px; border:none;\" width=\"272\" >\r\n" + 
		"				<span class=\"\">\r\n" + 
		"					<br></br>\r\n" + 
		"				</span>\r\n" + 
		"			</td>\r\n" + 
		"		</tr>\r\n" + 
		"	</tbody></table>\r\n" + 
		"	\r\n" + 
		" 	<span class=\"\" style=\"margin-bottom: 0in\">\r\n" + 
		"		<br></br>\r\n" + 
		"	</span>\r\n" + 
		"	<span class=\"\" style=\"margin-bottom: 0in; \">\r\n" + 
		"		<br></br>\r\n" + 
		"	</span>\r\n" + 
		"	<div style=\"text-align:center\" type=\"FOOTER\">\r\n" + 
		"		<span align=\"CENTER\" style=\"margin-top: 0.27in; margin-bottom: 0in; \">\r\n" + 
		"			<i><b>Janashakthi </b></i><font><i><b>for</b></i></font><i><b>\r\n" + 
		"					Insurance</b></i>\r\n" + 
		"		</span>\r\n" + 
		"	</div>\r\n" + 
		"\r\n" + 
		"</body></html>";

		return yourXhtmlContentAsString;

	}

	public void getAllFields(JSONObject jobj) {
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

		vestingDateCommencementDateOfLifeCover = (String) policyDetailInfo.get("vestingDateCommencementDateOfLifeCover");
		System.out.println("lastPremiumDue is : " + vestingDateCommencementDateOfLifeCover);
		
		dueDateOfFirstAnnuityInstallment = (String) policyDetailInfo.get("dueDateOfFirstAnnuityInstallment");
		System.out.println("lastPremiumDue is : " + dueDateOfFirstAnnuityInstallment);
		
		if (policyDetailInfo.get("prmForBasicLifeCover") instanceof Double)
			prmForBasicLifeCover = (Double) policyDetailInfo.get("prmForBasicLifeCover") + "";
		else if (policyDetailInfo.get("prmForBasicLifeCover") instanceof Long)
			prmForBasicLifeCover = (Long) policyDetailInfo.get("prmForBasicLifeCover") + "";	
		System.out.println("prmForBasicLifeCover is : " + prmForBasicLifeCover);

		if (policyDetailInfo.get("prmForAddiRiderBenefits") instanceof Double)
			prmForAddiRiderBenefits = (Double) policyDetailInfo.get("prmForAddiRiderBenefits") + "";
		else if (policyDetailInfo.get("prmForAddiRiderBenefits") instanceof Long)
			prmForAddiRiderBenefits = (Long) policyDetailInfo.get("prmForAddiRiderBenefits") + "";

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
		System.out.println("date is : " + date);

		sumAssuredNumeric=Double.parseDouble(basicSumAssuredInRs);
		System.out.println("sumAssuredNumeric is : " + sumAssuredNumeric);
		
		/*linBenefitJsonArray = new JSONArray();
		spuBenefitJsonArray = new JSONArray();
		chlBenefitJson = new JSONObject();
*//*
		JSONArray benefitWiseInfoarray = (JSONArray) jobj.get("benefitWiseInfo");
		
		JSONObject benefitWiseInfoJson = (JSONObject)benefitWiseInfoarray.get(0);
		
		applicableSchedules = (String) policyDetailInfo.get("applicableSchedules");
		System.out.println("applicableSchedules is : " + applicableSchedules);
*/
		
		// linBenefitArray;
		/*for (int i = 0; i < benefitWiseInfo.size(); i++) {
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
		System.out.println("linBenefitJsonArray is : " + linBenefitJsonArray.toJSONString());
		System.out.println("spuBenefitJsonArray is : " + spuBenefitJsonArray.toJSONString());
		System.out.println("chlBenefitJson is : " + chlBenefitJson.toJSONString());
		nomineeJsonArray = (JSONArray) jobj.get("nomineeDetailsInfo");
		System.out.println("nomineeJsonArray is : " + nomineeJsonArray.toJSONString());
		*/
		
		//sumAssuredNumeric=Double.parseDouble(basicSumAssuredInRs);
		//sumAssuredNumeric=1;
	}

	public static boolean generatePDF(String jsonString) {

		JSONObject jobj;
		try {
			jobj = (JSONObject) (new JSONParser()).parse(jsonString);
			HtmltopdfNJEV htmlToPdf = new HtmltopdfNJEV();
			htmlToPdf.getAllFields(jobj);
			String htmlString = htmlToPdf.convertHTMLtoPDF();
			FileUtils.writeStringToFile(new File("C:\\pdf\\a.html"),htmlString);
			// htmlString);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlString);
			renderer.layout();
			String fileNameWithPath = "C:\\pdf\\" + "PDF-XhtmlRendered-NJEV.pdf";
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

	public static String thousandSeparator(double no) {
		String numberAsString = String.format("%,.2f", no);
		return numberAsString;

	}

	public static void main(String[] args) throws ParseException, DocumentException, IOException {
		String jsonObjectSring = "{\r\n" + 
				"  \"welcomeDetailInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"companyCode\": \"00003\",\r\n" + 
				"      \"date\": \"20-07-2018\",\r\n" + 
				"      \"policyHolderName\": \"Shanmugam\",\r\n" + 
				"      \"addressLine1\": \"Colombo\",\r\n" + 
				"      \"addressLine2\": \"N/A\",\r\n" + 
				"      \"policyNumber\": \"NJEV1866869\",\r\n" + 
				"      \"digitalSignature\": \"GM\",\r\n" + 
				"      \"planCode\": \"NJEV\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"policyDetailInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"date\": \"20-07-2018\",\r\n" + 
				"      \"policyNumber\": \"NJEV1866869\",\r\n" + 
				"      \"nameOfPolicyHolder\": \"Shanmugam\",\r\n" + 
				"      \"nicNoOfPolicyHolder\": \"805684235v\",\r\n" + 
				"      \"nameOfLifeAssured\": \"Shanmugam Sarujan\",\r\n" + 
				"      \"nicNoOfLifeAssured\": \"N/A\",\r\n" + 
				"      \"ageAtNextBirthdayOflifeAssured\": \"9\",\r\n" + 
				"      \"dateOfBirth\": \"08-03-1980\",\r\n" + 
				"      \"term\": \"42\",\r\n" + 
				"      \"commencementDate\": \"12-07-2018\",\r\n" + 
				"      \"maturityDate\": \"12-07-2060\",\r\n" + 
				"      \"frequencyOfPremiumPayment\": \"M\",\r\n" + 
				"      \"lastPremiumDue\": \"12-06-2028\",\r\n" + 
				"      \"vestingDateCommencementDateOfLifeCover\": \"01-01-2028\",\r\n" + 
				"      \"dueDateOfFirstAnnuityInstallment\": \"01-01-2028\",\r\n" + 
				"      \"prmForBasicLifeCover\": 3576.6,\r\n" + 
				"      \"prmForAddiRiderBenefits\": 0,\r\n" + 
				"      \"basicSumAssured(Rs.)\": \"400000\",\r\n" + 
				"      \"nameOflifeAssured(BirthdayGift)\": \"Shanmugam Sarujan\",\r\n" + 
				"      \"digitalSignature\": \"GM\",\r\n" + 
				"      \"agentCode\": \"AGL000000032\",\r\n" + 
				"      \"branch\": \"BSL\",\r\n" + 
				"      \"policyRefNo\": \"JLP-10S-01-SEPT-2017\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"benefitWiseInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"relationship\": \"LIN\",\r\n" + 
				"      \"name\": \"Shanmugam Sarujan\",\r\n" + 
				"      \"benefitType\": \"BASIC\",\r\n" + 
				"      \"dateOfBirth\": \"01-01-2010\",\r\n" + 
				"      \"sumAssured(Rs.)\": \"400000\",\r\n" + 
				"      \"premium(Rs.)\": \"3576.6\",\r\n" + 
				"      \"expiryDate\": \"12-07-2060\",\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"nomineeDetailsInfo\": [],\r\n" + 
				"  \"clauseDetailsInfo\": [],\r\n" + 
				"  \"dTAQuoteRecordsInfo\": [],\r\n" + 
				"  \"receiptFees\": [\r\n" + 
				"    {\r\n" + 
				"      \"transType\": \"PEXPENSES\",\r\n" + 
				"      \"transCode\": \"13\",\r\n" + 
				"      \"foreignAmountPaid\": \"450\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"transType\": \"RENEWALFEE\",\r\n" + 
				"      \"transCode\": \"21\",\r\n" + 
				"      \"foreignAmountPaid\": \"60\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"transType\": \"CESS\",\r\n" + 
				"      \"transCode\": \"27\",\r\n" + 
				"      \"foreignAmountPaid\": \"7\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"message\": \"\",\r\n" + 
				"  \"status\": \"Success\"\r\n" + 
				"}";
		HtmltopdfNJEV.generatePDF(jsonObjectSring);
	}

}
