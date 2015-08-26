package cxf.headers.bridge;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;

import javax.xml.namespace.QName;

/**
 * @author fphilip@houseware.es
 */
public class Interceptor1 extends AbstractSoapInterceptor {

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        // Create Header object
        QName qnameCredentials = new QName("AuthenticationInfo");
        Header header = new Header(qnameCredentials, "pepe");
        message.getHeaders().add(header);

    }
}
