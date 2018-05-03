package org.ljl.look.user.feign;


import org.ljl.look.user.entity.Activity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient("activity")
public interface ActivityServiceFeign {

    // ************************ /api/activity ************************ //

    @GetMapping("/api/activity")
    Activity getActivity(@RequestParam("uuid") String uuid);

}
