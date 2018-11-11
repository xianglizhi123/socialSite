package com.huawei.socialsitespring.DbController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.socialsitespring.Dao.*;
import com.huawei.socialsitespring.Entity.Card;
import com.huawei.socialsitespring.Entity.Comment;
import com.huawei.socialsitespring.Entity.Likes;
import com.huawei.socialsitespring.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DbController {
    @Autowired
    UserDao userDao;
    @Autowired
    CardDao cardDao;
    @Autowired
    FriendsDao friendsDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    LikesDao likesDao;
    @RequestMapping(value="/getUserDetail",method= RequestMethod.POST)
    public JSONObject getUserDetail(@RequestBody Map<String,Object> param){
        JSONObject jsonObject=new JSONObject();
        String userId=param.get("userId").toString();
        try {
            User user = userDao.userDetail(userId);
            jsonObject.put("Code",200);
            jsonObject.put("school",user.getSchool());
            jsonObject.put("address",user.getAddress());
            jsonObject.put("image",user.getImage());
            jsonObject.put("phone",user.getPhone());
            return jsonObject;
        }catch(Exception e){
            jsonObject.put("Code",400);
            e.printStackTrace();
            return jsonObject;
        }
    }
    @RequestMapping(value="/updateUserDetail",method= RequestMethod.POST)
    public JSONObject updateUserDetail(@RequestBody Map<String,Object> param){
        JSONObject jsonObject=new JSONObject();
        String userId=param.get("userId").toString();
        String school=param.get("school").toString();
        String address=param.get("address").toString();
        String image=param.get("image").toString();
        String phone=param.get("phone").toString();
        User user=new User();
        user.setSchool(school);
        user.setPhone(phone);
        user.setImage(image);
        user.setAddress(address);
        user.setUserId(userId);
        try{
            userDao.updateUser(user);
            jsonObject.put("Code",200);
            jsonObject.put("Msg","Success");
            return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/getFriendsCards",method= RequestMethod.POST)
    public JSONObject getFriendsCards(@RequestBody Map<String,Object> param){
        String userId=param.get("userId").toString();
        List<String> friends=friendsDao.getFriends(userId);
        List<Card> friendsCards;
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("Code",200);
            friendsCards=cardDao.getFriendsCards(friends);
            JSONArray jsonArray=new JSONArray();
            for(int i=0;i!=friendsCards.size();++i){
                JSONObject temp=new JSONObject();
                temp.put("cardId",friendsCards.get(i).getCardId());
                temp.put("userId",friendsCards.get(i).getUserId());
                temp.put("content",friendsCards.get(i).getContent());
                temp.put("time",friendsCards.get(i).getPublishTime());
                temp.put("index",i);
                try{
                    List<Comment> commentList=commentDao.getComments(friendsCards.get(i).getCardId());
                    List<Likes> likesList=likesDao.getLikes(friendsCards.get(i).getCardId());
                    temp.put("commentList",commentList);
                    temp.put("likesList",likesList);
                    jsonArray.add(temp);
                }catch(Exception e){
                    e.printStackTrace();
                    temp.put("commentList","error");
                    jsonArray.add(temp);
                }
            }
            jsonObject.put("Msg",jsonArray);
            return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/signIn",method= RequestMethod.POST)
    public boolean signIn(@RequestBody Map<String, Object> param){
        String userId=param.get("userId").toString();
        String userPwd=param.get("userPwd").toString();
        if(userDao.hasUser(userId)){
            if(userDao.getPwd(userId).equals(userPwd)){
                return true;
            }else
                return false;
        }else
            return false;
    }
    @RequestMapping(value="/signUp",method= RequestMethod.POST)
    public JSONObject signUp(@RequestBody Map<String, Object> param){
        JSONObject jsonObject=new JSONObject();
        String userId;
        String userPwd;
        String phone;
        String address;
        String school;
        try {
            userId = param.get("userId").toString();
            userPwd = param.get("userPwd").toString();
            phone = param.get("phone").toString();
            address = param.get("address").toString();
            school = param.get("school").toString();
        }catch(Exception e) {
                jsonObject.put("Code", 400);
                jsonObject.put("Msg", "some areas missing");
                return jsonObject;
        }
        User user=new User();
        try{
            user.setUserId(userId);
            user.setUserPwd(userPwd);
            user.setAddress(address);
            user.setPhone(phone);
            user.setSchool(school);
            userDao.signUp(user);
            jsonObject.put("Code",200);
            jsonObject.put("Msg","Success");
            return jsonObject;
        }catch(Exception e){
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            e.printStackTrace();
            return jsonObject;
        }
    }
    @RequestMapping(value="/publishInfor", method= RequestMethod.POST)
    public JSONObject publish(@RequestBody Map<String,Object> param){
        JSONObject jsonObject=new JSONObject();
        String userId=param.get("userId").toString();
        String content=param.get("content").toString();
        long publishTime = System.currentTimeMillis();
        System.out.println(userId+"     "+content);
        Card card=new Card();
        card.setContent(content);
        card.setUserId(userId);
        card.setPublishTime(publishTime);
        try{
            cardDao.publishInfor(card);
            jsonObject.put("Code",200);
            jsonObject.put("Msg","Success");
            jsonObject.put("CardId",card.getCardId());
            return jsonObject;
        }catch(Exception e){
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/addFriend", method= RequestMethod.POST)
    public JSONObject addFriend(@RequestBody Map<String,Object> param){
        JSONObject jsonObject=new JSONObject();
        String userId=param.get("userId").toString();
        String friendId=param.get("friendId").toString();
        try {
            friendsDao.addFriend(userId, friendId);
            friendsDao.addFriend(friendId,userId);
            jsonObject.put("Code",200);
            jsonObject.put("Msg","Success");
            return jsonObject;
        }catch(Exception e){
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/getFriends",method= RequestMethod.POST)
    public JSONObject getFriends(@RequestBody Map<String,Object> param){
        System.out.println("getFriends");
        String userId=param.get("userId").toString();
        JSONObject jsonObject=new JSONObject();
        System.out.println(userId);
        List<String> friendList;
        try{
            friendList=friendsDao.getFriends(userId);
            System.out.println(friendList.get(0));
            jsonObject.put("Code",200);
            jsonObject.put("Msg",friendList);
            return jsonObject;
        }catch(Exception e){
            jsonObject.put("Code",400);
            return jsonObject;
        }
    }
    @RequestMapping(value="/selfNews",method= RequestMethod.POST)
    public JSONObject selfNews(@RequestBody Map<String,Object> param){
        String userId=param.get("userId").toString();
        JSONObject jsonObject=new JSONObject();
        List<String> news;
        try{
            news=cardDao.selfNews(userId);
            jsonObject.put("Code",200);
            jsonObject.put("Msg",news);
            return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
            jsonObject.put("Code",400);
            jsonObject.put("Msg","error");
            return jsonObject;
        }
    }
    @RequestMapping(value="/selfCards",method= RequestMethod.POST)
    public JSONObject selfCards(@RequestBody Map<String,Object> param){
        String userId=param.get("userId").toString();
        JSONObject jsonObject=new JSONObject();
        System.out.println(userId);
        List<Card> cards;
        try{
            cards=cardDao.selfCards(userId);
            System.out.println(cards.size());
            jsonObject.put("Code",200);
            JSONArray jsonArray=new JSONArray();
            for(int i=0;i!=cards.size();++i){
                List<Likes> likesList;
                List<Comment> commentList;
                JSONObject jsonTemp=new JSONObject();
                jsonTemp.put("cardId",cards.get(i).getCardId());
                jsonTemp.put("publishTime",cards.get(i).getPublishTime());
                jsonTemp.put("content",cards.get(i).getContent());
                jsonTemp.put("userId",cards.get(i).getUserId());
                jsonTemp.put("index",i);
                commentList=this.commentDao.getComments(cards.get(i).getCardId());
                likesList=this.likesDao.getLikes(cards.get(i).getCardId());
                jsonTemp.put("commentList",commentList);
                jsonTemp.put("likesList",likesList);
                jsonArray.add(jsonTemp);
            }
            jsonObject.put("Msg",jsonArray);
            return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/getLikes",method= RequestMethod.POST)
    public JSONObject getLikes(@RequestBody Map<String,Object> param){
        JSONObject jsonObject=new JSONObject();
        String cardId=param.get("cardId").toString();
        List<Likes> likes;
        try{
            likes=likesDao.getLikes(Integer.parseInt(cardId));
            jsonObject.put("Code",200);
            jsonObject.put("Msg",likes);
            return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/addLikes",method= RequestMethod.POST)
    public JSONObject addLikes(@RequestBody Map<String,Object> param){
        JSONObject jsonObject=new JSONObject();
        String cardId=param.get("cardId").toString();
        String userId=param.get("userId").toString();
        Likes likes=new Likes();
        likes.setCardId(Integer.parseInt(cardId));
        likes.setUserId(userId);
        try{
            int index=likesDao.addLikes(likes);
            jsonObject.put("Code",200);
            jsonObject.put("Msg",index);
            return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/addComments",method= RequestMethod.POST)
    public JSONObject addComments(@RequestBody Map<String,Object> param){
        Comment comment=new Comment();
        JSONObject jsonObject=new JSONObject();
        String cardId=param.get("cardId").toString();
        String userId=param.get("userId").toString();
        String content=param.get("content").toString();
        long time=System.currentTimeMillis();
        comment.setCardId(Integer.parseInt(cardId));
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setTime(time);
        System.out.println(comment.toString());
        try{
           commentDao.addComments(comment);
           jsonObject.put("Code",200);
           jsonObject.put("Msg","Success");
           return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
          jsonObject.put("Code",400);
          jsonObject.put("Msg","fail");
          return jsonObject;
        }
    }
    @RequestMapping(value="/getComments",method= RequestMethod.POST)
    public JSONObject getComments(@RequestBody Map<String,Object> param){
        JSONObject jsonObject=new JSONObject();
        String cardId=param.get("cardId").toString();
        List<Comment> commentList;
        try{
            commentList=commentDao.getComments(Integer.parseInt(cardId));
            jsonObject.put("Code",200);
            JSONArray jsonArray=new JSONArray();
            for(int i=0;i!=commentList.size();++i){
                JSONObject temp=new JSONObject();
                temp.put("cardId",commentList.get(i).getCardId());
                temp.put("userId",commentList.get(i).getUserId());
                temp.put("content",commentList.get(i).getContent());
                temp.put("time",commentList.get(i).getTime());
                jsonArray.add(temp);
            }
            System.out.println(jsonArray.size());
            jsonObject.put("Msg",jsonArray);
            return jsonObject;
        }catch(Exception e){
            e.printStackTrace();
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
    @RequestMapping(value="/searchFriend",method= RequestMethod.POST)
    public JSONObject searchFriend(@RequestBody Map<String,Object>param ){
        JSONObject jsonObject=new JSONObject();
        String userId=param.get("userId").toString();
        try{
            if(userDao.hasUser(userId)) {
                jsonObject.put("Code",200);
                jsonObject.put("Msg","found");
                return jsonObject;
            }else{
                jsonObject.put("Code",200);
                jsonObject.put("Msg","Not found");
                return jsonObject;
            }
        }catch(Exception e){
            jsonObject.put("Code",400);
            jsonObject.put("Msg","Fail");
            return jsonObject;
        }
    }
}
