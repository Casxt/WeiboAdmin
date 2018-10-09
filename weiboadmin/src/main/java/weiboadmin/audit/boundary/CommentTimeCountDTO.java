/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.boundary;

import java.util.List;
import weiboadmin.BasicDTO;

/**
 *
 * @author Surface
 */
public class CommentTimeCountDTO extends BasicDTO {
    private List<CommentTimeCount> commentTimeCount;
    public CommentTimeCountDTO(String State, String Msg, List<CommentTimeCount> commentTimeCount) {
        super(State, Msg);
        this.commentTimeCount = commentTimeCount;
    }

    public CommentTimeCountDTO() {
    }

    public CommentTimeCountDTO(String State, String Msg) {
        super(State, Msg);
    }

    public List<CommentTimeCount> getCommentTimeCount() {
        return commentTimeCount;
    }

    public void setCommentTimeCount(List<CommentTimeCount> commentTimeCount) {
        this.commentTimeCount = commentTimeCount;
    }

}
