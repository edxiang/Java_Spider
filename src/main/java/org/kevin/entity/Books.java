package org.kevin.entity;

import lombok.Data;

/**
 * Created by Kevin.Z on 2018/1/19.
 */
@Data
public class Books {
    private long id;
    private String name;
    private String author;
    private String translator;
    private String publisher;
    private String publishDate;
    private int pageNumber;
    private double price;
    private String isbn;
    private double star;
    private int starMan;
    private String content;
    private int commentNumber;
}
