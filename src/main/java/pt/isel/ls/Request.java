package pt.isel.ls;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by palex on 25/03/2017.
 */
public class Request {

    private String method;
    private String path;
    private String headers;
    private String parameters;


    private Map<String, String> parametersMap;

    private Map<String, String> headersMap;

    public Request(String method, String path, String headers, String parameters) {
        this.method = method.toUpperCase();
        this.path = path;
        this.headers = headers;
        this.parameters = parameters;
    }

    public static Request create(String[] request) {
        if (request.length < 2) {
            throw new IllegalArgumentException("Bad request");
        }
        if (request.length == 2) {
            return new Request(request[0], request[1], null, null);
        }
        if (request.length == 3) {
            if (request[2].contains(":")) {
                return new Request(request[0], request[1], request[2], null);
            }
            return new Request(request[0], request[1], null, request[2]);
        }
        return new Request(request[0], request[1], request[2], request[3]);
    }

    public Map<String, String> getParametersMap() throws UnsupportedEncodingException {
        if (parametersMap == null) {
            parametersMap = splitParametersOrHeaders(parameters, "&", "=");
        }
        return parametersMap;
    }

    public String getHeader(String key) throws UnsupportedEncodingException {
        return getHeaderOrDefault(key, null);
    }


    public String getHeaderOrDefault(String key, String defaultValue) throws UnsupportedEncodingException {
        if (headersMap == null) {
            headersMap = splitParametersOrHeaders(headers, "\\|", ":");
        }
        return headersMap.getOrDefault(key, defaultValue);
    }

    public Stack<String> createComplexParameterValueList(String parameterName){
        String[] refinedParameters = parameters.split("&");

        Stack<String> parameterValues = new Stack<>();

        for(int i = 0; i < refinedParameters.length; ++i) {
            String[] refinedParameter = refinedParameters[i].split("=");
            if (refinedParameter[0].equals(parameterName)) {
                parameterValues.push(refinedParameter[1]);
            }
        }
        return parameterValues;
    }

    public String getParameterOrDefault(String key, String defaultValue) throws UnsupportedEncodingException {
        if (parametersMap == null) {
            parametersMap = splitParametersOrHeaders(parameters, "&", "=");
        }
        return parametersMap.getOrDefault(key, defaultValue);
    }



    public Map<String, String> splitParametersOrHeaders(String toSplit, String charSeparator, String valueSeparator)
            throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        if (toSplit != null && !toSplit.isEmpty()) {
            String[] splittedByCharSeparator = toSplit.split(charSeparator);
            for (String splitted : splittedByCharSeparator) {
                String[] splittedByKeySeparator = splitted.split(valueSeparator);
                map.put(splittedByKeySeparator[0], splittedByKeySeparator[1].replaceAll(Pattern.quote("+"), " "));
            }
        }
        return map;
    }

    public String get(String key) throws UnsupportedEncodingException {
        if (parametersMap == null) {
            getParametersMap();
        }
        return parametersMap.get(key);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParameters() {
        return parameters;
    }


    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }
}