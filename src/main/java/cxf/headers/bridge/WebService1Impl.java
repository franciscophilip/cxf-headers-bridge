package cxf.headers.bridge;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import java.util.List;

@WebService(endpointInterface = "cxf.headers.bridge.WebService1", name = "service1")
public class WebService1Impl implements WebService1 {

    WebServiceContext webServiceContext;
    WebService2 webService2Client;

    @Override
    public Person find(Integer age, String name) {
        Person a = new Person();
        a.setAge(age);
        a.setName(name);
        return getClient().validate(a);
    }

    WebService2 getClient() {
        try {
            List<Header> headersList = (List<Header>) webServiceContext.getMessageContext().get(Header.HEADER_LIST);
            Header testSoapHeader2 = new Header(new QName("uri:cxf.ws.sample", "header2"), "Very nice person!", new JAXBDataBinding(String.class));
            headersList.add(testSoapHeader2);
            Client proxy = ClientProxy.getClient(webService2Client);
            proxy.getRequestContext().put(Header.HEADER_LIST, headersList);
            return webService2Client;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


    public void setWebService2Client(WebService2 webService2Client) {
        this.webService2Client = webService2Client;
    }

    public void setWebServiceContext(WebServiceContext webServiceContext) {
        this.webServiceContext = webServiceContext;
    }
}