import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String method;
        String uri;
        String model;
        String name;
        String returnType;
        String[] parameters = null;
        List<String> pathVariables = null;

        System.out.print("HTTP Method: ");
        method = scanner.next().toLowerCase();

        System.out.print("URI: ");
        uri = scanner.next();

        System.out.print("Model: ");
        model = scanner.next();

        System.out.print("Name: ");
        name = scanner.next();

        System.out.print("Return: ");
        returnType = scanner.next();

        if (method.equals("get")) {

            parameters = getParameters(uri);
            pathVariables = getPathVariables(uri);

        }

        createRestSignature(method, uri, model, name, returnType, parameters, pathVariables);
    }

    public static void createRestSignature(
            String method,
            String uri,
            String model,
            String name,
            String returnType,
            String[] parameters,
            List<String> pathVariables
    ) {
        Iterator<String> iterator = null;
        method = method.substring(0,1).toUpperCase() + method.substring(1);
        model = model.substring(0,1).toUpperCase() + model.substring(1);

        if (parameters != null && parameters.length != 0 ) {
            iterator = Arrays.stream(parameters).iterator();
        }

        System.out.println("\n Function Signature");
        System.out.println("-----------------------------\n");
        System.out.println("@" + method + "Mapping(" + "\"" + uri + "\")");

        if (method.equals("Get")) {

            if (pathVariables.size() != 0 && parameters.length != 0) {

                System.out.print("public " + returnType + " " + name + "(@PathVariable " + pathVariables.get(0) + ", ");
                while (iterator.hasNext()) {
                    var s = iterator.next();

                    if(iterator.hasNext()) {

                        System.out.print("@RequestParam int " + s + ", ");

                    } else {

                        System.out.print("@RequestParam int " + s);

                    }

                }
                System.out.print(") {");

            } else if (pathVariables.size() != 0) {

                System.out.println("public " + returnType + " " + name + "(@" + pathVariables + " ");
                System.out.print(") {");

            } else if (parameters.length != 0) {

                System.out.print("public " + returnType + " " + name + "(" );
                while (iterator.hasNext()) {
                    var s = iterator.next();

                    if(iterator.hasNext()) {

                        System.out.print("@RequestParam int " + s + ", ");

                    } else {

                        System.out.print("@RequestParam int " + s);

                    }

                }
                System.out.print(") {");
            }

        } else if (method.equals("Post")) {

                System.out.println("public " + returnType + " " + name + "(@RequestBody " + model + " " + returnType.toLowerCase() + ") {");

        }

    }

    public static String[] getParameters(String uri) {

        String[] queryString = uri.split("\\?")[1].split("=|&");
        return new HashSet<String>(Arrays.asList(queryString)).toArray(new String[0]);

    }

    public static List<String> getPathVariables(String uri) {

        var regex = "\\{(.*?)\\}";
        List<String> allMatches = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(uri);

        while(matcher.find()) {

            allMatches.add(matcher.group(1));

        }

        return allMatches;

    }

}
