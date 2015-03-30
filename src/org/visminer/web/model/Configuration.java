package org.visminer.web.model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Configuration {
	private String remoteRepositoryGit;
	private String remoteRepositoryLogin;
	private String remoteRepositoryPassword;
	private String localRepositoryPath;
	private String localRepositoryName;
	private String localRepositoryOwner;
	private boolean createTableFlag;
	private String jdbc_user;
	private String jdbc_password;
	private String jdbc_driver;
	private String jdbc_url;
	private String jdbc_generation;
	private String jdbc_logging;
	private String configPath;
	
	
	public Configuration() {
		
	}
	
	public Configuration(File xmlFile) throws ParserConfigurationException, SAXException, IOException{
		this.readXmlFileToObject(xmlFile);
	}

	/**
	 * 
	 * @param localRepositoryPath
	 * @param localRepositoryName
	 * @param localRepositoryOwner
	 * @param createTableFlag
	 * @param jdbc_user
	 * @param jdbc_password
	 * @param remoteRepositoryLogin
	 * @param remoteRepositoryPassword
	 */	
	public Configuration(String remoteRepositoryGit, String remoteRepositoryLogin,
			String remoteRepositoryPassword, String localRepositoryPath,
			String localRepositoryName, String localRepositoryOwner,
			String jdbc_user, String jdbc_password) {
		this.remoteRepositoryGit = remoteRepositoryGit;
		this.remoteRepositoryLogin = remoteRepositoryLogin;
		this.remoteRepositoryPassword = remoteRepositoryPassword;
		this.localRepositoryPath = localRepositoryPath;
		this.localRepositoryName = localRepositoryName;
		this.localRepositoryOwner = localRepositoryOwner;
		this.createTableFlag = true;
		this.jdbc_user = jdbc_user;
		this.jdbc_password = jdbc_password;
	}

	/**
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @return create config.xml file
	 * 
	 */
	public void writeXmlFile() throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		//root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("configuration");
		doc.appendChild(rootElement);
		
		/**** Begin RemoteRepository node ****/
		Element remoteRepository = doc.createElement("remoteRepository");
		rootElement.appendChild(remoteRepository);
		
		//remoteRepositoryLogin element;
		Element eRRG = doc.createElement("git");
		eRRG.appendChild(doc.createTextNode(this.remoteRepositoryGit));
		remoteRepository.appendChild(eRRG);

		//remoteRepositoryLogin element;
		Element eRRL = doc.createElement("login");
		eRRL.appendChild(doc.createTextNode(this.remoteRepositoryLogin));
		remoteRepository.appendChild(eRRL);

		//remoteRepositoryPassword element;
		Element eRRP = doc.createElement("password");
		eRRP.appendChild(doc.createTextNode(this.remoteRepositoryPassword));
		remoteRepository.appendChild(eRRP);		
		/**** End RemoteRepository node ****/
		
		/**** Begin localRepository node ****/
		Element localRepository = doc.createElement("localRepository");
		rootElement.appendChild(localRepository);
		
		//localRepositoryPath element;
		Element eLRP = doc.createElement("path");
		eLRP.appendChild(doc.createTextNode(this.localRepositoryPath));
		localRepository.appendChild(eLRP);

		//localRepositoryName element;
		Element eLRN = doc.createElement("name");
		eLRN.appendChild(doc.createTextNode(this.localRepositoryName));
		localRepository.appendChild(eLRN);

		//localRepositoryOwner element;
		Element eLRO = doc.createElement("owner");
		eLRO.appendChild(doc.createTextNode(this.localRepositoryOwner));
		localRepository.appendChild(eLRO);

		//createTableFlag element;
		Element eCTF = doc.createElement("createTableFlag");
		eCTF.appendChild(doc.createTextNode(""+this.createTableFlag));
		localRepository.appendChild(eCTF);
		/**** End localRepository node ****/
		
		/**** Begin JDBC node ****/
		Element jdbc = doc.createElement("jdbc");
		rootElement.appendChild(jdbc);
		
		//jdbc_driver element;
		Element eJD = doc.createElement("driver");
		eJD.appendChild(doc.createTextNode(this.jdbc_driver));
		jdbc.appendChild(eJD);

		//jdbc_url element;
		Element eJR = doc.createElement("url");
		eJR.appendChild(doc.createTextNode(this.jdbc_url));
		jdbc.appendChild(eJR);
		
		//jdbc_user element;
		Element eJU = doc.createElement("user");
		eJU.appendChild(doc.createTextNode(this.jdbc_user));
		jdbc.appendChild(eJU);

		//jdbc_password element;
		Element eJP = doc.createElement("password");
		eJP.appendChild(doc.createTextNode(this.jdbc_password));
		jdbc.appendChild(eJP);
		
		//jdbc_generation element;
		Element eJG = doc.createElement("generation");
		eJG.appendChild(doc.createTextNode(this.jdbc_generation));
		jdbc.appendChild(eJG);

		//jdbc_password element;
		Element eJL = doc.createElement("logging");
		eJL.appendChild(doc.createTextNode(this.jdbc_logging));
		jdbc.appendChild(eJL);
		/**** End JDBC node ****/
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		doc.getDocumentElement().normalize();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("config.xml"));
		//Output to console for testing
		//StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
	}

	/**
	 * 
	 * @param XmlFile
	 * @return  
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document readXmlFile(File XmlFile) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(XmlFile);
		/*
		 * optional, but recommended
		 * read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		 */
		doc.getDocumentElement().normalize();
		return doc;
	}

	/**
	 * 
	 * @param XmlFile
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void readXmlFileToObject(File XmlFile) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(XmlFile);
		/*
		 * optional, but recommended
		 * read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		 */
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("remoteRepository");
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				this.remoteRepositoryLogin = eElement.getElementsByTagName("git").item(0).getTextContent();
				this.remoteRepositoryLogin = eElement.getElementsByTagName("login").item(0).getTextContent();
				this.remoteRepositoryPassword = eElement.getElementsByTagName("password").item(0).getTextContent();
			}
		}
		nList = doc.getElementsByTagName("localRepository");
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				this.localRepositoryPath = eElement.getElementsByTagName("path").item(0).getTextContent();
				this.localRepositoryName = eElement.getElementsByTagName("name").item(0).getTextContent();
				this.localRepositoryOwner = eElement.getElementsByTagName("owner").item(0).getTextContent();
				this.createTableFlag = Boolean.parseBoolean(eElement.getElementsByTagName("createTableFlag").item(0).getTextContent());
			}
		}
		nList = doc.getElementsByTagName("jdbc"); 
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				this.jdbc_driver = eElement.getElementsByTagName("driver").item(0).getTextContent();
				this.jdbc_url = eElement.getElementsByTagName("url").item(0).getTextContent();
				this.jdbc_user = eElement.getElementsByTagName("user").item(0).getTextContent();
				this.jdbc_password = eElement.getElementsByTagName("password").item(0).getTextContent();
				this.jdbc_generation = eElement.getElementsByTagName("generation").item(0).getTextContent();
				this.jdbc_logging = eElement.getElementsByTagName("logging").item(0).getTextContent();
				
			}
		}
		
	}
	
	public String docToString(Document doc){
		String ln = "\n", tab="\t";
		String str = doc.getDocumentElement().getNodeName()+ln;
		NodeList nList = doc.getElementsByTagName("remoteRepository");
		str += tab + nList.item(0).getNodeName(); 
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				str +=ln
					+ tab + tab + "Url git        : " + eElement.getElementsByTagName("git").item(0).getTextContent()
					+ ln
					+ tab + tab + "login          : " + eElement.getElementsByTagName("login").item(0).getTextContent()
					+ ln
					+ tab + tab + "password       : " + eElement.getElementsByTagName("password").item(0).getTextContent()
					+ ln;
			}
		}
		nList = doc.getElementsByTagName("localRepository");
		str += tab + nList.item(0).getNodeName(); 
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				str +=ln
					+ tab + tab + "path           : " + eElement.getElementsByTagName("path").item(0).getTextContent()
					+ ln
					+ tab + tab + "name           : " + eElement.getElementsByTagName("name").item(0).getTextContent()
				    + ln
					+ tab + tab + "owner          : " + eElement.getElementsByTagName("owner").item(0).getTextContent()
				    + ln
					+ tab + tab + "createTableFlag: " + eElement.getElementsByTagName("createTableFlag").item(0).getTextContent()
					+ ln;
			}
		}
		nList = doc.getElementsByTagName("jdbc");
		str += tab + nList.item(0).getNodeName(); 
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				str +=ln
					+ tab + tab + "driver           : " + eElement.getElementsByTagName("driver").item(0).getTextContent() 
				    + ln
				    + tab + tab + "url           : " + eElement.getElementsByTagName("url").item(0).getTextContent() 
				    + ln
				    + tab + tab + "user           : " + eElement.getElementsByTagName("user").item(0).getTextContent() 
				    + ln
				    + tab + tab + "password           : " + eElement.getElementsByTagName("password").item(0).getTextContent() 
				    + ln
					+ tab + tab + "generation           : " + eElement.getElementsByTagName("generation").item(0).getTextContent() 
				    + ln
					+ tab + tab + "logging       : " + eElement.getElementsByTagName("logging").item(0).getTextContent()
					+ ln; 
			}
		}
		return str;
	}
	
	public String updateConfig(String jdbc_driver, String jdbc_url, String jdbc_user, String jdbc_password, String jdbc_generation, String jdbc_logging) throws ConfigurationException{
		
		//Path to config.properties file
		String path;
		
		PropertiesConfiguration config = new PropertiesConfiguration(this.configPath);
		
		try{
			//InputStream configStream = getClass().getResourceAsStream(this.configPath);
			
			
			config.setProperty("jdbc.driver",jdbc_driver);
			config.setProperty("jdbc.url", jdbc_url);
			config.setProperty("jdbc.user", jdbc_user);
			config.setProperty("jdbc.password", jdbc_password);
			config.setProperty("jdbc.generation.strategy", jdbc_generation);
			config.setProperty("jdbc.logging",jdbc_logging);
			config.save();
			
			
			
		} catch (org.apache.commons.configuration.ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		path = config.getPath();
		
		return path;
	}
	
	// SET's and GET's 
	
	public String getLocalRepositoryPath() {
		return localRepositoryPath;
	}

	public String getRemoteRepositoryGit() {
		return remoteRepositoryGit;
	}

	public void setRemoteRepositoryGit(String remoteRepositoryGit) {
		this.remoteRepositoryGit = remoteRepositoryGit;
	}

	public String getRemoteRepositoryLogin() {
		return remoteRepositoryLogin;
	}

	public void setRemoteRepositoryLogin(String remoteRepositoryLogin) {
		this.remoteRepositoryLogin = remoteRepositoryLogin;
	}

	public String getRemoteRepositoryPassword() {
		return remoteRepositoryPassword;
	}

	public void setRemoteRepositoryPassword(String remoteRepositoryPassword) {
		this.remoteRepositoryPassword = remoteRepositoryPassword;
	}

	public void setLocalRepositoryPath(String localRepositoryPath) {
		this.localRepositoryPath = localRepositoryPath;
	}

	public String getLocalRepositoryName() {
		return localRepositoryName;
	}

	public void setLocalRepositoryName(String localRepositoryName) {
		this.localRepositoryName = localRepositoryName;
	}

	public String getLocalRepositoryOwner() {
		return localRepositoryOwner;
	}

	public void setLocalRepositoryOwner(String localRepositoryOwner) {
		this.localRepositoryOwner = localRepositoryOwner;
	}

	public boolean isCreateTableFlag() {
		return createTableFlag;
	}

	public void setCreateTableFlag(boolean createTableFlag) {
		this.createTableFlag = createTableFlag;
	}
	
	public String getJdbc_driver() {
		return jdbc_driver;
	}

	public void setJdbc_driver(String jdbc_driver) {
		this.jdbc_driver = jdbc_driver;
	}

	public String getJdbc_url() {
		return jdbc_url;
	}

	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}
	
	public String getJdbc_user() {
		return jdbc_user;
	}

	public void setJdbc_user(String jdbc_user) {
		this.jdbc_user = jdbc_user;
	}

	public String getJdbc_password() {
		return jdbc_password;
	}

	public void setJdbc_password(String jdbc_password) {
		this.jdbc_password = jdbc_password;
	}
	
	public String getJdbc_generation() {
		return jdbc_generation;
	}

	public void setJdbc_generation(String jdbc_generation) {
		this.jdbc_generation = jdbc_generation;
	}

	public String getJdbc_logging() {
		return jdbc_logging;
	}

	public void setJdbc_logging(String jdbc_logging) {
		this.jdbc_logging = jdbc_logging;
	}
	
	public String getConfigPath() {
		return configPath;
	}
	
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	
	@Override
	public String toString() {
		return "Configuration [remoteRepositoryGit=" + remoteRepositoryGit
				+ ", remoteRepositoryLogin=" + remoteRepositoryLogin
				+ ", remoteRepositoryPassword=" + remoteRepositoryPassword
				+ ", localRepositoryPath=" + localRepositoryPath
				+ ", localRepositoryName=" + localRepositoryName
				+ ", localRepositoryOwner=" + localRepositoryOwner
				+ ", createTableFlag=" + createTableFlag + ", jdbc_driver="
				+ jdbc_driver + ", jdbc_url=" + jdbc_url +  ", jdbc_user="
				+ jdbc_user + ", jdbc_password=" + jdbc_password +  ", jdbc_generation="
				+ jdbc_generation + ", jdbc_logging=" + jdbc_logging + "]";
	}
	
}
