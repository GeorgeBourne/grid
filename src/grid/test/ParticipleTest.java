package grid.test;

import grid.text.participle.MechanicalParticiple;

import java.util.Vector;

/**
 * ParticipleTest.java 2014-3-21 17:29:15
 * 
 * @Author George Bourne
 */
public class ParticipleTest {

	private static String document = "ÄãµÄ²âÊÔÎÄµµ";

	public static void main(String args[]) {
		MechanicalParticiple participle = new MechanicalParticiple();
		Vector<String> vec = participle.partition(document);
		System.out.println(vec);
	}
}
