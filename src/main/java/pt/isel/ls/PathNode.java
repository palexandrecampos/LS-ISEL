package pt.isel.ls;

import pt.isel.ls.commands.ICommand;

import java.util.HashMap;

/**
 * Created by palex on 30/03/2017.
 */
public class PathNode{


    private ICommand ICommand;
    private HashMap<String,PathNode> possiblePaths;
    private PathParameter pathParameter;

    PathNode(){

    }

    public PathNode getPath(String method){
        if(possiblePaths != null)
            return possiblePaths.get(method);
        return null;
    }

    public void addPath(String method, PathNode pathNode){
        if(possiblePaths == null)
            possiblePaths = new HashMap<>();
        possiblePaths.put(method,pathNode);
    }

    public ICommand getICommand() {
        return ICommand;
    }

    public void setICommand(ICommand ICommand) {
        this.ICommand = ICommand;
    }

    public HashMap<String, PathNode> getPossiblePaths() {
        return possiblePaths;
    }

    public void setPossiblePaths(HashMap<String, PathNode> possiblePaths) {
        this.possiblePaths = possiblePaths;
    }

    public PathParameter getPathParameter() {
        return pathParameter;
    }

    public void setPathParameter(PathParameter pathParameter) {
        this.pathParameter = pathParameter;
    }
}

