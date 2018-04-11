package user.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Joins {
    private String userOpenId;
    private String activityUuid;
    private Date joinDate;
    private short valid;
}
