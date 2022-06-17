import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser; 

public class SmsService() {
    public List<T> sendSms(List<String, Object>) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();        
            factory.setNamespaceAware(true);             
            DocumentBuilder parser = factory.newDocumentBuilder();

            //request SOAP message DOMSource create
            String sendMessage = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<root> " +
                "     <body>" +
                "         <record>" +
                "               ... " +
                "           ... " +
                "           ... " +
                "        </record>" + 
                "    <body>" +
                "</root> "; 

            StringReader reader = new StringReader(sendMessage);
            InputSource is = new InputSource(reader);
            Document document = parser.parse(is);
            DOMSource requestSource = new DOMSource(document);

            //SOAPMessage create
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage requestSoapMessage = messageFactory.createMessage(); 
            SOAPPart requestSoapPart = requestSoapMessage.getSOAPPart(); 
            requestSoapPart.setContent(requestSource);

            //SOAPConnection create instance
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = scf.createConnection();

            //SOAP SEND MESSAGE
            SOAPMessage responseSoapMessage = connection.call(requestSoapMessage, "https://URL");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            responseSoapMessage.writeTo(out);

            SOAPBody soapBody = responseSoapMessage.getSOAPBody();  

            Iterator i = soapBody.getChildElements();  
            Node node = (Node) i.next();

            JSONParser jsonParser = new JSONParser();
            String soapErr = String.valueOf(jsonParser.parse(node.getChildNodes().item(0).getChildNodes().item(0).getNodeValue())); // xml response된 value

            log.debug(soapErr);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    

}
