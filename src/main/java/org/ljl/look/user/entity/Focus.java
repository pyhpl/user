package org.ljl.look.user.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Focus {
    private String uuid;
    private String fromUser;
    private String activityUuid;
    private Date focusDate;
    private short valid;
}
