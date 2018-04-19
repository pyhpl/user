package org.ljl.look.user.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstConfig {
    public static final String DEFAULT_VISITOR_OPEN_ID = "os0pV4xLjlAf20xsQkycdWk-vVe0";
    public static final String DEFAULT_VISITOR_TOKEN = "c13eb7a0-21ab-4e74-b121-970c030f7071";
    public static final short VALID = (short) 1;
    public static final short UNVALID = (short) 0;
    public static final String NONE_USER = "os0pV4xNonEf20xsQkycdWk-vVe0";
    public static final String SINGLE_DISCUSSION = "e2444628-5bea-4944-9867-bf92d6a90b36";

    /** page info */
    public static final String PAGE_INFO_JSON_STR = "pageInfoJsonStr";
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String DEFAULT_PAGE_NUM = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";

    /** rabbit mq */
    public static final String QUEUE_TOPIC_FOCUS = "queueTopicFocus";
}
