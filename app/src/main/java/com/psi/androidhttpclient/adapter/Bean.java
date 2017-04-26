package com.psi.androidhttpclient.adapter;

import java.util.List;

/**
 * Created by Administrator on 2017-04-05.
 */
public class Bean {

  /**
   * goods : [{"id":"17","title":"巧克力","price":"10.00","goodnums":"5"},{"id":"18","title":"青岛9度","price":"4.00","goodnums":"10"}]
   * comments : [{"id":"3","avatar":"http://static.mamtree.com/avatar/default.png","nickname":"小企鹅","score":"4","dateline":"2017-04-01","content":"这个还不错呢","rcontent":"123按时大多数是","images":[]}]
   * plus : 10
   */

  private int plus;
  private List<GoodsBean> goods;
  private List<CommentsBean> comments;

  public int getPlus() {
    return plus;
  }

  public void setPlus(int plus) {
    this.plus = plus;
  }

  public List<GoodsBean> getGoods() {
    return goods;
  }

  public void setGoods(List<GoodsBean> goods) {
    this.goods = goods;
  }

  public List<CommentsBean> getComments() {
    return comments;
  }

  public void setComments(List<CommentsBean> comments) {
    this.comments = comments;
  }

  public static class GoodsBean {
    /**
     * id : 17
     * title : 巧克力
     * price : 10.00
     * goodnums : 5
     */

    private String id;
    private String title;
    private String price;
    private String goodnums;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getPrice() {
      return price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    public String getGoodnums() {
      return goodnums;
    }

    public void setGoodnums(String goodnums) {
      this.goodnums = goodnums;
    }
  }

  public static class CommentsBean {
    /**
     * id : 3
     * avatar : http://static.mamtree.com/avatar/default.png
     * nickname : 小企鹅
     * score : 4
     * dateline : 2017-04-01
     * content : 这个还不错呢
     * rcontent : 123按时大多数是
     * images : []
     */

    private String id;
    private String avatar;
    private String nickname;
    private String score;
    private String dateline;
    private String content;
    private String rcontent;
    private List<?> images;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getAvatar() {
      return avatar;
    }

    public void setAvatar(String avatar) {
      this.avatar = avatar;
    }

    public String getNickname() {
      return nickname;
    }

    public void setNickname(String nickname) {
      this.nickname = nickname;
    }

    public String getScore() {
      return score;
    }

    public void setScore(String score) {
      this.score = score;
    }

    public String getDateline() {
      return dateline;
    }

    public void setDateline(String dateline) {
      this.dateline = dateline;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getRcontent() {
      return rcontent;
    }

    public void setRcontent(String rcontent) {
      this.rcontent = rcontent;
    }

    public List<?> getImages() {
      return images;
    }

    public void setImages(List<?> images) {
      this.images = images;
    }
  }
}
