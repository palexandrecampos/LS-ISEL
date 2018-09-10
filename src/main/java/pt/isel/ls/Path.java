package pt.isel.ls;

import pt.isel.ls.commands.ICommand;

import java.io.UnsupportedEncodingException;


public class Path {
    PathNode paths;

    public Path() {
        paths = new PathNode();
    }

    public void addPath(String method, String path, ICommand ICommand) {
        PathNode pathNode = paths.getPath(method);
        if (pathNode == null) {
            pathNode = new PathNode();
            paths.addPath(method, pathNode);
        }

        String[] pathParts = path.split("/");
        for (int i = 0; i < pathParts.length; i++) {
            if (pathParts[i].isEmpty()) continue;
            if (pathParts[i].startsWith("{") && pathParts[i].endsWith("}"))
                pathNode = addParameter(pathParts[i], pathNode);
            else
                pathNode = addNode(pathParts[i], pathNode);
        }

        pathNode.setICommand(ICommand);
    }

    public ICommand getCommand(Request request) throws UnsupportedEncodingException {
        PathNode node = paths.getPath(request.getMethod());
        if (node == null) {
            throw new UnsupportedOperationException("Method not found");
        }
        for (String name : request.getPath().split("/")) {
            if (name.isEmpty()) continue;
            PathNode auxNode = node.getPath(name);
            if (auxNode == null) {
                if (node.getPathParameter() == null) {
                    throw new UnsupportedOperationException("Path not found");
                }
                request.getParametersMap().put(node.getPathParameter().getParameterName(), name);
                node = node.getPathParameter().pathNode;
            } else {
                node = auxNode;
            }
        }
        return node.getICommand();
    }

    public PathNode addNode(String label, PathNode node) {
        PathNode auxNode = node.getPath(label);
        if (auxNode == null) {
            auxNode = new PathNode();
            node.addPath(label, auxNode);
        }
        return auxNode;
    }


    public PathNode addParameter(String label, PathNode node) {
        PathNode auxNode;
        if (node.getPathParameter() == null) {
            auxNode = new PathNode();
            node.setPathParameter(new PathParameter(label, auxNode));
        } else {
            auxNode = node.getPathParameter().pathNode;
        }
        return auxNode;
    }
}