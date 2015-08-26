package cxf.headers.bridge;

import javax.jws.WebService;

@WebService
public interface WebService1 {
	 Person find(Integer age, String name);
}
