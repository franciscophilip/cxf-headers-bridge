package cxf.headers.bridge;

import org.apache.cxf.headers.Header;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.w3c.dom.Element;

import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.util.List;

@WebService(endpointInterface = "cxf.headers.bridge.WebService2")
public class WebService2Impl implements WebService2 {

    WebServiceContext webServiceContext;

    @Override
    public Person validate(Person person) {
        Message m = PhaseInterceptorChain.getCurrentMessage();


        if (person.getAge() < 18) {
            throw new RuntimeException("too young");
        }

        person.setDescription(getHeader2());
        return person;
    }

    public String getHeader2() {
        List<Header> headersList = (List<Header>) webServiceContext.getMessageContext().get(Header.HEADER_LIST);

        return ((Element)headersList.get(0).getObject()).getFirstChild().getNodeValue();
    }

    public void setWebServiceContext(WebServiceContext webServiceContext) {
        this.webServiceContext = webServiceContext;
    }
}