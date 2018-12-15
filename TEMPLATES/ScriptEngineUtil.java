
/*
ID: arjvik1
LANG: JAVA
TASK: ScriptEngineUtil
*/
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineUtil {
	public static void main(String[] args) throws IOException, ScriptException {
		System.out.println(execute("1+2"));
	}

	public static Object execute(String s) throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("JavaScript");
		return se.eval(s);
	}
}
