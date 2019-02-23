package com.app.webdriver.common.core.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.app.webdriver.common.core.TestContext;
import com.app.webdriver.common.core.annotations.InBrowser;
import com.app.webdriver.common.core.exceptions.TestEnvInitFailedException;


public class Configuration {
	public static final String DEFAULT_LANGUAGE = "en";
	  private static final String CONFIG_FILE_NAME = "config.xml";
	  private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());
	  private static final String SELENIUM_CONFIG_REPO_CONFIG_FILE_NAME = "config.xml";
	  private static Map<String, String> defaultConfig;
	  private static Map<String, String> testConfig = new HashMap<String, String>();

	  public Configuration() {}

	  private static Map<String, String> readConfiguration() {
	    if (defaultConfig == null) {
	    	try {
	            File inputFile = new File("config.xml");
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(inputFile);
	            doc.getDocumentElement().normalize();
	            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	            NodeList nList = doc.getElementsByTagName("Variable");
	            System.out.println("----------------------------");
	            
	            for (int temp = 0; temp < nList.getLength(); temp++) {
	               Node nNode = nList.item(temp);
	               System.out.println("\nCurrent Element :" + nNode.getNodeName());
	               
	               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                  Element eElement = (Element) nNode;
	                  defaultConfig.put(eElement.getElementsByTagName("Name").item(0).getTextContent(), eElement.getElementsByTagName("Value").item(0).getTextContent());
	               }
	            }
	         } catch (Exception e) {
	            e.printStackTrace();
	         }

	    }
		return defaultConfig;
	  }
	  

	  public static String getPropertyFromFile(String propertyName) {
		 
		  StringBuilder sb = null ;
		  try {
		  BufferedReader br = new BufferedReader(new FileReader(new File("/Users/skathi/git/Framework/Framework/src/test/resources/config.xml")));
		  String line;
		  sb = new StringBuilder();

		  
			while((line=br.readLine())!= null){
			      sb.append(line.trim());
			  }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(sb);
		  //clear all comments
		  Pattern pattern = Pattern.compile("<!--.+?(?=-->)-->",Pattern.DOTALL);
		    Matcher matcher = pattern.matcher(sb);
		    while (matcher.find()) {
		        sb.replace(matcher.start(), matcher.end(), "");
		        matcher = pattern.matcher(sb);
		    }

		    System.out.println(sb);
	      Pattern p = Pattern.compile("<Name>"+propertyName+"</Name><Value>([^<]*)</Value>",Pattern.DOTALL);
	      Matcher m = p.matcher(sb);
		  if (m.find())
		  {
			  return m.group(1).trim();
			  
		  }
		return null;
		  	
	  }

	  private static String getProp(String propertyName) {
	    if (testConfig.get(propertyName) == null) {
	      return System.getProperty(propertyName) != null ? System.getProperty(propertyName)
	                                                      : getPropertyFromFile(propertyName);
	    } else {
	      return testConfig.get(propertyName);
	    }
	  }

	  public static String getBrowser() {
	    return getProp("browser");
	  }

	  public static String getDpr() {
	    return getProp("dpr");
	  }

	  public static String getDefaultWikiName() {
	    return getPropertyFromFile("wikiName");
	  }

	  public static String getEnv() {
	    return getProp("env");
	  }

	  public static String getWikiName() {
	    return getProp("wikiName");
	  }

	  public static String getWikiLanguage() {
	    return getProp("language");
	  }

	  public static String getPlatform() {
	    return getProp("platform");
	  }

	  public static String getMobileWikiVersion() { return getProp("mobileWikiVersion"); }

	  public static String getCredentialsFilePath() {
	    return Paths.get(getProp("seleniumConfigPath"), SELENIUM_CONFIG_REPO_CONFIG_FILE_NAME)
	        .toString();
	  }

	  public static String getQS() {
	    return getProp("qs");
	  }

	  public static String getAppiumIp() {
	    return getProp("appiumIp");
	  }

	  public static String getDeviceName() {
	    return getProp("deviceName");
	  }

	  public static String getDisableFlash() {
	    return getProp("disableFlash");
	  }

	  public static String getJSErrorsEnabled() {
	    return getProp("jsErrorsEnabled");
	  }

	  public static String getLogEnabled() {
	    return getProp("logEnabled");
	  }

	  public static Boolean getAdsData() { return "true".equals(getProp("adsData")); }

	  public static String getMockAds() {
	    return getProp("mockAds");
	  }

	  public static Boolean getForceHttps() {
	    return "true".equalsIgnoreCase(getProp("forceHttps"));
	  }

	  public static Boolean isUnsafePageLoad() {
	    return "true".equalsIgnoreCase(getProp("unsafePageLoad"));
	  }

	  public static Boolean getForceLanguageInPath() {
	    return "true".equalsIgnoreCase(getProp("forceLanguageInPath"));
	  }

	  public static Boolean getForceFandomDomain() {
	    return "true".equalsIgnoreCase(getProp("forceFandomDomain"));
	  }

	  

	  public static String useMITM() {
	    if (getForceHttps()) {
	      return "true";
	    }

	    return getProp("useMITM");
	  }

	  public static String useZap() {
	    return getProp("useZapProxy");
	  }

	  public static String getPageLoadStrategy() {
	    return getProp("unstablePageLoadStrategy");
	  }

	 

	  public static void setTestValue(String key, String value) {
	    testConfig.put(key, value);
	  }

	  public static void clearCustomTestProperties() {
	    testConfig.clear();
	  }

	  public static String getCountryCode() {
	    return getProp("countryCode");
	  }

	  public static boolean useProxy() {
	    return Boolean.valueOf(getProp("useProxy")) || StringUtils.isNotBlank(getCountryCode())
	           || Boolean.valueOf(getProp("useZapProxy"));
	  }

	  /**
	   * @return null if window is supposed to be maximised, Dimension if any other size is demanded
	   */
	  public static Dimension getBrowserSize() {
	    String size = getProp("browserSize");

	    if (StringUtils.isNotBlank(size) || "maximised".equals(size) || size.split("x").length == 2) {
	      if ("maximised".equals(size)) {
	        return null;
	      } else {
	        return new Dimension(Integer.valueOf(size.split("x")[0]),
	                             Integer.valueOf(size.split("x")[1])
	        );
	      }
	    } else {
	      throw new WebDriverException("browser size: " + size + " is not a proper value");
	    }
	  }

	  public static String[] getExtensions() {
	    String exts = getProp("extensions");

	    if (StringUtils.isEmpty(exts)) {
	      return new String[]{};
	    }

	    ArrayList<String> res = new ArrayList<>();
	    for (String ext : exts.replace("[", "").replace("]", "").split(",")) {
	      res.add(ext.trim());
	    }

	    return res.toArray(new String[res.size()]);
	  }

	  public static String getDisableCommunityPageSalesPitchDialog() {
	    return getProp("disableCommunityPageSalesPitchDialog");
	  }

	 
	}
