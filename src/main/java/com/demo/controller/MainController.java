package com.demo.controller;

import com.demo.myannotation.EncryptResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/hello")
public class MainController {

    @EncryptResponse
    @RequestMapping("/hello")
    public String responseAnnotation(){
        //String jsonStr = "{\"action\":\"users_oauth\",\"api_code\":200,\"api_msg\":null,\"is_reg\":\"0\",\"userId\":29916,\"nickName\":\"史佳男\",\"usernumber\":\"99999999\",\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlci1jbGllbnQiXSwiand0LWV4dCI6IkpXVCDmianlsZXkv6Hmga8iLCJzY29wZSI6WyJzZWxlY3QiXSwiZXhwIjoxNTg4MTg5MTYzLCJhdXRob3JpdGllcyI6WyJvYXV0aDIiXSwianRpIjoiMjczODQ4M2EtN2UwMy00MTNmLWE0NjctYjFlYjE0ZTYzYjM3IiwiY2xpZW50X2lkIjoiY2xpZW50MSJ9.uQJArgaElyHJaVbXKQ2aIgzJ4K1I93IVllWlS2hgcUo\",\"user\":{\"anchor_rank_id\":\"1\",\"avatar\":\"\",\"balance\":\"0\",\"balance2\":null,\"gamemoney\":\"0\",\"birthday\":\"\",\"age\":\"\",\"constellation\":\"\",\"devices\":[],\"emotion\":\"\",\"exp\":0,\"exp_show\":0,\"gender\":\"1\",\"haoma\":\"1\",\"hometown_city\":\"\",\"hometown_province\":\"\",\"id\":\"29916\",\"im_sig\":\"\",\"im_uid\":\"\",\"is_follow\":\"0\",\"job\":\"\",\"live_banner\":\"/apis/avatar.php?uid=29916\",\"nickname\":\"史佳男\",\"oauths\":[],\"rank_id\":\"1\",\"shower_rank_id\":\"1\",\"reg_time\":\"-998705\",\"summary\":null,\"switchs\":{\"push_on\":\"1\"},\"ticket\":\"0\",\"total_send_gift\":\"0\",\"total_ticket\":\"0\",\"unique_id\":\"1\",\"viplevel\":\"0\",\"vip_util\":null,\"update_avatar_time\":\"0\",\"is_no_play\":\"0\",\"isblock\":\"0\",\"guizhu\":\"0\",\"guizhu_vailddate\":\"0\",\"yinshen\":\"null\",\"onetoone_open\":\"0\",\"onetoone_money\":\"0\",\"onetoone_voice_money\":\"0\",\"shengao\":\"\",\"character\":\"\",\"hobby\":\"\",\"physique\":null,\"role\":null,\"tizhong\":\"\",\"like_physique\":null,\"like_character\":null,\"want_to\":null,\"onlinetime\":\"-991936\",\"shoufei_type\":null,\"shoufei_price\":null,\"shoufei_time_price\":null,\"radio_intro\":\"\",\"radio_time\":\"0\",\"video_name\":\"\",\"video_cover\":\"\",\"hello_text\":\"\",\"oto_status\":\"0\",\"photo_num\":0,\"video_num\":0,\"onetoone_success_percent\":\"100%\",\"video_address_suffix\":\"\",\"push_video_add\":\"rtmp://rtmppush.34541.cn/live/1?vhost=httppull.34541.cn\",\"push_video_add2\":\"rtmp://rtmppush.34541.cn/live/1?vhost=httppull.34541.cn\",\"beibei_verify\":1,\"fans_num\":0,\"follow_num\":0,\"haoyou_num\":0,\"person_verify\":1,\"push_video_w\":0,\"im_tip\":\"\",\"msg_isread\":\"0\",\"market\":\"0\",\"tuijianrentip\":\"0\",\"tuijianren\":\"\",\"jf_XNB\":\"1\"}}\n";
        String jsonStr = "hello";
        return jsonStr;
    }

}
