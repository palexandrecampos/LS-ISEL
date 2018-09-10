package pt.isel.ls;

/**
 * Created by palex on 30/03/2017.
 */
public class PathParameter{
    String label;
    PathNode pathNode;

    public PathParameter(String label, PathNode pathNode){
        this.label = label;
        this.pathNode = pathNode;
    }

    public String getParameterName(){
        return label.substring(1, label.length()-1);
    }
}
