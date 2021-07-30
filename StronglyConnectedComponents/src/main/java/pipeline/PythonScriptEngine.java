package pipeline;

import org.python.util.PythonInterpreter;

import java.io.File;
import java.io.StringWriter;

public class PythonScriptEngine {

    public void givenPythonScriptEngineIsAvailable_whenScriptInvoked_thenOutputDisplayed() throws Exception {

        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

//            String filepath = resolvePythonScriptPath("SCC.txt");
            String filepath = "src/main/resources/SCC.txt";

            pyInterp.exec(
                    "filename = \""+filepath+"\"\n" +
                    "data_list = []\n" +
                    "\n" +
                    "with open(filename, 'r') as fobj:\n" +
                    "    for line in fobj:\n" +
                    "        numbers = [int(num) for num in line.split()]\n" +
                    "        data = {\n" +
                    "            \"source\": numbers[0],\n" +
                    "            \"sink\": numbers[1]\n" +
                    "        }\n" +
                    "        data_list.append(data)\n" +
                    "\n" +
                    "print(data_list)");
            System.out.println(output.toString().trim());
        }
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        System.out.println(file.getAbsolutePath());
        return file.getAbsolutePath();
    }

    public static void main(String[] args) throws Exception {

        PythonScriptEngine pythonScriptEngine = new PythonScriptEngine();
        pythonScriptEngine.givenPythonScriptEngineIsAvailable_whenScriptInvoked_thenOutputDisplayed();

    }

}
