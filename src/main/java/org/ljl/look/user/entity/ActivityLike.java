package org.ljl.look.user.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLike {
    private String uuid;
    private String fromUser;
    private String activityUuid;
    private short valid;
}
