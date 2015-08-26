package cxf.headers.bridge;

import javax.jws.WebService;

@WebService
public interface WebService2 {
	 Person validate(Person person);
}
