package org.ljl.look.user.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private String uuid;
    private String userOpenId;
    private String name;
    private Date createDate;
    private short valid;
}
