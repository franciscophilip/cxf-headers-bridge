package cxf.headers.bridge;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class HeaderClient {


    public HeaderClient() throws JAXBException {

        ClassPathXmlApplicationContext classPathXmlAppContext = new ClassPathXmlApplicationContext("classpath:META-INF/beans.xml");
        classPathXmlAppContext.start();

        WebService1 webService1 = (WebService1) classPathXmlAppContext.getBean("client");
        Header testSoapHeader1 = new Header(new QName("uri:cxf.ws.sample", "header1"), "Very nice person!", new JAXBDataBinding(String.class));

        List<Header> headersList = new ArrayList<>();
        headersList.add(testSoapHeader1);
        Client proxy = ClientProxy.getClient(webService1);
        proxy.getRequestContext().put(Header.HEADER_LIST, headersList);

        Person p = webService1.find(18, "Francisco");
        System.out.println(p.getName());
        System.out.println(p.getDescription());
        System.out.println(p.getAge());

    }

    public static void main(String[] args) throws JAXBException {
        new HeaderClient();
    }
}
