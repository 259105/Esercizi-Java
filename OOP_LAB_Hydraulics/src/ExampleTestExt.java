import hydraulic.*;

import static it.polito.po.test.OOPAssertions.assertInOrder;
import static it.polito.po.test.OOPAssertions.assertSameIndent;
import static org.junit.Assert.*;
import org.junit.Test;

public class ExampleTestExt {
	
	@Test
	public void testAll(){
		
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");		
		Multisplit ms = new Multisplit("MS",3);		
		Sink s1 = new Sink("S1");		
		Sink s2 = new Sink("S2");		
		Sink s3 = new Sink("S3");		
		s.addElement(src);
		s.addElement(ms);
		s.addElement(s1);
		s.addElement(s2);
		s.addElement(s3);
		
		src.connect(ms);
		ms.connect(s1,0);
		ms.connect(s3,1);
		ms.connect(s2,2);
		
		String layout = s.layout();
		System.out.println(layout);
		
		assertInOrder(layout,"Src","MS","S1","S2","S3");
	
//		assertContainTimes("Not enoug multisplit branches", 
//							3, "+->", layout );
		
		assertSameIndent(layout, "S1","S2","S3");
	}
}
