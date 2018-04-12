package org.ljl.look.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Joins {
    private String userOpenId;
    private String activityUuid;
    private Date joinDate;
    private short valid;
}
