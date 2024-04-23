import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidator {
	public static void main(String[] args) {
		validateWithDTD(".\\bookstore.xml", ".\\bookstore.dtd");
		validateWithXSD(".\\bookstore.xml", ".\\bookstore.xsd");
	}

	private static void validateWithDTD(String xmlFile, String dtdFile) {
		try {
			InputStream xmlStream = new FileInputStream(new File(xmlFile));
			InputStream dtdStream = new FileInputStream(new File(dtdFile));
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new StreamSource(dtdStream));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlStream));
			System.out.println("Validation with DTD successful.");
		} catch (SAXException | IOException e) {
			System.out.println("Validation with DTD failed. Reason: " + e.getMessage());
		}
	}

	private static void validateWithXSD(String xmlFile, String xsdFile) {
    	try {
            InputStream xmlStream = new FileInputStream(new File(xmlFile));
            InputStream xsdStream = new FileInputStream(new File(xsdFile));
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(xsdStream));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlStream));
            System.out.println("Validation with XSD successful.");
    	} catch(SAXException | IOException e) {
    		System.out.println("Validation with XSD failed. Reason: " + e.getMessage());
    	}
	}
}

